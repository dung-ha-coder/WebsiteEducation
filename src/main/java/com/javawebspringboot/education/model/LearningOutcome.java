package com.javawebspringboot.education.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "learningoutcome")
public class LearningOutcome implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_learningoutcome")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLearningOutcome;

	@Column(name = "sign")
	private String sign;

	@Column(name = "namelearningoutcome")
	private String nameLearningOutcome;

	@OneToMany(mappedBy = "learningOutcome", fetch = FetchType.LAZY)
	private List<UserLearningOutcome> userLearningOutcomeList;

	@ManyToMany
	@JoinTable(name = "coursesgoal_learningoutcome", joinColumns = @JoinColumn(name = "id_learning_outcome", referencedColumnName = "id_learningoutcome"), inverseJoinColumns = @JoinColumn(name = "id_courses_goal", referencedColumnName = "id_course_goal"))
	private List<CoursesGoal> coursesGoalsList;

	public LearningOutcome() {
		super();
	}

	public LearningOutcome(String sign, String nameLearningOutcome, List<UserLearningOutcome> userLearningOutcomeList,
			List<CoursesGoal> coursesGoalsList) {
		super();
		this.sign = sign;
		this.nameLearningOutcome = nameLearningOutcome;
		this.userLearningOutcomeList = userLearningOutcomeList;
		this.coursesGoalsList = coursesGoalsList;
	}

	public Integer getIdLearningOutcome() {
		return idLearningOutcome;
	}

	public void setIdLearningOutcome(Integer idLearningOutcome) {
		this.idLearningOutcome = idLearningOutcome;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNameLearningOutcome() {
		return nameLearningOutcome;
	}

	public void setNameLearningOutcome(String nameLearningOutcome) {
		this.nameLearningOutcome = nameLearningOutcome;
	}

	public List<UserLearningOutcome> getUserLearningOutcomeList() {
		return userLearningOutcomeList;
	}

	public void setUserLearningoutcomeList(List<UserLearningOutcome> userLearningOutcomeList) {
		this.userLearningOutcomeList = userLearningOutcomeList;
	}

	public List<CoursesGoal> getCoursesGoalsList() {
		return coursesGoalsList;
	}

	public void setCoursesGoalsList(List<CoursesGoal> coursesGoalsList) {
		this.coursesGoalsList = coursesGoalsList;
	}

}
