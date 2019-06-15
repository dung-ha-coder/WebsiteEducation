package com.javawebspringboot.education.controller.lecturer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.exception.ReadFileException;
import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;
import com.javawebspringboot.education.service.AnswerService;
import com.javawebspringboot.education.service.CoursesGoalService;
import com.javawebspringboot.education.service.DepartmentService;
import com.javawebspringboot.education.service.LearningOutcomeService;
import com.javawebspringboot.education.service.LivingClassService;
import com.javawebspringboot.education.service.ScoresService;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.service.UserService;
import com.javawebspringboot.education.service.UserSubjectCoursesGoalService;
import com.javawebspringboot.education.utiles.TableScore;

@Controller
public class LecturerController {
	@Autowired
	private UserSubjectCoursesGoalService userSubjectCoursesGoalService;

	@Autowired
	private LearningOutcomeService learningOutcomeService;

	@Autowired
	private LivingClassService livingClassService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ScoresService scoreService;

	@Autowired
	private CoursesGoalService coursesGoalService;

	@RequestMapping("/lecturer/")
	public String studentHomePage(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("subject", subjectService.findAllSubjectBy(user));
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
		System.out.println("sixe " + scoresTables.size());
		model.addAttribute("subjectScore", scoresTables);
		List<String> label = new ArrayList<String>();
		List<Float> point = new ArrayList<Float>();
		boolean check = false;
		if (scoresTables.size() > 0) {
			check = scoreService.checkFullScore(scoresTables.get(0).getUser().getUsername(), subject);

		}

		if (scoresTables.size() != 0 && check == true) {

			scoreService.getDataChart(label, point, scoresTables);

		}
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
			subjectService.readData(lisTableScores, subject, cotDiem);
			boolean check = scoreService.checkFullScore(lisTableScores.get(0).getCodeStudent(), subject);
			if (check == true) {
				// day du cac cot diem
				// tinh diem trung binh cho sinh vien
				scoreService.updateScoreAverage(lisTableScores, subject);
			} else {
				// chua du cac cot diem
				// bo qua

			}
			scoreService.saveFileExcelToDisk(fileExcel);

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
		model.addAttribute("strName", department.getNameDepartment());
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
		learningOutcomeService.editLO(idLearningOutcome, txtLOEdit);
		return "redirect:/lecturer/them-chuan-dau-ra-chuong-trinh-dao-tao/khoa/{idDepartment}";

	}

