/**
 * Copyright(C) 2019 Hangzhou Differsoft Co., Ltd. All rights reserved.
 *
 */
package com.xiaogang.springboot.common;

import com.differ.jackyun.framework.component.basic.dto.JackYunResult;
import com.differ.jackyun.framework.component.basic.dto.PageInfo;

/**
 * @since 2019年2月28日 下午8:05:07
 * @author xiaog
 *
 */
public class ResultPackage<T> extends JackYunResult<T> {

	/** 构造 */
	public ResultPackage() {

	}

	/** 构造 */
	public ResultPackage(T data) {
		this.setData(data);
	}

	/** 构造 */
	public ResultPackage(T data, PageInfo pageInfo) {
		this.setData(data);
		this.setPageInfo(pageInfo);
	}

	/** 构造 */
	public ResultPackage(T data, PageInfo pageInfo, Long contextId) {
		this.setData(data);
		this.setPageInfo(pageInfo);
		this.setContextId(contextId + "");
	}

}
