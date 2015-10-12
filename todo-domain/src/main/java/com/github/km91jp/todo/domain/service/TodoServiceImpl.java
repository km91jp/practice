package com.github.km91jp.todo.domain.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.km91jp.todo.domain.model.Todo;
import com.github.km91jp.todo.domain.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	@Value("${max.unfinished.todo.count}")
	private int MAX_UNFINISHED_TODO_SIZE = 1;
	@Inject
	TodoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Todo findOne(String findId) {
		Todo todo = repository.findOne(findId);
		if (todo == null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText(String.format(
					"can not find.id=%s", findId)));
			throw new ResourceNotFoundException(messages);
		}
		return todo;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		List<Todo> todoList = repository.findAll();
		return todoList;
	}

	@Override
	public int create(String creatingTitle, String creatingContent) {
		if (repository.countByFinished(false) >= MAX_UNFINISHED_TODO_SIZE) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromText("can not create todo until finish at least one unfinished"));
			throw new BusinessException(messages);
		}
		Todo creatingTodo = new Todo(creatingTitle, creatingContent);
		int insertCount = repository.create(creatingTodo);
		return insertCount;
	}

	@Override
	public int finish(String targetId) {
		System.out.println("finishing"+targetId);
		Todo updatingTodo = repository.findOne(targetId);
		updatingTodo.setFinished();
		System.out.println(updatingTodo.isFinished());
		int updateCount = repository.update(updatingTodo);
		return updateCount;
	}

	@Override
	public int delete(String targetId) {
		Todo deletingTodo = repository.findOne(targetId);
		int deleteCount = repository.delete(deletingTodo);
		return deleteCount;
	}

}
