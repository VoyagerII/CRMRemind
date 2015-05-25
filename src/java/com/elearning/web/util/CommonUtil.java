package com.elearning.web.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.elearning.bean.UserBean;
import com.elearning.dao.BaseDao;
import com.elearning.entity.Admin;
import com.elearning.service.impl.LoginService;
import com.elearning.web.applicationContext.ApplicationContextHolder;

public class CommonUtil {

	/**
	 * 根据用户的访问, 得到当前请求的URL
	 * 
	 * @param request
	 * @return 末尾带 / http://lanqiaobei.net:8080/lanqiaobeiWeb/
	 * 
	 */
	public static String getHostNameURL(HttpServletRequest request) {

		String sret = "";
		sret += request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 80)
			sret += ":" + request.getServerPort();
		if (!"/".equals(request.getContextPath()))
			sret += request.getContextPath() + "/";
		else
			sret += request.getContextPath();
		return sret;
	}

	public static String getHostNameURLNoPath(HttpServletRequest request) {

		String sret = "";
		sret += request.getScheme() + "://" + request.getServerName();
		if (request.getServerPort() != 80)
			sret += ":" + request.getServerPort();
		return sret;
	}

	/**
	 * 发送邮件方法
	 * 
	 * @param model
	 *            邮件模版
	 * @param msg
	 *            邮件内容
	 * @param sendto
	 *            接收的用户
	 * @param subject
	 *            邮件标题
	 * @return
	 */
	public static boolean sendMail(int model, String msg, String sendto, String subject) {

		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(ConstantsUtil.mailserver);
		mailInfo.setMailServerPort(ConstantsUtil.mailserverport);
		mailInfo.setValidate(true);
		mailInfo.setUserName(ConstantsUtil.mailuser); // 发送人的邮箱
		mailInfo.setPassword(ConstantsUtil.mailpwd); // 您的邮箱密码
		mailInfo.setFromAddress(ConstantsUtil.mailuser); // 发送人的邮箱
		mailInfo.setToAddress(sendto); // 接收的邮箱
		mailInfo.setSubject(subject);
		mailInfo.setContent(msg);

		return MailSender.sendHtmlMail(mailInfo);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static UserBean getUserInfo(HttpServletRequest request) {

		UserBean userBean = null;
		Map userMap = CookieUtil.getUserFromCookie(request);
		if (null != userMap) {
			Long userId = (Long) userMap.get("userId");
			if (null != userId) {
				// cookies中userId不为空
				userBean = (UserBean) getInfoBySession(request, DictionaryCacheKeys.User_Info);
				if (null == userBean) {
					userBean = new UserBean();
					LoginService loginService = new LoginService();
					loginService.setBaseDao((BaseDao) ApplicationContextHolder.getBean("baseDao"));
					Admin admin = loginService.selectAdminByPrimaryKey(Integer.parseInt("" + userId));
					if (null != admin) {
						userBean.setAdmin(admin);
						userBean.setDepartmentname(loginService.selectDepartmentByPrimaryKey(admin.getDepartmentId()).getDepartmentName());//获取部门名称
						CommonUtil.setInfoBySession(request, DictionaryCacheKeys.User_Info, userBean);
					}
				}
			}
		}
		return userBean;
	}

	/**
	 * 根据key获取用户在session中的object
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	public static Object getInfoBySession(HttpServletRequest request, String key) {

		return request.getSession().getAttribute(key);
	}

	/**
	 * 根据key设置将信息存入session中
	 * 
	 * @param request
	 * @param us
	 * @param ru
	 * @return
	 */
	public static void setInfoBySession(HttpServletRequest request, String key, Object value) {

		try {
			request.getSession().setAttribute(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
