/**
 * Copyright(C) 2019 Hangzhou Differsoft Co., Ltd. All rights reserved.
 *
 */
package com.xiaogang.springboot.model;

import java.io.Serializable;

/**
 * @since 2019年2月18日 下午3:35:13
 * @author xiaog
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键id */
	private Integer id;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String passWord;
	/** 性别 */
	private String userSex;
	/** 别名 */
	private String nickName;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param id
	 * @param userName
	 * @param passWord
	 * @param userSex
	 * @param nickName
	 */
	public User(Integer id, String userName, String passWord, String userSex, String nickName) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.userSex = userSex;
		this.nickName = nickName;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord
	 *            the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}

	/**
	 * @param userSex
	 *            the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", userSex=" + userSex + ", nickName=" + nickName + "]";
	}

}
