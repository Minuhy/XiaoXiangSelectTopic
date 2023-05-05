package minuhy.xiaoxiang.lectopic.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.common.Executant;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;

/**
 * 用户数据库相关
 * 
 * @author y17mm
 * @time 2023-4-30 9:22:21
 * @version 1.0
 */
public class UserDb extends Executant {
	private static final Logger log = LoggerFactory.getLogger(UserDb.class);

	/**
	 * 通过账号查找用户信息
	 * @param account 账号
	 * @return 用户信息或者null
	 * @throws SQLException SQL异常
	 * @time 2023-4-30 11:21:43
	 */
	public UserEntity getUserByAccount(String account) throws SQLException {
		String sql = 	"select * " + 
						"from `t_user` " + 
						"where `account`=?  and `active`=? " + 
						"limit 0,1";

		if (CommonConfig.isDebug()) {
			log.debug("查找用戶：{}，{}", sql, account);
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
	 * 根据结果集生成 UserEntity
	 * @param resultSet 结果集
	 * @return 一个UserEntity对象或者null
	 * @throws SQLException SQL异常
	 * @time 2023-4-30 11:20:43
	 */
	public static UserEntity createUserEntity(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			int id = resultSet.getInt("id"); // ID，自动生成，唯一
			int active = resultSet.getInt("active"); // 账号是否激活：1激活，0禁用
			String passwd = resultSet.getString("passwd"); // 密码，登录用，MD5加密
			int role = resultSet.getInt("role"); // 角色，0：普通，1：管理员
			String nick = resultSet.getString("nick"); // 昵称
			String account = resultSet.getString("account"); // 账号
			String signature = resultSet.getString("signature"); // 签名
			String email = resultSet.getString("email"); // 电子邮箱
			String phone = resultSet.getString("phone"); // 手机号
			int sex = resultSet.getInt("sex"); // 性别，0：未设置，1：男，2：女
			String avatar = resultSet.getString("avatar"); // 头像
			int hasNewMsg = resultSet.getInt("has_new_msg"); // 新消息计数
			int createBy = resultSet.getInt("create_by"); // 创建者ID
			int updateBy = resultSet.getInt("update_by"); // 更新者ID
			long createTimestamp = resultSet.getLong("create_timestamp"); // 创建时间
			long updateTimestamp = resultSet.getLong("update_timestamp"); // 最后修改时间
			long lastLoginTimestamp = resultSet.getLong("last_login_timestamp"); // 最后登录时间
			String lastLoginIp = resultSet.getString("last_login_ip"); // 最后登录时间
			String remark = resultSet.getString("remark"); // 备注

			UserEntity userEntity = new UserEntity(id, active, account, passwd, role, nick, signature, email, phone,
					sex, avatar, hasNewMsg, createBy, updateBy, createTimestamp, updateTimestamp, lastLoginTimestamp,
					lastLoginIp, remark);

			if (CommonConfig.isDebug()) {
				log.debug("查到数据：{}", userEntity);
			}

			return userEntity;
		} else {
			if (CommonConfig.isDebug()) {
				log.debug("没有查到数据");
			}
		}
		return null;
	}

	/**
	 * 更新登录时间
	 * 
	 * @param id          用户ID
	 * @param currentTime 时间戳
	 * @return 成功/否 
	 * @throws SQLException SQL异常
	 */
	public boolean UpdateLoginTimeAndIp(int id, long currentTime,String ip) throws SQLException {
		String sql = "UPDATE `t_user` "
				+ "SET `last_login_timestamp` = ?,`last_login_ip` = ? "
				+ "WHERE `id` = ?";

		if (CommonConfig.isDebug()) {
			log.debug("更新登錄時間IP：{}，{}，{}，{}", sql, id, currentTime,ip);
		}

		int result = 0;
		try {
			result = update(sql, 
					String.valueOf(currentTime), 
					ip,
					String.valueOf(id)
				);

			if (CommonConfig.isDebug()) {
				log.debug("更新数据：{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}
	
	/**
	 * 注册，写入数据库
	 * 
	 * @param userEntity 用户信息
	 * @return 影响行数
	 * @throws SQLException 数据库错误
	 */
	public boolean writeUser(UserEntity userEntity) throws SQLException {
		String sql = "INSERT INTO `t_user`" 
				+ "(`account`,`role`, `passwd`, `nick`, `create_timestamp`, `create_by`,`avatar`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		if (CommonConfig.isDebug()) {
			log.debug("注册：{}，{}", sql, userEntity.toString());
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
				log.debug("插入数据：{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}

	/**
	 * 更新资料
	 * @param userEntity 用户资料
	 * @return true 成功 
	 * @throws SQLException
	 * @time 2023-5-5 22:01:47
	 */
	public boolean UpdateProfile(UserEntity userEntity) throws SQLException {
		String sql = "UPDATE `t_user` " + 
				"SET `nick` = ?, `signature` = ?, `sex` = ?, `email` = ?, `phone` = ?, `avatar` = ?,`update_timestamp`=?,`update_by`=? " + 
				"WHERE `id` = ?";

		if (CommonConfig.isDebug()) {
			log.debug("更新个人资料：{}，{}", sql, userEntity);
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
				log.debug("更新数据：{}", result);
			}
		} finally {
			close();
		}
		return result > 0;
	}
}
