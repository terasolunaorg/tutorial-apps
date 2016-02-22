package org.terasoluna.securelogin.app.unlock;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;

import org.terasoluna.securelogin.domain.service.unlock.UnlockService;

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
