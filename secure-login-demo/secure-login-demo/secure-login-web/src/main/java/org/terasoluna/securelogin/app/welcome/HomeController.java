package org.terasoluna.securelogin.app.welcome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Inject
	AccountSharedService accountSharedService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String home(@AuthenticationPrincipal LoggedInUser userDetails,
			Model model) {

		Account account = userDetails.getAccount();

		model.addAttribute("account", account);

		if (accountSharedService
				.isCurrentPasswordExpired(account.getUsername())) {
			ResultMessages messages = ResultMessages.warning().add(
					"w.sl.pe.0001");
			model.addAttribute(messages);
		}

		LocalDateTime lastLoginDate = userDetails.getLastLoginDate();
		if (lastLoginDate != null) {
			model.addAttribute("lastLoginDate", lastLoginDate
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}

		return "welcome/home";

	}

}
