package com.javawebspringboot.education.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.education.model.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByNameRole(String nameRole);
}
