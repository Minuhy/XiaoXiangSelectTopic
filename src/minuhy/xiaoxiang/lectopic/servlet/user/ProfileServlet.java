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
 * 修改资料，用户资料
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
		// 1. 拿到数据
		int userId = userBean.getId();

		String avatar = RequestUtil.getReqParam(request, "avatar", "").trim();
		String nick = RequestUtil.getReqParam(request, "nick", "").trim();
		String sex = RequestUtil.getReqParam(request, "sex", "").trim();
		String email = RequestUtil.getReqParam(request, "email", "").trim();
		String phone = RequestUtil.getReqParam(request, "phone", "").trim();
		String signature = RequestUtil.getReqParam(request, "signature", "").trim();
		int sexCode=-1;
		
		// 2. 校验数据
		
		// 头像
		if(!RegularUtil.contains("^h\\d{3}\\.[a-zA-Z]{3,4}$", avatar)) {
			if(CommonConfig.isDebug()) {
				log.debug("头像格式不正确（未满足正则表达式）：{}",avatar);
			}
			forwardErrorTipsPage("头像格式不正确（未满足正则表达式）", profileUrl);
			return;
		}
		// 昵称
		if(!TextUtil.stringBetweenLen(nick, 1, 10)) {
			if(CommonConfig.isDebug()) {
				log.debug("昵称格式不正确（1-10字），请重新操作：{}",nick);
			}
			forwardFailTipsPage("昵称格式不正确（1-10字），请重新操作", profileUrl);
			return;
		}
		// 性别
		try {
			sexCode = Integer.parseInt(sex);
			if(sexCode!=0&&sexCode!=1&&sexCode!=2) {
				throw new NumberFormatException("非法数字");
			}
		}catch (NumberFormatException e) {
			if(CommonConfig.isDebug()) {
				log.debug("性别格式不正确，请重新操作（"+e.getMessage()+"）：{}",sex);
			}
			forwardErrorTipsPage("性别格式不正确，请重新操作（"+e.getMessage()+"）", profileUrl);
			return;
		}
		// 邮箱
		if(!TextUtil.stringMaxLen(email, 50) || !RegularUtil.contains("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email)) {
			if(CommonConfig.isDebug()) {
				log.debug("电子邮箱地址格式不正确（未满足正则表达式），请重新操作：{}",email);
			}
			forwardFailTipsPage("电子邮箱地址格式不正确（未满足正则表达式），请重新操作", profileUrl);
			return;
		}
		// 手机
		if(!TextUtil.stringMaxLen(phone, 11) || !RegularUtil.contains("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", phone)) {
			if(CommonConfig.isDebug()) {
				log.debug("手机号码格式不正确（未满足正则表达式），请重新操作：{}",phone);
			}
			forwardFailTipsPage("手机号码格式不正确（未满足正则表达式），请重新操作", profileUrl);
			return;
		}
		// 签名
		if(!TextUtil.stringBetweenLen(signature, 0, 60)) {
			if(CommonConfig.isDebug()) {
				log.debug("签名太长（至多60字），请重新操作：{}",signature);
			}
			forwardFailTipsPage("签名太长（至多60字），请重新操作", profileUrl);
			return;
		}
		
		// 3. 处理业务，写入数据库
		long updateTime = TimeUtil.getTimestampMs();
		
		UserEntity userEntity = updateUserEntity(null,userId,avatar,nick,sexCode,email,phone,signature,updateTime,userId);
		
		UserDb userDb = new UserDb();
		
		try {
			if(userDb.UpdateProfile(userEntity)) {
				// 写入成功
				updateUserEntity(userBean.getUserEntity(),userId,avatar,nick,sexCode,email,phone,signature,updateTime,userId);
				forwardSuccessTipsPage("资料保存成功", profileUrl);
				return;
			}else {
				forwardFailTipsPage("数据写入失败", profileUrl);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("数据库错误：{}", e);
			forwardErrorInfoPage("数据库错误", profileUrl);
			return;
		}
		
	}
	
	/**
	 * 合并userEntity数据，为空则新建
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
