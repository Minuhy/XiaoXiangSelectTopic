package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseUserServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * �޸����ϣ��û�����
 * 
 * @author y17mm
 * @time 2023-5-5 19:54:53
 * @version 1.0
 */
@WebServlet("/user/password")
public class PasswordServlet extends BaseUserServlet {
	private static final Logger log = LoggerFactory.getLogger(PasswordServlet.class);
	private static final long serialVersionUID = -6430486950563263035L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passwordUrl = "/jsp/common/password.jsp";
		// 1. �õ�����
		int userId = userBean.getId();

		String rawPassword = RequestUtil.getReqParam(request, "rawPassword", "").trim();
		String newPassword = RequestUtil.getReqParam(request, "newPassword", "").trim();
		String renewPassword = RequestUtil.getReqParam(request, "renewPassword", "").trim();
		
		if(CommonConfig.isDebug()) {
			log.debug("�޸����룺{}��{}��{}",rawPassword,newPassword,renewPassword);
		}
		
		// 2. У������
		
		// ԭ����
		if(!TextUtil.stringMinLen(rawPassword, 1)) {
			if(CommonConfig.isDebug()) {
				log.debug("ԭ���벻��Ϊ�գ������²�����{}",rawPassword);
			}
			forwardFailTipsPage("ԭ���벻��Ϊ�գ������²���", passwordUrl);
			return;
		}
		
		// ������
		if(!TextUtil.stringBetweenLen(newPassword, 6, 20)) {
			if(CommonConfig.isDebug()) {
				log.debug("�����볤�Ȳ���ȷ��6-20�֣��������²�����{}",newPassword);
			}
			forwardFailTipsPage("�����볤�Ȳ���ȷ��6-20�֣��������²���", passwordUrl);
			return;
		}
		// �ж������Ƿ�һ��
		if(!newPassword.equals(renewPassword)) {
			if(CommonConfig.isDebug()) {
				log.debug("����������벻һ�£������²�����{}��{}",newPassword,renewPassword);
			}
			forwardFailTipsPage("����������벻һ�£������²���", passwordUrl);
			return;
		}
		
		// 3. ����ҵ��Ԥ����д�����ݿ�
		
		// ��������
		rawPassword = EncryptionUtil.EncodePasswd(userBean.getCreateTimestamp(), rawPassword);
		newPassword = EncryptionUtil.EncodePasswd(userBean.getCreateTimestamp(), newPassword);
		
		if(CommonConfig.isDebug()) {
			log.debug("�������룺{}��{}",rawPassword,newPassword);
		}
		
		long updateTime = TimeUtil.getTimestampMs();
		
		UserDb userDb = new UserDb();
		
		try {
			if(userDb.updatePasswd(userId, rawPassword, newPassword)) {
				// ���»�������
				userBean.getUserEntity().setPasswd(newPassword);
				// �������ݸ���ʱ��
				userDb.updateUpdateTimestamp(userId, updateTime);
				// д��ɹ�
				forwardSuccessTipsPage("�޸ĳɹ�", passwordUrl);
				return;
			} else {
				forwardFailTipsPage("ԭ�������", passwordUrl);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("���ݿ����{}", e);
			forwardErrorInfoPage("���ݿ����", passwordUrl);
			return;
		}
		
	}
	

}
