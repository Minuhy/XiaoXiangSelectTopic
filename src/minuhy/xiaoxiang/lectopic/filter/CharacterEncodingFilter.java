package minuhy.xiaoxiang.lectopic.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;

/**
 * 字符编码过滤器，统一应用编码
 * 
 * @author y17mm
 * @time 2023-4-29 18:37:37
 * @version 1.0
 */
public class CharacterEncodingFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(CharacterEncodingFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding(CommonConfig.getCharacterEncoding());
		request.setCharacterEncoding(CommonConfig.getCharacterEncoding());
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		log.info("字符编码过滤器初始化：{}",CommonConfig.getCharacterEncoding());
	}

	public void destroy() {
		
	}

}
