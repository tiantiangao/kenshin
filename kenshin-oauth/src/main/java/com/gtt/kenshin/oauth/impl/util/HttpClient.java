package com.gtt.kenshin.oauth.impl.util;

import com.gtt.kenshin.oauth.impl.exception.OAuthSystemException;
import com.gtt.kenshin.oauth.impl.request.HttpRequest;
import com.gtt.kenshin.oauth.impl.response.HttpResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.HttpMethod.GET;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.HttpMethod.POST;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.MARK_AND;
import static com.gtt.kenshin.oauth.impl.util.OAuthConstants.MARK_QUESTION;

/**
 * @author tiantiangao
 */
public class HttpClient {

	public static HttpResponse execute(HttpRequest request, String requestMethod) throws OAuthSystemException {
		return doExecute(request, requestMethod, false);
	}

	/**
	 * 执行Post请求时，将URL也按照GET方式来拼接(新浪的open api接口怎能做成这样，哎...)
	 *
	 * @param request
	 * @param requestMethod
	 * @return
	 * @throws OAuthSystemException
	 */
	public static HttpResponse executePostWithGetUri(HttpRequest request, String requestMethod)
			throws OAuthSystemException {
		return doExecute(request, requestMethod, true);
	}

	/**
	 * 执行http请求
	 *
	 * @param request
	 * @param requestMethod
	 * @param postAsGetUri
	 * @return
	 * @throws OAuthSystemException
	 */
	private static HttpResponse doExecute(HttpRequest request, String requestMethod, boolean postAsGetUri)
			throws OAuthSystemException {
		String responseBody = null;
		String contentType = null;
		int responseCode = 0;
		try {

			String uri = request.getUrl();
			if (requestMethod == GET || (requestMethod == POST && postAsGetUri)) {
				uri = buildGetUrl(uri, request);
			}

			URL url = new URL(uri);

			URLConnection c = url.openConnection();
			responseCode = -1;
			if (c instanceof HttpURLConnection) {
				HttpURLConnection httpURLConnection = (HttpURLConnection) c;

				if (request.getHeaders() != null) {
					for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
						httpURLConnection.addRequestProperty(header.getKey(), header.getValue());
					}
				}

				if (!OAuthUtils.isEmpty(requestMethod)) {
					httpURLConnection.setRequestMethod(requestMethod);
					if (requestMethod.equals(OAuthConstants.HttpMethod.POST)) {
						httpURLConnection.setDoOutput(true);
						OutputStream ost = httpURLConnection.getOutputStream();
						PrintWriter pw = new PrintWriter(ost);
						pw.print(request.getBody());
						pw.flush();
						pw.close();
					}
				} else {
					httpURLConnection.setRequestMethod(OAuthConstants.HttpMethod.GET);
				}

				httpURLConnection.connect();

				InputStream inputStream;
				responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 400 || responseCode == 401) {
					inputStream = httpURLConnection.getErrorStream();
				} else {
					inputStream = httpURLConnection.getInputStream();
				}

				responseBody = OAuthUtils.saveStreamAsString(inputStream);
				contentType = c.getContentType();
			}
		} catch (IOException e) {
			throw new OAuthSystemException(e);
		}

		return new HttpResponse(contentType, responseBody, responseCode);
	}

	private static String buildGetUrl(String uri, HttpRequest request) {
		boolean containsQuestionMark = uri.contains(MARK_QUESTION);
		StringBuffer url = new StringBuffer(uri);

		StringBuffer query = new StringBuffer(OAuthUtils.format(request.getHeaders().entrySet(), "UTF-8"));

		if (StringUtils.isNotBlank(query.toString())) {
			if (containsQuestionMark) {
				url.append(MARK_AND).append(query);
			} else {
				url.append(MARK_QUESTION).append(query);
			}
		}

		return url.toString();
	}

}

