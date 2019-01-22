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
package com.example.securelogin.app.unlock;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;

import com.example.securelogin.domain.service.unlock.UnlockService;

@Controller
@RequestMapping("/unlock")
public class UnlockController {

    @Inject
    UnlockService unlockService;

    @RequestMapping(params = "form")
    public String showForm(UnlockForm form) {
        return "unlock/unlockForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String unlock(@Validated UnlockForm form,
            BindingResult bindingResult, Model model,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return showForm(form);
        }

        try {
            unlockService.unlock(form.getUsername());
            attributes.addFlashAttribute("username", form.getUsername());
            return "redirect:/unlock?complete";
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessages());
            return showForm(form);
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String unlockComplete() {
        return "unlock/unlockComplete";
    }

}
