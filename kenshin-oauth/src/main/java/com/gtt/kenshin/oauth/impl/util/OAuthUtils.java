package com.gtt.kenshin.oauth.impl.util;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiantiangao
 */
public final class OAuthUtils {

	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_SEPARATOR = "&";
	private static final String NAME_VALUE_SEPARATOR = "=";

	public static final String AUTH_SCHEME = OAuthConstants.OAUTH_HEADER_NAME;

	private static final Pattern OAUTH_HEADER = Pattern.compile("\\s*(\\w*)\\s+(.*)");
	private static final Pattern NVP = Pattern.compile("(\\S*)\\s*\\=\\s*\"([^\"]*)\"");

	public static final String MULTIPART = "multipart/";

	private static final String DEFAULT_CONTENT_CHARSET = ENCODING;

	/**
	 * Translates parameters into <code>application/x-www-form-urlencoded</code> String
	 *
	 * @param parameters parameters to encode
	 * @param encoding   The name of a supported
	 *                   <a href="../lang/package-summary.html#charenc">character
	 *                   encoding</a>.
	 * @return Translated string
	 */
	public static String format(final Collection<? extends Map.Entry<String, String>> parameters,
								final String encoding) {
		final StringBuilder result = new StringBuilder();
		for (final Map.Entry<String, String> parameter : parameters) {
			String value = parameter.getValue() == null ? null : parameter.getValue();
			if (!OAuthUtils.isEmpty(parameter.getKey()) && !OAuthUtils.isEmpty(value)) {
				final String encodedName = encode(parameter.getKey(), encoding);
				final String encodedValue = value != null ? encode(value, encoding) : "";
				if (result.length() > 0) {
					result.append(PARAMETER_SEPARATOR);
				}
				result.append(encodedName);
				result.append(NAME_VALUE_SEPARATOR);
				result.append(encodedValue);
			}
		}
		return result.toString();
	}

	private static String encode(final String content, final String encoding) {
		try {
			return URLEncoder.encode(content, encoding != null ? encoding : "UTF-8");
		} catch (UnsupportedEncodingException problem) {
			throw new IllegalArgumentException(problem);
		}
	}

	/**
	 * Read data from Input Stream and save it as a String.
	 *
	 * @param is InputStream to be read
	 * @return String that was read from the stream
	 */
	public static String saveStreamAsString(InputStream is) throws IOException {
		return toString(is, ENCODING);
	}

	/**
	 * Get the entity content as a String, using the provided default character set
	 * if none is found in the entity.
	 * If defaultCharset is null, the default "UTF-8" is used.
	 *
	 * @param is             input stream to be saved as string
	 * @param defaultCharset character set to be applied if none found in the entity
	 * @return the entity content as a String
	 * @throws IllegalArgumentException if entity is null or if content length > Integer.MAX_VALUE
	 * @throws java.io.IOException              if an error occurs reading the input stream
	 */
	public static String toString(final InputStream is, final String defaultCharset) throws IOException {
		if (is == null) {
			throw new IllegalArgumentException("InputStream may not be null");
		}

		String charset = defaultCharset;
		if (charset == null) {
			charset = DEFAULT_CONTENT_CHARSET;
		}
		Reader reader = new InputStreamReader(is, charset);
		StringBuilder sb = new StringBuilder();
		int l;
		try {
			char[] tmp = new char[4096];
			while ((l = reader.read(tmp)) != -1) {
				sb.append(tmp, 0, l);
			}
		} finally {
			reader.close();
		}
		return sb.toString();
	}

