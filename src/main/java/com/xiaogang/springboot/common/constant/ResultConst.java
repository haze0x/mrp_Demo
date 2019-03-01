/**

 * Copyright(C) 2019 Hangzhou Differsoft Co., Ltd. All rights reserved.
 *
 */
package com.xiaogang.springboot.common.constant;

/**
 * @since 2019年2月28日 下午8:03:59
 * @author xiaog
 *
 */
public class ResultConst {

	/**
	 * 返回成功代码[code:200]
	 */
	public static final int OK_CODE = 200;
	public static final String OK_SUBCODE = "0000000000";
	public static final String OK_MESSAGE = "成功";

	/**
	 * 返回失败代码[code:500]
	 */
	public static final int ERROR_CODE = 500;
	public static final String ERROR_SUBCODE = "0100060000";
	public static final String ERROR_MESSAGE = "失败";

	/**
	 * 返回异常代码
	 */
	public static final int EXCEPTION_CODE = -1;
	public static final String EXCEPTION_SUBCODE = "0000010001";
	public static final String EXCEPTION_MESSAGE = "内部错误，请稍后重试";

	/**
	 * JackyunUserInfoUtil:获取当前用户信息失败
	 */
	public static final String NO_USER_SUBCODE = "0000010003";
	public static final String NO_USER_MESSAGE = "JackyunUserInfoUtil:获取当前用户信息失败";

	/**
	 * erp服务异常
	 */
	public static final String ERP_FEIGN_EXCEPTION_SUBCODE = "0000000004";
	public static final String ERP_FEIGN_EXCEPTION_MESSAGE = "ERP服务出现异常";

}
