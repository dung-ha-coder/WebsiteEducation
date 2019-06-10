package com.javawebspringboot.education.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	public Department() {
		super();
	}

	public Department(String nameDepartment, User userHeadDepartment, List<User> userList) {
		super();
		this.nameDepartment = nameDepartment;
		this.userHeadDepartment = userHeadDepartment;
		this.userList = userList;
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
