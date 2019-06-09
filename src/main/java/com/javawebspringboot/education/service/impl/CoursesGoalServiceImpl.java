package com.javawebspringboot.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.repository.CoursesGoalRepository;
import com.javawebspringboot.education.service.CoursesGoalService;

@Service
@Transactional
public class CoursesGoalServiceImpl implements CoursesGoalService{
	@Autowired
	private CoursesGoalRepository coursesGoalRepository;

	@Override
	public List<CoursesGoal> findBySubjectOrderBySignAsc(Subject subject) {
		// TODO Auto-generated method stub
		return coursesGoalRepository.findBySubjectOrderBySignAsc(subject);
	}

}
