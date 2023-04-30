package minuhy.xiaoxiang.lectopic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ��ȡconfig.properties�����ô浽������
 * 
 * @author y17mm
 * @time 2023-4-29 20:53:18
 * @version 1.0
 */
public class CommonConfig {
	private static final Logger log = LoggerFactory.getLogger(CommonConfig.class);
	
	// ����ģʽĬ�Ϲر�
	private static boolean isDebug;

	// �ַ�����Ĭ��Ϊ UTF-8
	private static String characterEncoding="UTF-8";
	
	// ����Ա�˺�Ĭ��Ϊ admin
	private static String adminUsername="admin";
	
	static {
		try {
			Properties properties = new Properties();
			String propertyFile = CommonConfig.class.getResource("/config.properties").getPath();
			propertyFile = URLDecoder.decode(propertyFile,"utf-8");
			properties.load(new FileInputStream(propertyFile));
			
			String str = null;
		
			// ����ģʽ
			if("true".equals(properties.getProperty("isDebug"))) {
				isDebug = true;
			}
			log.info("����ģʽ��{}",isDebug);
			
			// �ַ�����
			str = properties.getProperty("characterEncoding","");
			if(!"".equals(str)) {
				characterEncoding = str;
			}
			
			// ����Ա�˺�
			str = properties.getProperty("adminUsername","");
			if(!"".equals(str)) {
				adminUsername = str;
			}

		} catch (IOException e) {
			log.error("��ȡ�����ļ�config.properties������������ΪĬ��ֵ��{}", e);
		}
	}

	/**
	 * ��ǰ�Ƿ��ǵ���ģʽ
	 * @return true ���� false
	 * @time 2023-4-29 20:57:08
	 */
	public static boolean isDebug() {
		return isDebug;
	}

	/**
	 * ��ȡ�����е��ַ�����
	 * @return �ַ���������
	 * @time 2023-4-29 21:09:30
	 */
	public static String getCharacterEncoding() {
		return characterEncoding;
	}

	/**
	 * ��ȡ�����еĹ���Ա�˺�
	 * @return ����Ա�˺�
	 * @time 2023-4-30 1:23:07
	 */
	public static String getAdminUsername() {
		return adminUsername;
	}
	
	
}
