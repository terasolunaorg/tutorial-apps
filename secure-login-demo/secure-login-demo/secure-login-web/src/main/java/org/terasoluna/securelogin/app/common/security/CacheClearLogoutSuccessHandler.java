package org.terasoluna.securelogin.app.common.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

public class CacheClearLogoutSuccessHandler extends
		SimpleUrlLogoutSuccessHandler {

	@Inject
	AccountSharedService accountSharedService;

	public CacheClearLogoutSuccessHandler(String defaultTargetURL) {
		this.setDefaultTargetUrl(defaultTargetURL);
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication.getPrincipal() instanceof LoggedInUser) {
			LoggedInUser details = (LoggedInUser) authentication.getPrincipal();
			accountSharedService.clearPasswordValidationCache(details
					.getUsername());
		}
		super.onLogoutSuccess(request, response, authentication);
	}
}
