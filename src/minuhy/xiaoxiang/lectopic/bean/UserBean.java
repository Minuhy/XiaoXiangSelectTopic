package minuhy.xiaoxiang.lectopic.bean;

import minuhy.xiaoxiang.lectopic.entity.UserEntity;
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
	

	/**
	 * 获取最后一次登录时间 yyyy-MM-dd HH:mm
	 * @return yyyy-MM-dd HH:mm
	 * @time 2023-4-30 19:23:38
	 */
	public String getLastLoginTime() {
		return TimeUtil.timestamp2DateTime(userEntity.getLastLoginTimestamp());
	}
	
	/**
	 * 获取最后登录IP
	 * @return IP
	 * @time 2023-4-30 19:24:01
	 */
	public String getLastLoginIp() {
		return userEntity.getLastLoginIp();
	}
	
	/**
	 * 获取头像
	 * 
	 * @return
	 * @time 2023-4-30 19:27:47
	 */
	public String getAvatar() {
		return userEntity.getAvatar();
	}
	
	/**
	 * 获取头像路径
	 * @return
	 * @time 2023-4-30 19:28:51
	 */
	public String getAvatarPath() {
		if(userEntity.getAvatar().startsWith("h") && userEntity.getAvatar().endsWith(".gif")) {
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
	
}
