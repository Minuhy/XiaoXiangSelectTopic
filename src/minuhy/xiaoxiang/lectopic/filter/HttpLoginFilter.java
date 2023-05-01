package minuhy.xiaoxiang.lectopic.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.filter.common.HttpFilter;

/**
 * 登录过滤器 
 * 
 * 登录页面： /login.jsp、/user/login 
 * 
 * 管理员注册页面
 * /jsp/admin/register.jsp、/admin/register 
 * 
 * 非资源页面： /index.jsp、/jsp/*
 * 
 * 状态为三种：已登录、未登录和管理员注册 已登录： 不能访问登录页面、管理员注册页面，默认到/index.jsp 未登录：
 * 不能访问非资源页面、管理员注册页面，默认到/login.jsp 管理员注册：
 * 不能访问登录页面、非资源页面，默认到/jsp/admin/register.jsp
 * 
 * 登录了不能访问登录页面（跳转到首页） 没登录不能访问业务页面（跳转到登录页面，并且记录当前访问页面到session中）
 * 
 * @author y17mm
 * @time 2023-4-29 21:11:50
 * @version 1.0
 */
public class HttpLoginFilter extends HttpFilter {
	private static final Logger log = LoggerFactory.getLogger(HttpLoginFilter.class);

	/**
	 * 页面
	 * 
	 * @author y17mm
	 * @time 2023-4-30 15:15:51
	 * @version 1.0
	 */
	enum Page {
		LOGIN, // 登录页面
		ADMIN, // 管理员注册页面
		NOMAL, // 正常的需要登录的页面
		STATIC // 静态资源，验证码
	}

	/**
	 * 状态
	 * 
	 * @author y17mm
	 * @time 2023-4-30 15:15:58
	 * @version 1.0
	 */
	enum State {
		LOGIN, // 已登录
		NOT_LOGIN, // 未登录
		ADMIN // 管理员注册
	}

	static String staticPath = "/common/";

	static String captchaServletPath = "/util/captcha";
	static String loginServletPath = "/user/login";
	static String loginJspPath = "/login.jsp";
	static String adminServletPath = "/admin/register";
	static String adminJspPath = "/jsp/admin/register.jsp";
	static String indexJspPath = "/index.jsp";

	@Override
	protected boolean httpIncoming()
			throws UnsupportedEncodingException, ServletException, IOException {
		String uri = request.getRequestURI();
		String currentPath = request.getContextPath();

		Page page = Page.STATIC;
		if (!uri.startsWith(currentPath + staticPath) && !uri.endsWith(captchaServletPath)) {
			// 非资源页面
			if (uri.endsWith(loginJspPath) || uri.endsWith(loginServletPath)) {
				// 登录页面
				page = Page.LOGIN;
			} else if (uri.endsWith(adminJspPath) || uri.endsWith(adminServletPath)) {
				// 管理员注册页面
				page = Page.ADMIN;
			} else {
				// 正常的，需要登录的页面
				page = Page.NOMAL;
			}
		}

		State state = State.NOT_LOGIN;

		Object obj1 = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);
		Object obj2 = session.getAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);

		if (obj1 instanceof UserBean && ((UserBean) obj1).getId() > 0) {
			state = State.LOGIN;
		} else if (obj2 instanceof Boolean && ((Boolean) obj2)) {
			state = State.ADMIN;
		}

		if (CommonConfig.isDebug()) {
			log.debug("登录过滤器：{}，{}，{}", uri, page, state);
		}

		if (state == State.ADMIN) {
			// 管理员注册状态
			if (page == Page.LOGIN || page == Page.NOMAL) {
				response.sendRedirect(currentPath + adminJspPath);
				return false;
			}
		} else if (state == State.LOGIN) {
			// 已登录状态
			if (page == Page.LOGIN || page == Page.ADMIN) {
				response.sendRedirect(currentPath + indexJspPath);
				return false;
			}
		} else if (state == State.NOT_LOGIN) {
			// 未登录状态
			if (page == Page.ADMIN) {
				response.sendRedirect(currentPath + loginJspPath);
				return false;
			} else if (page == Page.NOMAL) {
				recordUrl(session, request.getRequestURL().toString());
				response.sendRedirect(currentPath + loginJspPath);
				return false;
			}
		}

		return true;
	}

	void recordUrl(HttpSession session, String url) {
		// 记录URL
		if (session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE) == null) {
			session.setAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE, url);
			if (CommonConfig.isDebug()) {
				log.debug("记录URL：{}", url);
			}
		}
	}

}
