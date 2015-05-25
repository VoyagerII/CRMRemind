package com.elearning.web.viewResolver;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 
 * TODO 多视图处理
 * 
 * @author xinglt
 * @date 2014年7月30日 下午6:20:11
 *
 */
public class MixedViewResolver implements ViewResolver {

	private Map<String, ViewResolver> resolvers;

	public void setResolvers(Map<String, ViewResolver> resolvers) {

		this.resolvers = resolvers;
	}

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {

		System.out.println("ViewResolver");

		ViewResolver viewResolver = null;

		int n = viewName.lastIndexOf(".");
		if (n != -1) {
			// 取出扩展名
			String suffix = viewName.substring(n + 1);

			// 取出相应的ViewResolver
			viewResolver = resolvers.get(suffix);

			if (viewResolver == null) {
				throw new RuntimeException("No ViewResolver for " + suffix);
			}

		} else {

			viewResolver = resolvers.get("html");

		}

		return viewResolver.resolveViewName(viewName, locale);
	}
}
