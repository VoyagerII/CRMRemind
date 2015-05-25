package com.elearning.web.inteceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * TODO 登录拦截器
 * 
 * @author xinglt
 * @date 2014年7月29日 下午8:00:31
 *
 */
public class LoginInteceptor implements HandlerInterceptor {

	/**
	 * 最后执行，用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object object, Exception exception) throws Exception {

		System.out.println("afterCompletion");
	}

	/**
	 * 生成视图之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

		System.out.println("postHandle");
	}

	/**
	 * Action之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
			throws Exception {

		System.out.println("preHandle");

		return true;
	}

}
