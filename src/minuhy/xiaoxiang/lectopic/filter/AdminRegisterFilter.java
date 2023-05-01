package minuhy.xiaoxiang.lectopic.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.filter.common.HttpFilter;

/**
 * 如果检测到是管理员注册状态，直接跳转到管理员注册界面
 * 
 * @author y17mm
 * @time 2023-5-1 17:16:00
 * @version 1.0
 */
public class AdminRegisterFilter extends HttpFilter {
	private static final Logger log = LoggerFactory.getLogger(AdminRegisterFilter.class);
	
	public	static final String adminServletPath = "/admin/register";
	public	static final String adminJspPath = "/jsp/admin/register.jsp";
	
	@Override
	protected boolean httpIncoming() throws UnsupportedEncodingException, ServletException, IOException {
		Object obj = session.getAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
		if (obj instanceof Boolean && ((Boolean) obj)) { // 管理员注册状态
			if((!(uri.endsWith(adminJspPath) // 非指定页面
					|| uri.endsWith(adminServletPath)))) {
				if (CommonConfig.isDebug()) {
					log.debug("管理员注册状态过滤器：{}", uri);
				}
				
				// 跳转到指定页面
				response.sendRedirect(adminJspPath);
				// 拦截
				return false;
			}
		}
		// 放行
		return true;
	}
	
	
}
