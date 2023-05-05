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
import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseUserServlet;
import minuhy.xiaoxiang.lectopic.util.RegularUtil;
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
@WebServlet("/user/profile")
public class ProfileServlet extends BaseUserServlet {
	private static final Logger log = LoggerFactory.getLogger(ProfileServlet.class);
	private static final long serialVersionUID = -6430486950563263035L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String profileUrl = "/jsp/common/profile.jsp";
		// 1. �õ�����
		int userId = userBean.getId();

		String avatar = RequestUtil.getReqParam(request, "avatar", "").trim();
		String nick = RequestUtil.getReqParam(request, "nick", "").trim();
		String sex = RequestUtil.getReqParam(request, "sex", "").trim();
		String email = RequestUtil.getReqParam(request, "email", "").trim();
		String phone = RequestUtil.getReqParam(request, "phone", "").trim();
		String signature = RequestUtil.getReqParam(request, "signature", "").trim();
		int sexCode=-1;
		
		// 2. У������
		
		// ͷ��
		if(!RegularUtil.contains("^h\\d{3}\\.[a-zA-Z]{3,4}$", avatar)) {
			if(CommonConfig.isDebug()) {
				log.debug("ͷ���ʽ����ȷ��δ����������ʽ����{}",avatar);
			}
			forwardErrorTipsPage("ͷ���ʽ����ȷ��δ����������ʽ��", profileUrl);
			return;
		}
		// �ǳ�
		if(!TextUtil.stringBetweenLen(nick, 1, 10)) {
			if(CommonConfig.isDebug()) {
				log.debug("�ǳƸ�ʽ����ȷ��1-10�֣��������²�����{}",nick);
			}
			forwardFailTipsPage("�ǳƸ�ʽ����ȷ��1-10�֣��������²���", profileUrl);
			return;
		}
		// �Ա�
		try {
			sexCode = Integer.parseInt(sex);
			if(sexCode!=0&&sexCode!=1&&sexCode!=2) {
				throw new NumberFormatException("�Ƿ�����");
			}
		}catch (NumberFormatException e) {
			if(CommonConfig.isDebug()) {
				log.debug("�Ա��ʽ����ȷ�������²�����"+e.getMessage()+"����{}",sex);
			}
			forwardErrorTipsPage("�Ա��ʽ����ȷ�������²�����"+e.getMessage()+"��", profileUrl);
			return;
		}
		// ����
		if(!TextUtil.stringMaxLen(email, 50) || !RegularUtil.contains("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email)) {
			if(CommonConfig.isDebug()) {
				log.debug("���������ַ��ʽ����ȷ��δ����������ʽ���������²�����{}",email);
			}
			forwardFailTipsPage("���������ַ��ʽ����ȷ��δ����������ʽ���������²���", profileUrl);
			return;
		}
		// �ֻ�
		if(!TextUtil.stringMaxLen(phone, 11) || !RegularUtil.contains("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", phone)) {
			if(CommonConfig.isDebug()) {
				log.debug("�ֻ������ʽ����ȷ��δ����������ʽ���������²�����{}",phone);
			}
			forwardFailTipsPage("�ֻ������ʽ����ȷ��δ����������ʽ���������²���", profileUrl);
			return;
		}
		// ǩ��
		if(!TextUtil.stringBetweenLen(signature, 0, 60)) {
			if(CommonConfig.isDebug()) {
				log.debug("ǩ��̫��������60�֣��������²�����{}",signature);
			}
			forwardFailTipsPage("ǩ��̫��������60�֣��������²���", profileUrl);
			return;
		}
		
		// 3. ����ҵ��д�����ݿ�
		long updateTime = TimeUtil.getTimestampMs();
		
		UserEntity userEntity = updateUserEntity(null,userId,avatar,nick,sexCode,email,phone,signature,updateTime,userId);
		
		UserDb userDb = new UserDb();
		
		try {
			if(userDb.UpdateProfile(userEntity)) {
				// д��ɹ�
				updateUserEntity(userBean.getUserEntity(),userId,avatar,nick,sexCode,email,phone,signature,updateTime,userId);
				forwardSuccessTipsPage("���ϱ���ɹ�", profileUrl);
				return;
			}else {
				forwardFailTipsPage("����д��ʧ��", profileUrl);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("���ݿ����{}", e);
			forwardErrorInfoPage("���ݿ����", profileUrl);
			return;
		}
		
	}
	
	/**
	 * �ϲ�userEntity���ݣ�Ϊ�����½�
	 * @param userEntity
	 * @param userId
	 * @param avatar
	 * @param nick
	 * @param sexCode
	 * @param email
	 * @param phone
	 * @param signature
	 * @param updateTime
	 * @param updateBy
	 * @time 2023-5-5 21:50:14
	 */
	public UserEntity updateUserEntity(UserEntity userEntity,
			int userId,
			String avatar,
			String nick,
			int sexCode,
			String email,
			String phone,
			String signature,
			long updateTime,
			int updateBy
			) {
		if(userEntity == null) {
			userEntity = new UserEntity();
		}
		userEntity.setId(userId);
		userEntity.setAvatar(avatar);
		userEntity.setNick(nick);
		userEntity.setSex(sexCode);
		userEntity.setEmail(email);
		userEntity.setPhone(phone);
		userEntity.setSignature(signature);
		userEntity.setUpdateTimestamp(updateTime);
		userEntity.setUpdateBy(updateBy);
		return userEntity;
	}

}
