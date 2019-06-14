package com.javawebspringboot.education.controller.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;
import com.javawebspringboot.education.service.CoursesGoalService;
import com.javawebspringboot.education.service.LearningOutcomeService;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.service.UserService;
import com.javawebspringboot.education.service.UserSubjectCoursesGoalService;

@Controller
public class ManageController {

	@Autowired
	private UserSubjectCoursesGoalService userSubjectCoursesGoalService;

	
	@Autowired
	private CoursesGoalService coursesGoalService;

	@Autowired
	private UserService userService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private LearningOutcomeService learningOutcomeService;

	@RequestMapping("/manage/")
	public String studentHomePage() {
		return "manage/homeManage";
	}

	@RequestMapping("/manage/xem-danh-sach-giang-vien")
	public String showListLecturer(Model model, @RequestParam("page") Optional<Integer> page) {
		Sort sortable = Sort.by("idUser").ascending();
		int size = 10;
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<User> listLecturer = userService.findAllLecturer(pageable);
		model.addAttribute("listLecturer", listLecturer);

		int totalPages = listLecturer.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "manage/listLecturer";
	}

	@RequestMapping("/manage/xem-danh-sach-sinh-vien")
	public String showListStudent(Model model, @RequestParam("page") Optional<Integer> page) {
		Sort sortable = Sort.by("idUser").ascending();
		int size = 10;
		int currentPage = page.orElse(1);
		Pageable pageable = PageRequest.of(currentPage - 1, size, sortable);
		Page<User> userList = userService.findAllStudent(pageable);

		model.addAttribute("listStudent", userList);

		int totalPages = userList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "manage/listStudent";
	}

	@RequestMapping("/manage/xem-thong-tin-sinh-vien/{username}")
	public String showInfoStudent(Model model, @PathVariable(name = "username") String username) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "manage/infoStudent";
	}

	@RequestMapping("/manage/xem-thong-tin-giang-vien/{username}")
	public String showInfoLecturer(Model model, @PathVariable(name = "username") String username) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("listSubject", subjectService.findByTeacherOrPracticeTeacher(user, user));
		return "manage/infoLecturer";
	}

	@RequestMapping("/manage/xem-danh-sach-cac-mon-hoc")
	public String showListSubject(Model model) {
		model.addAttribute("listSubject", subjectService.findAllByOrderByStartTimeAsc());
		return "manage/listSubject";
	}

	@RequestMapping("/manage/xem-thong-tin-mon-hoc/{idSubject}")
	public String showInfoSubject(Model model, @PathVariable(name = "idSubject") Integer idSubject) {
		Subject subject = subjectService.findByIdSubject(idSubject);
		model.addAttribute("coursesGoalList", coursesGoalService.findBySubjectOrderBySignAsc(subject));
		model.addAttribute("subject", subject);
		return "manage/infoSubject";
	}

	@RequestMapping("/manage/xem-chuan-mon-hoc/{idSubject}/student/{username}")
	public String getCoursesGoalStudentSubject(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "username") String username) {

		User sinhVien = userService.findByUsername(username);
		Subject subject = subjectService.findByIdSubject(idSubject);
		List<UserSubjectCoursesGoal> userSubjectCoursesGoalList = userSubjectCoursesGoalService
				.findByUserAndSubject(sinhVien, subject);
		model.addAttribute("userSubjectCoursesGoalList", userSubjectCoursesGoalList);
		model.addAttribute("user", sinhVien);

		List<String> label = new ArrayList<String>();
		List<Float> point = new ArrayList<Float>();
		for (UserSubjectCoursesGoal userSubjectCoursesGoal : userSubjectCoursesGoalList) {
			label.add(userSubjectCoursesGoal.getCoursesGoal().getSign());
			point.add(userSubjectCoursesGoal.getPercent());
		}

		model.addAttribute("label", label);
		model.addAttribute("point", point);

		return "manage/userSubjectCoursesGoalList";

	}

}
