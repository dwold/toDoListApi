package com.dagnachew.toDoListApi.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dagnachew.toDoListApi.entity.Credentials;
import com.dagnachew.toDoListApi.entity.User;
import com.dagnachew.toDoListApi.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setFirstName(cred.getFirstName());
		user.setLastName(cred.getLastName());
		user.setEmail(cred.getEmail());
		user.setHash((BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt())));
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Email is already registered, use new email.");
		}
	
	}
	
	public User login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepository.findByEmail(cred.getEmail());
		if (foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			return foundUser;
		}
		throw new AuthenticationException("Incorrect email or password");
	}
	
}
