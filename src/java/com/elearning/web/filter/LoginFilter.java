package com.elearning.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;

import com.elearning.web.util.LanqiaoConstants;

/**
 * 
 * TODO 登录过滤器
 * 
 * @author xinglt
 * @date 2014年9月10日 下午1:56:32
 *
 */
public class LoginFilter implements Filter {

	protected static final Log log = org.apache.commons.logging.LogFactory.getLog(LoginFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		chain.doFilter(request, response);
		return;

	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {


		// 将网站的静态域名放入application
		filterconfig.getServletContext().setAttribute("staticurl", LanqiaoConstants.staticurl);
		filterconfig.getServletContext().setAttribute("webhost", LanqiaoConstants.webhost);
		filterconfig.getServletContext().setAttribute("uploadurl", LanqiaoConstants.uploadurl);

	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub
		
	}

}
