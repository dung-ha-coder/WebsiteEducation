package com.javawebspringboot.education.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.Role;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.repository.UserRepository;
import com.javawebspringboot.education.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Page<User> findAllLecturer(Pageable pageable) {
		List<Role> listRole = new ArrayList<Role>();
		listRole.add(new Role(2));
		return userRepository.findByRoleList(listRole, pageable);
	}

	@Override
	public Page<User> findAllStudent(Pageable pageable) {
		List<Role> listRole = new ArrayList<Role>();
		listRole.add(new Role(1));
		return userRepository.findByRoleList(listRole, pageable);
	}

	@Override
	public List<User> findByDepartmentAndRoleListLecturer(Department department) {
		List<Role> listRole = new ArrayList<Role>();
		listRole.add(new Role(2));
		return userRepository.findByDepartmentAndRoleList(department, listRole);
	}

	@Override
	public List<User> findByDepartmentAndRoleListStudent(Department department) {
		List<Role> listRole = new ArrayList<Role>();
		listRole.add(new Role(1));
		return userRepository.findByDepartmentAndRoleList(department, listRole);
	}

	@Override
	public List<User> findAllLecturer() {
		List<Role> listRole = new ArrayList<Role>();
		listRole.add(new Role(2));
		return userRepository.findByRoleList(listRole);
	}

}
