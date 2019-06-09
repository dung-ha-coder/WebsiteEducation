package com.javawebspringboot.education.utiles;

import java.util.List;

public class TableScore {

	private String codeStudent;
	private String nameStudent;
	private List<Float> scoreList;

	public TableScore() {
	}

	public TableScore(String codeStudent, String nameStudent, List<Float> scoreList) {
		super();
		this.codeStudent = codeStudent;
		this.nameStudent = nameStudent;
		this.scoreList = scoreList;
	}

	public String getCodeStudent() {
		return codeStudent;
	}

	public void setCodeStudent(String codeStudent) {
		this.codeStudent = codeStudent;
	}

	public String getNameStudent() {
		return nameStudent;
	}

	public void setNameStudent(String nameStudent) {
		this.nameStudent = nameStudent;
	}

	public List<Float> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Float> scoreList) {
		this.scoreList = scoreList;
	}

}
