package minuhy.xiaoxiang.lectopic.entity;

/**
 * 
 * 
 * @author y17mm
 * @time 2023-4-30 18:16:03
 * @version 1.0
 */
public class StudentEntity {
	/**
	    * �󶨵��û�id
	    */
	    private int userId;

	    /**
	    * ������Ψһ
	    */
	    private int id;

	    /**
	    * �Ƿ񼤻1���0����
	    */
	    private int active;

	    /**
	    * ѧ��
	    */
	    private String number;

	    /**
	    * ����
	    */
	    private String name;

	    /**
	    * �꼶���꣩
	    */
	    private String grade;

	    /**
	    * רҵ
	    */
	    private String major;

	    /**
	    * Ժϵ
	    */
	    private String college;

	    /**
	    * �༶
	    */
	    private String classes;

	    /**
	    * ������
	    */
	    private int createBy;

	    /**
	    * ������
	    */
	    private int updateBy;

	    /**
	    * ����ʱ��
	    */
	    private long createTimestamp;

	    /**
	    * ����޸�ʱ��
	    */
	    private long updateTimestamp;

	    /**
	    * ��ע
	    */
	    private String remark;
	    

	    public StudentEntity(int id,int userId,  int active, String number, String name, String grade, String major, String college, String classes, int createBy, int updateBy, long createTimestamp, long updateTimestamp, String remark) {
	        this.userId = userId;
	        this.id = id;
	        this.active = active;
	        this.number = number;
	        this.name = name;
	        this.grade = grade;
	        this.major = major;
	        this.college = college;
	        this.classes = classes;
	        this.createBy = createBy;
	        this.updateBy = updateBy;
	        this.createTimestamp = createTimestamp;
	        this.updateTimestamp = updateTimestamp;
	        this.remark = remark;
	    }

	    public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getMajor() {
			return major;
		}

		public void setMajor(String major) {
			this.major = major;
		}

		public String getCollege() {
			return college;
		}

		public void setCollege(String college) {
			this.college = college;
		}

		public String getClasses() {
			return classes;
		}

		public void setClasses(String classes) {
			this.classes = classes;
		}

		public int getCreateBy() {
			return createBy;
		}

		public void setCreateBy(int createBy) {
			this.createBy = createBy;
		}

		public int getUpdateBy() {
			return updateBy;
		}

		public void setUpdateBy(int updateBy) {
			this.updateBy = updateBy;
		}

		public long getCreateTimestamp() {
			return createTimestamp;
		}

		public void setCreateTimestamp(long createTimestamp) {
			this.createTimestamp = createTimestamp;
		}

		public long getUpdateTimestamp() {
			return updateTimestamp;
		}

		public void setUpdateTimestamp(long updateTimestamp) {
			this.updateTimestamp = updateTimestamp;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public int getId() {
			return id;
		}

		@Override
	    public String toString() {
	        return "StudentEntity{" +
	                "userId=" + userId +
	                ", id=" + id +
	                ", active=" + active +
	                ", number='" + number + '\'' +
	                ", name='" + name + '\'' +
	                ", grade='" + grade + '\'' +
	                ", major='" + major + '\'' +
	                ", college='" + college + '\'' +
	                ", classes='" + classes + '\'' +
	                ", createBy=" + createBy +
	                ", updateBy=" + updateBy +
	                ", createTimestamp=" + createTimestamp +
	                ", updateTimestamp=" + updateTimestamp +
	                ", remark='" + remark + '\'' +
	                '}';
	    }
	    
}
