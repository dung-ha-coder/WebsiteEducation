package com.javawebspringboot.education.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {

	@RequestMapping("/login")
	public String loginPage() {
		return "loginPage";
	}

	@RequestMapping("/loginHandler")
	public String loginHandler(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails.getAuthorities().toString().equals("[ROLE_STUDENT]")) {
			return "redirect:/student";
		}

		if (userDetails.getAuthorities().toString().equals("[ROLE_LECTURER]")) {
			return "redirect:/lecturer/";
		}

		if (userDetails.getAuthorities().toString().equals("[ROLE_MANAGE]")) {
			return "redirect:/manage/";
		}
		if (userDetails.getAuthorities().toString().equals("[ROLE_LECTURER, ROLE_MANAGE]")) {
			return "redirect:/manage/";
		}

		return "redirect:/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}
