package com.javawebspringboot.education.controller.manage;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.service.AnswerService;
import com.javawebspringboot.education.service.CoursesGoalService;
import com.javawebspringboot.education.service.DepartmentService;
import com.javawebspringboot.education.service.LearningOutcomeService;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.service.UserService;

@Controller
public class ManageController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CoursesGoalService coursesGoalService;

	@Autowired
	private DepartmentService departmentService;

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
		return "manage/infostudent";
	}

	@RequestMapping("/manage/xem-thong-tin-giang-vien/{username}")
	public String showInfoLecturer(Model model, @PathVariable(name = "username") String username) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
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

	@RequestMapping("/manage/them-g-moi/subject/{idSubject}")
	public String addNewCoursesGoal(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@RequestParam(name = "txtG", required = false) String txtG) {

		coursesGoalService.newCoursesGoal(idSubject, txtG);
		return "redirect:/manage/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping(value = "/manage/them-mon-hoc", method = RequestMethod.GET)
	public String newSubjectFrom(Model model) {
		model.addAttribute("newSubject", new Subject());
		model.addAttribute("listDepartment", departmentService.findAllDepartment());
		model.addAttribute("listLecturer", userService.findAllLecturer());
		return "manage/newSubject";
	}

	@RequestMapping(value = "/manage/them-mon-hoc", method = RequestMethod.POST)
	public String newSubject(Model model, @ModelAttribute(name = "newSubject") Subject newSubject) {

		subjectService.newSubject(newSubject);
		return "redirect:/manage/xem-danh-sach-cac-mon-hoc";
	}

	@RequestMapping("/manage/xoa-g/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String deleteCoursesGoal(@PathVariable(name = "idCoursesGoal") Integer idCoursesGoal,
			@PathVariable(name = "idSubject") Integer idSubject) {
		coursesGoalService.deleteCoursesGoal(idCoursesGoal);
		return "redirect:/manage/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/manage/chinh-sua-g/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String editCoursesGoal(Model model, @PathVariable(name = "idCoursesGoal") Integer idCoursesGoal,
			@PathVariable(name = "idSubject") Integer idSubject) {
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
		return "manage/editCoursesGoal";
	}

	@RequestMapping("/manage/chinh-sua-thong-tin-mon-hoc/subject/{idSubject}/coursesgoal/{idCoursesGoal}")
	public String editSubject(@RequestParam(name = "lo", required = false, defaultValue = "0") List<Integer> idLOList,
			@RequestParam(name = "txtCoursesGoal") String txtCoursesGoal,
			@PathVariable(name = "idCoursesGoal") Integer idCoursesGoal) {
		coursesGoalService.editCoursesGoal(idLOList, txtCoursesGoal, idCoursesGoal);
		return "redirect:/manage/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/manage/them-cau-hoi/subject/{idSubject}")
	public String newAnswer(@PathVariable(name = "idSubject") Integer idSubject,
			@RequestParam(name = "idExam") Integer idExam, @RequestParam(name = "contentAnswer") String contentAnswer) {

		subjectService.saveAnswer(idSubject, idExam, contentAnswer);
		return "redirect:/manage/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/manage/xoa-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String deleteAnswer(@PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "idAnswer") Integer idAnswer) {
		answerService.deleteAnswer(idAnswer);
		return "redirect:/manage/xem-thong-tin-mon-hoc/{idSubject}";
	}

	@RequestMapping("/manage/sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String updateAnswer(Model model, @PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "idAnswer") Integer idAnswer) {

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

		return "manage/editAnswer";
	}

	@RequestMapping("/manage/chinh-sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}")
	public String editAnswerAndCoursesGoal(@RequestParam(name = "idExam") Integer idExam,
			@RequestParam(name = "contentAnswer") String contentAnswer,
			@RequestParam(name = "g", required = false, defaultValue = "0") List<Integer> idGList,
			@PathVariable(name = "idSubject") Integer idSubject,
			@PathVariable(name = "idAnswer") Integer idAnswer) {
		answerService.editAnswer(idGList,idAnswer,idExam, contentAnswer);
		return "redirect:/manage/sua-cau-hoi/subject/{idSubject}/answer/{idAnswer}";
	}
}
