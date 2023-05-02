package minuhy.xiaoxiang.lectopic.bean.admin;

import minuhy.xiaoxiang.lectopic.bean.common.BaseBean;
import minuhy.xiaoxiang.lectopic.bean.common.UpdateDataBean;
import minuhy.xiaoxiang.lectopic.database.AdminDb;

/**
 * ����Ա��ҳ��Ҫ������
 * 
 * @author y17mm
 * @time 2023-5-1 18:37:19
 * @version 1.0
 */
public class AdminIndexBean extends BaseBean implements UpdateDataBean{
	/**
	 * ѧ������
	 */
	private int studentCount;
	/**
	 * ��ʦ����
	 */
	private	int teacherCount;
	/**
	 * ѡ������
	 */
	private	int topicCount;
	/**
	 * ��ѡ����
	 */
	private	int selectedCount;
	
	public AdminIndexBean() {
		// 10�����һ������
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
