package com.javawebspringboot.education.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_answer")
	private Integer idAnswer;

	@Column(name = "id_exam")
	private Integer idExam;

	@JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
	@ManyToOne(fetch = FetchType.LAZY)
	private Subject subject;

	@JoinTable(name = "answer_coursesgoal", joinColumns = {
			@JoinColumn(name = "id_answer", referencedColumnName = "id_answer") }, inverseJoinColumns = {
					@JoinColumn(name = "id_coursesgoal", referencedColumnName = "id_course_goal") })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<CoursesGoal> coursesGoalList;

	@Column(name = "content_answer")
	private String contentAnswer;

	public Answer() {
		super();
	}

	public Answer(Integer idExam, Subject subject, List<CoursesGoal> coursesGoalList, String contentAnswer) {
		super();
		this.idExam = idExam;
		this.subject = subject;
		this.coursesGoalList = coursesGoalList;
		this.contentAnswer = contentAnswer;
	}

	public Answer(Integer idExam, String contentAnswer) {
		super();
		this.idExam = idExam;
		this.contentAnswer = contentAnswer;
	}
	
	

	public Answer(Integer idExam, Subject subject, String contentAnswer) {
		super();
		this.idExam = idExam;
		this.subject = subject;
		this.contentAnswer = contentAnswer;
	}

	public Answer(Integer idExam) {
		super();
		this.idExam = idExam;
	}

	public String getContentAnswer() {
		return contentAnswer;
	}

	public void setContentAnswer(String contentAnswer) {
		this.contentAnswer = contentAnswer;
	}

	public Integer getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(Integer idAnswer) {
		this.idAnswer = idAnswer;
	}

	public Integer getIdExam() {
		return idExam;
	}

	public void setIdExam(Integer idExam) {
		this.idExam = idExam;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<CoursesGoal> getCoursesGoalList() {
		return coursesGoalList;
	}

	public void setCoursesGoalList(List<CoursesGoal> coursesGoalList) {
		this.coursesGoalList = coursesGoalList;
	}

}
