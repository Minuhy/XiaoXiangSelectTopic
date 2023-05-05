package minuhy.xiaoxiang.lectopic.servlet.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minuhy.xiaoxiang.lectopic.config.CommonConfig;
import minuhy.xiaoxiang.lectopic.database.UserDb;
import minuhy.xiaoxiang.lectopic.servlet.common.BaseUserServlet;
import minuhy.xiaoxiang.lectopic.util.EncryptionUtil;
import minuhy.xiaoxiang.lectopic.util.RequestUtil;
import minuhy.xiaoxiang.lectopic.util.TextUtil;
import minuhy.xiaoxiang.lectopic.util.TimeUtil;

/**
 * 修改资料，用户资料
 * 
 * @author y17mm
 * @time 2023-5-5 19:54:53
 * @version 1.0
 */
@WebServlet("/user/password")
public class PasswordServlet extends BaseUserServlet {
	private static final Logger log = LoggerFactory.getLogger(PasswordServlet.class);
	private static final long serialVersionUID = -6430486950563263035L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passwordUrl = "/jsp/common/password.jsp";
		// 1. 拿到数据
		int userId = userBean.getId();

		String rawPassword = RequestUtil.getReqParam(request, "rawPassword", "").trim();
		String newPassword = RequestUtil.getReqParam(request, "newPassword", "").trim();
		String renewPassword = RequestUtil.getReqParam(request, "renewPassword", "").trim();
		
		if(CommonConfig.isDebug()) {
			log.debug("修改密码：{}，{}，{}",rawPassword,newPassword,renewPassword);
		}
		
		// 2. 校验数据
		
		// 原密码
		if(!TextUtil.stringMinLen(rawPassword, 1)) {
			if(CommonConfig.isDebug()) {
				log.debug("原密码不能为空，请重新操作：{}",rawPassword);
			}
			forwardFailTipsPage("原密码不能为空，请重新操作", passwordUrl);
			return;
		}
		
		// 新密码
		if(!TextUtil.stringBetweenLen(newPassword, 6, 20)) {
			if(CommonConfig.isDebug()) {
				log.debug("新密码长度不正确（6-20字），请重新操作：{}",newPassword);
			}
			forwardFailTipsPage("新密码长度不正确（6-20字），请重新操作", passwordUrl);
			return;
		}
		// 判断密码是否一致
		if(!newPassword.equals(renewPassword)) {
			if(CommonConfig.isDebug()) {
				log.debug("输入的新密码不一致，请重新操作：{}，{}",newPassword,renewPassword);
			}
			forwardFailTipsPage("输入的新密码不一致，请重新操作", passwordUrl);
			return;
		}
		
		// 3. 处理业务，预处理，写入数据库
		
		// 加密密码
		rawPassword = EncryptionUtil.EncodePasswd(userBean.getCreateTimestamp(), rawPassword);
		newPassword = EncryptionUtil.EncodePasswd(userBean.getCreateTimestamp(), newPassword);
		
		if(CommonConfig.isDebug()) {
			log.debug("加密密码：{}，{}",rawPassword,newPassword);
		}
		
		long updateTime = TimeUtil.getTimestampMs();
		
		UserDb userDb = new UserDb();
		
		try {
			if(userDb.updatePasswd(userId, rawPassword, newPassword)) {
				// 更新缓存密码
				userBean.getUserEntity().setPasswd(newPassword);
				// 更新数据更新时间
				userDb.updateUpdateTimestamp(userId, updateTime);
				// 写入成功
				forwardSuccessTipsPage("修改成功", passwordUrl);
				return;
			} else {
				forwardFailTipsPage("原密码错误", passwordUrl);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("数据库错误：{}", e);
			forwardErrorInfoPage("数据库错误", passwordUrl);
			return;
		}
		
	}
	

}
