package minuhy.xiaoxiang.lectopic.database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.common.BaseDb;


public class AdminDb extends BaseDb{
	private static final Logger log = LoggerFactory.getLogger(AdminDb.class);

	/**
	 * 查找学生总数
	 * @return 学生总数，出错返回 -1
	 * @time 2023-5-1 19:19:50
	 */
	public int selectStudentCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_student`";

		if (CommonConfig.isDebug()) {
			log.debug("查找学生总数：{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("查找学生总数时出错：{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * 查找老师总数
	 * @return 老师总数，出错返回 -1
	 * @time 2023-5-1 19:20:19
	 */
	public int selectTeacherCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_teacher`";

		if (CommonConfig.isDebug()) {
			log.debug("查找老师总数：{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("查找老师总数时出错：{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * 查找选题总数
	 * @return 选题总数，出错返回 -1
	 * @time 2023-5-1 19:21:24
	 */
	public int selectTopicCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_topic`";

		if (CommonConfig.isDebug()) {
			log.debug("查找选题总数：{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("查找选题总数时出错：{}", e);
			}
			return -1;
		}
		return count;
	}

	/**
	 * 查找已选择的总数
	 * @return 已选择的总数，出错返回 -1
	 * @time 2023-5-1 19:23:11
	 */
	public int selectSelectedCount() {
		String sql = "SELECT COUNT(*) AS `count` FROM `t_select`";

		if (CommonConfig.isDebug()) {
			log.debug("查找已选择的总数：{}", sql);
		}
		int count = 0;
		try {
			count = getCount(sql);
		} catch (SQLException e) {
			if (CommonConfig.isDebug()) {
				log.error("查找已选择的总数时出错：{}", e);
			}
			return -1;
		}
		return count;
	}

}
