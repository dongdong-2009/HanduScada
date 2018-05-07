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

        dataSource.setDefaultAutoCommit(Boolean.parseBoolean(ps.getProperty("autoCommit", "false")));

        dataSource.setInitialSize(Integer.parseInt(ps.getProperty("initialSize", "5")));
        dataSource.setMaxActive(Integer.parseInt(ps.getProperty("maxActive", "8")));
        dataSource.setMaxIdle(Integer.parseInt(ps.getProperty("maxIdle", "8")));
        dataSource.setMinIdle(Integer.parseInt(ps.getProperty("minIdle", "5")));
        dataSource.setMaxWait(Long.parseLong(ps.getProperty("maxWait", "10000")));

        dataSource.setRemoveAbandoned(Boolean.parseBoolean(ps.getProperty("removeAbandoned", "true")));
        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(ps.getProperty("removeAbandonedTimeout", "60")));

        dataSource.setTestWhileIdle(Boolean.parseBoolean(ps.getProperty("testWhileIdle", "true")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(ps.getProperty("testOnBorrow", "false")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(ps.getProperty("testOnReturn", "false")));

        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(ps.getProperty("timeBetweenEvictionRunsMillis", "30000")));
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(ps.getProperty("minEvictableIdleTimeMillis", "1800000")));
        dataSource.setNumTestsPerEvictionRun(Integer.parseInt(ps.getProperty("numTestsPerEvictionRun", "10")));

        dataSource.setValidationQuery(ps.getProperty("validationQuery", " SELECT 1 "));
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
