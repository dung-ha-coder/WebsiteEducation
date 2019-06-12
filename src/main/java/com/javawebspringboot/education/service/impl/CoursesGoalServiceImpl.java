package com.javawebspringboot.education.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.repository.CoursesGoalRepository;
import com.javawebspringboot.education.repository.LearningOutcomeRepository;
import com.javawebspringboot.education.repository.SubjectRepository;
import com.javawebspringboot.education.service.CoursesGoalService;

@Service
@Transactional
public class CoursesGoalServiceImpl implements CoursesGoalService {
	@Autowired
	private CoursesGoalRepository coursesGoalRepository;

	@Autowired
	private LearningOutcomeRepository learningOutcomeRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<CoursesGoal> findBySubjectOrderBySignAsc(Subject subject) {
		return coursesGoalRepository.findBySubjectOrderBySignAsc(subject);
	}

	@Override
	public void newCoursesGoal(Integer idSubject, String txtG) {

		Subject subject = subjectRepository.findByIdSubject(idSubject);
		List<CoursesGoal> coursesGoalList = coursesGoalRepository.findBySubject(subject);
		int maxG = findMaxG(coursesGoalList);
		CoursesGoal coursesGoal = new CoursesGoal("G" + (maxG + 1), txtG, subject, null, null, null);

		coursesGoalRepository.save(coursesGoal);

	}

	private int findMaxG(List<CoursesGoal> coursesGoalList) {
		int max = 0;
		for (CoursesGoal coursesGoal : coursesGoalList) {
			String sign = coursesGoal.getSign();
			int i = Integer.parseInt(sign.substring(1, sign.length()));
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	@Override
	public void deleteCoursesGoal(Integer idCoursesGoal) {

		coursesGoalRepository.deleteByIdCoursesGoal(idCoursesGoal);

	}

	@Override
	public CoursesGoal findByIdCoursesGoal(Integer idCoursesGoal) {
		return coursesGoalRepository.findByIdCoursesGoal(idCoursesGoal);
	}

	@Override
	public void editCoursesGoal(List<Integer> idLOList, String txtCoursesGoal, Integer idCoursesGoal) {
		CoursesGoal coursesGoal = coursesGoalRepository.findByIdCoursesGoal(idCoursesGoal);

		if (!idLOList.isEmpty()) {
			List<LearningOutcome> lo = new ArrayList<LearningOutcome>();
			for (Integer idLearningOutcome : idLOList) {

				LearningOutcome learningOutcome = learningOutcomeRepository.findByIdLearningOutcome(idLearningOutcome);
				lo.add(learningOutcome);

			}
			coursesGoal.setNameCoursesGoal(txtCoursesGoal);
			coursesGoal.setLearningOutcomeList(lo);
			coursesGoalRepository.save(coursesGoal);
		} else {
			coursesGoal.setNameCoursesGoal(txtCoursesGoal);
			coursesGoal.setLearningOutcomeList(null);
			coursesGoalRepository.save(coursesGoal);
		}

	}

}
