package com.gtt.kenshin.oauth;

/**
 * @author tiantiangao
 */
public class ThirdUserInfo {

	/**
	 * 第三方用户的access token
	 */
	String accessToken;

	/**
	 * 第三方用户的ID
	 */
	String thirdUserId;


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getThirdUserId() {
		return thirdUserId;
	}

	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}
}
