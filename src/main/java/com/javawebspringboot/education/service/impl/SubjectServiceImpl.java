package com.javawebspringboot.education.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.exception.ReadFileException;
import com.javawebspringboot.education.model.Answer;
import com.javawebspringboot.education.model.CoursesGoal;
import com.javawebspringboot.education.model.Department;
import com.javawebspringboot.education.model.LearningOutcome;
import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.model.UserLearningOutcome;
import com.javawebspringboot.education.model.UserSubjectCoursesGoal;
import com.javawebspringboot.education.repository.AnswerRepository;
import com.javawebspringboot.education.repository.DepartmentRepository;
import com.javawebspringboot.education.repository.ScoresRepository;
import com.javawebspringboot.education.repository.SubjectRepository;
import com.javawebspringboot.education.repository.UserLearningOutcomeRepository;
import com.javawebspringboot.education.repository.UserRepository;
import com.javawebspringboot.education.repository.UserSubjectCoursesGoalRepository;
import com.javawebspringboot.education.service.SubjectService;
import com.javawebspringboot.education.utiles.TableScore;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoresRepository scoresRepository;

	@Autowired
	private UserLearningOutcomeRepository userLearningOutcomeRepository;

	@Autowired
	private UserSubjectCoursesGoalRepository userSubjectCoursesGoalRepository;

	@Override
	public List<TableScore> fileHandler(MultipartFile fileExcel) throws ReadFileException {
		Workbook workbook = null;
		List<TableScore> listTableScore = new ArrayList<TableScore>();

		String lowerCaseFileName = fileExcel.getOriginalFilename().toLowerCase();
		if (lowerCaseFileName.endsWith(".xlsx")) {
			try {
				workbook = new XSSFWorkbook(fileExcel.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				Iterator<Row> rowIterator = sheet.iterator();
				if (rowIterator.hasNext()) {
					// bo qua dong dau tien trong bang
					// title cua bang
					rowIterator.next();
					// bat dau duyet tu dong thu 2 de lay du lieu
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						// tao ra moi doi tuong TableScore de luu tung row

						TableScore tableScore = new TableScore();
						List<Float> diems = new ArrayList<Float>();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							int columnIndex = cell.getColumnIndex();

							// STT trong bang
							if (columnIndex == 0) {
								// bo qua khong lam gi het
							}

							// ma so sinh vien
							if (columnIndex == 1) {
								String codeStudent = cell.getStringCellValue();
								tableScore.setCodeStudent(codeStudent);
							}

							// ho ten sinh vien
							if (columnIndex == 2) {
								String nameStudent = cell.getStringCellValue();
								tableScore.setNameStudent(nameStudent);
							}
							if (columnIndex > 2) {
								if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getNumericCellValue() > 10
										|| cell.getNumericCellValue() < 0) {

									String msg = getErrorMsg(row);
									ReadFileException.messageError(msg);
									throw new ReadFileException();
								}

								double diem = cell.getNumericCellValue();
								float d = (float) diem;
								diems.add(d);

							}
						}
						tableScore.setScoreList(diems);
						listTableScore.add(tableScore);

					}
				}

			} catch (IOException e) {

				return null;
			} finally {
				try {
					workbook.close();
				} catch (IOException e) {
				}
			}
		} else if (lowerCaseFileName.endsWith(".xls")) {
			try {
				workbook = new HSSFWorkbook(fileExcel.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);

				Iterator<Row> rowIterator = sheet.iterator();
				if (rowIterator.hasNext()) {
					// bo qua dong dau tien trong bang
					// title cua bang
					rowIterator.next();
					// bat dau duyet tu dong thu 2 de lay du lieu
					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();
						Iterator<Cell> cellIterator = row.cellIterator();
						// tao ra moi doi tuong TableScore de luu tung row

						TableScore tableScore = new TableScore();
						List<Float> diems = new ArrayList<Float>();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							int columnIndex = cell.getColumnIndex();

							// STT trong bang
							if (columnIndex == 0) {
								// bo qua khong lam gi het
							}

							// ma so sinh vien
							if (columnIndex == 1) {
								String codeStudent = cell.getStringCellValue();
								tableScore.setCodeStudent(codeStudent);
							}

							// ho ten sinh vien
							if (columnIndex == 2) {
								String nameStudent = cell.getStringCellValue();
								tableScore.setNameStudent(nameStudent);
							}
							if (columnIndex > 2) {
								if (cell.getCellType() == Cell.CELL_TYPE_STRING || cell.getNumericCellValue() > 10
										|| cell.getNumericCellValue() < 0) {

									String msg = getErrorMsg(row);
									ReadFileException.messageError(msg);
									throw new ReadFileException();
								}

								double diem = cell.getNumericCellValue();
								float d = (float) diem;
								diems.add(d);

							}
						}
						tableScore.setScoreList(diems);
						listTableScore.add(tableScore);

					}
				}

			} catch (IOException e) {

				return null;
			}
		}

		return listTableScore;
	}

	@Override
	public Subject findByIdSubject(Integer idSubject) {

		return subjectRepository.findByIdSubject(idSubject);
	}

	private String getErrorMsg(Row row) {

		String strError = "";
		Iterator<Cell> cellIterator1 = row.cellIterator();
		while (cellIterator1.hasNext()) {

			Cell c = cellIterator1.next();
			if (c.getCellType() == Cell.CELL_TYPE_STRING) {
				String value = c.getStringCellValue();
				strError += value + " ";
			}
			if (c.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				double val = c.getNumericCellValue();
				if (c.getColumnIndex() == 0) {
					strError += (int) val + " ";
					continue;
				}
				strError += String.valueOf(val) + " ";

			}
		}
		return strError;

	}

	@Override
	public void readData(List<TableScore> listTableScore, Subject subject, String cotDiem) {
		// 1 mon hoc chi co nhieu nhat 4 cot diem chinh
		// vi the khi client truyen len chuoi -> ma cot diem
		int idExam = getIdCotDiem(cotDiem);
		// lay du lieu bang anwer de biet duoc cau hoi nao, cua mon hoc nay, ung voi ki
		// thi nay
		// thi cau hoi nay se co nhung chuan dau ra mon hoc tuong ung thong qua
		// hibernate

		// vi du: cau hoi 1, mon cong nghe java, ki thi giua ki
		// se co cac chuan G1, G3,G4,...
		List<Answer> answerList = answerRepository.findBySubjectAndIdExamOrderByIdExam(subject, idExam);
		// tao Map chua key la CoursesGoal va value la %G dat duoc
		Map<CoursesGoal, Float> coursesGoalMap = new HashMap<>();

		for (Answer answer : answerList) {
		}
		// tung dong du lieu trong table da duoc luu tru trong listTableScore
		// lap tung phan tu trong listTableScore <=> lay dong trong table
		// listTableScore duoc luu tru nhu sau
		// maSV | tenSV | diem cau 1| diem cau 2 | diem cau 3 | ....| diem tong
		for (TableScore score : listTableScore) {
			String maSV = score.getCodeStudent();
			User sinhVien = userRepository.findByUsername(maSV);

			// khoi tao lai tung gia tri G cho tung sinhVien
			initValueMap(coursesGoalMap, answerList);

			Map<LearningOutcome, Float> learningOutcomeMap = new HashMap<LearningOutcome, Float>();

			// tong so cot diem tu cau 1 , cau 2 ... -> diem tong
			int tongCotDiem = score.getScoreList().size();
			for (int i = 0; i < tongCotDiem; i++) {
				// diem cau cac cau trong 1 bai thi
				if ((i < tongCotDiem - 1)) {
					// diem tung cau hoi
					// lay cot diem thu i trong bang
					float diem = score.getScoreList().get(i);

					float diem1Cau = (float) (10.0 / (tongCotDiem - 1));
					float phanTramG = (diem / diem1Cau) * 100;

					// dua gia tri cua G tung cau hoi vao Map - co G da ton tai trong csdl
					// -> can tim %G cua sinh vien - mon hoc
					putValueToMap(answerList, i, coursesGoalMap, phanTramG, learningOutcomeMap);

					// tong diem cua bai thi
				} else {
					float diemTong = score.getScoreList().get(i);
					// luu diem cho sinh vien
					saveTableScore(sinhVien, subject, idExam, diemTong);

				}

			}

			// luu nhung G ma sinh vien nay dat duoc
			saveCourseGoal(coursesGoalMap, sinhVien, subject);
			saveLearningOutcome(learningOutcomeMap, sinhVien);

		}

	}

	private void saveLearningOutcome(Map<LearningOutcome, Float> learningOutcomeMap, User sinhVien) {
		List<UserLearningOutcome> userLearningOutcomeList = userLearningOutcomeRepository.findByUser(sinhVien);
		for (LearningOutcome learningOutcome : learningOutcomeMap.keySet()) {
			UserLearningOutcome userLearningOutcome = checkExistLearningOutcome(learningOutcome,
					userLearningOutcomeList);
			// da ton tai trong csdl
			if (userLearningOutcome != null) {
				userLearningOutcome.setPercent(learningOutcomeMap.get(learningOutcome));

			} else {
				// chua ton tai
				userLearningOutcome = new UserLearningOutcome(sinhVien, learningOutcome,
						learningOutcomeMap.get(learningOutcome));

			}
			userLearningOutcomeRepository.save(userLearningOutcome);

		}

	}

	private UserLearningOutcome checkExistLearningOutcome(LearningOutcome learningOutcome,
			List<UserLearningOutcome> userLearningOutcomeList) {
		for (UserLearningOutcome userLearningOutcome : userLearningOutcomeList) {
			if (userLearningOutcome.getLearningOutcome().equals(learningOutcome)) {
				return userLearningOutcome;
			}
		}
		return null;
	}

	private void putValueToMap(List<Answer> answerList, int i, Map<CoursesGoal, Float> coursesGoalMap, float phanTramG,
			Map<LearningOutcome, Float> learningOutcomeMap) {

		// cau hoi co nhieu G
		for (int j = 0; j < answerList.get(i).getCoursesGoalList().size(); j++) {
			// lay ra cau hoi thu i co Gj
			// cau hoi j co G_j+1
			CoursesGoal coursesGoal = answerList.get(i).getCoursesGoalList().get(j);
			if (coursesGoalMap.get(coursesGoal) == null) {
				coursesGoalMap.put(coursesGoal, phanTramG);

			} else {
				phanTramG = (coursesGoalMap.get(coursesGoal) + phanTramG) / 2;
				coursesGoalMap.put(coursesGoal, phanTramG);
			}
			putValueLOToMap(coursesGoal, learningOutcomeMap, phanTramG);
		}

	}

	private void putValueLOToMap(CoursesGoal coursesGoal, Map<LearningOutcome, Float> learningOutcomeMap,
			float phanTram) {
		for (LearningOutcome learningOutcome : coursesGoal.getLearningOutcomeList()) {
			if (learningOutcomeMap.get(learningOutcome) == null) {
				learningOutcomeMap.put(learningOutcome, phanTram);
			} else {
				learningOutcomeMap.put(learningOutcome, (learningOutcomeMap.get(learningOutcome) + phanTram) / 2);
			}
		}

	}

	private void initValueMap(Map<CoursesGoal, Float> coursesGoalMap, List<Answer> answerList) {
		for (Answer answer : answerList) {
			for (CoursesGoal cg : answer.getCoursesGoalList()) {
				coursesGoalMap.put(cg, null);
			}

		}

	}

	private void saveCourseGoal(Map<CoursesGoal, Float> coursesGoalMap, User sinhVien, Subject subject) {

		List<UserSubjectCoursesGoal> userSubjectCoursesGoalList = userSubjectCoursesGoalRepository
				.findByUserAndSubject(sinhVien, subject);

		for (CoursesGoal coursesGoal : coursesGoalMap.keySet()) {
			UserSubjectCoursesGoal userSubjectCoursesGoal = checkExistCoursesGoal(coursesGoal,
					userSubjectCoursesGoalList);
			// da ton tai - can update %G
			if (userSubjectCoursesGoal != null) {
				userSubjectCoursesGoal.setPercent(coursesGoalMap.get(coursesGoal));
			} else {
				// chua ton tai new UserSubjectCoursesGoal
				userSubjectCoursesGoal = new UserSubjectCoursesGoal(coursesGoal, sinhVien, subject,
						coursesGoalMap.get(coursesGoal));
			}
			// luu vao csdl
			userSubjectCoursesGoalRepository.save(userSubjectCoursesGoal);
		}

	}

	private UserSubjectCoursesGoal checkExistCoursesGoal(CoursesGoal coursesGoal,
			List<UserSubjectCoursesGoal> userSubjectCoursesGoalList) {
		for (UserSubjectCoursesGoal userSubjectCoursesGoal : userSubjectCoursesGoalList) {
			if (userSubjectCoursesGoal.getCoursesGoal().equals(coursesGoal)) {
				return userSubjectCoursesGoal;
			}
		}

		return null;
	}

	private void saveTableScore(User sinhVien, Subject subject, int idExam, float score) {
		ScoresTable scoresTable = scoresRepository.findByUserAndSubject(sinhVien, subject);
		if (scoresTable == null) {
			scoresTable = new ScoresTable();
		}
		switch (idExam) {
		case 1:
			scoresTable.setScoreProcess(score);

			break;
		case 2:
			scoresTable.setScorePractice(score);
			break;
		case 3:
			scoresTable.setScoreMidTerm(score);
			break;
		case 4:
			scoresTable.setScoreEndTerm(score);
			break;

		}

		scoresTable.setUser(sinhVien);
		scoresTable.setSubject(subject);
		scoresRepository.save(scoresTable);
	}

	private int getIdCotDiem(String cotDiem) {
		int idExam = 0;
		switch (cotDiem) {

		case "diem-qua-trinh":
			idExam = 1;
			break;

		case "diem-thuc-hanh":
			idExam = 2;
			break;
		case "diem-giua-ki":
			idExam = 3;
			break;

		case "diem-cuoi-ki":
			idExam = 4;
			break;

		}
		return idExam;
	}

	@Override
	public List<Subject> findAllByOrderByStartTimeAsc() {
		return subjectRepository.findAllByOrderByStartTimeAsc();
	}

	@Override
	public void newSubject(Subject subject, Integer idDepartment) {
		if (subject != null) {
			Department department = departmentRepository.findByIdDepartment(idDepartment);
			subject.setDepartment(department);

			subjectRepository.save(subject);
		} else {
			return;
		}

	}

	@Override
	public void saveAnswer(Integer idSubject, Integer idExam, String contentAnswer) {

		Subject subject = subjectRepository.findByIdSubject(idSubject);
		Answer answer = new Answer(idExam, subject, contentAnswer);
		List<Answer> list = new ArrayList<>();
		list.add(answer);
		subject.setAnswerList(list);
		subjectRepository.save(subject);
	}

	@Override
	public List<Subject> findAllSubjectBy(User user) {
		return subjectRepository.findByTeacherOrPracticeTeacher(user, user);
	}

	@Override
	public List<Subject> registerSubject() {

		Date date = new Date();
		return subjectRepository.findByStartTimeGreaterThan(date);
	}

	@Override
	public void dowloadFile(Integer idSubject, String strCotDiem, HttpServletResponse response) {

		Subject subject = subjectRepository.findByIdSubject(idSubject);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("sinhvien");

		int sl = 0;
		for (Answer answer : subject.getAnswerList()) {
			if (answer.getIdExam() == getIdCotDiem(strCotDiem)) {
				sl++;
			}
		}

		XSSFRow row = spreadsheet.createRow(0);
		XSSFCell cell;
		int cc = 4 + sl;
		// title
		int stt = 0;
		for (int i = 0; i < cc; i++) {
			if (i == 0) {
				cell = row.createCell(i);
				cell.setCellValue("STT");
			}
			if (i == 1) {
				cell = row.createCell(i);
				cell.setCellValue("MSSV");
			}

			if (i == 2) {
				cell = row.createCell(i);
				cell.setCellValue("Ho va ten");
			}

			if (i > 2 && i < cc - 1) {
				String nameAnswer = "Cau " + (++stt);
				cell = row.createCell(i);
				cell.setCellValue(nameAnswer);
			}
			if (i == cc - 1) {
				cell = row.createCell(i);
				cell.setCellValue("Tong");
			}

		}

		for (int i = 0; i < subject.getUserList().size(); i++) {
			XSSFRow row1 = spreadsheet.createRow(i + 1);
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					cell = row1.createCell(j);
					cell.setCellValue(i + 1);
				}
				if (j == 1) {
					cell = row1.createCell(j);
					cell.setCellValue(subject.getUserList().get(i).getUsername());
				}
				if (j == 2) {
					cell = row1.createCell(j);
					cell.setCellValue(subject.getUserList().get(i).getFullname());
				}

			}
		}

		try {
			
			File file = new File("DanhSachSinhVien.xlsx");
			file.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
			fileOutputStream.close();

			byte[] fileContent = Files.readAllBytes(file.toPath());
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
			response.setContentLength(fileContent.length);
			InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(fileContent));

			FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (Exception e) {
			System.out.println("khong dowloadFile duoc");
		}
	}

	@Override
	public List<Subject> findByDepartmentOrderByStartTimeAsc(Integer idDepartment) {
		Department department = departmentRepository.findByIdDepartment(idDepartment);

		return subjectRepository.findByDepartmentOrderByStartTimeAsc(department);
	}

	@Override
	public List<Subject> findByTeacherOrPracticeTeacher(User teacher, User practiceTeacher) {
		// TODO Auto-generated method stub
		return subjectRepository.findByTeacherOrPracticeTeacher(teacher, practiceTeacher);
	}

}
