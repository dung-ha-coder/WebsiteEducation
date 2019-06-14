package com.javawebspringboot.education.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;
import com.javawebspringboot.education.repository.UserSubjectCoursesGoalRepository;
import com.javawebspringboot.education.service.UserSubjectCoursesGoalService;

@Service
@Transactional
public class UserSubjectCoursesGoalServiceImpl implements UserSubjectCoursesGoalService {

	@Autowired
	private UserSubjectCoursesGoalRepository userSubjectCoursesGoalRepository;



	@Override
	public List<UserSubjectCoursesGoal> findByUserAndSubject(User user, Subject subject) {

		return userSubjectCoursesGoalRepository.findByUserAndSubject(user, subject);

	}

	@Override
	public List<UserSubjectCoursesGoal> findByUserAndSubjectOrderByCoursesGoalAsc(User user, Subject subject) {
		return userSubjectCoursesGoalRepository.findByUserAndSubjectOrderByCoursesGoalAsc(user, subject);
	}

	

}
