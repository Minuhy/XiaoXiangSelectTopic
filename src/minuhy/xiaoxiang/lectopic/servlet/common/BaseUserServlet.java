package minuhy.xiaoxiang.lectopic.servlet.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;

/**
 * ��֤�û��Ƿ��¼����¼���ٴ������󣬷��򷵻���ʾҳ��
 * 
 * �̳д��������֤���û���¼�����õ�userBean����
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
	 * �Զ�����ʾ��Ϣ
	 * @param tips ��ʾ��Ϣ
	 */
	public BaseUserServlet(String tips) {
		this.tips = tips;
	}

	/**
	 * ��֤�û��Ƿ��¼
	 */
	@Override
	public boolean beforeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Object obj = session.getAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME);

		if (obj instanceof UserBean && ((UserBean) obj).getId() > 0) {
			userBean = (UserBean) obj;
			return true;
		}

		forwardInfoTipsPage(tips == null ? "���¼�������²���" : tips, UriConfig.LOGIN);

		return false;
	}
}
