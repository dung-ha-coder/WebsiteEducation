package com.javawebspringboot.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Subject;

@Repository
@Transactional
public interface CoursesGoalRepository extends JpaRepository<CoursesGoal, Integer> {
	List<CoursesGoal> findBySubjectOrderBySignAsc(Subject subject);

	List<CoursesGoal> findBySubject(Subject subject);

	CoursesGoal findByIdCoursesGoal(Integer idCoursesGoal);

	void deleteByIdCoursesGoal(Integer idCoursesGoal);

}
