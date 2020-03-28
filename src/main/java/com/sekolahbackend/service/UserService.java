package com.sekolahbackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sekolahbackend.model.UserModel;
import com.sekolahbackend.model.UserRequestModel;

public interface UserService extends UserDetailsService {

	UserModel register(UserRequestModel requestModel);
}
