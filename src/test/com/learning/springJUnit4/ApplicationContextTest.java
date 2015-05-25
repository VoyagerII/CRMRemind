package com.learning.springJUnit4;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * TODO SpringJUnit4
 * 
 * @author xinglt
 * @date 2014年8月2日 下午7:07:33
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ApplicationContextTest extends AbstractTransactionalJUnit4SpringContextTests {

	static Logger log = LoggerFactory.getLogger(AbastractSpringJUnit4Test.class);

}
