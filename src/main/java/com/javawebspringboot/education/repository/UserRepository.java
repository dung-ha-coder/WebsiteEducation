package com.javawebspringboot.education.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.Role;
import com.javawebspringboot.education.model.User;

@Repository
@Transactional
public interface UserRepository  extends JpaRepository<User, Integer>{
	User findByUsername(String username);	
	Page<User> findByRoleList(List<Role> listRole, Pageable pageable);
	
	List<User> findByRoleList(List<Role> listRole);
        
	User findByIdUser(Integer idUser);
	
	List<User> findByDepartmentAndRoleList(Department department, List<Role> roleList);
	
	
}
