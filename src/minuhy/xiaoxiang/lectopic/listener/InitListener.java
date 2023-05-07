package minuhy.xiaoxiang.lectopic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.util.TextUtil;

/**
 * Ӧ�ó�ʼ������
 * 
 * @author y17mm
 * @time 2023-4-29 20:43:48
 * @version 1.0
 */
@WebListener
public class InitListener implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(InitListener.class);

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		// ����ԱĬ������
		String pwd = TextUtil.getRandomString(6);
		event.getServletContext().setAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD, pwd);
		log.info("����ԱĬ�����룺{}", pwd);
		// Ӧ��·��
		String ContextPath = event.getServletContext().getContextPath();
		event.getServletContext().setAttribute(ConstantsConfig.APPLICATION_CONTENT_PATH, ContextPath);
		log.info("Ӧ��·����{}", ContextPath);
	}
}
