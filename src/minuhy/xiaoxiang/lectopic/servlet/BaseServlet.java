package minuhy.xiaoxiang.lectopic.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.config.enums.MessageState;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = -7684719899209422438L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	/**
	 * ��ǰ�Ự����
	 */
	protected HttpSession session;
	protected ServletContext application;
	/**
	 * ��ǰӦ��·��
	 */
	protected String currentPath;

	@Override
	protected final void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.request = req;
		this.response = resp;
		this.currentPath = req.getContextPath();
		this.session = req.getSession();
		this.application = req.getServletContext();
		super.service(req, resp);
	}
	
	public final void forwardFailInfoPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.FAIL, info, uri, true);
	}

	public final void forwardErrorInfoPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.ERROR, info, uri, true);
	}
	
	public final void forwardInfoPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.INFO, info, uri, true);
	}
	
	public final void forwardSuccessInfoPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.SUCCESS, info, uri, true);
	}

	public final void forwardFailTipsPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.FAIL, info, uri, true);
	}

	public final void forwardErrorTipsPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.ERROR, info, uri, true);
	}
	
	public final void forwardInfoTipsPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.INFO, info, uri, true);
	}
	
	public final void forwardSuccessTipsPage(String info, String uri) throws ServletException, IOException {
		forwardMessagePage(MessageState.SUCCESS, info, uri, true);
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
		if(!(uri.startsWith("http://") || uri.startsWith("https://"))) {
			uri = currentPath + uri;
		}
		
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_STATE, sta);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_INFO, info);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_URI, uri);
		request.setAttribute(ConstantsConfig.REQUEST_TIPS_SKIP, isAutoSkip);
		request.getRequestDispatcher(UriConfig.MESSAGE).include(request, response);
		if(isAutoSkip) {
			response.setHeader("refresh", "3;url="+uri);
		}
	}

}
