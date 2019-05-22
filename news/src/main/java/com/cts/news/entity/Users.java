package com.cts.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserDetails")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String userName;
	private String userMailId;
	private String userPassword;
	private String userProfession;
	private int userRoleId=2;
	private String userRoleName="ROLE_NEWSANALYST";
	private int userAvailabilityStatus = 1;

	public Users() {
		super(); // super() can be used to invoke immediate parent class
					// constructor.
	}

//	public Users(String userName, String userMailId, String userPassword, int userRoleId, int userAvailabilityStatus,
//			String userRoleName, String userProfession) {
//		super(); // super() can be used to invoke immediate parent class
//					// constructor.
//		this.userName = userName;
//		this.userMailId = userMailId;
//		this.userPassword = userPassword;
//		this.userRoleId = userRoleId;
//		this.userAvailabilityStatus = userAvailabilityStatus = 1;
//		this.userProfession = userProfession;
//		this.userRoleName = userRoleName;
//	}

	

	public String getUserRoleName() {
		return userRoleName;
	}

	public String getUserProfession() {
		return userProfession;
	}

	public void setUserProfession(String userProfession) {
		this.userProfession = userProfession;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public int getUserAvailabilityStatus() {
		return userAvailabilityStatus;
	}

	public void setUserAvailabilityStatus(int userAvailabilityStatus) {
		this.userAvailabilityStatus = userAvailabilityStatus;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}

	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userMailId=" + userMailId + ", userPassword="
				+ userPassword + ",userRoleId = " + userRoleId + ",userAvailabilityStatus = " + userAvailabilityStatus
				+ ",userProfession = " + userProfession + ",userRoleName = " + userRoleName + "]";
	}

}
