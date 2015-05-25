package com.elearning.web.freemarker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

/**
 * 
 * TODO Freemarker过滤器
 * 
 * @author xinglt
 * @date 2014年7月24日 下午4:49:06
 *
 */
public class FreemarkerServletFilter extends FreemarkerServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean preTemplateProcess(HttpServletRequest request, HttpServletResponse response,
			Template template, TemplateModel data) throws ServletException, IOException {

		request.setAttribute("HOST", "http://localhost/");

		return super.preTemplateProcess(request, response, template, data);
	}
}
