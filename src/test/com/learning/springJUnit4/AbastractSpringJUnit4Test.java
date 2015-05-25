package com.learning.springJUnit4;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * TODO SpringJUnit4测试继承方法
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:52:51
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"classpath:test-session-config.xml", "classpath:springmvc-dispatcher-servlet.xml" })
public class AbastractSpringJUnit4Test extends AbstractTransactionalJUnit4SpringContextTests {

	static Logger log = LoggerFactory.getLogger(AbastractSpringJUnit4Test.class);

	@Override
	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {

		super.setDataSource(dataSource);
	}

	// @Autowired
	// private TestMainCtrl controller;

	// 这种方法适用于Springframework3.0，3.1换了handler的处理类。
	// @Autowired
	// private AnnotationMethodHandlerAdapter handlerAdapter;

	protected final MockHttpServletRequest request = new MockHttpServletRequest();

	protected final MockHttpServletResponse response = new MockHttpServletResponse();

}