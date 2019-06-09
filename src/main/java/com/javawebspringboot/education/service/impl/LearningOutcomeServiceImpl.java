package com.javawebspringboot.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.repository.LearningOutcomeRepository;
import com.javawebspringboot.education.service.LearningOutcomeService;

@Service
@Transactional
public class LearningOutcomeServiceImpl implements LearningOutcomeService{

	@Autowired
	private LearningOutcomeRepository learningOutcomeRepository;
	
	@Override
	public List<LearningOutcome> findAll() {
		return learningOutcomeRepository.findAll();
	}

}
