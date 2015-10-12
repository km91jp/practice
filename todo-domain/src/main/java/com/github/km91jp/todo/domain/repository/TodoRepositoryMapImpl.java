package com.github.km91jp.todo.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Repository;

import com.github.km91jp.todo.domain.model.Todo;

@Repository
public class TodoRepositoryMapImpl implements TodoRepository {

	private Map<String, Todo> todoMap = new TreeMap<>(); 

	@Override
	public Todo findOne(String findId) {
		Todo todo = todoMap.get(findId);
		return todo;
	}

	@Override
	public List<Todo> findAll() {
		List<Todo> todoList = new ArrayList<>(todoMap.values());
		return todoList;
	}

	@Override
	public int create(Todo creatingTodo) {
		todoMap.put(creatingTodo.getId(), creatingTodo);
		return 1;
	}
	
	@Override
	public int update(Todo updatingTodo) {
		//todoMap.remove(updatingTodo.getId());
		todoMap.get(updatingTodo.getId()).setFinished();
		return 1;
	}

	@Override
	public int delete(Todo deletingTodo) {
		todoMap.remove(deletingTodo.getId());
		return 1;
	}

	@Override
	public long countByFinished(boolean isFinished) {
		long count = todoMap.values().stream().filter(e -> e.isFinished() == isFinished).count();
		System.out.println(count);
		return count;
	}

}
