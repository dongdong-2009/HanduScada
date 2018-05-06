package main.com.handu.scada.netty.server.dtu;

import io.netty.channel.*;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by 柳梦 on 2018/05/04.
 */
public class DtuWriteTimeoutHandler extends ChannelOutboundHandlerAdapter {

    private static final long MIN_TIMEOUT_NANOS;
    private final long timeoutNanos;
    private DtuWriteTimeoutHandler.WriteTimeoutTask lastTask;

    public DtuWriteTimeoutHandler(int timeoutSeconds) {
        this((long) timeoutSeconds, TimeUnit.SECONDS);
    }

    public DtuWriteTimeoutHandler(long timeout, TimeUnit unit) {
        if (unit == null) {
            throw new NullPointerException("unit");
        } else {
            if (timeout <= 0L) {
                this.timeoutNanos = 0L;
            } else {
                this.timeoutNanos = Math.max(unit.toNanos(timeout), MIN_TIMEOUT_NANOS);
            }

        }
    }

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (this.timeoutNanos > 0L) {
            promise = promise.unvoid();
            this.scheduleTimeout(ctx, promise);
        }
        ctx.write(msg, promise);
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        DtuWriteTimeoutHandler.WriteTimeoutTask task = this.lastTask;
        DtuWriteTimeoutHandler.WriteTimeoutTask prev;
        for (this.lastTask = null; task != null; task = prev) {
            task.scheduledFuture.cancel(false);
            prev = task.prev;
            task.prev = null;
            task.next = null;
        }

    }

    private void scheduleTimeout(ChannelHandlerContext ctx, ChannelPromise promise) {
        DtuWriteTimeoutHandler.WriteTimeoutTask task = new DtuWriteTimeoutHandler.WriteTimeoutTask(ctx, promise);
        task.scheduledFuture = ctx.executor().schedule(task, this.timeoutNanos, TimeUnit.NANOSECONDS);
        if (!task.scheduledFuture.isDone()) {
            this.addWriteTimeoutTask(task);
            promise.addListener(task);
        }

    }

    private void addWriteTimeoutTask(DtuWriteTimeoutHandler.WriteTimeoutTask task) {
        if (this.lastTask == null) {
            this.lastTask = task;
        } else {
            this.lastTask.next = task;
            task.prev = this.lastTask;
            this.lastTask = task;
        }

    }

    private void removeWriteTimeoutTask(DtuWriteTimeoutHandler.WriteTimeoutTask task) {
        if (task == this.lastTask) {
            assert task.next == null;

            this.lastTask = this.lastTask.prev;
            if (this.lastTask != null) {
                this.lastTask.next = null;
            }
        } else {
            if (task.prev == null && task.next == null) {
                return;
            }

            if (task.prev == null) {
                task.next.prev = null;
            } else {
                task.prev.next = task.next;
                task.next.prev = task.prev;
            }
        }

        task.prev = null;
        task.next = null;
    }

    protected void writeTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (ctx != null) {
            String clientId = ctx.channel().id().asShortText();
            DtuNetworkConnection connection = DtuChannelManager.getNetworkState(clientId);
            if (connection != null) {
                connection.writeTimeout();
            }
        }
    }

    static {
        MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1L);
    }

    private final class WriteTimeoutTask implements Runnable, ChannelFutureListener {
        private final ChannelHandlerContext ctx;
        private final ChannelPromise promise;
        DtuWriteTimeoutHandler.WriteTimeoutTask prev;
        DtuWriteTimeoutHandler.WriteTimeoutTask next;
        ScheduledFuture<?> scheduledFuture;

        WriteTimeoutTask(ChannelHandlerContext ctx, ChannelPromise promise) {
            this.ctx = ctx;
            this.promise = promise;
        }

        public void run() {
            if (!this.promise.isDone()) {
                try {
                    DtuWriteTimeoutHandler.this.writeTimedOut(this.ctx);
                } catch (Throwable var2) {
                    this.ctx.fireExceptionCaught(var2);
                }
            }

            DtuWriteTimeoutHandler.this.removeWriteTimeoutTask(this);
        }

        public void operationComplete(ChannelFuture future) throws Exception {
            this.scheduledFuture.cancel(false);
            DtuWriteTimeoutHandler.this.removeWriteTimeoutTask(this);
        }
    }
}
