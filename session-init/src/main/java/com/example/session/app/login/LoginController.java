package com.example.session.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value = "loginForm")
	public String viewLoginForm() {
		return "login/loginForm";
	}

}
