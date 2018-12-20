package com.social.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.dao.RoleRepository;
import com.social.dao.StatusRepository;
import com.social.dao.UserRepository;
import com.social.entities.Roles;
import com.social.entities.Status;
import com.social.entities.User;
import com.social.entities.UserCredentialDetails;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	StatusRepository statusRepository;

	public User save(User user) {
		Roles role=roleRepository.findOne(1L);
		Status status=statusRepository.findOne(1L);
		user.setRole(role);
		user.setStatus(status);
		return userRepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public User find(Long id) {
		return userRepository.findOne(id);
	}
}
