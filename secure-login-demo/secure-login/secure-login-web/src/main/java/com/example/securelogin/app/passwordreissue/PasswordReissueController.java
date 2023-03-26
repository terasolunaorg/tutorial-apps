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
package com.example.securelogin.app.passwordreissue;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import com.example.securelogin.domain.model.PasswordReissueInfo;
import com.example.securelogin.domain.service.passwordreissue.PasswordReissueService;

@Controller
@RequestMapping("/reissue")
public class PasswordReissueController {

    @Inject
    PasswordReissueService passwordReissueService;

    @ModelAttribute("createReissueInfoForm")
    public CreateReissueInfoForm setupReissueForm() {
        return new CreateReissueInfoForm();
    }

    @ModelAttribute("passwordResetForm")
    public PasswordResetForm setupResetForm() {
        return new PasswordResetForm();
    }

    @GetMapping(value = "create", params = "form")
    public String showCreateReissueInfoForm(CreateReissueInfoForm form) {
        return "passwordreissue/createReissueInfoForm";
    }

    @PostMapping("create")
    public String createReissueInfo(@Validated CreateReissueInfoForm form,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return showCreateReissueInfoForm(form);
        }

        String rawSecret = passwordReissueService.createAndSendReissueInfo(form
                .getUsername());
        attributes.addFlashAttribute("secret", rawSecret);
        return "redirect:/reissue/create?complete";
    }

    @GetMapping(value = "create", params = "complete")
    public String createReissueInfoComplete() {
        return "passwordreissue/createReissueInfoComplete";
    }

    @GetMapping(value = "resetpassword", params = "form")
    public String showPasswordResetForm(PasswordResetForm form, Model model,
            @RequestParam("token") String token) {

        PasswordReissueInfo info = passwordReissueService.findOne(token);

        form.setUsername(info.getUsername());
        form.setToken(token);
        model.addAttribute("passwordResetForm", form);
        return "passwordreissue/passwordResetForm";
    }

    @PostMapping("resetpassword")
    public String resetPassword(@Validated PasswordResetForm form,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showPasswordResetForm(form, model, form.getToken());
        }

        try {
            passwordReissueService.resetPassword(form.getUsername(), form
                    .getToken(), form.getSecret(), form.getNewPassword());
            return "redirect:/reissue/resetpassword?complete";
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return showPasswordResetForm(form, model, form.getToken());
        }
    }

    @GetMapping(value = "resetpassword", params = "complete")
    public String resetPasswordComplete() {
        return "passwordreissue/passwordResetComplete";
    }
}
