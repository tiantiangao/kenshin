package com.gtt.kenshin.oauth.impl.provider.impl;

import com.gtt.kenshin.oauth.impl.provider.OAuthProviderType;

/**
 * 新浪OAuth
 *
 * @author tiantiangao
 */
public class OAuthProviderTypeSina implements OAuthProviderType {

	@Override
	public String getName() {
		return "sina";
	}

	@Override
	public String getAuthorizeUri() {
		return "https://api.weibo.com/oauth2/authorize";
	}

	@Override
	public String getAccessTokenUri() {
		return "https://api.weibo.com/oauth2/access_token";
	}
}
