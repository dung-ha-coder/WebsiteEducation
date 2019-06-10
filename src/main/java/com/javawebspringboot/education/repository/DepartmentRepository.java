package com.javawebspringboot.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.education.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Department findByIdDepartment(Integer idDepartment);
}
