package minuhy.xiaoxiang.lectopic.database.common;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * һЩ���ݿⳣ�ò�ѯ����
 * 
 * @author y17mm
 * @time 2023-5-1 19:16:04
 * @version 1.0
 */
public class BaseDb extends Executant {
	/**
	 * ��ȡ��ѯ��countֵ
	 * @param sql SQL���
	 * @return countֵ
	 * @throws SQLException SQL�쳣
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
