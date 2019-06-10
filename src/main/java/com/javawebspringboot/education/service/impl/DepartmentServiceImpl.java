package com.javawebspringboot.education.service.impl;

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
		// TODO Auto-generated method stub
		return departmentRepository.findByIdDepartment(idDepartment);
	}

}
