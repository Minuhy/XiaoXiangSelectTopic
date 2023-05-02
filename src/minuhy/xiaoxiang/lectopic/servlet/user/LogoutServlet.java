package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseUserServlet;
/**
 * 退出登录
 * 
 * @author y17mm
 * @time 2023-5-1 17:44:03
 * @version 1.0
 */
@WebServlet("/user/logout")
public class LogoutServlet extends BaseUserServlet {
	private static final long serialVersionUID = 7971537569019139666L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.removeAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
		session.invalidate();
		
		forwardSuccessTipsPage("退出登录成功", UriConfig.LOGIN);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
