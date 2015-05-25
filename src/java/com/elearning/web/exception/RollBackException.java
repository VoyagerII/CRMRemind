package com.elearning.web.exception;

/**
 * 
 * TODO 异常回滚
 * 
 * @author xinglt
 * @date 2014年8月2日 下午3:32:03
 *
 */
public class RollBackException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -943644670442185867L;

	public RollBackException() {

		super();
	}

	public RollBackException(String message, Throwable cause) {

		super(message, cause);
	}

	public RollBackException(String message, String method) {

		super(message);
	}

	public RollBackException(Throwable cause) {

		super(cause);
	}

}
