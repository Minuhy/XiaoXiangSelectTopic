package minuhy.xiaoxiang.lectopic.bean;

import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

public class UserBean {

	int id;
	UserEntity userEntity;
	
	public UserBean(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.userEntity = userEntity;
	}

	public int getId() {
		return id;
	}
	
	public int getRole() {
		return userEntity.getRole();
	}
	
	public String getAccount() {
		return userEntity.getAccount();
	}
	
	
	public UserEntity getUserEntity() {
		return userEntity;
	}

	/**
	 * 获取性别（字符）
	 * @return 性别
	 * @time 2023-5-5 0:52:40
	 */
	public String getSexStr() {
		int sex = userEntity.getSex();
		
		if(sex == 1) {
			return "男";
		}else if(sex == 2) {
			return "女";
		}
		
		return "保密";
	}
	
	/**
	 * 设置性别
	 * @return 性别 0：保密，1：男，2：女
	 * @time 2023-5-5 20:08:37
	 */
	public int getSex() {
		return userEntity.getSex();
	}
	
	/**
	 * 获取电子邮件地址
	 * @return 电子邮件地址
	 * @time 2023-5-5 0:55:22
	 */
	public String getEmail() {
		return TextUtil.getString(userEntity.getEmail(), "");
	}
	
	/**
	 * 获取手机号
	 * @return 手机号
	 * @time 2023-5-5 0:56:34
	 */
	public String getPhone() {
		return TextUtil.getString(userEntity.getPhone(), "");
	}
	
	/**
	 * 获取签名信息
	 * @return 签名
	 * @time 2023-5-5 0:57:31
	 */
	public String getSignature() {
		return TextUtil.getString(userEntity.getSignature(), "");
	}
	
	

	/**
	 * 获取最后一次登录时间 yyyy-MM-dd HH:mm
	 * @return yyyy-MM-dd HH:mm 或者 无
	 * @time 2023-4-30 19:23:38
	 */
	public String getLastLoginTime() {
		if(userEntity.getLastLoginTimestamp() == 0) {
			return "无";
		}
		return TimeUtil.timestamp2DateTime(userEntity.getLastLoginTimestamp());
	}
	
	/**
	 * 获取最后登录IP
	 * @return IP 或者 无
	 * @time 2023-4-30 19:24:01
	 */
	public String getLastLoginIp() {
		return TextUtil.getString(userEntity.getLastLoginIp(), "无");
	}
	
	/**
	 * 获取头像
	 * 
	 * @return
	 * @time 2023-4-30 19:27:47
	 */
	public String getAvatar() {
		return TextUtil.getString(userEntity.getAvatar(), "h000.gif");
	}
	
	/**
	 * 获取头像路径
	 * @return
	 * @time 2023-4-30 19:28:51
	 */
	public String getAvatarPath() {
		if(getAvatar().startsWith("h") && userEntity.getAvatar().endsWith(".gif")) {
			return "/common/lectopic/img/"+ userEntity.getAvatar();
		}else {
			return userEntity.getAvatar();
		}
	}
	
	/**
	 * 获取用户昵称
	 * @return
	 * @time 2023-4-30 19:41:56
	 */
	public String getNick() {
		return userEntity.getNick();
	}
	
	/**
	 * 获取时间戳字符串，用于加密密码
	 * @return
	 * @time 2023-5-6 0:58:11
	 */
	public String getCreateTimestamp() {
		return String.valueOf(userEntity.getCreateTimestamp());
	}
}
