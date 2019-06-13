package com.javawebspringboot.education.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;

public interface UserService {

    User findByUsername(String username);

    Page<User> findAllLecturer(Pageable pageable);

    List<User> findAllLecturer();

    Page<User> findAllStudent(Pageable pageable);

    List<User> findByDepartmentAndRoleListLecturer(Department department);

    List<User> findByDepartmentAndRoleListStudent(Department department);

    void registerSubject(Integer idUser, Integer idSubject);

    List<Subject> getListSubjectComplete(List<Subject> subjects);

    public List<Subject> getListSubjectRegister(List<Subject> subjects);

    public List<Subject> getListSubject(List<Subject> subjects);
}
