package minuhy.xiaoxiang.lectopic.entity;


/**
 * 用户类
 * 
 * @author y17mm
 * @time 2023-4-30 10:14:19
 * @version 1.0
 */
public class UserEntity {
	private int id; // ID，自动生成，唯一
    private int active; // 账号是否激活：1激活，0禁用
    private String account; // 账号，登录用，不可修改
    private String passwd; // 密码，登录用，MD5加密
    private int role; // 角色，0：普通，1：管理员
    private String nick; // 昵称
    private String signature; // 签名
    private String email; // 电子邮箱
    private String phone; // 手机号
    private int sex; // 性别，0：未设置，1：男，2：女
    private String avatar; // 头像ID
    private int hasNewMsg; // 新消息计数
    private int createBy; // 创建者ID
    private int updateBy; // 更新者ID
    private long createTimestamp; // 创建时间
    private long updateTimestamp; // 最后修改时间
    private long lastLoginTimestamp; // 最后登录时间
    private String lastLoginIp; // 最后登录的IP
    private String remark; // 备注

    public int getId() {
        return id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getHasNewMsg() {
        return hasNewMsg;
    }

    public void setHasNewMsg(int hasNewMsg) {
        this.hasNewMsg = hasNewMsg;
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

    public long getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(long lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public UserEntity() {}

	public UserEntity(int id, int active, String account, String passwd, int role, String nick, String signature,
			String email, String phone, int sex, String avatar, int hasNewMsg, int createBy, int updateBy,
			long createTimestamp, long updateTimestamp, long lastLoginTimestamp, String lastLoginIp, String remark) {
        this.id = id;
        this.active = active;
        this.account = account;
        this.passwd = passwd;
        this.role = role;
        this.nick = nick;
        this.signature = signature;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.avatar = avatar;
        this.hasNewMsg = hasNewMsg;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.lastLoginTimestamp = lastLoginTimestamp;
        this.lastLoginIp = lastLoginIp;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", active=" + active +
                ", account='" + account + '\'' +
                ", passwd='" + passwd + '\'' +
                ", role=" + role +
                ", nick='" + nick + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", avatar=" + avatar +
                ", hasNewMsg=" + hasNewMsg +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createTimestamp=" + createTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                ", lastLoginTimestamp=" + lastLoginTimestamp +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

