package minuhy.xiaoxiang.lectopic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 读取config.properties的配置存到变量中
 * 
 * @author y17mm
 * @time 2023-4-29 20:53:18
 * @version 1.0
 */
public class CommonConfig {
	private static final Logger log = LoggerFactory.getLogger(CommonConfig.class);
	
	// 调试模式默认关闭
	private static boolean isDebug;

	// 字符编码默认为 UTF-8
	private static String characterEncoding="UTF-8";
	
	// 管理员账号默认为 admin
	private static String adminUsername="admin";
	
	static {
		try {
			Properties properties = new Properties();
			String propertyFile = CommonConfig.class.getResource("/config.properties").getPath();
			propertyFile = URLDecoder.decode(propertyFile,"utf-8");
			properties.load(new FileInputStream(propertyFile));
			
			String str = null;
		
			// 调试模式
			if("true".equals(properties.getProperty("isDebug"))) {
				isDebug = true;
			}
			log.info("调试模式：{}",isDebug);
			
			// 字符编码
			str = properties.getProperty("characterEncoding","");
			if(!"".equals(str)) {
				characterEncoding = str;
			}
			
			// 管理员账号
			str = properties.getProperty("adminUsername","");
			if(!"".equals(str)) {
				adminUsername = str;
			}

		} catch (IOException e) {
			log.error("读取配置文件config.properties出错，所有配置为默认值：{}", e);
		}
	}

	/**
	 * 当前是否是调试模式
	 * @return true 或者 false
	 * @time 2023-4-29 20:57:08
	 */
	public static boolean isDebug() {
		return isDebug;
	}

	/**
	 * 获取配置中的字符编码
	 * @return 字符编码名称
	 * @time 2023-4-29 21:09:30
	 */
	public static String getCharacterEncoding() {
		return characterEncoding;
	}

	/**
	 * 获取配置中的管理员账号
	 * @return 管理员账号
	 * @time 2023-4-30 1:23:07
	 */
	public static String getAdminUsername() {
		return adminUsername;
	}
	
	
}
