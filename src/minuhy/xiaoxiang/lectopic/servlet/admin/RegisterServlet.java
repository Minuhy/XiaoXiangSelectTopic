package minuhy.xiaoxiang.lectopic.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.RoleConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

@WebServlet("/admin/register")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. ��Ȩ��
		Object obj = session.getAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
		if (!(obj instanceof Boolean && ((Boolean) obj))) {
			forwardInfoTipsPage("û��Ȩ�޷���", UriConfig.LOGIN);
			return;
		}
		
		// 1. �õ�����
		String password = RequestUtil.getReqParam(request, "password", "");
		String rePassword = RequestUtil.getReqParam(request, "repasswd", "");

		// Ԥ����
		rePassword = rePassword.trim();
		password = password.trim();

		if (CommonConfig.isDebug()) {
			log.debug("����Աע�᣺{}��{}��{}", password, rePassword);
		}

		// 2. У�����

		if (!password.equals(rePassword)) {
			forwardFailTipsPage("�������벻һ��", UriConfig.REGISTER);
			return;
		}

		if (!TextUtil.stringBetweenLen(password, 6, 18)) {
			forwardFailTipsPage("�����ʽ����ȷ��6-18���ַ���", UriConfig.REGISTER);
			return;
		}

		// 3. ����ҵ���߼�

		UserDb userDb = new UserDb();
		try {
			UserEntity userEntity = userDb.getUserByAccount(CommonConfig.getAdminUsername());
			// ��ѯ���ݿ��е��˺���Ϣ
			if (userEntity == null) {

				long timeMs = TimeUtil.getTimestampMs();

				// ��������
				String inputPwd = EncryptionUtil.EncodePasswd(String.valueOf(timeMs), password);

				// ��������
				userEntity = new UserEntity();
				userEntity.setAccount(CommonConfig.getAdminUsername());
				userEntity.setRole(RoleConfig.ADMIN);
				userEntity.setPasswd(inputPwd);
				userEntity.setNick(CommonConfig.getAdminUsername());
				userEntity.setCreateTimestamp(timeMs);
				userEntity.setCreateBy(0);
				userEntity.setAvatar("h000.png");

				// �������ݿ�
				if (userDb.writeUser(userEntity)) {
					session.removeAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
					forwardSuccessTipsPage("����Ա�˺�ע��ɹ�", UriConfig.LOGIN);
					return;
				} else {
					forwardFailInfoPage("����Ա�˺�ע��ʧ�ܣ�д�����ݿ����", UriConfig.REGISTER);
					return;
				}

			} else {
				if (CommonConfig.isDebug()) {
					log.debug("����Ա�˺���ע�᣺{}", CommonConfig.getAdminUsername());
				}
				session.removeAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
				forwardFailInfoPage("����Ա�˺���ע��", UriConfig.LOGIN);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("���ݿ����{}", e);
			forwardErrorInfoPage("���ݿ����", UriConfig.LOGIN);
			return;
		}

	}

}
