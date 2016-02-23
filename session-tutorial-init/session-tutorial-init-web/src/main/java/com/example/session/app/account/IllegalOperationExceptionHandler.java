package com.example.session.app.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("session.app.account")
public class IllegalOperationExceptionHandler {

	@ExceptionHandler({ IllegalOperationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ModelAndView handleOrderException(IllegalOperationException e) {
		return new ModelAndView("common/error/illegalOperationError")
				.addObject(e.getResultMessages());
	}
}
