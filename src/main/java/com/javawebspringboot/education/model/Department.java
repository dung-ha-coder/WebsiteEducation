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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_department")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDepartment;

	@Column(name = "name_department")
	private String nameDepartment;

	@OneToOne
	@JoinColumn(name = "id_user_head_department")
	private User userHeadDepartment;

	@OneToMany(mappedBy = "department")
	private List<User> userList;

	@OneToMany(mappedBy = "department", cascade=  CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<LearningOutcome> learningOutcomeList;

	@OneToMany(mappedBy = "department")
	private List<Subject> subjectList;

	public Department() {
		super();
	}

	public Department(String nameDepartment, User userHeadDepartment, List<User> userList,
			List<LearningOutcome> learningOutcomeList, List<Subject> subjectList) {
		super();
		this.nameDepartment = nameDepartment;
		this.userHeadDepartment = userHeadDepartment;
		this.userList = userList;
		this.learningOutcomeList = learningOutcomeList;
		this.subjectList = subjectList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<LearningOutcome> getLearningOutcomeList() {
		return learningOutcomeList;
	}

	public void setLearningOutcomeList(List<LearningOutcome> learningOutcomeList) {
		this.learningOutcomeList = learningOutcomeList;
	}

	public User getUserHeadDepartment() {
		return userHeadDepartment;
	}

	public void setUserHeadDepartment(User userHeadDepartment) {
		this.userHeadDepartment = userHeadDepartment;
	}

	public Integer getIdDepartment() {
		return idDepartment;
	}

	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getNameDepartment() {
		return nameDepartment;
	}

	public void setNameDepartment(String nameDepartment) {
		this.nameDepartment = nameDepartment;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
