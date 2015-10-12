package com.github.km91jp.todo.domain.repository;

import java.util.List;

import com.github.km91jp.todo.domain.model.Todo;

public interface TodoRepository {

	Todo findOne(String findId);

	List<Todo> findAll();

	int create(Todo creatingTodo);

	int update(Todo updatingTodo);

	int delete(Todo deletingTodo);

	long countByFinished(boolean isFinished);

}
