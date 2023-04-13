/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.securelogin.app.passwordchange;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.service.passwordchange.PasswordChangeService;
import com.example.securelogin.domain.service.userdetails.LoggedInUser;

import jakarta.inject.Inject;

@Controller
@RequestMapping("password")
public class PasswordChangeController {

    @Inject
    PasswordChangeService passwordService;

    @ModelAttribute("passwordChangeForm")
    public PasswordChangeForm setUpPasswordChangeForm() {
        return new PasswordChangeForm();
    }

    @GetMapping(params = "form")
    public String showForm(PasswordChangeForm form,
            @AuthenticationPrincipal LoggedInUser userDetails, Model model) {

        Account account = userDetails.getAccount();
        model.addAttribute(account);
        return "passwordchange/changeForm";
    }

    @PostMapping
    public String change(@AuthenticationPrincipal LoggedInUser userDetails,
            @Validated PasswordChangeForm form, BindingResult bindingResult,
            Model model) {

        Account account = userDetails.getAccount();
        if (bindingResult.hasErrors() || !account.getUsername().equals(form
                .getUsername())) {
            model.addAttribute(account);
            return "passwordchange/changeForm";
        }

        passwordService.updatePassword(form.getUsername(), form
                .getNewPassword());

        return "redirect:/password?complete";

    }

    @GetMapping(params = "complete")
    public String changeComplete() {
        return "passwordchange/changeComplete";
    }
}
