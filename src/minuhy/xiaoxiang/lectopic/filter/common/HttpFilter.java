package minuhy.xiaoxiang.lectopic.filter.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ��Servlet������ת����Http������������һЩ�Ż�
 * 
 * @author y17mm
 * @time 2023-4-29 21:13:58
 * @version 1.0
 */
public class HttpFilter implements Filter {
	public void destroy() {

	}

	/**
	 * ��������ʱ�������в���
	 * 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @return true������
	 * @throws UnsupportedEncodingException Other
	 * @throws IOException
	 * @throws ServletException
	 */
	protected boolean doHttpFilterFront(HttpServletRequest req, HttpServletResponse res)
			throws UnsupportedEncodingException, ServletException, IOException {
		return true;
	}

	/**
	 * ������Ӧ��ʱ�������в���
	 * 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 */
	protected void doHttpFilterAfter(HttpServletRequest req, HttpServletResponse res) {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			// �����HTTP���󣬿�ʼ����ҵ��
			if (doHttpFilterFront((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse)) {
				filterChain.doFilter(servletRequest, servletResponse);
				doHttpFilterAfter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
			}
		} else {
			// ��HTTP����ֱ�ӷ���
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public HttpSession getSession(HttpServletRequest req) {
		return req.getSession();
	}
}
