package com.javawebspringboot.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javawebspringboot.education.model.LivingClass;

public interface LivingClassRepository extends JpaRepository<LivingClass, Integer> {

	LivingClass findByNameLivingClass(String nameLivingClass);
	
	LivingClass findByIdLivingClass(Integer idLivingClass);
}
