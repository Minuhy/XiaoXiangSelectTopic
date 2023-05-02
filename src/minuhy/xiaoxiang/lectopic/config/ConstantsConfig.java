package minuhy.xiaoxiang.lectopic.config;

public class ConstantsConfig {
	/**
	 * 用户数据，存在session中的用户数据名称
	 */
	public static final String SESSION_USER_BEAN_NAME = "sessionUserBean";

	/**
	 * 登录源，存在session中，用户登录后如果有这个会跳转到这里，然后删除
	 */
	public static final String SESSION_LOGIN_SOURCE = "sessionLoginSource";

	/**
	 * 保存在session中的验证码
	 */
	public static final String SESSION_CAPTCHA = "sessionCaptcha";

	/**
	 * 管理员注册，在session中有这个并且为true才允许权限
	 */
	public static final String SESSION_ADMIN_REGISTER = "sessionAdminRegister";
	

	/**
	 * 首页记录，只跳转一次到首页
	 */
	public static final String SESSION_INDEX_PAGE = "sessionIndexPage";

	/**
	 * 保存在request中的提示信息，状态
	 */
	public static final String REQUEST_TIPS_STATE = "requestTipsState";
	/**
	 * 保存在request中的提示信息，是否自动跳转
	 */
	public static final String REQUEST_TIPS_SKIP = "requestTipsSkip";
	/**
	 * 保存在request中的提示信息，信息
	 */
	public static final String REQUEST_TIPS_INFO = "requestTipsInfo";
	/**
	 * 保存在request中的提示信息，跳转的链接
	 */
	public static final String REQUEST_TIPS_URI = "requestTipsUri";

	/**
	 * 保存在application中的管理员密码，使用一次后删除
	 */
	public static final String APPLICATION_ADMIN_PWD = "appAdminPwd";
}
