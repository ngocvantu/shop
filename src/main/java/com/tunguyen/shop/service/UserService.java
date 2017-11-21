package com.tunguyen.shop.service;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.exception.AuthenticationException;

public interface UserService {
	public User login(User user) throws AuthenticationException;
}
