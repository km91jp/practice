package com.github.km91jp.todo.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Todo implements Serializable {

	private static final long serialVersionUID = -1192799272580589440L;

	private String id;

	private String title;

	private String content;

	private Date createdAt;

	private boolean finished;

	public Todo(String creatingTitle, String creatingContent) {
		this.id = UUID.randomUUID().toString();
		this.title = creatingTitle;
		this.content = creatingContent;
		this.createdAt = new Date();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished() {
		this.finished = true;
	}

}
