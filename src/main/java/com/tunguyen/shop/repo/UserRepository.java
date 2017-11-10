package com.tunguyen.shop.repo;

import com.tunguyen.shop.domain.User;

public interface UserRepository {
	User findByUsername(String username);
}
