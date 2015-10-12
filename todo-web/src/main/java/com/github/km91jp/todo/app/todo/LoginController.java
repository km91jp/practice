package com.github.km91jp.todo.app.todo;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.km91jp.todo.domain.service.TodoService;

@Controller
public class LoginController {
	@Inject
	TodoService service;

	@ModelAttribute
	public LoginForm setUpForm() {
		LoginForm form = new LoginForm();
		return form;
	}

	@RequestMapping("login")
	public String list(Model model) {
		return "login";
	}

}
