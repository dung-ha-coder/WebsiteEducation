package com.javawebspringboot.education.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawebspringboot.education.model.User;

public interface UserService {
	User findByUsername(String username);
	Page<User> findAllLecturer(Pageable pageable);
	Page<User> findAllStudent(Pageable pageable);

}
