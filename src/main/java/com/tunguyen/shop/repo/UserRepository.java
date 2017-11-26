package com.tunguyen.shop.repo;

import com.tunguyen.shop.domain.User;

public interface UserRepository {
	User findByUsername(String username, String pass);

	User findUserFaceByEmail(String email);

	User findUserGoByEmail(String email);

	boolean checkEmailExist(String email);

	void save(User user);
}
