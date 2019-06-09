package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Subject;

public interface CoursesGoalService {
	List<CoursesGoal> findBySubjectOrderBySignAsc(Subject subject);

}
