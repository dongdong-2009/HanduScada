package main.com.handu.scada.event;

import java.lang.annotation.*;

/**
 * Created by 柳梦 on 2017/12/22.
 * 标识订阅者
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscriber {
}
