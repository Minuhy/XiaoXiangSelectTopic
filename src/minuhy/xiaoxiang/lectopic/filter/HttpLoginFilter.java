package minuhy.xiaoxiang.lectopic.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.filter.common.HttpFilter;

/**
 * 登录过滤器 
 * 登录了不能访问登录页面（跳转到首页） 
 * 没登录不能访问业务页面（跳转到登录页面，并且记录当前访问页面到session中）
 * 
 * @author y17mm
 * @time 2023-4-29 21:11:50
 * @version 1.0
 */
@WebFilter("/*")
public class HttpLoginFilter extends HttpFilter {
	private static final Logger log = LoggerFactory.getLogger(HttpLoginFilter.class);

	@Override
	protected boolean doHttpFilterFront(HttpServletRequest req, HttpServletResponse res)
			throws UnsupportedEncodingException, ServletException, IOException {
		String uri = req.getRequestURI();

		// 以login.jsp或者/user/login结尾的都是登录页面
		boolean isLoginPage = uri.endsWith("login.jsp") || uri.endsWith("/user/login");

		// 不是以.jsp结尾的或者uri中不含有.的都是资源
		boolean isAsset = !uri.endsWith(".jsp") || !uri.contains(".");

		HttpSession session = getSession(req);
		if (session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME) instanceof UserBean) {
			// 已经登录
			if (isLoginPage) {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		} else {
			// 没有登录，并且不是登录页面（是业务页面）,更不是资源
			if (!isLoginPage && !isAsset) {
				// 记录URL
				String url = req.getRequestURL().toString();
				if (session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE) == null) {
					session.setAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE, url);
					if (CommonConfig.isDebug()) {
						log.debug("没有登录", url);
					}
				}
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
		}

		return true;
	}

}