	@RequestMapping("/lecturer/tao-file-excel/mon-hoc/{idSubject}/diem/{strCotDiem}")
	public void createFileExcel(HttpServletResponse response, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "strCotDiem") String strCotDiem) throws FileNotFoundException, IOException {

		subjectService.dowloadFile(idSubject, strCotDiem, response);

	}

	@RequestMapping(value = "/lecturer/them-mon-hoc/khoa/{idDepartment}", method = RequestMethod.GET)

	public String newSubjectFrom(Model model, @PathVariable(name = "idDepartment") Integer idDepartment) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		model.addAttribute("newSubject", new Subject());
		model.addAttribute("idDepartment", idDepartment);
		model.addAttribute("listLecturer", userService.findAllLecturer());
		return "lecturer/newSubject";
	}

	@RequestMapping(value = "/lecturer/them-mon-hoc/khoa/{idDepartment}", method = RequestMethod.POST)
	public String newSubject(Model model, @PathVariable(name = "idDepartment") Integer idDepartment,
			@ModelAttribute(name = "newSubject") Subject newSubject) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		subjectService.newSubject(newSubject, idDepartment);
		return "redirect:/lecturer";
	}

	@RequestMapping(value = "/lecturer/xem-danh-sach-mon-hoc/khoa/{idDepartment}")
	public String getListSubject(Model model, @PathVariable(name = "idDepartment") Integer idDepartment,
			@ModelAttribute(name = "newSubject") Subject newSubject) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);

		List<Subject> listSubject = subjectService.findByDepartmentOrderByStartTimeAsc(idDepartment);
		model.addAttribute("listSubject", listSubject);
		return "lecturer/listSubject";
	}

	@RequestMapping("/lecturer/xem-thong-tin-mon-hoc/{idSubject}")
	public String showInfoSubject(Model model, @PathVariable(name = "idSubject") Integer idSubject) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		Subject subject = subjectService.findByIdSubject(idSubject);
		model.addAttribute("coursesGoalList", coursesGoalService.findBySubjectOrderBySignAsc(subject));
		model.addAttribute("subject", subject);
		return "lecturer/infoSubject";
	}

	@RequestMapping("/lecturer/them-g-moi/subject/{idSubject}")
	public String addNewCoursesGoal(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@RequestParam(name = "txtG", required = false) String txtG) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu
		coursesGoalService.newCoursesGoal(idSubject, txtG);
		return "redirect:/lecturer/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/lecturer/xoa-g/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String deleteCoursesGoal(@PathVariable(name = "idCoursesGoal") Integer idCoursesGoal,
			@PathVariable(name = "idSubject") Integer idSubject) {
		coursesGoalService.deleteCoursesGoal(idCoursesGoal);
		return "redirect:/lecturer/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/lecturer/chinh-sua-g/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String editCoursesGoal(Model model, @PathVariable(name = "idCoursesGoal") Integer idCoursesGoal,
			@PathVariable(name = "idSubject") Integer idSubject) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		Subject subject = subjectService.findByIdSubject(idSubject);
		CoursesGoal coursesGoal = coursesGoalService.findByIdCoursesGoal(idCoursesGoal);

		model.addAttribute("coursesGoalList", coursesGoalService.findBySubjectOrderBySignAsc(subject));
		model.addAttribute("subject", subject);
		// chua LO thuoc mon hoc da check
		model.addAttribute("coursesGoal", coursesGoal);

		// lo cua khoa chua check, mon hoc thuoc khoa quan ly
		List<LearningOutcome> learningOutcomeList = learningOutcomeService.findByDepartment(subject.getDepartment());

		for (int i = 0; i < learningOutcomeList.size(); i++) {
			for (int j = 0; j < coursesGoal.getLearningOutcomeList().size(); j++) {
				if (learningOutcomeList.get(i).equals(coursesGoal.getLearningOutcomeList().get(j))) {
					learningOutcomeList.remove(learningOutcomeList.get(i));
				}
			}
		}

		model.addAttribute("learningOutcomeList", learningOutcomeList);
		return "lecturer/editCoursesGoal";
	}

	@RequestMapping("/lecturer/chinh-sua-thong-tin-mon-hoc/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String editSubject(Model model,
			@RequestParam(name = "lo", required = false, defaultValue = "0") List<Integer> idLOList,
			@RequestParam(name = "txtCoursesGoal") String txtCoursesGoal,
			@PathVariable(name = "idCoursesGoal") Integer idCoursesGoal) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu
		coursesGoalService.editCoursesGoal(idLOList, txtCoursesGoal, idCoursesGoal);
		return "redirect:/lecturer/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/lecturer/them-cau-hoi/subject/{idSubject}")
	public String newAnswer(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@RequestParam(name = "idExam") Integer idExam, @RequestParam(name = "contentAnswer") String contentAnswer) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu
		subjectService.saveAnswer(idSubject, idExam, contentAnswer);
		return "redirect:/lecturer/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/lecturer/xoa-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String deleteAnswer(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "idAnswer") Integer idAnswer) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu
		answerService.deleteAnswer(idAnswer);
		return "redirect:/lecturer/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/lecturer/sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String updateAnswer(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "idAnswer") Integer idAnswer) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		// chua tat ca cac G cua mon hoc
		Subject subject = subjectService.findByIdSubject(idSubject);

		Answer answer = answerService.findByIdAnswer(idAnswer);

		model.addAttribute("answer", answer);

		for (int i = 0; i < subject.getCoursesGoalList().size(); i++) {
			for (int j = 0; j < answer.getCoursesGoalList().size(); j++) {
				if (subject.getCoursesGoalList().get(i).equals(answer.getCoursesGoalList().get(j))) {
					subject.getCoursesGoalList().remove(answer.getCoursesGoalList().get(j));
				}
			}
		}
		model.addAttribute("subject", subject);

		return "lecturer/editAnswer";
	}

	@RequestMapping("/lecturer/chinh-sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String editAnswerAndCoursesGoal(Model model, @RequestParam(name = "idExam") Integer idExam,
			@RequestParam(name = "contentAnswer") String contentAnswer,
			@RequestParam(name = "g", required = false, defaultValue = "0") List<Integer> idGList,
			@PathVariable(name = "idSubject") Integer idSubject, @PathVariable(name = "idAnswer") Integer idAnswer) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu
		answerService.editAnswer(idGList, idAnswer, idExam, contentAnswer);
		return "redirect:/lecturer/sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}";
	}

	@RequestMapping("/lecturer/xem-courses-goal/subject/{idSubject}/sinh-vien/{username}")

	public String showGStudent(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "username") String username) {

		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		Subject subject = subjectService.findByIdSubject(idSubject);
		User sinhVien = userService.findByUsername(username);
		List<UserSubjectCoursesGoal> listGStudent = userSubjectCoursesGoalService
				.findByUserAndSubjectOrderByCoursesGoalAsc(sinhVien, subject);
		model.addAttribute("sinhVien", sinhVien);

		model.addAttribute("listGStudent", listGStudent);

		return "lecturer/showGStudent";
	}

	@RequestMapping("/lecturer/xem-thong-tin-sinh-vien/{username}")
	public String showInfoStudent(Model model, @PathVariable(name = "username") String username) {
		// menu
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		User sinhVien = userService.findByUsername(username);
		model.addAttribute("users", sinhVien);

		model.addAttribute("sumNumberOfCredit", scoreService.sumNumberOfCredit(sinhVien));
		model.addAttribute("avg", scoreService.scoreAvg(sinhVien));
		return "lecturer/infoStudentInLivingClass";
	}
	
	@RequestMapping("/lecturer/xem-thong-tin-giang-vien/{username}")
	public String infoLecturer(Model model, @PathVariable(name="username") String username) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		// menu

		User lecturer = userService.findByUsername(username);
		model.addAttribute("lecturer", lecturer);
		model.addAttribute("listSubject", subjectService.findByTeacherOrPracticeTeacher(lecturer, lecturer));
		return "lecturer/infoLecturer";
	}
	
	
	
}
