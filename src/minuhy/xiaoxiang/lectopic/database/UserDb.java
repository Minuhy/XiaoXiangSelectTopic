package minuhy.xiaoxiang.lectopic.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.common.Executant;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;

/**
 * �û����ݿ����
 * 
 * @author y17mm
 * @time 2023-4-30 9:22:21
 * @version 1.0
 */
public class UserDb extends Executant {
	private static final Logger log = LoggerFactory.getLogger(UserDb.class);

	/**
	 * ͨ���˺Ų����û���Ϣ
	 * @param account �˺�
	 * @return �û���Ϣ����null
	 * @throws SQLException SQL�쳣
	 * @time 2023-4-30 11:21:43
	 */
	public UserEntity getUserByAccount(String account) throws SQLException {
		String sql = 	"select * " + 
						"from `t_user` " + 
						"where `account`=?  and `active`=? " + 
						"limit 0,1";

		if (CommonConfig.isDebug()) {
			log.debug("�����Ñ���{}��{}", sql, account);
		}

		UserEntity userEntity = null;
		try {
			ResultSet resultSet = query(sql, account, String.valueOf(1));
			userEntity = createUserEntity(resultSet);
		} finally {
			close();
		}
		return userEntity;
	}

	/**
	 * ���ݽ�������� UserEntity
	 * @param resultSet �����
	 * @return һ��UserEntity�������null
	 * @throws SQLException SQL�쳣
	 * @time 2023-4-30 11:20:43
	 */
	public static UserEntity createUserEntity(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			int id = resultSet.getInt("id"); // ID���Զ����ɣ�Ψһ
			int active = resultSet.getInt("active"); // �˺��Ƿ񼤻1���0����
			String passwd = resultSet.getString("passwd"); // ���룬��¼�ã�MD5����
			int role = resultSet.getInt("role"); // ��ɫ��0����ͨ��1������Ա
			String nick = resultSet.getString("nick"); // �ǳ�
			String account = resultSet.getString("account"); // �˺�
			String signature = resultSet.getString("signature"); // ǩ��
			String email = resultSet.getString("email"); // ��������
			String phone = resultSet.getString("phone"); // �ֻ���
			int sex = resultSet.getInt("sex"); // �Ա�0��δ���ã�1���У�2��Ů
			String avatar = resultSet.getString("avatar"); // ͷ��
			int hasNewMsg = resultSet.getInt("has_new_msg"); // ����Ϣ����
			int createBy = resultSet.getInt("create_by"); // ������ID
			int updateBy = resultSet.getInt("update_by"); // ������ID
			long createTimestamp = resultSet.getLong("create_timestamp"); // ����ʱ��
			long updateTimestamp = resultSet.getLong("update_timestamp"); // ����޸�ʱ��
			long lastLoginTimestamp = resultSet.getLong("last_login_timestamp"); // ����¼ʱ��
			String lastLoginIp = resultSet.getString("last_login_ip"); // ����¼ʱ��
			String remark = resultSet.getString("remark"); // ��ע

			UserEntity userEntity = new UserEntity(id, active, account, passwd, role, nick, signature, email, phone,
					sex, avatar, hasNewMsg, createBy, updateBy, createTimestamp, updateTimestamp, lastLoginTimestamp,
					lastLoginIp, remark);

			if (CommonConfig.isDebug()) {
				log.debug("�鵽���ݣ�{}", userEntity);
			}

			return userEntity;
		} else {
			if (CommonConfig.isDebug()) {
				log.debug("û�в鵽����");
			}
		}
		return null;
	}

	/**
	 * ���µ�¼ʱ��
	 * 
	 * @param id          �û�ID
	 * @param currentTime ʱ���
	 * @return �ɹ�/�� 
	 * @throws SQLException SQL�쳣
	 */
	public boolean UpdateLoginTimeAndIp(int id, long currentTime,String ip) throws SQLException {
		String sql = "UPDATE `t_user` "
				+ "SET `last_login_timestamp` = ?,`last_login_ip` = ? "
				+ "WHERE `id` = ?";

		if (CommonConfig.isDebug()) {
			log.debug("���µ�䛕r�gIP��{}��{}��{}��{}", sql, id, currentTime,ip);
		}

		int result = 0;
		try {
			result = update(sql, 
					String.valueOf(currentTime), 
					ip,
					String.valueOf(id)
				);

			if (CommonConfig.isDebug()) {
				log.debug("�������ݣ�{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}
	
	/**
	 * ע�ᣬд�����ݿ�
	 * 
	 * @param userEntity �û���Ϣ
	 * @return Ӱ������
	 * @throws SQLException ���ݿ����
	 */
	public boolean writeUser(UserEntity userEntity) throws SQLException {
		String sql = "INSERT INTO `t_user`" 
				+ "(`account`,`role`, `passwd`, `nick`, `create_timestamp`, `create_by`,`avatar`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		if (CommonConfig.isDebug()) {
			log.debug("ע�᣺{}��{}", sql, userEntity.toString());
		}

		int result = 0;
		try {
			result = insert(sql, 
					userEntity.getAccount(), 
					String.valueOf(userEntity.getRole()), 
					userEntity.getPasswd(), 
					userEntity.getNick(),
					String.valueOf(userEntity.getCreateTimestamp()),
					String.valueOf(userEntity.getCreateBy()),
					userEntity.getAvatar()
				);

			if (CommonConfig.isDebug()) {
				log.debug("�������ݣ�{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}

	/**
	 * ��������
	 * @param userEntity �û�����
	 * @return true �ɹ� 
	 * @throws SQLException
	 * @time 2023-5-5 22:01:47
	 */
	public boolean UpdateProfile(UserEntity userEntity) throws SQLException {
		String sql = "UPDATE `t_user` " + 
				"SET `nick` = ?, `signature` = ?, `sex` = ?, `email` = ?, `phone` = ?, `avatar` = ?,`update_timestamp`=?,`update_by`=? " + 
				"WHERE `id` = ?";

		if (CommonConfig.isDebug()) {
			log.debug("���¸������ϣ�{}��{}", sql, userEntity);
		}

		int result = 0;
		try {
			result = update(sql, 
					userEntity.getNick(),
					userEntity.getSignature(),
					String.valueOf(userEntity.getSex()),
					userEntity.getEmail(),
					userEntity.getPhone(),
					userEntity.getAvatar(),
					String.valueOf(userEntity.getUpdateTimestamp()),
					String.valueOf(userEntity.getUpdateBy()),
					String.valueOf(userEntity.getId())
				);

			if (CommonConfig.isDebug()) {
				log.debug("�������ݣ�{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}
}
