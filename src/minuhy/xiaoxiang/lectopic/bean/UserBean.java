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
	 * ��ȡ�Ա��ַ���
	 * @return �Ա�
	 * @time 2023-5-5 0:52:40
	 */
	public String getSexStr() {
		int sex = userEntity.getSex();
		
		if(sex == 1) {
			return "��";
		}else if(sex == 2) {
			return "Ů";
		}
		
		return "����";
	}
	
	/**
	 * �����Ա�
	 * @return �Ա� 0�����ܣ�1���У�2��Ů
	 * @time 2023-5-5 20:08:37
	 */
	public int getSex() {
		return userEntity.getSex();
	}
	
	/**
	 * ��ȡ�����ʼ���ַ
	 * @return �����ʼ���ַ
	 * @time 2023-5-5 0:55:22
	 */
	public String getEmail() {
		return TextUtil.getString(userEntity.getEmail(), "");
	}
	
	/**
	 * ��ȡ�ֻ���
	 * @return �ֻ���
	 * @time 2023-5-5 0:56:34
	 */
	public String getPhone() {
		return TextUtil.getString(userEntity.getPhone(), "");
	}
	
	/**
	 * ��ȡǩ����Ϣ
	 * @return ǩ��
	 * @time 2023-5-5 0:57:31
	 */
	public String getSignature() {
		return TextUtil.getString(userEntity.getSignature(), "");
	}
	
	

	/**
	 * ��ȡ���һ�ε�¼ʱ�� yyyy-MM-dd HH:mm
	 * @return yyyy-MM-dd HH:mm ���� ��
	 * @time 2023-4-30 19:23:38
	 */
	public String getLastLoginTime() {
		if(userEntity.getLastLoginTimestamp() == 0) {
			return "��";
		}
		return TimeUtil.timestamp2DateTime(userEntity.getLastLoginTimestamp());
	}
	
	/**
	 * ��ȡ����¼IP
	 * @return IP ���� ��
	 * @time 2023-4-30 19:24:01
	 */
	public String getLastLoginIp() {
		return TextUtil.getString(userEntity.getLastLoginIp(), "��");
	}
	
	/**
	 * ��ȡͷ��
	 * 
	 * @return
	 * @time 2023-4-30 19:27:47
	 */
	public String getAvatar() {
		return TextUtil.getString(userEntity.getAvatar(), "h000.gif");
	}
	
	/**
	 * ��ȡͷ��·��
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
	 * ��ȡ�û��ǳ�
	 * @return
	 * @time 2023-4-30 19:41:56
	 */
	public String getNick() {
		return userEntity.getNick();
	}
	
	/**
	 * ��ȡʱ����ַ��������ڼ�������
	 * @return
	 * @time 2023-5-6 0:58:11
	 */
	public String getCreateTimestamp() {
		return String.valueOf(userEntity.getCreateTimestamp());
	}
}
