package minuhy.xiaoxiang.lectopic.bean.admin;

import minuhy.xiaoxiang.lectopic.bean.common.BaseBean;
import minuhy.xiaoxiang.lectopic.bean.common.UpdateDataBean;
import minuhy.xiaoxiang.lectopic.database.AdminDb;

/**
 * 管理员首页需要的内容
 * 
 * @author y17mm
 * @time 2023-5-1 18:37:19
 * @version 1.0
 */
public class AdminIndexBean extends BaseBean implements UpdateDataBean{
	/**
	 * 学生数量
	 */
	private int studentCount;
	/**
	 * 老师数量
	 */
	private	int teacherCount;
	/**
	 * 选题数量
	 */
	private	int topicCount;
	/**
	 * 已选数量
	 */
	private	int selectedCount;
	
	public AdminIndexBean() {
		// 10秒更新一次数据
		super(10);
	}

	@Override
	public boolean updateData() {
		if(!isUpdatable()) {
			return true;
		}
		
		AdminDb db = new AdminDb();
		try {
			studentCount = db.selectStudentCount();
			teacherCount = db.selectTeacherCount();
			topicCount = db.selectTopicCount();
			selectedCount = db.selectSelectedCount();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public int getStudentCount() {
		return studentCount;
	}

	public int getTeacherCount() {
		return teacherCount;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public int getSelectedCount() {
		return selectedCount;
	}
}
