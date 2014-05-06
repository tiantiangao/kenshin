package com.gtt.kenshin.oauth.impl;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.gtt.kenshin.oauth.OAuthClient;
import com.gtt.kenshin.oauth.ThirdUserInfo;
import com.gtt.kenshin.oauth.impl.provider.OAuthProvider;
import com.gtt.kenshin.oauth.impl.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tiantiangao
 */
public class OAuthClientDefaultImpl implements OAuthClient {

	private Map<String, OAuthProvider> providerMap;

	@Override
	public String buildAuthorizeRequest(String oauthType, String redir, String state) {
		Preconditions.checkArgument(StringUtils.isNotBlank(oauthType), "oauthType must not be empty");
		Preconditions.checkArgument(providerMap.containsKey(oauthType), "oauthType must exist");

		return providerMap.get(oauthType).buildAuthorizeRequest(redir, state);
	}

	@Override
	public ThirdUserInfo getUserInfo(String oauthType, HttpServletRequest request, String redir) {
		Preconditions.checkArgument(StringUtils.isNotBlank(oauthType), "oauthType must not be empty");
		Preconditions.checkArgument(providerMap.containsKey(oauthType), "oauthType must exist");

		return providerMap.get(oauthType).getUserInfo(request, redir);
	}

	public void setProviderList(List<OAuthProvider> providerList) {
		Preconditions.checkArgument(!CollectionUtils.isEmpty(providerList));

		providerMap = Maps.uniqueIndex(providerList, new Function<OAuthProvider, String>() {
			@Override
			public String apply(OAuthProvider provider) {
				return provider.getProviderType().getName();
			}
		});
	}
}
