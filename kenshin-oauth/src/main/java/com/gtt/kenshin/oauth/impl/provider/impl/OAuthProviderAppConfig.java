package com.gtt.kenshin.oauth.impl.provider.impl;


import com.gtt.kenshin.oauth.impl.provider.OAuthProviderApp;

/**
 * @author tiantiangao
 */
public class OAuthProviderAppConfig implements OAuthProviderApp {

	private String clientID;
	private String clientSecret;

	@Override
	public String getClientID() {
		return clientID;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
