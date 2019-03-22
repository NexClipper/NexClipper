package com.nexcloud.rdb.domain.mysql;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
public class User {
	private String userId;
	private String userName;
	private String userPasswd;
	private String upperUserId;
	private String companyName;
	private String deptName;
	private String phoneNum;
	private String accessIp;
	private String accessDate;
	private Integer userState;
	private String regdate;
	private String updtdate;

	public User(String userId) {
		this.userId = userId;
	}
	public User(String userId, String userPasswd) {
		this.userId = userId;
		this.userPasswd = userPasswd;
	}
	public User(String userId, String userPasswd, String companyName) {
		this.userId = userId;
		this.userPasswd = userPasswd;
		this.companyName = companyName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getUpperUserId() {
		return upperUserId;
	}
	public void setUpperUserId(String upperUserId) {
		this.upperUserId = upperUserId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public String getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}
	public Integer getUserState() {
		return userState;
	}
	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdtdate() {
		return updtdate;
	}
	public void setUpdtdate(String updtdate) {
		this.updtdate = updtdate;
	}
	public User(String userId, String userName, String userPasswd, String upperUserId, String companyName,
			String deptName, String phoneNum, String accessIp, String accessDate, Integer userState, String regdate,
			String updtdate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPasswd = userPasswd;
		this.upperUserId = upperUserId;
		this.companyName = companyName;
		this.deptName = deptName;
		this.phoneNum = phoneNum;
		this.accessIp = accessIp;
		this.accessDate = accessDate;
		this.userState = userState;
		this.regdate = regdate;
		this.updtdate = updtdate;
	}
}
