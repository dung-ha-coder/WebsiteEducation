package com.javawebspringboot.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.repository.DepartmentRepository;
import com.javawebspringboot.education.service.DepartmentService;

@Service
public class DepartmentServiceImpl  implements DepartmentService{
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department findByIdDepartment(Integer idDepartment) {
		return departmentRepository.findByIdDepartment(idDepartment);
	}

	@Override
	public List<Department> findAllDepartment() {
		return departmentRepository.findAll();
	}

}
