kenshin-oauth
=======
kenshin-oauth是封装的QQ和新浪微博的OAuth2.0登录相关流程


### 接口

com.gtt.kenshin.oauth.OAuthClient

```java  

	/**
	 * 构建用户授权的URL
	 *
	 * @param oauthType 第三方oauth类型
	 * @param redir 第三方授权通过后的重定向地址
	 * @param state 状态值
	 * @return
	 */
	String buildAuthorizeRequest(String oauthType, String redir, String state);
	
	
	/**
	 * 获取用户信息
	 *
	 * @param oauthType 第三方oauth类型
	 * @param request 授权完成后，重定向地址的request
	 * @param redir 重定向地址
	 * @return
	 */
	ThirdUserInfo getUserInfo(String oauthType, HttpServletRequest request, String redir);
```


### 模型
![模型](https://raw.githubusercontent.com/tiantiangao/kenshin/dev/kenshin-oauth/oauth.png)

<b>OAuthClient: </b> 负责对外提供服务  
<b>OAuthProvider: </b> 第三方OAuth提供者接口(目前内置有腾讯QQ和新浪微博的实现)  
该接口为第三方OAuth提供者的主要实现接口，主要包含以下两个功能:  
1. buildAuthorizeRequest: 构建相应的授权URL   
2. getUserInfo: 用户通过步骤1的授权URL完成登录及授权后，会重定向回当前应用，并带上code(一般10分钟有效), 本步骤解析code，并根据code获取access token和第三方ID  
注：腾讯QQ是分两步完成，先拿code换access token, 再拿openid；新浪微博是一步完成。(相关请求及解析已封装至组件中，有兴趣可以参考源码)

<b>OAuthProviderType: </b> 第三方OAuth提供者类型(包含名称、授权地址、获取access token地址等固定信息)  
<b>OAuthProviderApp: </b> 当前应用在第三方OAuth提供者所申请的应用(包含应用ID和应用Secret)  


### 使用

Tips: 建议通过spring来使用  

#### 1. 声明
```xml  

<!-- 声明OAuthClient -->  
<bean id="oAuthClient" class="com.gtt.kenshin.oauth.impl.OAuthClientDefaultImpl">
    <property name="providerList">
        <list>
            <ref bean="oAuthProviderQQ"/>
            <ref bean="oAuthProviderSina"/>
        </list>
    </property>
</bean>

<!-- 声明腾讯QQ的OAuth -->
<bean id="oAuthProviderQQ" class="com.gtt.kenshin.oauth.impl.provider.impl.OAuthProviderQQ">
    <property name="oAuthProviderApp" ref="oAuthProviderAppConfigQQ"/>
</bean>

<bean id="oAuthProviderAppConfigQQ" class="com.gtt.kenshin.oauth.impl.provider.impl.OAuthProviderAppConfig">
    <property name="clientID" value="${oauth.qq.appid}"/>
    <property name="clientSecret" value="${oauth.qq.appsecret}" />
</bean>

<!-- 声明新浪微博的OAuth -->
<bean id="oAuthProviderSina" class="com.gtt.kenshin.oauth.impl.provider.impl.OAuthProviderSina">
    <property name="oAuthProviderApp" ref="oAuthProviderAppConfigSina"/>
</bean>

<bean id="oAuthProviderAppConfigSina" class="com.gtt.kenshin.oauth.impl.provider.impl.OAuthProviderAppConfig">
    <property name="clientID" value="${oauth.sina.appid}"/>
    <property name="clientSecret" value="${oauth.sina.appsecret}" />
</bean>

```

#### 2. 获取授权URL, 并将用户重定向

```java  
String type = "qq"; // type="sina";
String authorizeUri = oAuthClient.buildAuthorizeRequest(type, redir, RandomStringUtils.random(8));
redirect(authorizeUri);
```

#### 3. 解析授权完成后的code信息，并获取access token和第三方用户ID

```java  
ThirdUserInfo thirdUserInfo = oAuthClient.getUserInfo(type, request, redir);  
```  
