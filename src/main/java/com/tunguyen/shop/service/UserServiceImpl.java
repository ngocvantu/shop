package com.tunguyen.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(user.getUsername());
	}

}
