package org.terasoluna.securelogin.app.passwordchange;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.service.passwordchange.PasswordChangeService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

@Controller
@RequestMapping("password")
public class PasswordChangeController {

	@Inject
	PasswordChangeService passwordService;

	@RequestMapping(params = "form")
	public String showForm(PasswordChangeForm form,
			@AuthenticationPrincipal LoggedInUser userDetails, Model model) {

		Account account = userDetails.getAccount();
		model.addAttribute(account);
		return "passwordchange/changeForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String change(@AuthenticationPrincipal LoggedInUser userDetails,
			@Validated PasswordChangeForm form, BindingResult bindingResult,
			Model model) {

		Account account = userDetails.getAccount();
		if (bindingResult.hasErrors() || !account.getUsername().equals(form.getUsername())) {
			model.addAttribute(account);
			return "passwordchange/changeForm";
		}

		passwordService.updatePassword(form.getUsername(),
				form.getNewPassword());

		return "redirect:/password?complete";

	}

	@RequestMapping(method = RequestMethod.GET, params = "complete")
	public String changeComplete() {
		return "passwordchange/changeComplete";
	}

	@ModelAttribute("passwordChangeForm")
	public PasswordChangeForm setUpPasswordChangeForm() {
		return new PasswordChangeForm();
	}
}
