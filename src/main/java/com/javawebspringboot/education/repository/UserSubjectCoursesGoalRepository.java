package com.javawebspringboot.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;

@Repository
@Transactional
public interface UserSubjectCoursesGoalRepository extends JpaRepository<UserSubjectCoursesGoal, Integer> {

	List<UserSubjectCoursesGoal> findByUserAndSubject(User user, Subject subject);
	List<UserSubjectCoursesGoal> findByUserAndSubjectOrderByCoursesGoalAsc(User user, Subject subject);


}
