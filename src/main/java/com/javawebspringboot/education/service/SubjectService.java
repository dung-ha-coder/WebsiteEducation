package com.javawebspringboot.education.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.exception.ReadFileException;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.utiles.TableScore;

public interface SubjectService {

	Subject findByIdSubject(Integer idSubject);

	List<TableScore> fileHandler(MultipartFile fileExcel) throws ReadFileException;

	void readData(List<TableScore> listTableScore, Subject subject, String cotDiem);

	List<Subject> findAllByOrderByStartTimeAsc();

	void newSubject(Subject subject);

	void saveAnswer(Integer idSubject, Integer idExam, String contentAnswer);

}
