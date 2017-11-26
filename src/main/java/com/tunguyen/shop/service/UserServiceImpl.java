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
		User u = userRepository.findByUsername(user.getUsername(), user.getPassword());
//		if (u==null) {
//			throw new AuthenticationException("user khong ton tai", "notthatuser");
//		}
		return u;
	}

	@Override
	public User loginFace(User user) {
		User userFace = userRepository.findUserFaceByEmail(user.getEmail());
		if (userFace == null) {
			if (!userRepository.checkEmailExist(user.getEmail())) {
				user.setUserType("face");
				userRepository.save(user);
				return user;
			}
		}

		if (userFace != null && userFace.getUserType().equals("face")) {
			return userFace;
		}
		return null;
	}

	@Override
	public User loginGo(User user) {
		User userFace = userRepository.findUserGoByEmail(user.getEmail());
		if (userFace == null) {
			if (!userRepository.checkEmailExist(user.getEmail())) {
				user.setUserType("go");
				userRepository.save(user);
				return user;
			}
		}

		if (userFace != null && userFace.getUserType().equals("go")) {
			return userFace;
		}
		return null;
	}

}
