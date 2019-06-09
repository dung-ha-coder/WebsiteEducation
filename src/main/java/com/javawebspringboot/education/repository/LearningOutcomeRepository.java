package com.javawebspringboot.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.LearningOutcome;

@Repository
@Transactional
public interface LearningOutcomeRepository extends JpaRepository<LearningOutcome, Integer> {
	List<LearningOutcome> findAll();
}
