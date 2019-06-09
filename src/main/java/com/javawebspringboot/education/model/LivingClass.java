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
@Table(name = "living_class")
public class LivingClass implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_living_class")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLivingClass;

	@Column(name = "name_living_class")
	private String nameLivingClass;

	@OneToOne
	@JoinColumn(name = "id_user_adviser")
	private User userAdviser;

	@OneToMany(mappedBy = "livingClass")
	private List<User> user;

	public LivingClass() {
		super();
	}

	public LivingClass(String nameLivingClass, User userAdviser, List<User> user) {
		super();
		this.nameLivingClass = nameLivingClass;
		this.userAdviser = userAdviser;
		this.user = user;
	}

	public Integer getIdLivingClass() {
		return idLivingClass;
	}

	public void setIdLivingClass(Integer idLivingClass) {
		this.idLivingClass = idLivingClass;
	}

	public String getNameLivingClass() {
		return nameLivingClass;
	}

	public void setNameLivingClass(String nameLivingClass) {
		this.nameLivingClass = nameLivingClass;
	}

	public User getUserAdviser() {
		return userAdviser;
	}

	public void setUserAdviser(User userAdviser) {
		this.userAdviser = userAdviser;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
