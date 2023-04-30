package minuhy.xiaoxiang.lectopic.database.common;

import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;

/**
 * 数据库连接类
 * 
 * @author y17mm
 * @time 2023-02-14 11:10
 * @version 1.1
 */
public class Connector {
	private static final Logger log = LoggerFactory.getLogger(Connector.class);
	private static DataSource cp;

	static {
		// 1. 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2. 加载配置
		Properties properties = new Properties();
		String propertyFile = Connector.class.getResource("/druid.properties").getPath();
		try {
			propertyFile = URLDecoder.decode(propertyFile, "utf-8");
			properties.load(new FileInputStream(propertyFile));
		} catch (Exception e) {
			log.error("尝试加载数据库配置文件时出错：/druid.properties {}", e);
		}

		String url = properties.getProperty("url");
		if (CommonConfig.isDebug()) {
			log.debug("数据库配置：{}，{}，{}", url, properties.getProperty("username"), properties.getProperty("password"));
		}

		// 3. 创建连接池
		try {
			cp = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			log.error("尝试创建数据库连接池时出错：{}", e);
		}

		log.info("数据库连接成功：{}", url);
	}

	public static Connection getConnection() throws SQLException {
		Connection conn;
		if (cp != null) {
			conn = cp.getConnection();
		} else {
			throw new NullPointerException("连接池为空");
		}
		return conn;
	}

	public static void disconnect() {
		try {
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				if (driver instanceof com.mysql.jdbc.Driver) {
					log.info("数据库反注册MySQL驱动：{}", driver);
					DriverManager.deregisterDriver(driver);
				}
			}
			AbandonedConnectionCleanupThread.uncheckedShutdown();
		} catch (Exception e) {

			log.error("反注册数据库驱动异常：{}", e);
		}
	}
}
