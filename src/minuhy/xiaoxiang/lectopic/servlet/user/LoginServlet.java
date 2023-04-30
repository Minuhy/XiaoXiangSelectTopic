package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.servlet.BaseServlet;
import minuhy.xiaoxiang.lectopic.util.TextUtil;

/**
 * �����¼����
 * 
 * @author y17mm
 * @time 2023-4-29 20:40:29
 * @version 1.0
 */
@WebServlet("/user/login")
public class LoginServlet extends BaseServlet {
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 2474643203909771018L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. �õ�����
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(CommonConfig.isDebug()) {
			log.debug("��¼��{}��{}",username,password);
		}
		
		// 2. У�����
		
		if(!TextUtil.stringBetweenLen(username,6,20)) {
			forwardTipsPage(MessageState.FAIL,"�˺Ÿ�ʽ����ȷ��6-20���ַ���","/login.jsp");
			return;
		}
		
		if(!TextUtil.stringBetweenLen(password,6,20)) {
			forwardTipsPage(MessageState.FAIL,"�����ʽ����ȷ��6-20���ַ���","/login.jsp");
			return;
		}
		
		// 3. ����ҵ���߼�
		
		if(CommonConfig.getAdminUsername().equals(username)) {
			// ����ǹ���Ա�˺�
			if(password.equals(
					request.getServletContext().getAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD))) {
				log.info("����Ա��¼��" + username);
				
			}
		}
	}

}
