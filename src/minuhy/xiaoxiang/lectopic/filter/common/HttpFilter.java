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

	/**
	 * ��ǰ�����URI
	 */
	protected String uri;
	/**
	 * ��ǰӦ�õ�·��
	 */
	protected String currentPath;
	/**
	 * �Ự����
	 */
	protected HttpSession session;
	/**
	 * �������
	 */
	protected HttpServletRequest request;
	/**
	 * ��Ӧ����
	 */
	protected HttpServletResponse response;

	/**
	 * ��������ʱ�������в���
	 * 
	 * @return true�����У�Ĭ�ϣ�
	 * @throws UnsupportedEncodingException Other
	 * @throws IOException
	 * @throws ServletException
	 */
	protected boolean httpIncoming() throws UnsupportedEncodingException, ServletException, IOException {
		return true;
	}

	/**
	 * ������Ӧ��ʱ�������в���
	 */
	protected void httpOutgoing() throws UnsupportedEncodingException, ServletException, IOException {
	}

	@Override
	public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			// �����HTTP���󣬿�ʼ����ҵ��

			request = (HttpServletRequest) servletRequest;
			response = (HttpServletResponse) servletResponse;
			uri = request.getRequestURI();
			currentPath = request.getContextPath();

			if (httpIncoming()) {
				filterChain.doFilter(servletRequest, servletResponse);
			}

			httpOutgoing();

		} else {
			// ��HTTP����ֱ�ӷ���

			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
