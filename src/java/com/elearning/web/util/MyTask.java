package com.elearning.web.util;

import java.util.TimerTask;

public class MyTask extends TimerTask {

	private int count = 1;

	@SuppressWarnings("unused")
	private int day = 60 * 60 * 24;

	private int hour = 60 * 60;

	@Override
	public void run() {

		count++;
		// 每小时执行一次
		if (count % hour == 0) {}
	}

}
