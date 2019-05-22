package com.cts.news.entity;

public class ErrorResponse {

	private String timestamp;
	private int reasonCode;
	private String errorMessage;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String timestamp, int reasonCode, String errorMessage) {
		super();
		this.timestamp = timestamp;
		this.reasonCode = reasonCode;
		this.errorMessage = errorMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", reasonCode=" + reasonCode + ", errorMessage=" + errorMessage
				+ "]";
	}

}
