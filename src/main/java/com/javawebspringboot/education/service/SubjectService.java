package com.javawebspringboot.education.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.exception.ReadFileException;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.utiles.TableScore;
import javax.servlet.http.HttpServletResponse;

public interface SubjectService {

    Subject findByIdSubject(Integer idSubject);

    List<TableScore> fileHandler(MultipartFile fileExcel) throws ReadFileException;

    void readData(List<TableScore> listTableScore, Subject subject, String cotDiem);

    List<Subject> findAllByOrderByStartTimeAsc();

	void newSubject(Subject subject, Integer idDepartment);

    void saveAnswer(Integer idSubject, Integer idExam, String contentAnswer);

    List<Subject> findAllSubjectBy(User user);

    List<Subject> registerSubject();

    public void dowloadFile(Integer idSubject, String strCotDiem, HttpServletResponse response);

	List<Subject> findByDepartmentOrderByStartTimeAsc(Integer idDepartment);

	 List<Subject> findByTeacherOrPracticeTeacher(User teacher, User practiceTeacher);
}
