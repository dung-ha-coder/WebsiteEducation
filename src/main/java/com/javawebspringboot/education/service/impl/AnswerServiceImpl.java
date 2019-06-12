package com.javawebspringboot.education.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.repository.AnswerRepository;
import com.javawebspringboot.education.repository.CoursesGoalRepository;
import com.javawebspringboot.education.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private CoursesGoalRepository coursesGoalRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public List<Answer> findBySubjectAndIdExamOrderByIdExam(Subject subject, Integer idExam) {
		return answerRepository.findBySubjectAndIdExamOrderByIdExam(subject, idExam);
	}

	@Override
	public void deleteAnswer(Integer idAnswer) {
		answerRepository.deleteByIdAnswer(idAnswer);
	}

	@Override
	public Answer findByIdAnswer(Integer idAnswer) {
		return answerRepository.findByIdAnswer(idAnswer);
	}

	@Override
	public void editAnswer(List<Integer> idGList, Integer idAnswer, Integer idExam, String contentAnswer) {
		Answer answer = answerRepository.findByIdAnswer(idAnswer);

		if (!idGList.isEmpty()) {
			List<CoursesGoal> coursesGoalList = new ArrayList<CoursesGoal>();
			for (int i = 0; i < idGList.size(); i++) {
				CoursesGoal cg = coursesGoalRepository.findByIdCoursesGoal(idGList.get(i));
				coursesGoalList.add(cg);
			}
			answer.setIdAnswer(idAnswer);
			answer.setContentAnswer(contentAnswer);
			answer.setCoursesGoalList(coursesGoalList);
			answerRepository.save(answer);

		} else {
			answer.setIdExam(idExam);
			answer.setContentAnswer(contentAnswer);
			answerRepository.save(answer);
		}
	}

}
