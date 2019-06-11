package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.Department;

public interface DepartmentService {
	Department findByIdDepartment(Integer idDepartment);
	
	List<Department> findAllDepartment();
}
