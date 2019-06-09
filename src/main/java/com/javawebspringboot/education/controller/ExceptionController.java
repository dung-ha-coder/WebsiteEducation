package com.javawebspringboot.education.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController implements ErrorController {

	@RequestMapping("/403")
	public String error_403() {
		return "redirect:/loginHandler";
	}

	@RequestMapping("/error")
	public String error() {
		return "redirect:/loginHandler";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
