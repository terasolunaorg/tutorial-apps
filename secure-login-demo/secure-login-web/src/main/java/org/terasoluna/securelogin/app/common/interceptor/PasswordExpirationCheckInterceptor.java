package org.terasoluna.securelogin.app.common.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.terasoluna.securelogin.domain.model.Role;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

public class PasswordExpirationCheckInterceptor extends
		HandlerInterceptorAdapter {

	@Inject
	AccountSharedService accountSharedService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		Authentication authentication = (Authentication) request
				.getUserPrincipal();

		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				LoggedInUser userDetails = (LoggedInUser) principal;
				if ((userDetails.getAccount().getRoles().contains(Role.ADMIN) && accountSharedService
						.isCurrentPasswordExpired(userDetails.getUsername()))
						|| accountSharedService.isInitialPassword(userDetails
								.getUsername())) {
					response.sendRedirect(request.getContextPath()
							+ "/password?form");
					return false;
				}
			}
		}

		return true;
	}
}
