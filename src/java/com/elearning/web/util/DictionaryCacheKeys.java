package com.elearning.web.util;

/**
 * 字典表数据常用类型，做ServiceUtil中的map中的key值 TODO 类描述
 * 
 * @author guocy
 * @date 2014年8月6日 下午3:41:31
 *
 */
public class DictionaryCacheKeys {

	/**
	 * 返回状态
	 */
	public enum returnStatus {
		SUCCESS("SUCCESS"), FAIL("FAIL");

		private String returnKey;

		private returnStatus(String returnKey) {

			this.returnKey = returnKey;
		}

		public String getReturnKey() {

			return this.returnKey;
		}
	}


	public final static String User_Info = "elearn_User_Info";

	public final static String User_MobileCode = "elearn_User_MobileCode";
}
