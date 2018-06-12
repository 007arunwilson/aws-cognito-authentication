package com.cyberinfoscipter.scripts.lambda.cognito.model;

public class CognitoReq {
	private String developerProviderName;
	private String userId;
	
	public String getDeveloperProviderName() {
		return developerProviderName;
	}
	public void setDeveloperProviderName(String developerProviderName) {
		this.developerProviderName = developerProviderName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
