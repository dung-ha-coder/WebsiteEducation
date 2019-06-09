package com.javawebspringboot.education.service;

import com.javawebspringboot.education.model.LivingClass;

public interface LivingClassService {
	LivingClass findByNameLivingClass(String nameLivingClass);

	LivingClass findByIdLivingClass(Integer idLivingClass);

}
