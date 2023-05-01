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
 * 将Servlet过滤器转换成Http过滤器，并做一些优化
 * 
 * @author y17mm
 * @time 2023-4-29 21:13:58
 * @version 1.0
 */
public class HttpFilter implements Filter {

	/**
	 * 当前请求的URI
	 */
	protected String uri;
	/**
	 * 当前应用的路径
	 */
	protected String currentPath;
	/**
	 * 会话对象
	 */
	protected HttpSession session;
	/**
	 * 请求对象
	 */
	protected HttpServletRequest request;
	/**
	 * 响应对象
	 */
	protected HttpServletResponse response;

	/**
	 * 请求发来的时候对其进行操作
	 * 
	 * @return true：放行（默认）
	 * @throws UnsupportedEncodingException Other
	 * @throws IOException
	 * @throws ServletException
	 */
	protected boolean httpIncoming() throws UnsupportedEncodingException, ServletException, IOException {
		return true;
	}

	/**
	 * 返回响应的时候对其进行操作
	 */
	protected void httpOutgoing() throws UnsupportedEncodingException, ServletException, IOException {
	}

	@Override
	public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
			// 如果是HTTP请求，开始处理业务

			request = (HttpServletRequest) servletRequest;
			response = (HttpServletResponse) servletResponse;
			uri = request.getRequestURI();
			currentPath = request.getContextPath();

			if (httpIncoming()) {
				filterChain.doFilter(servletRequest, servletResponse);
			}

			httpOutgoing();

		} else {
			// 非HTTP请求，直接放行

			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
