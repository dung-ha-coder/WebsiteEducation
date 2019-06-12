package com.javawebspringboot.education.controller.lecturer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.exception.ReadFileException;
import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.service.DepartmentService;
import com.javawebspringboot.education.service.LearningOutcomeService;
import com.javawebspringboot.education.service.LivingClassService;
import com.javawebspringboot.education.service.ScoresService;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.service.UserService;
import com.javawebspringboot.education.utiles.TableScore;

@Controller
public class LecturerController {
	@Autowired
	private LearningOutcomeService learningOutcomeService;

	@Autowired
	private LivingClassService livingClassService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ScoresService scoreService;

	@RequestMapping("/lecturer/")
	public String studentHomePage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		return "lecturer/homeLecturer";
	}

	@RequestMapping(value = "/lecturer/subject/{idSubject}")
	public String showContest(Model model, @PathVariable(name = "idSubject") Integer idSubject) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		Subject subject = subjectService.findByIdSubject(idSubject);
		model.addAttribute("subject", subject);
		List<ScoresTable> scoresTables = scoreService.findBySubject(subject);
		model.addAttribute("subjectScore", scoresTables);

		List<String> label = new ArrayList<String>();
		List<Float> point = new ArrayList<Float>();
		scoreService.getDataChart(label, point, scoresTables);
		model.addAttribute("label", label);
		model.addAttribute("point", point);

		// doc file excel co the bi loi
		// bi loi thi vao controller nay va lay loi tra ve view
		if (ReadFileException.getMsgError() != "") {
			model.addAttribute("status", new ReadFileException().getMessage());
			ReadFileException.setMsgError("");
		}

		return "lecturer/subject";
	}

	@RequestMapping(value = "/lecturer/subject/{idSubject}/upload/{cotDiem}")
	public String readFileExcel(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "cotDiem") String cotDiem, @RequestParam(name = "fileExcel") MultipartFile fileExcel) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		List<TableScore> lisTableScores = null;
		try {
			lisTableScores = new ArrayList<>();
			lisTableScores = subjectService.fileHandler(fileExcel);
		} catch (ReadFileException ex) {
			return "redirect:/lecturer/subject/{idSubject}";
		}

		// file excel khong bi loi
		// xu li file
		if (lisTableScores != null) {
			Subject subject = subjectService.findByIdSubject(idSubject);
			// subjectService.readData(lisTableScores, subject, cotDiem);
			boolean check = scoreService.checkFullScore(lisTableScores.get(0).getCodeStudent(), subject);
			System.out.println("check " + check);
			if (check == true) {
				// day du cac cot diem
				// tinh diem trung binh cho sinh vien
				scoreService.updateScoreAverage(lisTableScores, subject);
			} else {
				// chua du cac cot diem
				// bo qua

			}
			// scoreService.saveFileExcelToDisk(fileExcel);

		}
		return "redirect:/lecturer/subject/{idSubject}";
	}

	@RequestMapping("/lecturer/co-van-hoc-tap-lop-{nameLivingClass}")
	public String showLivingClass(Model model, @PathVariable(name = "nameLivingClass") String nameLivingClass) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		model.addAttribute("livingClass", livingClassService.findByNameLivingClass(nameLivingClass));

		return "lecturer/livingClass";

	}

	@RequestMapping("/lecturer/thong-tin-sinh-vien/lop-{nameLivingClass}/username-{username}")
	public String showInfoStudent(Model model, @PathVariable(name = "nameLivingClass") String nameLivingClass,
			@PathVariable(name = "username") String username) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		// thong tin user tim kiem
		User sinhVien = userService.findByUsername(username);
		model.addAttribute("users", sinhVien);

		model.addAttribute("sumNumberOfCredit", scoreService.sumNumberOfCredit(sinhVien));
		model.addAttribute("avg", scoreService.scoreAvg(sinhVien));
		return "lecturer/infoStudentInLivingClass";
	}

	@RequestMapping("/lecturer/xem-danh-sach-user-trong-khoa/{idDepartment}")
	public String showListUserDepartment(Model model, @PathVariable(name = "idDepartment") Integer idDepartment) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		// tim ds user
		Department department = departmentService.findByIdDepartment(idDepartment);
		model.addAttribute("listLecturer", userService.findByDepartmentAndRoleListLecturer(department));
		model.addAttribute("listStudent", userService.findByDepartmentAndRoleListStudent(department));

		return "lecturer/listUserDepartment";
	}

	@RequestMapping("/lecturer/subject/{idSubject}/upload/update/{cotDiem}")
	public String updateScore(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "cotDiem") String cotDiem, @RequestParam(name = "fileExcel") MultipartFile fileExcel) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		List<TableScore> lisTableScores = null;
		try {
			lisTableScores = new ArrayList<>();
			lisTableScores = subjectService.fileHandler(fileExcel);
		} catch (ReadFileException ex) {
			return "redirect:/lecturer/subject/{idSubject}";
		}

		// file excel khong bi loi
		// xu li file
		if (lisTableScores != null) {
			Subject subject = subjectService.findByIdSubject(idSubject);
			// subjectService.readData(lisTableScores, subject, cotDiem);
			boolean check = scoreService.checkFullScore(lisTableScores.get(0).getCodeStudent(), subject);
			System.out.println("check " + check);
			if (check == true) {
				// day du cac cot diem
				// tinh diem trung binh cho sinh vien
				// scoreService.updateScoreAverage(lisTableScores, subject);
			} else {
				// chua du cac cot diem
				// bo qua

			}
			// scoreService.saveFileExcelToDisk(fileExcel);

		}
		return "redirect:/lecturer/subject/{idSubject}";
	}

	@RequestMapping("/lecturer/them-chuan-dau-ra-chuong-trinh-dao-tao/khoa/{idDepartment}")
	public String addLearningOutcome(Model model, @PathVariable(name = "idDepartment") Integer idDepartment) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		model.addAttribute("department", departmentService.findByIdDepartment(idDepartment));

		return "lecturer/addLearningOutcome";
	}

	@RequestMapping("/lecturer/them-moi-lo/{idDepartment}")
	public String newLearningOutcome(Model model, @PathVariable(name = "idDepartment") Integer idDepartment,
			@RequestParam(name = "txtLO") String txtLO) {
		Department department = departmentService.findByIdDepartment(idDepartment);
		model.addAttribute("department", department);

		learningOutcomeService.newLearningOutcome(department, txtLO);
		return "redirect:/lecturer/them-chuan-dau-ra-chuong-trinh-dao-tao/khoa/{idDepartment}";
	}

	@RequestMapping("/lecturer/xoa-lo/{idLearningOutcome}/khoa/{idDepartment}")
	public String xoaLo(Model model, @PathVariable(name = "idLearningOutcome") Integer idLearningOutcome,
			@PathVariable(name = "idDepartment") Integer idDepartment) {

		learningOutcomeService.delteLearningOutcome(idLearningOutcome);

		return "redirect:/lecturer/them-chuan-dau-ra-chuong-trinh-dao-tao/khoa/{idDepartment}";
	}

	@RequestMapping("/lecturer/sua-learning-outcome/{idLearningOutcome}/khoa/{idDepartment}")
	public String showEditFormLO(Model model, @PathVariable(name = "idLearningOutcome") Integer idLearningOutcome,
			@PathVariable(name = "idDepartment") Integer idDepartment) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		Department department = departmentService.findByIdDepartment(idDepartment);
		model.addAttribute("department", department);
		model.addAttribute("lo", learningOutcomeService.findByIdLearningOutcome(idLearningOutcome));
		return "lecturer/editLO";
	}

	@RequestMapping("/lecturer/edit-learning-outcome/{idLearningOutcome}/khoa/{idDepartment}")
	public String eidtLo(@PathVariable(name = "idLearningOutcome") Integer idLearningOutcome,
			@PathVariable(name = "idDepartment") Integer idDepartment,
			@RequestParam(name = "txtLOEdit") String txtLOEdit) {
		learningOutcomeService.editLO(idLearningOutcome,txtLOEdit);
		return "redirect:/lecturer/them-chuan-dau-ra-chuong-trinh-dao-tao/khoa/{idDepartment}";

	}
}
