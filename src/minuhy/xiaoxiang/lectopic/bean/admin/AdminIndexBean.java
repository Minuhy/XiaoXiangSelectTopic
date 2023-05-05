package minuhy.xiaoxiang.lectopic.bean.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final Logger log = LoggerFactory.getLogger(AdminIndexBean.class);
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
			log.error("��ѯ���ݿ�ʱ����{}",e);
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
