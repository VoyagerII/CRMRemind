package com.elearning.web.mybatis;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

@SuppressWarnings("rawtypes")
public class ConfigBean implements InitializingBean, FactoryBean {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private CompositeConfiguration configuration;

	private Configuration[] configurations;

	private String appEnv;

	public ConfigBean() {

	}

	public ConfigBean(Configuration configuration) {

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
			String userIndex = System.getProperty(appEnv, "0");
			log.info("appEnv:" + appEnv + " ============ " + "userIndex:" + userIndex);

			
			int beginIndex = 0;
			try
			{
				beginIndex = Integer.parseInt(userIndex);
			
			}catch(Exception se)
			{}
			
			for (int i = beginIndex; i < configurations.length; i++) {
				configuration.addConfiguration(configurations[i]);
				System.out.println("configuration.addConfiguration::"+i);
				break;
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

	public void setAppEnv(String appEnv) {

		this.appEnv = appEnv;
	}
}
