package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Subject;

public interface CoursesGoalService {
	List<CoursesGoal> findBySubjectOrderBySignAsc(Subject subject);

	void newCoursesGoal(Integer idSubject, String txtG);

	void deleteCoursesGoal(Integer idCoursesGoal);

	CoursesGoal findByIdCoursesGoal(Integer idCoursesGoal);

	void editCoursesGoal(List<Integer> idLOList, String txtCoursesGoal, Integer idCoursesGoal);

}
