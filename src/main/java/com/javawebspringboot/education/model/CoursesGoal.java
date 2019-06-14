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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coursegoal")
public class CoursesGoal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_course_goal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCoursesGoal;

	@Column(name = "sign")
	private String sign;

	@Column(name = "name_course_goal")
	private String nameCoursesGoal;

	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coursesGoal", fetch = FetchType.LAZY)
	private List<UserSubjectCoursesGoal> userSubjectCoursesgoalList;

	@ManyToMany(mappedBy = "coursesGoalList", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
	private List<Answer> answerList;

	@ManyToMany
	@JoinTable(name = "coursesgoal_learningoutcome", joinColumns = @JoinColumn(name = "id_courses_goal", referencedColumnName = "id_course_goal"),
			inverseJoinColumns = @JoinColumn(name = "id_learning_outcome", referencedColumnName = "id_learningoutcome"))

	private List<LearningOutcome> learningOutcomeList;

	public CoursesGoal() {
		super();
	}

	public CoursesGoal(String sign, String nameCoursesGoal, Subject subject,
			List<UserSubjectCoursesGoal> userSubjectCoursesgoalList, List<Answer> answerList,
			List<LearningOutcome> learningOutcomeList) {
		super();
		this.sign = sign;
		this.nameCoursesGoal = nameCoursesGoal;
		this.subject = subject;
		this.userSubjectCoursesgoalList = userSubjectCoursesgoalList;
		this.answerList = answerList;
		this.learningOutcomeList = learningOutcomeList;
	}

	public List<LearningOutcome> getLearningOutcomeList() {
		return learningOutcomeList;
	}

	public void setLearningOutcomeList(List<LearningOutcome> learningOutcomeList) {
		this.learningOutcomeList = learningOutcomeList;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public Integer getIdCoursesGoal() {
		return idCoursesGoal;
	}

	public void setIdCoursesGoal(Integer idCoursesGoal) {
		this.idCoursesGoal = idCoursesGoal;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNameCoursesGoal() {
		return nameCoursesGoal;
	}

	public void setNameCoursesGoal(String nameCoursesGoal) {
		this.nameCoursesGoal = nameCoursesGoal;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<UserSubjectCoursesGoal> getUserSubjectCoursesgoalList() {
		return userSubjectCoursesgoalList;
	}

	public void setUserSubjectCoursesgoalList(List<UserSubjectCoursesGoal> userSubjectCoursesgoalList) {
		this.userSubjectCoursesgoalList = userSubjectCoursesgoalList;
	}

}
