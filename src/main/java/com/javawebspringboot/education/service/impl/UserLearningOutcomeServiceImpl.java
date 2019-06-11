package com.javawebspringboot.education.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserLearningOutcome;
import com.javawebspringboot.education.repository.LearningOutcomeRepository;
import com.javawebspringboot.education.repository.UserLearningOutcomeRepository;
import com.javawebspringboot.education.service.UserLearningOutcomeService;

@Service
@Transactional
public class UserLearningOutcomeServiceImpl implements UserLearningOutcomeService {
	@Autowired
	private LearningOutcomeRepository learningOutcomeRepository;

	@Autowired
	private UserLearningOutcomeRepository userLearningOutcomeRepository;

	@Override
	public List<UserLearningOutcome> findByUser(User user) {
		List<LearningOutcome> learningOutcomeList = learningOutcomeRepository.findByDepartment(user.getDepartment());
		List<UserLearningOutcome> userLearningOutcomeList = userLearningOutcomeRepository.findByUser(user);

		for (LearningOutcome learningOutcome : learningOutcomeList) {
			boolean check = checkExistInUserLearningOutcome(learningOutcome, userLearningOutcomeList);
			if (check == true) {
				// da ton tai
			} else {
				// chua ton tai thi add vao userLearningOutcomeList
				// LO chua dat
				// can phai dat
				UserLearningOutcome userLearningOutcome = new UserLearningOutcome(
						userLearningOutcomeList.get(0).getUser(), learningOutcome, null);
				userLearningOutcomeList.add(userLearningOutcome);
			}
		}

		return userLearningOutcomeList;
	}

	private boolean checkExistInUserLearningOutcome(LearningOutcome learningOutcome,
			List<UserLearningOutcome> userLearningOutcomeList) {
		for (UserLearningOutcome userLearningOutcome : userLearningOutcomeList) {

			if (userLearningOutcome.getLearningOutcome().equals(learningOutcome)) {
				// da ton tai
				return true;
			}
		}

		return false;
	}

}
