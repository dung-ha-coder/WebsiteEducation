package com.javawebspringboot.education.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject")

public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_subject")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubject;

	@Column(name = "name_subject")
	private String nameSubject;

	@Column(name = "code_subject")
	private String codeSubject;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "rate_process")
	private Float rateProcess;

	@Column(name = "rate_practice")
	private Float ratePractice;

	@Column(name = "rate_mid_term")
	private Float rateMidTerm;

	@Column(name = "reate_end_term")
	private Float reateEndTerm;

	@Column(name = "number_of_credit")
	private Integer numberOfCredits;

	@ManyToMany(mappedBy = "subjects")
	private List<User> userList;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
	private List<CoursesGoal> coursesGoalList;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
	private List<ScoresTable> scoresList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject", fetch = FetchType.LAZY)
	private List<UserSubjectCoursesGoal> userSubjectCoursesgoalList;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Answer> answerList;

	@JoinColumn(name = "id_teacher", referencedColumnName = "id_user")
	@ManyToOne(fetch = FetchType.LAZY)
	private User teacher;

	@JoinColumn(name = "id_practice_teacher", referencedColumnName = "id_user")
	@ManyToOne(fetch = FetchType.LAZY)
	private User practiceTeacher;

	@OneToOne
	@JoinColumn(name = "id_department")
	private Department department;

	public Subject() {
		super();
	}

	public Subject(String nameSubject, String codeSubject, Date startTime, Date endTime, Float rateProcess,
			Float ratePractice, Float rateMidTerm, Float reateEndTerm, Integer numberOfCredits, List<User> userList,
			List<CoursesGoal> coursesGoalList, List<ScoresTable> scoresList,
			List<UserSubjectCoursesGoal> userSubjectCoursesgoalList, List<Answer> answerList, User teacher,
			User practiceTeacher, Department department) {
		super();
		this.nameSubject = nameSubject;
		this.codeSubject = codeSubject;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rateProcess = rateProcess;
		this.ratePractice = ratePractice;
		this.rateMidTerm = rateMidTerm;
		this.reateEndTerm = reateEndTerm;
		this.numberOfCredits = numberOfCredits;
		this.userList = userList;
		this.coursesGoalList = coursesGoalList;
		this.scoresList = scoresList;
		this.userSubjectCoursesgoalList = userSubjectCoursesgoalList;
		this.answerList = answerList;
		this.teacher = teacher;
		this.practiceTeacher = practiceTeacher;
		this.department = department;
	}

	public Subject(String nameSubject, String codeSubject, Date startTime, Date endTime, Float rateProcess,
			Float ratePractice, Float rateMidTerm, Float reateEndTerm, User teacher, User practiceTeacher,
			Department department) {
		super();
		this.nameSubject = nameSubject;
		this.codeSubject = codeSubject;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rateProcess = rateProcess;
		this.ratePractice = ratePractice;
		this.rateMidTerm = rateMidTerm;
		this.reateEndTerm = reateEndTerm;
		this.teacher = teacher;
		this.practiceTeacher = practiceTeacher;
		this.department = department;
	}

	public User getPracticeTeacher() {
		return practiceTeacher;
	}

	public void setPracticeTeacher(User practiceTeacher) {
		this.practiceTeacher = practiceTeacher;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public Integer getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Integer idSubject) {
		this.idSubject = idSubject;
	}

	public Float getRateProcess() {
		return rateProcess;
	}

	public void setRateProcess(Float rateProcess) {
		this.rateProcess = rateProcess;
	}

	public Float getRatePractice() {
		return ratePractice;
	}

	public void setRatePractice(Float ratePractice) {
		this.ratePractice = ratePractice;
	}

	public Float getRateMidTerm() {
		return rateMidTerm;
	}

	public void setRateMidTerm(Float rateMidTerm) {
		this.rateMidTerm = rateMidTerm;
	}

	public Float getReateEndTerm() {
		return reateEndTerm;
	}

	public void setReateEndTerm(Float reateEndTerm) {
		this.reateEndTerm = reateEndTerm;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	public String getCodeSubject() {
		return codeSubject;
	}

	public void setCodeSubject(String codeSubject) {
		this.codeSubject = codeSubject;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(Integer numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<CoursesGoal> getCoursesGoalList() {
		return coursesGoalList;
	}

	public void setCoursesGoalList(List<CoursesGoal> coursesGoalList) {
		this.coursesGoalList = coursesGoalList;
	}

	public List<ScoresTable> getScoresList() {
		return scoresList;
	}

	public void setScoresList(List<ScoresTable> scoresList) {
		this.scoresList = scoresList;
	}

	public List<UserSubjectCoursesGoal> getUserSubjectCoursesgoalList() {
		return userSubjectCoursesgoalList;
	}

	public void setUserSubjectCoursesgoalList(List<UserSubjectCoursesGoal> userSubjectCoursesgoalList) {
		this.userSubjectCoursesgoalList = userSubjectCoursesgoalList;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

}
