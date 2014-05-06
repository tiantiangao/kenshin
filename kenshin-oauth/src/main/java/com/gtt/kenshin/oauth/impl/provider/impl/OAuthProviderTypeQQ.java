package com.gtt.kenshin.oauth.impl.provider.impl;

import com.gtt.kenshin.oauth.impl.provider.OAuthProviderType;

/**
 * 腾讯QQ OAuth
 *
 * @author tiantiangao
 */
public class OAuthProviderTypeQQ implements OAuthProviderType {

	@Override
	public String getName() {
		return "qq";
	}

	@Override
	public String getAuthorizeUri() {
		return "https://graph.qq.com/oauth2.0/authorize";
	}

	@Override
	public String getAccessTokenUri() {
		return "https://graph.qq.com/oauth2.0/token";
	}

}
