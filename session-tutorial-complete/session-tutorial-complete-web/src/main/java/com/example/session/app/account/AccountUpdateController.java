/*
 * Copyright (C) 2013-2017 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.session.app.account;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.session.app.account.AccountUpdateForm.Wizard1;
import com.example.session.app.account.AccountUpdateForm.Wizard2;
import com.example.session.domain.model.Account;
import com.example.session.domain.service.account.AccountService;
import com.example.session.domain.service.userdetails.AccountDetails;

@Controller
@SessionAttributes(value = { "accountUpdateForm" }) // (1)
@RequestMapping("account")
public class AccountUpdateController {

    @Inject
    AccountService accountService;

    @Inject
    Mapper beanMapper;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute(value = "accountUpdateForm") // (2)
    public AccountUpdateForm setUpAccountForm() {
        return new AccountUpdateForm();
    }

    @RequestMapping(value = "update", params = "form1")
    public String showUpdateForm1(
            @AuthenticationPrincipal AccountDetails userDetails,
            AccountUpdateForm form) { // (3)

        Account account = accountService.findOne(userDetails.getAccount()
                .getEmail());
        beanMapper.map(account, form);

        return "account/updateForm1";
    }

    @RequestMapping(value = "update", params = "form2")
    public String showUpdateForm2(
            @Validated(Wizard1.class) AccountUpdateForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "account/updateForm1";
        }

        return "account/updateForm2";
    }

    @RequestMapping(value = "update", params = "redoForm1")
    public String redoUpdateForm1() {
        return "account/updateForm1";
    }

    @RequestMapping(value = "update", params = "confirm")
    public String confirmUpdate(
            @Validated(Wizard2.class) AccountUpdateForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "account/updateForm2";
        }

        return "account/updateConfirm";
    }

    @RequestMapping(value = "update", params = "redoForm2")
    public String redoUpdateForm2() {
        return "account/updateForm2";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(
            @AuthenticationPrincipal AccountDetails userDetails,
            @Validated({ Wizard1.class, Wizard2.class }) AccountUpdateForm form,
            BindingResult result, RedirectAttributes attributes, SessionStatus sessionStatus) {

        if (result.hasErrors()) {
            ResultMessages messages = ResultMessages.error();
            messages.add("e.st.ac.5001");
            throw new IllegalOperationException(messages);
        }

        Account account = beanMapper.map(form, Account.class);
        accountService.update(account);
        userDetails.setAccount(account);
        attributes.addFlashAttribute("account", account);
        sessionStatus.setComplete();  // (4)

        return "redirect:/account/update?finish";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET, params = "finish")
    public String finishUpdate() {
        return "account/updateFinish";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET, params = "home")
    public String home(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/goods";
    }

}
