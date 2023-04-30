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
 * ��¼������ 
 * ��¼�˲��ܷ��ʵ�¼ҳ�棨��ת����ҳ�� 
 * û��¼���ܷ���ҵ��ҳ�棨��ת����¼ҳ�棬���Ҽ�¼��ǰ����ҳ�浽session�У�
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

		// ��login.jsp����/user/login��β�Ķ��ǵ�¼ҳ��
		boolean isLoginPage = uri.endsWith("login.jsp") || uri.endsWith("/user/login");

		// ������.jsp��β�Ļ���uri�в�����.�Ķ�����Դ
		boolean isAsset = !uri.endsWith(".jsp") || !uri.contains(".");

		HttpSession session = getSession(req);
		if (session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME) instanceof UserBean) {
			// �Ѿ���¼
			if (isLoginPage) {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		} else {
			// û�е�¼�����Ҳ��ǵ�¼ҳ�棨��ҵ��ҳ�棩,��������Դ
			if (!isLoginPage && !isAsset) {
				// ��¼URL
				String url = req.getRequestURL().toString();
				if (session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE) == null) {
					session.setAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE, url);
					if (CommonConfig.isDebug()) {
						log.debug("û�е�¼", url);
					}
				}
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
		}

		return true;
	}

}
