package com.github.km91jp.todo.app.todo;

import java.util.List;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.km91jp.todo.app.todo.TodoForm.CreateCheck;
import com.github.km91jp.todo.app.todo.TodoForm.DeleteOrFinishCheck;
import com.github.km91jp.todo.domain.model.Todo;
import com.github.km91jp.todo.domain.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
	@Inject
	TodoService service;

	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm form = new TodoForm();
		return form;
	}

	@RequestMapping("list")
	public String list(Model model) {
		List<Todo> todoList = service.findAll();
		model.addAttribute("todoList", todoList);
		return "todo/list";
	}

	@RequestMapping("create")
	public String create(
			@Validated({ Default.class, CreateCheck.class }) TodoForm form,
			BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {
		if (bindingResult.hasErrors()) {
			return list(model);
		}
		try {
			service.create(form.getTitle(), "dummy");
			attributes.addFlashAttribute(ResultMessages.success().add(
					ResultMessage.fromText("Created successfully!")));
		} catch (BusinessException e) {
			String errorMsg = e.getResultMessages().getList().get(0).getText();
			attributes.addFlashAttribute(ResultMessages.success().add(
					ResultMessage.fromText("failed to create because "
							+ errorMsg)));
		}
		return "redirect:/todo/list";
	}

	@RequestMapping("finish")
	public String finish(
			@Validated({ Default.class, DeleteOrFinishCheck.class }) TodoForm form,
			Model model) {
		service.finish(form.getId());
		return "redirect:/todo/list";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
		service.delete(id);
		return "redirect:/todo/list";
	}
}
