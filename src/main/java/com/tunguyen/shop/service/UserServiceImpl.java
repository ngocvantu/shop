package com.tunguyen.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.exception.AuthenticationException;
import com.tunguyen.shop.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User login(User user) throws AuthenticationException {
		// TODO Auto-generated method stub
		User u = userRepository.findByUsername(user.getUsername());
//		if (u==null) {
//			throw new AuthenticationException("user khong ton tai", "notthatuser");
//		}
		return u;
	}

}
