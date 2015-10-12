package com.github.km91jp.todo.app.todo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoForm {
	
	public interface CreateCheck {
	}
	
	public interface DeleteOrFinishCheck {
	}

	@NotNull(groups=DeleteOrFinishCheck.class)
	private String id;

	@NotNull(groups=CreateCheck.class)
	@Size(groups=CreateCheck.class, min=1, max=30)
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