	/**
	 * Parse a form-urlencoded document.
	 */
	public static Map<String, Object> decodeForm(String form) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (!OAuthUtils.isEmpty(form)) {
			for (String nvp : form.split("\\&")) {
				int equals = nvp.indexOf('=');
				String name;
				String value;
				if (equals < 0) {
					name = decodePercent(nvp);
					value = null;
				} else {
					name = decodePercent(nvp.substring(0, equals));
					value = decodePercent(nvp.substring(equals + 1));
				}
				params.put(name, value);
			}
		}
		return params;
	}

	/**
	 * Return true if the given Content-Type header means FORM_ENCODED.
	 */
	public static boolean isFormEncoded(String contentType) {
		if (contentType == null) {
			return false;
		}
		int semi = contentType.indexOf(";");
		if (semi >= 0) {
			contentType = contentType.substring(0, semi);
		}
		return OAuthConstants.ContentType.URL_ENCODED.equalsIgnoreCase(contentType.trim());
	}

	public static String decodePercent(String s) {
		try {
			return URLDecoder.decode(s, ENCODING);
			// This implements http://oauth.pbwiki.com/FlexibleDecoding
		} catch (UnsupportedEncodingException wow) {
			throw new RuntimeException(wow.getMessage(), wow);
		}
	}

	/**
	 * Construct a &-separated list of the given values, percentEncoded.
	 */
	public static String percentEncode(Iterable values) {
		StringBuilder p = new StringBuilder();
		for (Object v : values) {
			String stringValue = toString(v);
			if (!isEmpty(stringValue)) {
				if (p.length() > 0) {
					p.append("&");
				}
				p.append(OAuthUtils.percentEncode(toString(v)));
			}
		}
		return p.toString();
	}

	public static String percentEncode(String s) {
		if (s == null) {
			return "";
		}
		try {
			return URLEncoder.encode(s, ENCODING)
					// OAuth encodes some characters differently:
					.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
			// This could be done faster with more hand-crafted code.
		} catch (UnsupportedEncodingException wow) {
			throw new RuntimeException(wow.getMessage(), wow);
		}
	}

	private static final String toString(Object from) {
		return (from == null) ? null : from.toString();
	}

	private static boolean isEmpty(Set<String> missingParams) {
		if (missingParams == null || missingParams.size() == 0) {
			return true;
		}
		return false;
	}

	public static String getAuthHeaderField(String authHeader) {

		if (authHeader != null) {
			Matcher m = OAUTH_HEADER.matcher(authHeader);
			if (m.matches()) {
				if (AUTH_SCHEME.equalsIgnoreCase(m.group(1))) {
					return m.group(2);
				}
			}
		}
		return null;
	}

	public static Map<String, String> decodeOAuthHeader(String header) {
		Map<String, String> headerValues = new HashMap<String, String>();
		if (header != null) {
			Matcher m = OAUTH_HEADER.matcher(header);
			if (m.matches()) {
				if (AUTH_SCHEME.equalsIgnoreCase(m.group(1))) {
					for (String nvp : m.group(2).split("\\s*,\\s*")) {
						m = NVP.matcher(nvp);
						if (m.matches()) {
							String name = decodePercent(m.group(1));
							String value = decodePercent(m.group(2));
							headerValues.put(name, value);
						}
					}
				}
			}
		}
		return headerValues;
	}

	/**
	 * Decodes the Basic Authentication header into a username and password
	 *
	 * @param authenticationHeader {@link String} containing the encoded header value.
	 *                             e.g. "Basic dXNlcm5hbWU6cGFzc3dvcmQ="
	 * @return a {@link String[]} if the header could be decoded into a non null username and password or null.
	 */
	public static String[] decodeClientAuthenticationHeader(String authenticationHeader) {
		if (authenticationHeader == null || "".equals(authenticationHeader)) {
			return null;
		}
		String[] tokens = authenticationHeader.split(" ");
		if (tokens == null) {
			return null;
		}
		if (tokens[0] != null && !"".equals(tokens[0])) {
			String authType = tokens[0];
			if (!authType.equalsIgnoreCase("basic")) {
				return null;
			}
		}
		if (tokens[1] != null && !"".equals(tokens[1])) {
			String encodedCreds = tokens[1];
			String decodedCreds = new String(Base64.decodeBase64(encodedCreds));
			if (decodedCreds.contains(":") && decodedCreds.split(":").length == 2) {
				String[] creds = decodedCreds.split(":");
				if (!OAuthUtils.isEmpty(creds[0]) && !OAuthUtils.isEmpty(creds[1])) {
					return decodedCreds.split(":");
				}
			}
		}
		return null;
	}

	/**
	 * Construct a WWW-Authenticate header
	 */
	public static String encodeOAuthHeader(Map<String, Object> entries) {
		StringBuffer sb = new StringBuffer();
		sb.append(OAuthConstants.OAUTH_HEADER_NAME).append(" ");
		for (Map.Entry<String, Object> entry : entries.entrySet()) {
			String value = entry.getValue() == null ? null : String.valueOf(entry.getValue());
			if (!OAuthUtils.isEmpty(entry.getKey()) && !OAuthUtils.isEmpty(value)) {
				sb.append(entry.getKey());
				sb.append("=\"");
				sb.append(value);
				sb.append("\",");
			}
		}

		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * Construct an Authorization Bearer header
	 */
	public static String encodeAuthorizationBearerHeader(Map<String, Object> entries) {
		StringBuffer sb = new StringBuffer();
		sb.append(OAuthConstants.OAUTH_HEADER_NAME).append(" ");
		for (Map.Entry<String, Object> entry : entries.entrySet()) {
			String value = entry.getValue() == null ? null : String.valueOf(entry.getValue());
			if (!OAuthUtils.isEmpty(entry.getKey()) && !OAuthUtils.isEmpty(value)) {
				sb.append(value);
			}
		}

		return sb.toString();
	}

	public static boolean isEmpty(String value) {
		return value == null || "".equals(value);
	}

	public static boolean hasEmptyValues(String[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		for (String s : array) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	public static String getAuthzMethod(String header) {
		if (header != null) {
			Matcher m = OAUTH_HEADER.matcher(header);
			if (m.matches()) {
				return m.group(1);

			}
		}
		return null;
	}

	public static Set<String> decodeScopes(String s) {
		Set<String> scopes = new HashSet<String>();
		if (!OAuthUtils.isEmpty(s)) {
			StringTokenizer tokenizer = new StringTokenizer(s, " ");

			while (tokenizer.hasMoreElements()) {
				scopes.add(tokenizer.nextToken());
			}
		}
		return scopes;

	}

	public static String encodeScopes(Set<String> s) {
		StringBuffer scopes = new StringBuffer();
		for (String scope : s) {
			scopes.append(scope).append(" ");
		}
		return scopes.toString().trim();

	}

	public static boolean isMultipart(HttpServletRequest request) {

		if (!"post".equals(request.getMethod().toLowerCase())) {
			return false;
		}
		String contentType = request.getContentType();
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase().startsWith(MULTIPART)) {
			return true;
		}
		return false;
	}


	public static boolean hasContentType(String requestContentType, String requiredContentType) {
		if (OAuthUtils.isEmpty(requiredContentType) || OAuthUtils.isEmpty(requestContentType)) {
			return false;
		}
		StringTokenizer tokenizer = new StringTokenizer(requestContentType, ";");
		while (tokenizer.hasMoreTokens()) {
			if (requiredContentType.equals(tokenizer.nextToken())) {
				return true;
			}
		}

		return false;
	}

}


