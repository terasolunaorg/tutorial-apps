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
package com.example.securelogin.app.account;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.apache.commons.io.IOUtils;
import org.dozer.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import com.example.securelogin.app.account.AccountCreateForm.Confirm;
import com.example.securelogin.app.account.AccountCreateForm.CreateAccount;
import com.example.securelogin.domain.common.message.MessageKeys;
import com.example.securelogin.domain.model.Account;
import com.example.securelogin.domain.model.Role;
import com.example.securelogin.domain.model.TempFile;
import com.example.securelogin.domain.model.AccountImage;
import com.example.securelogin.domain.service.account.AccountSharedService;
import com.example.securelogin.domain.service.fileupload.FileUploadSharedService;
import com.example.securelogin.domain.service.userdetails.LoggedInUser;

@Controller
@RequestMapping("accounts")
public class AccountController {

    @Inject
    FileUploadSharedService fileUploadSharedService;

    @Inject
    AccountSharedService accountSharedService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public AccountCreateForm SetUpAccountCreateForm() {
        return new AccountCreateForm();
    }

    @GetMapping
    public String view(@AuthenticationPrincipal LoggedInUser userDetails,
            Model model) {
        Account account = userDetails.getAccount();
        model.addAttribute(account);
        return "account/view";
    }

    @GetMapping("/image")
    @ResponseBody
    public ResponseEntity<byte[]> showImage(
            @AuthenticationPrincipal LoggedInUser userDetails)
            throws IOException {
        AccountImage userImage = accountSharedService.getImage(userDetails
                .getUsername());
        HttpHeaders headers = new HttpHeaders();
        if (userImage.getExtension().equalsIgnoreCase("png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        } else if (userImage.getExtension().equalsIgnoreCase("gif")) {
            headers.setContentType(MediaType.IMAGE_GIF);
        } else if (userImage.getExtension().equalsIgnoreCase("jpg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        }
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(userImage.getBody()), headers,
                HttpStatus.OK);
    }

    @GetMapping(value = "/create", params = "form")
    public String createForm() {
        return "account/accountCreateForm";
    }

    @PostMapping(value = "/create", params = "redo")
    public String redoCreateForm(AccountCreateForm form) {
        return "account/accountCreateForm";
    }

    @PostMapping(value = "/create", params = "confirm")
    public String createConfirm(
            @Validated({ Confirm.class, Default.class }) AccountCreateForm form,
            BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return createForm();
        }
        if (accountSharedService.exists(form.getUsername())) {
            model.addAttribute(ResultMessages.error().add(
                    MessageKeys.E_SL_AC_5001));
            return createForm();
        }
        try {
            TempFile tempFile = new TempFile();
            tempFile.setBody(form.getImage().getInputStream());
            tempFile.setOriginalName(form.getImage().getOriginalFilename());
            String fileId = fileUploadSharedService.uploadTempFile(tempFile);
            form.setImageId(fileId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        redirectAttributes.addFlashAttribute("accountCreateForm", form);
        return "account/accountConfirm";
    }

    @PostMapping("/create")
    public String create(
            @Validated({ CreateAccount.class, Default.class }) AccountCreateForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return createForm();
        }
        Account account = beanMapper.map(form, Account.class);
        List<Role> roles = Arrays.asList(Role.USER);
        account.setRoles(roles);
        String password = accountSharedService.create(account,
                form.getImageId());
        redirectAttributes.addFlashAttribute("firstName", form.getFirstName());
        redirectAttributes.addFlashAttribute("lastName", form.getLastName());
        redirectAttributes.addFlashAttribute("password", password);
        return "redirect:/accounts/create?complete";
    }

    @GetMapping(value = "/create", params = "complete")
    public String createComplete() {
        return "account/createComplete";
    }

}
