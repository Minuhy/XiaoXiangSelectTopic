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
	 * ��ȡ���һ�ε�¼ʱ�� yyyy-MM-dd HH:mm
	 * @return yyyy-MM-dd HH:mm
	 * @time 2023-4-30 19:23:38
	 */
	public String getLastLoginTime() {
		return TimeUtil.timestamp2DateTime(userEntity.getLastLoginTimestamp());
	}
	
	/**
	 * ��ȡ����¼IP
	 * @return IP
	 * @time 2023-4-30 19:24:01
	 */
	public String getLastLoginIp() {
		return userEntity.getLastLoginIp();
	}
	
	/**
	 * ��ȡͷ��
	 * 
	 * @return
	 * @time 2023-4-30 19:27:47
	 */
	public String getAvatar() {
		return userEntity.getAvatar();
	}
	
	/**
	 * ��ȡͷ��·��
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
	 * ��ȡ�û��ǳ�
	 * @return
	 * @time 2023-4-30 19:41:56
	 */
	public String getNick() {
		return userEntity.getNick();
	}
	
}
