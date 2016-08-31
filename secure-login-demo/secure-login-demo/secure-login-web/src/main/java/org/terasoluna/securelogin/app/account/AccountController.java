package org.terasoluna.securelogin.app.account;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.securelogin.app.account.AccountCreateForm.Confirm;
import org.terasoluna.securelogin.app.account.AccountCreateForm.CreateAccount;
import org.terasoluna.securelogin.domain.common.message.MessageKeys;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.model.Role;
import org.terasoluna.securelogin.domain.model.TempFile;
import org.terasoluna.securelogin.domain.model.AccountImage;
import org.terasoluna.securelogin.domain.service.account.AccountSharedService;
import org.terasoluna.securelogin.domain.service.fileupload.FileUploadSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

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

	@RequestMapping
	public String view(@AuthenticationPrincipal LoggedInUser userDetails,
			Model model) {
		Account account = userDetails.getAccount();
		model.addAttribute(account);
		return "account/view";
	}

	@RequestMapping(value = "/image")
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

	@RequestMapping(value = "/create", params = "form")
	public String createForm() {
		return "account/accountCreateForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "redo")
	public String redoCreateForm(AccountCreateForm form) {
		return "account/accountCreateForm";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "confirm")
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

	@RequestMapping(value = "/create", method = RequestMethod.POST)
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
		return "redirect:/account/create?complete";
	}

	@RequestMapping(value = "/create", params = "complete")
	public String createComplete() {
		return "account/createComplete";
	}

}
