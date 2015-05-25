package com.elearning.web.mybatis;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * TODO 类描述
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:33:02
 *
 */
@SuppressWarnings("rawtypes")
public class RunEnvironmentConfig implements InitializingBean, FactoryBean {

	private CompositeConfiguration configuration;

	private Configuration[] configurations;

	private String appEnvironment;

	public RunEnvironmentConfig() {

	}

	public RunEnvironmentConfig(Configuration configuration) {

		this.configuration = new CompositeConfiguration(configuration);
	}

	public void afterPropertiesSet() throws Exception {

		if (configuration == null && (configurations == null || configurations.length == 0)) {
			throw new IllegalArgumentException("no configuration object or location specified");
		}
		if (configuration == null) {
			configuration = new CompositeConfiguration();
		}
		if (configurations != null) {
			// 获取应用运行的环境 本地 测试服务器 还是正式服务器
			String envType = System.getProperty(appEnvironment, "localhost");

			int beginIndex = 0;

			// 测试服务器
			if ("test".equals(envType)) {
				beginIndex = 1;
			}
			// 正式服务器
			if ("online".equals(envType)) {
				beginIndex = 2;
			}
			for (int i = beginIndex; i < configurations.length; i++) {
				configuration.addConfiguration(configurations[i]);
			}
		}
	}

	public void setConfigurations(Configuration[] configurations) {

		this.configurations = configurations;
	}

	public Object getObject() throws Exception {

		return (configuration != null) ? ConfigurationConverter.getProperties(configuration) : null;
	}

	public Class getObjectType() {

		return java.util.Properties.class;
	}

	public boolean isSingleton() {

		return true;
	}

	public void setAppEnvironment(String appEnvironment) {

		this.appEnvironment = appEnvironment;
	}

}
