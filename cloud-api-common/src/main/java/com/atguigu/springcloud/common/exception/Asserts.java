package com.atguigu.springcloud.common.exception;

/**
 * @description:
 * @author: jiangyuan
 * @date: 2020/11/13 11:26
 */
public class Asserts {

	public static void fail(String message) {
		throw new ServiceException(message);
	}

	public static void fail(boolean condition, String message) {
		if (condition) {
			throw new ServiceException(message);
		}
	}
}
