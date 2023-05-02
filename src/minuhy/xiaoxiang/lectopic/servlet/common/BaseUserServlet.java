package minuhy.xiaoxiang.lectopic.servlet.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;

/**
 * 验证用户是否登录，登录后再处理请求，否则返回提示页面
 * 
 * 继承此类可以验证码用户登录，并得到userBean对象
 * 
 * @author y17mm
 * @time 2023-5-2 8:41:18
 * @version 1.0
 */
public class BaseUserServlet extends BaseServlet {
	private static final long serialVersionUID = 8616683109378834695L;

	protected UserBean userBean;

	private String tips;

	public BaseUserServlet() {
		this.tips = null;
	}

	/**
	 * 自定义提示信息
	 * @param tips 提示信息
	 */
	public BaseUserServlet(String tips) {
		this.tips = tips;
	}

	/**
	 * 验证用户是否登录
	 */
	@Override
	public boolean beforeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object obj = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);

		if (obj instanceof UserBean && ((UserBean) obj).getId() > 0) {
			userBean = (UserBean) obj;
			return true;
		}

		forwardInfoTipsPage(tips == null ? "请登录后再重新操作" : tips, UriConfig.LOGIN);

		return false;
	}
}
