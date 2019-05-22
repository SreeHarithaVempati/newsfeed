package com.cts.news.entity;

import org.springframework.stereotype.Component;

@Component
public class SignUpStatus {
	private boolean status;
	private String error;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "SignupStatus [status=" + status + ", error=" + error + "]";
	}

	public boolean equals(SignUpStatus obj) {
		// TODO Auto-generated method stub
		if (this.status == obj.status)
			return true;
		else
			return false;
	}

}