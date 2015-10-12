package com.github.km91jp.todo.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.km91jp.todo.domain.model.Account;
import com.github.km91jp.todo.domain.model.TodoUserDetails;

@Service
public class TodoUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		// dummy
		if("invalid".equals(userId)) throw new UsernameNotFoundException(userId);
		Account account = new Account();
		account.setUserId("demo");
		account.setPassword("demo");
		account.setName("this is a test user");
		return new TodoUserDetails(account);
	}

}
