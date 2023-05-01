package minuhy.xiaoxiang.lectopic.entity;

/**
 * 老师
 * 
 * @author y17mm
 * @time 2023-4-30 18:15:55
 * @version 1.0
 */
public class TeacherEntity {
    /**
    * 绑定的用户id
    */
    private int userId;

    /**
    * 主键，唯一
    */
    private int id;

    /**
    * 是否激活：1激活，0禁用
    */
    private int active;

    /**
    * 姓名
    */
    private String name;

    /**
    * 职称
    */
    private String title;

    /**
    * 院系
    */
    private String college;

    /**
    * 教研室
    */
    private String section;

    /**
    * 个人简介
    */
    private String profile;

    /**
    * 创建者
    */
    private int createBy;

    /**
    * 更新者
    */
    private int updateBy;

    /**
    * 创建时间
    */
    private Long createTimestamp;

    /**
    * 最后修改时间
    */
    private Long updateTimestamp;

    /**
    * 备注
    */
    private String remark;
    

    public TeacherEntity(int id,int userId,  int active, String name, String title, String college, String section, String profile, int createBy, int updateBy, Long createTimestamp, Long updateTimestamp, String remark) {
        this.userId = userId;
        this.id = id;
        this.active = active;
        this.name = name;
        this.title = title;
        this.college = college;
        this.section = section;
        this.profile = profile;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getCollege() {
		return college;
	}



	public void setCollege(String college) {
		this.college = college;
	}



	public String getSection() {
		return section;
	}



	public void setSection(String section) {
		this.section = section;
	}



	public String getProfile() {
		return profile;
	}



	public void setProfile(String profile) {
		this.profile = profile;
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



	public Long getCreateTimestamp() {
		return createTimestamp;
	}



	public void setCreateTimestamp(Long createTimestamp) {
		this.createTimestamp = createTimestamp;
	}



	public Long getUpdateTimestamp() {
		return updateTimestamp;
	}



	public void setUpdateTimestamp(Long updateTimestamp) {
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
        return "TeacherEntity{" +
                "userId=" + userId +
                ", id=" + id +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", college='" + college + '\'' +
                ", section='" + section + '\'' +
                ", profile='" + profile + '\'' +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTimestamp=" + createTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                ", remark='" + remark + '\'' +
                '}';
    }
    
}
