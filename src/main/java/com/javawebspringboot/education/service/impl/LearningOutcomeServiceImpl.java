package com.javawebspringboot.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.repository.LearningOutcomeRepository;
import com.javawebspringboot.education.service.LearningOutcomeService;

@Service
@Transactional
public class LearningOutcomeServiceImpl implements LearningOutcomeService {

	@Autowired
	private LearningOutcomeRepository learningOutcomeRepository;

	@Override
	public List<LearningOutcome> findAll() {
		return learningOutcomeRepository.findAll();
	}

	@Override
	public List<LearningOutcome> findByDepartment(Department department) {

		return learningOutcomeRepository.findByDepartment(department);
	}

	@Override
	public void newLearningOutcome(Department department, String txtLO) {

		int max = 0;
		for (LearningOutcome learningOutcome : department.getLearningOutcomeList()) {
			String sign = learningOutcome.getSign();
			int s = Integer.parseInt(sign.substring(1, sign.length()));
			if (max < s) {
				max = s;
			}
		}

		LearningOutcome learningOutcome = new LearningOutcome("L" + (max + 1), txtLO, department, null, null);

		learningOutcomeRepository.save(learningOutcome);

	}

	@Override
	public void delteLearningOutcome(Integer idLearningOutcome) {

		learningOutcomeRepository.deleteByIdLearningOutcome(idLearningOutcome);
	}

	@Override
	public LearningOutcome findByIdLearningOutcome(Integer idLearningOutcome) {
		return learningOutcomeRepository.findByIdLearningOutcome(idLearningOutcome);
	}

	@Override
	public void editLO(Integer idLearningOutcome, String txtLOEdit) {

		
		LearningOutcome learningOutcome = learningOutcomeRepository.findByIdLearningOutcome(idLearningOutcome);
		learningOutcome.setNameLearningOutcome(txtLOEdit);
		learningOutcomeRepository.save(learningOutcome);
	}

}
