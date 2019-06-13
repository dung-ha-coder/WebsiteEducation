package com.javawebspringboot.education.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.Role;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.repository.SubjectRepository;
import com.javawebspringboot.education.repository.UserRepository;
import com.javawebspringboot.education.service.UserService;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAllLecturer(Pageable pageable) {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(2));
        return userRepository.findByRoleList(listRole, pageable);
    }

    @Override
    public Page<User> findAllStudent(Pageable pageable) {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(1));
        return userRepository.findByRoleList(listRole, pageable);
    }

    @Override
    public List<User> findByDepartmentAndRoleListLecturer(Department department) {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(2));
        return userRepository.findByDepartmentAndRoleList(department, listRole);
    }

    @Override
    public List<User> findByDepartmentAndRoleListStudent(Department department) {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(1));
        return userRepository.findByDepartmentAndRoleList(department, listRole);
    }

    @Override
    public List<User> findAllLecturer() {
        List<Role> listRole = new ArrayList<Role>();
        listRole.add(new Role(2));
        return userRepository.findByRoleList(listRole);
    }

    @Override
    public void registerSubject(Integer idUser, Integer idSubject) {
        User user = userRepository.findByIdUser(idUser);
        Subject subject = subjectRepository.findByIdSubject(idSubject);
        for (Subject subject1 : user.getSubjects()) {

            if (subject.equals(subject1)) {
                // trung mon hoc da dang ky
                return;

            }
        }
        user.getSubjects().add(subject);
        userRepository.save(user);

    }

    @Override
    public List<Subject> getListSubjectComplete(List<Subject> listSujbect) {

        Date date = new Date();
        for (int i = 0; i < listSujbect.size(); i++) {
            Date d = new Date(listSujbect.get(i).getEndTime().getTime());
            if (date.before(d)) {
                listSujbect.remove(listSujbect.get(i));
                i--;
            }
        }

        return listSujbect;

    }

    @Override
    public List<Subject> getListSubjectRegister(List<Subject> listSujbect) {

        Date date = new Date();
        for (int i = 0; i < listSujbect.size(); i++) {
            Date d = new Date(listSujbect.get(i).getStartTime().getTime());
            if (date.after(d)) {
                listSujbect.remove(listSujbect.get(i));
                i--;
            }
        }

        return listSujbect;
    }

    @Override
    public List<Subject> getListSubject(List<Subject> listSujbect) {

        Date date = new Date();
        for (int i = 0; i < listSujbect.size(); i++) {
            Date start = new Date(listSujbect.get(i).getStartTime().getTime());
            Date end = new Date(listSujbect.get(i).getEndTime().getTime());
            if (date.after(start) && date.before(end)) {

            } else {
                listSujbect.remove(listSujbect.get(i));
                i--;
            }
        }

        return listSujbect;

    }

}
