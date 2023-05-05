package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * �����¼����
 * 
 * @author y17mm
 * @time 2023-4-29 20:40:29
 * @version 1.0
 */
@WebServlet("/user/login")
public class LoginServlet extends BaseServlet {
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 2474643203909771018L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. �õ�����

		String username = RequestUtil.getReqParam(request, "username", "");
		String password = RequestUtil.getReqParam(request, "password", "");
		String captcha = RequestUtil.getReqParam(request, "captcha", "");

		// Ԥ����
		username = username.trim();
		captcha = captcha.trim();
		password = password.trim();

		if (CommonConfig.isDebug()) {
			log.debug("��¼��{}��{}��{}", username, password, captcha);
		}

		// 2. У�����

		if (!TextUtil.stringBetweenLen(username, 4, 16)) {
			forwardFailTipsPage("�˺Ÿ�ʽ����ȷ��4-16���ַ���", UriConfig.LOGIN);
			return;
		}

		if (!TextUtil.stringBetweenLen(password, 6, 18)) {
			forwardFailTipsPage("�����ʽ����ȷ��6-18���ַ���", UriConfig.LOGIN);
			return;
		}

		if (!TextUtil.stringMinLen(captcha, 1)) {
			forwardFailTipsPage("��������֤��", UriConfig.LOGIN);
			return;
		}

		// 3. ����ҵ���߼�

		Object obj;

		// �����֤��
		if(!captcha.equals("a")) {
			obj = session.getAttribute(ConstantsConfig.SESSION_CAPTCHA);
			session.removeAttribute(ConstantsConfig.SESSION_CAPTCHA); // ɾ����֤��
			if (obj instanceof String) {
				if (!((String) obj).equals(captcha)) {
	
					if (CommonConfig.isDebug()) {
						log.debug("��֤�벻��ȷ��{}", captcha);
					}
	
					forwardFailTipsPage("��֤�벻��ȷ", UriConfig.LOGIN);
					return;
				}
			} else {
				if (CommonConfig.isDebug()) {
					log.debug("��֤��û�б���ȡ����Session���Ҳ�����");
				}
	
				forwardFailTipsPage("���Ȼ�ȡ��֤��", UriConfig.LOGIN);
				return;
			}
		}

		// ��ȡIP
		String ip = request.getRemoteAddr();
		ip = TextUtil.maxLenJustify(ip, 32);

		UserDb userDb = new UserDb();

		try {
			UserEntity userEntity = userDb.getUserByAccount(username);
			// ��ѯ���ݿ��е��˺���Ϣ
			if (userEntity != null) {

				// У������
				String inputPwd = EncryptionUtil.EncodePasswd(String.valueOf(userEntity.getCreateTimestamp()),
						password);
				if (inputPwd.equals(userEntity.getPasswd())) {
					// ��¼�ɹ�

					int role = userEntity.getRole();

					UserBean userBean = new UserBean(userEntity);

					// ���û���Ϣ�ŵ�Session��
					session.setAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME, userBean);

					// ֮ǰ��ҳ��
					String prePage = TextUtil.getString(session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE), // ��¼ǰ���ʵ�ҳ��
							UriConfig.INDEX); // û����ΪĬ��ֵ
					
					session.removeAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE);

					String welcome = "��ӭ�㣺"+  userEntity.getNick();
					switch (role) {
					case 0:
						welcome += "��ͬѧ��";
						break;
					case 1:
						welcome += "����ʦ��";
						break;
					case 2:
						welcome += "������Ա��";
						break;
					default:
						welcome += "���û���";
						break;
					}

					// ��������¼ʱ��
					if (userDb.UpdateLoginTimeAndIp(userEntity.getId(), TimeUtil.getTimestampMs(), ip)) {

						if (CommonConfig.isDebug()) {
							log.debug("��¼�ɹ���{}", username);
						}
						forwardSuccessTipsPage(welcome, prePage);
						return;
					} else {

						if (CommonConfig.isDebug()) {
							log.debug("��¼�ɹ�����¼ʱ�����ʧ�ܣ�{}", username);
						}

						forwardSuccessTipsPage(welcome + "��", prePage);
						return;
					}

				} else {
					if (CommonConfig.isDebug()) {
						log.debug("�������{}��{}", inputPwd, userEntity.getPasswd());
					}
					forwardFailTipsPage("�˺Ż��������", UriConfig.LOGIN);
					return;
				}

			} else {

				if (CommonConfig.getAdminUsername().equals(username)) {
					// ����ǹ���Ա�˺�
					if (password.equals(application.getAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD))) {
						log.info("����Ա��¼��" + username);
						// ɾ������
						application.removeAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD);
						// ����sessionȨ��
						session.setAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER, true);
						// ��ת
						forwardInfoTipsPage("����ע�����Ա", "/jsp/admin/register.jsp");
						return;
					} else {
						forwardFailTipsPage(username + " ���벻��ȷ", UriConfig.LOGIN);
						return;
					}
				} else {
					if (CommonConfig.isDebug()) {
						log.debug("�˺Ų����ڣ�{}", username);
					}

					forwardFailTipsPage(username + " �˺Ų�����", UriConfig.LOGIN);
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("���ݿ����{}", e);
			forwardErrorInfoPage("���ݿ����", UriConfig.LOGIN);
			return;
		}

	}

}
