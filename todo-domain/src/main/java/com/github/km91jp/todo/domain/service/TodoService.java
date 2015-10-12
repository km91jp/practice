package com.github.km91jp.todo.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.km91jp.todo.domain.model.Todo;

@Service
public interface TodoService {

	Todo findOne(String findId);

	List<Todo> findAll();

	int create(String creatingTitle, String creatingContent);

	int finish(String targetId);

	int delete(String targetId);
}
