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
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseUserServlet;
/**
 * ÍË³öµÇÂ¼
 * 
 * @author y17mm
 * @time 2023-5-1 17:44:03
 * @version 1.0
 */
@WebServlet("/user/logout")
public class LogoutServlet extends BaseUserServlet {
	private static final long serialVersionUID = 7971537569019139666L;
	private static final Logger log = LoggerFactory.getLogger(LogoutServlet.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
		session.invalidate();
		
		if(CommonConfig.isDebug()) {
			log.debug("ÍË³öµÇÂ¼£º{}£¬{}",userBean.getId(),userBean.getAccount());
		}
		
		forwardSuccessTipsPage("ÍË³öµÇÂ¼³É¹¦", UriConfig.LOGIN);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
