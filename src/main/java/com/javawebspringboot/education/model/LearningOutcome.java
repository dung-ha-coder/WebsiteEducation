package com.javawebspringboot.education.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@ManyToOne
	@JoinColumn(name = "id_department")
	private Department department;

	@OneToMany(mappedBy = "learningOutcome", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	private List<UserLearningOutcome> userLearningOutcomeList;

	//
	@ManyToMany(mappedBy = "learningOutcomeList")
	private List<CoursesGoal> coursesGoalsList;

	public LearningOutcome() {
		super();
	}

	public LearningOutcome(String sign, String nameLearningOutcome, Department department,
			List<UserLearningOutcome> userLearningOutcomeList, List<CoursesGoal> coursesGoalsList) {
		super();
		this.sign = sign;
		this.nameLearningOutcome = nameLearningOutcome;
		this.department = department;
		this.userLearningOutcomeList = userLearningOutcomeList;
		this.coursesGoalsList = coursesGoalsList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setUserLearningOutcomeList(List<UserLearningOutcome> userLearningOutcomeList) {
		this.userLearningOutcomeList = userLearningOutcomeList;
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
