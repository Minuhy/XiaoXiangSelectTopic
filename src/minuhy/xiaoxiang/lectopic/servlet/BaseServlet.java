package minuhy.xiaoxiang.lectopic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -7684719899209422438L;

	HttpServletRequest request;
	HttpServletResponse response;
	String currentPath;

	/**
	 * ��Ϣ״̬
	 * 
	 * @author y17mm
	 * @time 2023-4-29 23:39:24
	 * @version 1.0
	 */
	public static enum MessageState {
		SUCCESS, INFO,ERROR, FAIL,  None
	}

	@Override
	protected final void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.request = req;
		this.response = resp;
		this.currentPath = request.getContextPath();
		super.service(req, resp);
	}

	/**
	 * ת������ʾҳ�棨�Զ���ת��
	 * 
	 * @param sta  ״̬
	 * @param info ��Ϣ
	 * @param uri  ����������ҳ��
	 * @throws ServletException �쳣
	 * @throws IOException      �쳣
	 * @time 2023-4-30 0:27:13
	 */
	public final void forwardTipsPage(MessageState sta, String info, String uri) throws ServletException, IOException {
		forwardMessagePage(sta, info, uri, true);
	}

	/**
	 * ת������Ϣҳ�棨���Զ���ת��
	 * 
	 * @param sta  ״̬
	 * @param info ��Ϣ
	 * @param uri  ����������ҳ��
	 * @throws ServletException �쳣
	 * @throws IOException      �쳣
	 * @time 2023-4-30 0:28:30
	 */
	public final void forwardInfoPage(MessageState sta, String info, String uri) throws ServletException, IOException {
		forwardMessagePage(sta, info, uri, false);
	}

	/**
	 * ת������Ϣҳ��
	 * 
	 * @param sta        ״̬
	 * @param info       ��Ϣ
	 * @param uri        ����������ҳ��
	 * @param isAutoSkip �Ƿ��Զ���ת
	 * @throws ServletException �쳣
	 * @throws IOException      �쳣
	 * @time 2023-4-30 0:27:52
	 */
	private final void forwardMessagePage(MessageState sta, String info, String uri, boolean isAutoSkip)
			throws ServletException, IOException {
		uri = currentPath + uri;
		
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_STATE, sta);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_INFO, info);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_URI, uri);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_SKIP, isAutoSkip);
		request.getRequestDispatcher("/message.jsp").include(request, response);
		if(isAutoSkip) {
			response.setHeader("refresh", "3;url="+uri);
		}
	}

}
