package com.elearning.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.data.engine.executor.cache.Md5Util;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.elearning.bean.UserBean;
import com.elearning.bean.output.base.BaseResult;
import com.elearning.bean.output.base.ResultCode;
import com.elearning.bean.output.login.LoginBean;
import com.elearning.entity.Admin;
import com.elearning.service.impl.LoginService;
import com.elearning.web.util.CommonServiceUtil;
import com.elearning.web.util.CommonUtil;
import com.elearning.web.util.CookieUtil;
import com.elearning.web.util.DictionaryCacheKeys;
import com.elearning.web.util.HttpRequestUtils;
import com.elearning.web.util.LanqiaoConstants;
import com.elearning.web.util.LoggerUtil;
import com.elearning.web.util.NullUtil;

public class LoginAction extends MultiActionController {

	private LoginService ls;

	private CaptchaImageAction ai;

	public void setLs(LoginService ls) {

		this.ls = ls;
	}

	public void setAi(CaptchaImageAction ai) {

		this.ai = ai;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		String adminLogin = ServletRequestUtils.getStringParameter(request, "adminLogin", "");// 用户名
		String adminPass = ServletRequestUtils.getStringParameter(request, "adminPass", "");// 密码
		String authcode = ServletRequestUtils.getStringParameter(request, "authcode", "");// 验证码
		LoggerUtil.DebugLogger.info("[login] [loginname=" + adminLogin + "] [pwd=" + adminPass + "] [authcode=" + authcode + "]");
		LoginBean loginBean = new LoginBean();
		if (NullUtil.isNull(adminLogin, adminPass, authcode)) {
			loginBean.setResultcode(ResultCode.ILLEGALPARA);
			loginBean.setResultmsg(ResultCode.getResultMsg(ResultCode.ILLEGALPARA));
			CommonServiceUtil.beanToJsonWrite(loginBean, response);
			return null;
		}
		String createcode = (String) request.getSession().getAttribute(ai.getSessionKeyValue());
		request.getSession().setAttribute(ai.getSessionKeyValue(), null);
		authcode = createcode;
		if (null == createcode ||(!authcode.equals(createcode))) {// 判断验证码是否输入正确
			loginBean.setResultcode(ResultCode.VERIFYCODEERRO);
			loginBean.setResultmsg(ResultCode.getResultMsg(ResultCode.VERIFYCODEERRO));
			CommonServiceUtil.beanToJsonWrite(loginBean, response);
			return null;
		}
		Admin admin = ls.selectLogin(adminLogin,Md5Util.getMD5(adminPass));
		if (null != admin) {
			admin.setAdminPass("");
			UserBean userBean = new UserBean();
			userBean.setAdmin(admin);
			userBean.setDepartmentname(ls.selectDepartmentByPrimaryKey(admin.getDepartmentId()).getDepartmentName());//获取部门名称
			if (this.setCookie(request, response, userBean)) {
				loginBean.setResultcode(ResultCode.SUCCESS);
				loginBean.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
				loginBean.setAdmin(admin);
			}else{
				loginBean.setResultcode(ResultCode.COOKIEERROR);
				loginBean.setResultmsg(ResultCode.getResultMsg(ResultCode.COOKIEERROR));
			}
		}else{
			loginBean.setResultcode(ResultCode.USRPWILLEGAL);
			loginBean.setResultmsg(ResultCode.getResultMsg(ResultCode.USRPWILLEGAL));
		}
		CommonServiceUtil.beanToJsonWrite(loginBean, response);
		return null;
	}
	/**
	 * 检测用户名是否重复
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView loginnameRepeated(HttpServletRequest request, HttpServletResponse response) {
		String adminLogin = ServletRequestUtils.getStringParameter(request, "adminLogin", "");// 用户名
		LoggerUtil.DebugLogger.info("[loginnameRepeated] [loginname=" + adminLogin + "]");
		BaseResult br = new BaseResult();
		if (NullUtil.isNull(adminLogin)) {
			br.setResultcode(ResultCode.ILLEGALPARA);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.ILLEGALPARA));
			CommonServiceUtil.beanToJsonWrite(br, response);
			return null;
		}
		Admin admin = ls.selectAdminByadminLogin(adminLogin);
		if(NullUtil.isNull(admin)){
			br.setResultcode(ResultCode.SUCCESS);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}else{
			br.setResultcode(ResultCode.EMAILORMOBUSED);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.EMAILORMOBUSED));
		}
		CommonServiceUtil.beanToJsonWrite(br, response);
		return null;
	}
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView changeAdmin(HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = CommonUtil.getUserInfo(request);
		BaseResult br = new BaseResult();
		if(NullUtil.isNotNull(userBean)){
			br.setResultcode(ResultCode.NOLOGIN);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.NOLOGIN));
			CommonServiceUtil.beanToJsonWrite(br, response);
			return null;
		}
		Admin subAdmin = HttpRequestUtils.getBean(Admin.class, request);
		subAdmin.setAdminId(null);
		if(0 < ls.updateAdmin(subAdmin)){
			br.setResultcode(ResultCode.SUCCESS);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.SUCCESS));
		}else{
			br.setResultcode(ResultCode.UPADMINERROR);
			br.setResultmsg(ResultCode.getResultMsg(ResultCode.UPADMINERROR));
		}
		CommonServiceUtil.beanToJsonWrite(br, response);
		return null;
	}
	/**
	 * ModelAndView的例子
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView modelandviewDemo(HttpServletRequest request, HttpServletResponse response) {
		int pageno = ServletRequestUtils.getIntParameter(request, "pageno", 1);// 用户名
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		for (Integer i = 0; i < 10; i++) {
			if(i%2 == 0){
				list1.add(i.toString());
			}else{
				list2.add(i.toString());
			}
		}
		model.put("Resultcode", ResultCode.SUCCESS);
		model.put("Resultmsg", ResultCode.getResultMsg(ResultCode.SUCCESS));
		model.put("demodata1", list1);
		model.put("demodata2", list2);
		
		//下面是jsp分页  获取到分页信息和数据后 将信息放入map中
		model.put("demodata3", "当前是第"+pageno+"页，此处为数据的显示");
		model.put("pageno", pageno);
		model.put("totalPages", 10);
		return new ModelAndView("../pages/demo.jsp",model);
	}
	
	/**
	 * ModelAndView的例子
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView ajaxpagesDemo(HttpServletRequest request, HttpServletResponse response) {
		int pageno = ServletRequestUtils.getIntParameter(request, "pageno", 1);// 用户名
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("Resultcode", ResultCode.SUCCESS);
		model.put("Resultmsg", ResultCode.getResultMsg(ResultCode.SUCCESS));
		model.put("data", "当前是第"+pageno+"页，此处为数据的显示");
		
		//下面是jsp分页  获取到分页信息和数据后 将信息放入map中
		
		model.put("pageno", pageno);
		model.put("totalPages", 10);
		CommonServiceUtil.beanToJsonWrite(model, response);//此处应该返回bean  demo就不特意建了
		return null;
	}

	/**
	 * 注册或者登录时，设置用户的cookies
	 * 
	 * @param request
	 * @param response
	 * @param ru
	 */
	private boolean setCookie(HttpServletRequest request, HttpServletResponse response, UserBean userBean) {

		boolean ret = CookieUtil.setLoginUseridCookie(response, userBean.getAdmin().getAdminId(), LanqiaoConstants.webdomain);
		if (ret) {
			// 设置cookies成功，将admin存入session中
			CommonUtil.setInfoBySession(request, DictionaryCacheKeys.User_Info, userBean);
		}
		return ret;
	}
	
	/**
	 * 注销
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Map<String, Object> hsret = new HashMap<String, Object>();
		request.getSession().removeAttribute(DictionaryCacheKeys.User_Info);
		request.getSession().invalidate();
		CookieUtil.removeCookie(response, CookieUtil.COOKIE_KEY);
		response.setContentType("text/html; charset=utf-8");
		hsret.put("result", 1);
		CommonServiceUtil.beanToJsonWrite(hsret, response);
		return null;

	}
}
