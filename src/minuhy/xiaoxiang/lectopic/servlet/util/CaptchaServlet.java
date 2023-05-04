package minuhy.xiaoxiang.lectopic.servlet.util;


import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.util.CaptchaUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;

/**
 * ��֤��
 * 
 * ʹ�ô�ͳ��ʽ����
 * 
 * @author y17mm
 */
@WebServlet("/util/captcha")
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = -8665852098389921334L;
	private static final Logger log = LoggerFactory.getLogger(CaptchaServlet.class);
	
	CaptchaUtil captchaUtil;

	@Override
	public void init() throws ServletException {
		captchaUtil = new CaptchaUtil();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		minuhy.xiaoxiang.lectopic.util.CaptchaUtil.CaptchaInfo info = 
				captchaUtil.GgenerateCaptcha(115,36,3,16,"����");
		
		int t = RequestUtil.getReqParam(req, "t", 0);
		if(CommonConfig.isDebug()) {
			log.debug("��{}�λ�ȡ��֤�� {} -> {}", t,info.formula,info.result);
		}
		
		// ��֤����� session
        HttpSession session = req.getSession();
        session.setAttribute(ConstantsConfig.SESSION_CAPTCHA, info.result);
		
		// response�������ͼƬ��ҳ�棬Servlet���������ͼƬ�����
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(info.image, "png", sos);

        sos.flush();
        sos.close();
	}
	
}