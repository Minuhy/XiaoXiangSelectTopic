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
 * ���ݿ�������
 * 
 * @author y17mm
 * @time 2023-02-14 11:10
 * @version 1.1
 */
public class Connector {
	private static final Logger log = LoggerFactory.getLogger(Connector.class);
	private static DataSource cp;

	static {
		// 1. ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2. ��������
		Properties properties = new Properties();
		String propertyFile = Connector.class.getResource("/druid.properties").getPath();
		try {
			propertyFile = URLDecoder.decode(propertyFile, "utf-8");
			properties.load(new FileInputStream(propertyFile));
		} catch (Exception e) {
			log.error("���Լ������ݿ������ļ�ʱ����/druid.properties {}", e);
		}

		String url = properties.getProperty("url");
		if (CommonConfig.isDebug()) {
			log.debug("���ݿ����ã�{}��{}��{}", url, properties.getProperty("username"), properties.getProperty("password"));
		}

		// 3. �������ӳ�
		try {
			cp = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			log.error("���Դ������ݿ����ӳ�ʱ����{}", e);
		}

		log.info("���ݿ����ӳɹ���{}", url);
	}

	public static Connection getConnection() throws SQLException {
		Connection conn;
		if (cp != null) {
			conn = cp.getConnection();
		} else {
			throw new NullPointerException("���ӳ�Ϊ��");
		}
		return conn;
	}

	public static void disconnect() {
		try {
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				if (driver instanceof com.mysql.jdbc.Driver) {
					log.info("���ݿⷴע��MySQL������{}", driver);
					DriverManager.deregisterDriver(driver);
				}
			}
			AbandonedConnectionCleanupThread.uncheckedShutdown();
		} catch (Exception e) {

			log.error("��ע�����ݿ������쳣��{}", e);
		}
	}
}
