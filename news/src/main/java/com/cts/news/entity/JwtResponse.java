package com.cts.news.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {

	private String accessToken;
	private String userName;
	private String name;
	private String type = "Bearer";
	private Collection<? extends GrantedAuthority> role;
	private long userId;

	

	public JwtResponse() {
		super();
	}

	public JwtResponse(String accessToken, long userId, String userName,String name, Collection<? extends GrantedAuthority> role) {
		super();
		this.accessToken = accessToken;
		this.userId=userId;
		this.userName = userName;
		this.name=name;
		this.role = role;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Collection<? extends GrantedAuthority> getRole() {
		return role;
	}

	public void setRole(Collection<? extends GrantedAuthority> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "JwtResponse [accessToken=" + accessToken + ", userName=" + userName + ",name=" + name + ", type=" + type + ", role="
				+ role + "]";
	}

}
