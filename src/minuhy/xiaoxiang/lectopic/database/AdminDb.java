package minuhy.xiaoxiang.lectopic.database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.common.BaseDb;


public class AdminDb extends BaseDb{
	private static final Logger log = LoggerFactory.getLogger(AdminDb.class);

	/**
	 * ����ѧ������
	 * @return ѧ�������������� -1
	 * @time 2023-5-1 19:19:50
	 */
	public int selectStudentCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_student`";

		if (CommonConfig.isDebug()) {
			log.debug("����ѧ��������{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("����ѧ������ʱ����{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * ������ʦ����
	 * @return ��ʦ������������ -1
	 * @time 2023-5-1 19:20:19
	 */
	public int selectTeacherCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_teacher`";

		if (CommonConfig.isDebug()) {
			log.debug("������ʦ������{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("������ʦ����ʱ����{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * ����ѡ������
	 * @return ѡ�������������� -1
	 * @time 2023-5-1 19:21:24
	 */
	public int selectTopicCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_topic`";

		if (CommonConfig.isDebug()) {
			log.debug("����ѡ��������{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("����ѡ������ʱ����{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * ������ѡ�������
	 * @return ��ѡ��������������� -1
	 * @time 2023-5-1 19:23:11
	 */
	public int selectSelectedCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_select`";

		if (CommonConfig.isDebug()) {
			log.debug("������ѡ���������{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("������ѡ�������ʱ����{}", e);
			}
			return -1;
		}
		return count;
	}

}
