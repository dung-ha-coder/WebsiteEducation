package com.javawebspringboot.education.service;

import java.util.List;

import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.Subject;

public interface AnswerService {

	List<Answer> findBySubjectAndIdExamOrderByIdExam(Subject subject, Integer idKithi);
	void deleteAnswer(Integer idAnswer);
	Answer findByIdAnswer(Integer idAnswer);
	void editAnswer(List<Integer> idGList, Integer idAnswer, Integer idExam, String contentAnswer);

}
