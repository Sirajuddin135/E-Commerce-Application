package com.app.config;

public class AppConstants {
	public static final Long ADMIN_ID = 101L;
	public static final Long USER_ID = 102L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final String[] PUBLIC_URLS = { "/api/v1/auth/**", "/v3/api-docs", "/v2/api-docs", "/api", "/api/**",
			"/api/public", "/api/public/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**",
			"/swagger-ui.html" };

	public static final String[] ADMIN_URLS = { "/api/admin", "/api/admin/**" };
}
