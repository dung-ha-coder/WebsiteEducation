package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.Subject;

public interface AnswerService {

	List<Answer> findBySubjectAndIdExamOrderByIdExam(Subject subject, Integer idKithi);
}
