package minuhy.xiaoxiang.lectopic.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.config.ConstantsConfig;
import minuhy.xiaoxiang.lectopic.config.RoleConfig;
import minuhy.xiaoxiang.lectopic.config.UriConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.entity.UserEntity;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

@WebServlet("/admin/register")
public class RegisterServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(RegisterServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. 查权限
		Object obj = session.getAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
		if (!(obj instanceof Boolean && ((Boolean) obj))) {
			forwardInfoTipsPage("没有权限访问", UriConfig.LOGIN);
			return;
		}
		
		// 1. 拿到参数
		String password = RequestUtil.getReqParam(request, "password", "");
		String rePassword = RequestUtil.getReqParam(request, "repasswd", "");

		// 预处理
		rePassword = rePassword.trim();
		password = password.trim();

		if (CommonConfig.isDebug()) {
			log.debug("管理员注册：{}，{}，{}", password, rePassword);
		}

		// 2. 校验参数

		if (!password.equals(rePassword)) {
			forwardFailTipsPage("两次密码不一致", UriConfig.REGISTER);
			return;
		}

		if (!TextUtil.stringBetweenLen(password, 6, 18)) {
			forwardFailTipsPage("密码格式不正确（6-18个字符）", UriConfig.REGISTER);
			return;
		}

		// 3. 处理业务逻辑

		UserDb userDb = new UserDb();
		try {
			UserEntity userEntity = userDb.getUserByAccount(CommonConfig.getAdminUsername());
			// 查询数据库中的账号信息
			if (userEntity == null) {

				long timeMs = TimeUtil.getTimestampMs();

				// 加密密码
				String inputPwd = EncryptionUtil.EncodePasswd(String.valueOf(timeMs), password);

				// 设置资料
				userEntity = new UserEntity();
				userEntity.setAccount(CommonConfig.getAdminUsername());
				userEntity.setRole(RoleConfig.ADMIN);
				userEntity.setPasswd(inputPwd);
				userEntity.setNick(CommonConfig.getAdminUsername());
				userEntity.setCreateTimestamp(timeMs);
				userEntity.setCreateBy(0);
				userEntity.setAvatar("h000.png");

				// 存入数据库
				if (userDb.writeUser(userEntity)) {
					session.removeAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
					forwardSuccessTipsPage("管理员账号注册成功", UriConfig.LOGIN);
					return;
				} else {
					forwardFailInfoPage("管理员账号注册失败（写入数据库出错）", UriConfig.REGISTER);
					return;
				}

			} else {
				if (CommonConfig.isDebug()) {
					log.debug("管理员账号已注册：{}", CommonConfig.getAdminUsername());
				}
				session.removeAttribute(ConstantsConfig.SESSION_ADMIN_REGISTER);
				forwardFailInfoPage("管理员账号已注册", UriConfig.LOGIN);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("数据库错误：{}", e);
			forwardErrorInfoPage("数据库错误", UriConfig.LOGIN);
			return;
		}

	}

}
