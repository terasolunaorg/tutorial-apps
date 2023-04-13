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
package com.example.securelogin.app.welcome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.service.account.AccountSharedService;
import com.example.securelogin.domain.service.userdetails.LoggedInUser;

import jakarta.inject.Inject;

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
    @GetMapping(value = "/")
    public String home(@AuthenticationPrincipal LoggedInUser userDetails,
            Model model) {

        Account account = userDetails.getAccount();

        model.addAttribute("account", account);

        if (accountSharedService.isCurrentPasswordExpired(account
                .getUsername())) {
            ResultMessages messages = ResultMessages.warning().add(
                    "w.sl.pe.0001");
            model.addAttribute(messages);
        }

        LocalDateTime lastLoginDate = userDetails.getLastLoginDate();
        if (lastLoginDate != null) {
            model.addAttribute("lastLoginDate", lastLoginDate.format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return "welcome/home";

    }

}
