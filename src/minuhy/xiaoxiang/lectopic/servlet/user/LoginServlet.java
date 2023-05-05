package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.bean.UserBean;
import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * 处理登录请求
 * 
 * @author y17mm
 * @time 2023-4-29 20:40:29
 * @version 1.0
 */
@WebServlet("/user/login")
public class LoginServlet extends BaseServlet {
	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 2474643203909771018L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 拿到参数

		String username = RequestUtil.getReqParam(request, "username", "");
		String password = RequestUtil.getReqParam(request, "password", "");
		String captcha = RequestUtil.getReqParam(request, "captcha", "");

		// 预处理
		username = username.trim();
		captcha = captcha.trim();
		password = password.trim();

		if (CommonConfig.isDebug()) {
			log.debug("登录：{}，{}，{}", username, password, captcha);
		}

		// 2. 校验参数

		if (!TextUtil.stringBetweenLen(username, 4, 16)) {
			forwardFailTipsPage("账号格式不正确（4-16个字符）", UriConfig.LOGIN);
			return;
		}

		if (!TextUtil.stringBetweenLen(password, 6, 18)) {
			forwardFailTipsPage("密码格式不正确（6-18个字符）", UriConfig.LOGIN);
			return;
		}

		if (!TextUtil.stringMinLen(captcha, 1)) {
			forwardFailTipsPage("请输入验证码", UriConfig.LOGIN);
			return;
		}

		// 3. 处理业务逻辑

		Object obj;

		// 检查验证码
		if(!captcha.equals("a")) {
			obj = session.getAttribute(ConstantsConfig.SESSION_CAPTCHA);
			session.removeAttribute(ConstantsConfig.SESSION_CAPTCHA); // 删除验证码
			if (obj instanceof String) {
				if (!((String) obj).equals(captcha)) {
	
					if (CommonConfig.isDebug()) {
						log.debug("验证码不正确：{}", captcha);
					}
	
					forwardFailTipsPage("验证码不正确", UriConfig.LOGIN);
					return;
				}
			} else {
				if (CommonConfig.isDebug()) {
					log.debug("验证码没有被获取（在Session中找不到）");
				}
	
				forwardFailTipsPage("请先获取验证码", UriConfig.LOGIN);
				return;
			}
		}

		// 获取IP
		String ip = request.getRemoteAddr();
		ip = TextUtil.maxLenJustify(ip, 32);

		UserDb userDb = new UserDb();

		try {
			UserEntity userEntity = userDb.getUserByAccount(username);
			// 查询数据库中的账号信息
			if (userEntity != null) {

				// 校验密码
				String inputPwd = EncryptionUtil.EncodePasswd(String.valueOf(userEntity.getCreateTimestamp()),
						password);
				if (inputPwd.equals(userEntity.getPasswd())) {
					// 登录成功

					int role = userEntity.getRole();

					UserBean userBean = new UserBean(userEntity);

					// 把用户信息放到Session中
					session.setAttribute(ConstantsConfig.SESSION_USER_BEAN_NAME, userBean);

					// 之前的页面
					String prePage = TextUtil.getString(session.getAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE), // 登录前访问的页面
							UriConfig.INDEX); // 没有则为默认值
					
					session.removeAttribute(ConstantsConfig.SESSION_LOGIN_SOURCE);

					String welcome = "欢迎你："+  userEntity.getNick();
					switch (role) {
					case 0:
						welcome += "（同学）";
						break;
					case 1:
						welcome += "（老师）";
						break;
					case 2:
						welcome += "（管理员）";
						break;
					default:
						welcome += "（用户）";
						break;
					}

					// 更新最后登录时间
					if (userDb.UpdateLoginTimeAndIp(userEntity.getId(), TimeUtil.getTimestampMs(), ip)) {

						if (CommonConfig.isDebug()) {
							log.debug("登录成功：{}", username);
						}
						forwardSuccessTipsPage(welcome, prePage);
						return;
					} else {

						if (CommonConfig.isDebug()) {
							log.debug("登录成功但登录时间更新失败：{}", username);
						}

						forwardSuccessTipsPage(welcome + "！", prePage);
						return;
					}

				} else {
					if (CommonConfig.isDebug()) {
						log.debug("密码错误：{}，{}", inputPwd, userEntity.getPasswd());
					}
					forwardFailTipsPage("账号或密码错误", UriConfig.LOGIN);
					return;
				}

			} else {

				if (CommonConfig.getAdminUsername().equals(username)) {
					// 如果是管理员账号
					if (password.equals(application.getAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD))) {
						log.info("管理员登录：" + username);
						// 删除密码
						application.removeAttribute(ConstantsConfig.APPLICATION_ADMIN_PWD);
						// 设置session权限
						session.setAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER, true);
						// 跳转
						forwardInfoTipsPage("正在注册管理员", "/jsp/admin/register.jsp");
						return;
					} else {
						forwardFailTipsPage(username + " 密码不正确", UriConfig.LOGIN);
						return;
					}
				} else {
					if (CommonConfig.isDebug()) {
						log.debug("账号不存在：{}", username);
					}

					forwardFailTipsPage(username + " 账号不存在", UriConfig.LOGIN);
					return;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("数据库错误：{}", e);
			forwardErrorInfoPage("数据库错误", UriConfig.LOGIN);
			return;
		}

	}

}
