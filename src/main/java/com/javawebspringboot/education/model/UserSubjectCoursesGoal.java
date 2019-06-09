package com.javawebspringboot.education.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_subject_coursesgoal")
public class UserSubjectCoursesGoal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_user_subject_coursesgoal")
	private Integer idUserSubjectCoursesgoal;

	@JoinColumn(name = "id_coursesgoal", referencedColumnName = "id_course_goal")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private CoursesGoal coursesGoal;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Subject subject;

	@Column(name = "percent")
	private Float percent;


	public UserSubjectCoursesGoal() {
	}

	public UserSubjectCoursesGoal(CoursesGoal coursesGoal, User user, Subject subject, Float percent) {
		super();
		this.coursesGoal = coursesGoal;
		this.user = user;
		this.subject = subject;
		this.percent = percent;
	}

	public Integer getIdUserSubjectCoursesgoal() {
		return idUserSubjectCoursesgoal;
	}

	public void setIdUserSubjectCoursesgoal(Integer idUserSubjectCoursesgoal) {
		this.idUserSubjectCoursesgoal = idUserSubjectCoursesgoal;
	}

	public CoursesGoal getCoursesGoal() {
		return coursesGoal;
	}

	public void setCoursesGoal(CoursesGoal coursesGoal) {
		this.coursesGoal = coursesGoal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Float getPercent() {
		return percent;
	}

	public void setPercent(Float percent) {
		this.percent = percent;
	}

	

}
