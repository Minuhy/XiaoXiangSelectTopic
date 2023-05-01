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
 * ��¼������ 
 * 
 * ��¼ҳ�棺 /login.jsp��/user/login 
 * 
 * ����Աע��ҳ��
 * /jsp/admin/register.jsp��/admin/register 
 * 
 * ����Դҳ�棺 /index.jsp��/jsp/*
 * 
 * ״̬Ϊ���֣��ѵ�¼��δ��¼�͹���Աע�� �ѵ�¼�� ���ܷ��ʵ�¼ҳ�桢����Աע��ҳ�棬Ĭ�ϵ�/index.jsp δ��¼��
 * ���ܷ��ʷ���Դҳ�桢����Աע��ҳ�棬Ĭ�ϵ�/login.jsp ����Աע�᣺
 * ���ܷ��ʵ�¼ҳ�桢����Դҳ�棬Ĭ�ϵ�/jsp/admin/register.jsp
 * 
 * ��¼�˲��ܷ��ʵ�¼ҳ�棨��ת����ҳ�� û��¼���ܷ���ҵ��ҳ�棨��ת����¼ҳ�棬���Ҽ�¼��ǰ����ҳ�浽session�У�
 * 
 * @author y17mm
 * @time 2023-4-29 21:11:50
 * @version 1.0
 */
public class HttpLoginFilter extends HttpFilter {
	private static final Logger log = LoggerFactory.getLogger(HttpLoginFilter.class);

	/**
	 * ҳ��
	 * 
	 * @author y17mm
	 * @time 2023-4-30 15:15:51
	 * @version 1.0
	 */
	enum Page {
		LOGIN, // ��¼ҳ��
		ADMIN, // ����Աע��ҳ��
		NOMAL, // ��������Ҫ��¼��ҳ��
		STATIC // ��̬��Դ����֤��
	}

	/**
	 * ״̬
	 * 
	 * @author y17mm
	 * @time 2023-4-30 15:15:58
	 * @version 1.0
	 */
	enum State {
		LOGIN, // �ѵ�¼
		NOT_LOGIN, // δ��¼
		ADMIN // ����Աע��
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
			// ����Դҳ��
			if (uri.endsWith(loginJspPath) || uri.endsWith(loginServletPath)) {
				// ��¼ҳ��
				page = Page.LOGIN;
			} else if (uri.endsWith(adminJspPath) || uri.endsWith(adminServletPath)) {
				// ����Աע��ҳ��
				page = Page.ADMIN;
			} else {
				// �����ģ���Ҫ��¼��ҳ��
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
			log.debug("��¼��������{}��{}��{}", uri, page, state);
		}

		if (state == State.ADMIN) {
			// ����Աע��״̬
			if (page == Page.LOGIN || page == Page.NOMAL) {
				response.sendRedirect(currentPath + adminJspPath);
				return false;
			}
		} else if (state == State.LOGIN) {
			// �ѵ�¼״̬
			if (page == Page.LOGIN || page == Page.ADMIN) {
				response.sendRedirect(currentPath + indexJspPath);
				return false;
			}
		} else if (state == State.NOT_LOGIN) {
			// δ��¼״̬
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
		// ��¼URL
		if (session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE) == null) {
			session.setAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE, url);
			if (CommonConfig.isDebug()) {
				log.debug("��¼URL��{}", url);
			}
		}
	}

}
