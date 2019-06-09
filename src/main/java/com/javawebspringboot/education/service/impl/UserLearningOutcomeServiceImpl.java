package com.javawebspringboot.education.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserLearningOutcome;
import com.javawebspringboot.education.repository.UserLearningOutcomeRepository;
import com.javawebspringboot.education.service.UserLearningOutcomeService;

@Service
@Transactional
public class UserLearningOutcomeServiceImpl implements UserLearningOutcomeService {

	@Autowired
	private UserLearningOutcomeRepository userLearningOutcomeRepository;

	@Override
	public List<UserLearningOutcome> findByUser(User user) {
		return userLearningOutcomeRepository.findByUser(user);
	}

}
