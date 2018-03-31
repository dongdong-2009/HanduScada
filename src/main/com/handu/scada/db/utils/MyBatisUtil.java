package main.com.handu.scada.db.utils;

import main.com.handu.scada.exception.ExceptionHandler;
import main.com.handu.scada.utils.AesUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by 柳梦 on 2017/12/20.
 * 获取mybatis数据操作相关
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 禁止外部new来调用
     */
    private MyBatisUtil() {

    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession(boolean autoCommit) {
        //返回SqlSession对象
        return getSqlSessionFactory().openSession(autoCommit);
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSession(true);
    }

    /**
     * 获取SqlSessionFactory
     *
     * @return sqlSessionFactory
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        try {
            if (sqlSessionFactory == null) {
                //使用MyBatis提供的Resources类加载mybatis的配置文件
                Reader reader = Resources.getResourceAsReader("mybatis.cfg.xml");
                Properties p = Resources.getResourceAsProperties("db.properties");
                String url = AesUtils.decrypt(p.getProperty("jdbc.url"));
                String username = AesUtils.decrypt(p.getProperty("jdbc.username"));
                String password = AesUtils.decrypt(p.getProperty("jdbc.password"));
                String driver = AesUtils.decrypt(p.getProperty("jdbc.driver"));
                p.setProperty("jdbc.driver", driver);
                p.setProperty("jdbc.url", url);
                p.setProperty("jdbc.username", username);
                p.setProperty("jdbc.password", password);
                //构建sqlSession的工厂
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, p);
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
        return sqlSessionFactory;
    }
}
