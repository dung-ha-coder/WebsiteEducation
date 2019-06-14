package com.javawebspringboot.education.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "scores_table")
public class ScoresTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_score_table")
	private Integer idScoreTable;

	@Column(name = "score_process")
	private Float scoreProcess;

	@Column(name = "score_practice")
	private Float scorePractice;

	@Column(name = "score_mid_term")
	private Float scoreMidTerm;

	@Column(name = "score_end_term")
	private Float scoreEndTerm;

	@Column(name = "score_average")
	private Float scoreAverage;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private User user;

	@JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
	@ManyToOne(fetch = FetchType.LAZY , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Subject subject;

	public ScoresTable() {
		super();
	}

	public ScoresTable(Float scoreProcess, Float scorePractice, Float scoreMidTerm, Float scoreEndTerm,
			Float scoreAverage, User user, Subject subject) {
		super();
		this.scoreProcess = scoreProcess;
		this.scorePractice = scorePractice;
		this.scoreMidTerm = scoreMidTerm;
		this.scoreEndTerm = scoreEndTerm;
		this.scoreAverage = scoreAverage;
		this.user = user;
		this.subject = subject;
	}

	public Integer getIdScoreTable() {
		return idScoreTable;
	}

	public void setIdScoreTable(Integer idScoreTable) {
		this.idScoreTable = idScoreTable;
	}

	public Float getScoreProcess() {
		return scoreProcess;
	}

	public void setScoreProcess(Float scoreProcess) {
		this.scoreProcess = scoreProcess;
	}

	public Float getScorePractice() {
		return scorePractice;
	}

	public void setScorePractice(Float scorePractice) {
		this.scorePractice = scorePractice;
	}

	public Float getScoreMidTerm() {
		return scoreMidTerm;
	}

	public void setScoreMidTerm(Float scoreMidTerm) {
		this.scoreMidTerm = scoreMidTerm;
	}

	public Float getScoreEndTerm() {
		return scoreEndTerm;
	}

	public void setScoreEndTerm(Float scoreEndTerm) {
		this.scoreEndTerm = scoreEndTerm;
	}

	public Float getScoreAverage() {
		return scoreAverage;
	}

	public void setScoreAverage(Float scoreAverage) {
		this.scoreAverage = scoreAverage;
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

}
