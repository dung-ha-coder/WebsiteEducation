package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.LearningOutcome;

public interface LearningOutcomeService {

	List<LearningOutcome> findAll();

	List<LearningOutcome> findByDepartment(Department department);

	void newLearningOutcome(Department department, String txtLO);

	void delteLearningOutcome(Integer idLearningOutcome);

	LearningOutcome findByIdLearningOutcome(Integer idLearningOutcome);

	void editLO(Integer idLearningOutcome, String txtLOEdit);


}
