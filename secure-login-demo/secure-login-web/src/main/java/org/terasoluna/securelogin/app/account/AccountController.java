package org.terasoluna.securelogin.app.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

@Controller
@RequestMapping("account")
public class AccountController {

	@RequestMapping
	public String view(@AuthenticationPrincipal LoggedInUser userDetails,
			Model model) {
		Account account = userDetails.getAccount();
		model.addAttribute(account);
		return "account/view";
	}

}
