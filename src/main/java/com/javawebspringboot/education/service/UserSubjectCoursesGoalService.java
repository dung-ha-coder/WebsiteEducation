package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;

public interface UserSubjectCoursesGoalService {

	List<UserSubjectCoursesGoal> findByUserAndSubject(User user, Subject subject);

	List<UserSubjectCoursesGoal> findByUserAndSubjectOrderByCoursesGoalAsc(User user, Subject subject);


}
