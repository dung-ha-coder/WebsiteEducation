package com.javawebspringboot.education.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;

@Repository
@Transactional
public interface ScoresRepository extends JpaRepository<ScoresTable, Integer> {

	List<ScoresTable> findByUser(User user);
	
	List<ScoresTable> findBySubject(Subject subject);
	
	ScoresTable findByUserAndSubject(User user, Subject monhoc);

}
