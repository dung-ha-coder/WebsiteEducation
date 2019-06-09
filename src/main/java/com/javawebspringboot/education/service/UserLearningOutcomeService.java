package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserLearningOutcome;

public interface UserLearningOutcomeService {

	
	List<UserLearningOutcome> findByUser(User user);
        
	
}
