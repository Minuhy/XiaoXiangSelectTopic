package minuhy.xiaoxiang.lectopic.database.common;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 一些数据库常用查询操作
 * 
 * @author y17mm
 * @time 2023-5-1 19:16:04
 * @version 1.0
 */
public class BaseDb extends Executant {
	/**
	 * 获取查询的count值
	 * @param sql SQL语句
	 * @return count值
	 * @throws SQLException SQL异常
	 * @time 2023-5-1 19:16:01
	 */
	public int getCount(String sql) throws SQLException {
		try {
			ResultSet resultSet = query(sql);
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
		} finally {
			close();
		}
		return 0;
	}
}
