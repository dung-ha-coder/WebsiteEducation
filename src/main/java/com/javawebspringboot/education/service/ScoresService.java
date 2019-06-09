package com.javawebspringboot.education.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.utiles.TableScore;

public interface ScoresService {

	List<ScoresTable> findByUser(User user);

	List<ScoresTable> findBySubject(Subject subject);

	void saveFileExcelToDisk(MultipartFile file);

	boolean checkFullScore(String mssv, Subject subject);
	
	void updateScoreAverage(List<TableScore>lisTableScores,Subject subject);
	
	int sumNumberOfCredit(User student);
	float scoreAvg(User sinhVien);

	void getDataChart(List<String> label, List<Float> point, List<ScoresTable> scoresTables);

}
