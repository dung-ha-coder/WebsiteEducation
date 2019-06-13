package com.javawebspringboot.education.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;
import com.javawebspringboot.education.service.ScoresService;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.service.UserLearningOutcomeService;
import com.javawebspringboot.education.service.UserService;
import com.javawebspringboot.education.service.UserSubjectCoursesGoalService;

@Controller
public class StudentHomeController {

    @Autowired
    private UserLearningOutcomeService userLearningOutcomeService;

    @Autowired
    private UserService userServiece;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScoresService scoresService;

    @Autowired
    private UserSubjectCoursesGoalService userSubjectCoursesGoalService;

    @RequestMapping("/student")
    public String studentHomePage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        
        List<Subject> listSubject = userServiece.getListSubject(user.getSubjects());
      
        model.addAttribute("listSubject", listSubject);

        return "student/homeStudent";
    }

    @RequestMapping(value = "/chuan-mon-hoc/subject/{idSubject}")
    public String showInfoSubject(@PathVariable(name = "idSubject") Integer idSubject, Model model) {
        Subject subject = subjectService.findByIdSubject(idSubject);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());

        List<UserSubjectCoursesGoal> list = userSubjectCoursesGoalService
                .findByUserAndSubjectOrderByCoursesGoalAsc(user, subject);
        model.addAttribute("userSubjectCoursesGoal", list);
        List<String> label = new ArrayList<String>();
        List<Float> point = new ArrayList<Float>();
        for (UserSubjectCoursesGoal userSubjectCoursesGoal : list) {
            label.add(userSubjectCoursesGoal.getCoursesGoal().getSign());
            point.add(userSubjectCoursesGoal.getPercent());
        }

        model.addAttribute("label", label);
        model.addAttribute("point", point);

        return "student/infoSubject";
    }

    @RequestMapping("/student/xem-bang-diem")
    public String xemBangDiem(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());

        model.addAttribute("scoresTable", scoresService.findByUser(user));
        model.addAttribute("sumNumberOfCredit", scoresService.sumNumberOfCredit(user));
        model.addAttribute("avg", scoresService.scoreAvg(user));

        return "student/scoresTableStudent";
    }

    @RequestMapping("/student/xem-chuan-dau-ra")
    public String xemChuanDauRa(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());
        model.addAttribute("listLO", userLearningOutcomeService.findByUser(user));
        return "student/learningOutcome";
    }

    @RequestMapping("/student/xem-thong-tin-ca-nhan")
    public String xemThongTinCaNhan(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userServiece.findByUsername(userDetails.getUsername()));

        return "student/studentProfile";
    }

    @RequestMapping("/student/dang-ky-mon-hoc")
    public String registerSubject(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user);
        List<Subject> listSubject = subjectService.registerSubject();
        model.addAttribute("listSubject", listSubject);
        return "student/registerSubject";
    }

    @RequestMapping("/student/{idUser}/dang-ki-mon-hoc/{idSubject}")
    public String registerSb(@PathVariable(name = "idUser") Integer idUser,
            @PathVariable(name = "idSubject") Integer idSubject) {
        userServiece.registerSubject(idUser, idSubject);
        return "redirect:/student/danh-sach-mon-hoc-dang-ky";
    }

    @RequestMapping("/student/danh-sach-mon-hoc-hoan-thanh")
    public String subjectComplete(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());

        model.addAttribute("user", user);

        List<Subject> listSubject = userServiece.getListSubjectComplete(user.getSubjects());
      
        model.addAttribute("listSubject", listSubject);
        return "student/subjectComplete";
    }
    
    
    //student/danh-sach-mon-hoc-dang-ky
    
    @RequestMapping("/student/danh-sach-mon-hoc-dang-ky")
    public String subjectRegister(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userServiece.findByUsername(userDetails.getUsername());

        model.addAttribute("user", user);

        List<Subject> listSubject = userServiece.getListSubjectRegister(user.getSubjects());
      
        model.addAttribute("listSubject", listSubject);
        return "student/subjectRegister";
    }

}
