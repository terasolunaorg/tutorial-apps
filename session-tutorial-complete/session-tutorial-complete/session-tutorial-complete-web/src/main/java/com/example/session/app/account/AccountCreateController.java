package com.example.session.app.account;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.session.domain.model.Account;
import com.example.session.domain.service.account.AccountService;

@Controller
@SessionAttributes(value = { "accountCreateForm" })
@RequestMapping("account")
public class AccountCreateController {

	@Inject
	AccountService accountService;

	@Inject
	Mapper beanMapper;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ModelAttribute(value = "accountCreateForm")
	public AccountCreateForm setUpAccountForm() {
		return new AccountCreateForm();
	}

	@RequestMapping(value = "create", params = "form")
	public String showCreateForm() {
		return "account/createForm";
	}

	@RequestMapping(value = "create", params = "confirm")
	public String confirmCreate(@Validated AccountCreateForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return showCreateForm();
		}

		return "account/createConfirm";
	}

	@RequestMapping(value = "create", params = "redoForm")
	public String redoCreateForm() {
		return showCreateForm();
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String update(@Validated AccountCreateForm form, BindingResult result) {

		if (result.hasErrors()) {
			ResultMessages messages = ResultMessages.error();
			messages.add("e.st.ac.5001");
			throw new IllegalOperationException(messages);
		}

		Account account = beanMapper.map(form, Account.class);
		accountService.create(account, form.getPassword());

		return "redirect:/account/create?finish";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET, params = "finish")
	public String finishCreate() {
		return "account/createFinish";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET, params = "home")
	public String home(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/goods";
	}

}
