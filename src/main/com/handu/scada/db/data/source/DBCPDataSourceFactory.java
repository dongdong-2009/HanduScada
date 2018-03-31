package main.com.handu.scada.db.data.source;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by 柳梦 on 2018/02/09.
 * dbcp类型的数据库连接池
 */
public class DBCPDataSourceFactory implements DataSourceFactory {

    private BasicDataSource dataSource;

    public DBCPDataSourceFactory() {
        this.dataSource = new BasicDataSource();
    }

    @Override
    public void setProperties(Properties ps) {
        dataSource.setDriverClassName(ps.getProperty("driver"));
        dataSource.setUsername(ps.getProperty("username"));
        dataSource.setUrl(ps.getProperty("url"));
        dataSource.setPassword(ps.getProperty("password"));
        dataSource.setDefaultAutoCommit(ps.getProperty("autoCommit", "0").equals("1"));
        dataSource.setInitialSize(Integer.parseInt(ps.getProperty("initialSize", "2")));
        dataSource.setMaxActive(Integer.parseInt(ps.getProperty("poolMaximumActiveConnections", "30")));
        dataSource.setMaxIdle(Integer.parseInt(ps.getProperty("poolMaximumIdleConnections", "0")));
        dataSource.setMaxWait(Long.parseLong(ps.getProperty("maxWait", "0")));
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
