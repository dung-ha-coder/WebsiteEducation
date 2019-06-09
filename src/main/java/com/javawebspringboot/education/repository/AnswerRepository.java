package com.javawebspringboot.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.Subject;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	List<Answer> findBySubjectAndIdExamOrderByIdExam(Subject subject, Integer idExam);

}
