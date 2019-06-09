package com.javawebspringboot.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.LivingClass;
import com.javawebspringboot.education.repository.LivingClassRepository;
import com.javawebspringboot.education.service.LivingClassService;

@Service
public class LivingClassServiceImpl implements LivingClassService {

	@Autowired
	private LivingClassRepository livingClassRepository;

	@Override
	public LivingClass findByNameLivingClass(String nameLivingClass) {
		return livingClassRepository.findByNameLivingClass(nameLivingClass);
	}

	@Override
	public LivingClass findByIdLivingClass(Integer idLivingClass) {
		return livingClassRepository.findByIdLivingClass(idLivingClass);
	}

}
