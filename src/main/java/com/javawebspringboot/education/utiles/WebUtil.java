package com.javawebspringboot.education.utiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.javawebspringboot.education.model.Role;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.repository.RoleRepository;
import com.javawebspringboot.education.repository.UserRepository;

@Component
public class WebUtil implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if(userRepository.findByUsername("test") == null) {
			User sinhVien = new User();
			String username = "test";
			sinhVien.setUsername(username);
			sinhVien.setPassword(passwordEncoder.encode(username));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByNameRole("ROLE_STUDENT"));
			sinhVien.setRoleList(roles);
			userRepository.save(sinhVien);
		}
		
	}

}
