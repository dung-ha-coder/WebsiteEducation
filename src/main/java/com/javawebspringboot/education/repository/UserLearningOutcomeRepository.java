package com.javawebspringboot.education.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserLearningOutcome;

@Repository
@Transactional
public interface UserLearningOutcomeRepository extends JpaRepository<UserLearningOutcome, Integer>{
	List<UserLearningOutcome> findByUser(User user);
        
}
