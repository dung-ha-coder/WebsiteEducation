package com.javawebspringboot.education.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javawebspringboot.education.model.ScoresTable;
import com.javawebspringboot.education.model.Subject;
import com.javawebspringboot.education.model.User;
import com.javawebspringboot.education.repository.ScoresRepository;
import com.javawebspringboot.education.repository.UserRepository;
import com.javawebspringboot.education.service.ScoresService;
import com.javawebspringboot.education.utiles.TableScore;

@Service
public class ScoresServiceImpl implements ScoresService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoresRepository scoresRepository;

	@Override
	public List<ScoresTable> findByUser(User user) {
		return scoresRepository.findByUser(user);
	}

	@Override
	public List<ScoresTable> findBySubject(Subject subject) {
		return scoresRepository.findBySubject(subject);
	}

	@Override
	public void saveFileExcelToDisk(MultipartFile file) {
		String UPLOAD_FOLDER = System.getProperty("user.dir") + "/FileUpload/";
		try {
			byte[] bytes = file.getBytes();

			String fileName = Instant.now().toEpochMilli() + "_" + file.getOriginalFilename();
			Path path = Paths.get(UPLOAD_FOLDER + "" + fileName);
			Files.write(path, bytes);
		} catch (IOException e) {
		}

	}

	@Override
	public boolean checkFullScore(String mssv, Subject subject) {
		User sinhVien = userRepository.findByUsername(mssv);
		ScoresTable score = scoresRepository.findByUserAndSubject(sinhVien, subject);

		if ((subject.getRateProcess() != null && score.getScoreProcess() == null)
				|| (subject.getRatePractice() != null && score.getScorePractice() == null)
				|| (subject.getRateMidTerm() != null && score.getScoreMidTerm() == null)
				|| (subject.getReateEndTerm() != null && score.getScoreEndTerm() == null)) {
			return false;
		}
		return true;
	}

	@Override
	public void updateScoreAverage(List<TableScore> lisTableScores, Subject subject) {

		// lay ma so sinh vien
		for (TableScore tableScore : lisTableScores) {
			String mssv = tableScore.getCodeStudent();
			// mssv <=> username
			User sinhVien = userRepository.findByUsername(mssv);
			ScoresTable score = scoresRepository.findByUserAndSubject(sinhVien, subject);
//			float diemQT = score.getScoreProcess();
//			float diemTH = score.getScorePractice();
//			float diemGK = score.getScoreMidTerm();
//			float diemCK = score.getScoreEndTerm();

			float scoreProcess = 0;
			float scorePractice = 0;
			float scoreMidTerm = 0;
			float scoreEndTerm = 0;

			float rateProcess = 0;
			float ratePractice = 0;
			float rateMidTerm = 0;
			float rateEndTerm = 0;

			// lay diem
			if (score.getScoreProcess() != null) {
				scoreProcess = score.getScoreProcess();
			}
			if (score.getScorePractice() != null) {
				scorePractice = score.getScorePractice();
			}

			if (score.getScoreMidTerm() != null) {
				scoreMidTerm = score.getScoreMidTerm();
			}
			if (score.getScoreEndTerm() != null) {
				scoreEndTerm = score.getScoreEndTerm();
			}

			// lay % diem tung phan
			if (subject.getRateProcess() != null) {
				rateProcess = subject.getRateProcess();
			}
			if (subject.getRatePractice() != null) {
				ratePractice = subject.getRatePractice();
			}
			if (subject.getRateMidTerm() != null) {
				rateMidTerm = subject.getRateMidTerm();
			}
			if (subject.getReateEndTerm() != null) {
				rateEndTerm = subject.getReateEndTerm();
			}

			float scoreAverage = (scoreProcess * rateProcess + scorePractice * ratePractice + scoreMidTerm * rateMidTerm
					+ scoreEndTerm * rateEndTerm) / 100;
			// luu diem cho sinh vien
			score.setScoreAverage(scoreAverage);
			scoresRepository.save(score);

		}

	}

	@Override
	public int sumNumberOfCredit(User student) {
		int sumNumberOfCredit = 0;
		for (ScoresTable scoresTable : student.getScoresTableList()) {
			sumNumberOfCredit += scoresTable.getSubject().getNumberOfCredits();
		}

		return sumNumberOfCredit;
	}

	@Override
	public float scoreAvg(User sinhVien) {
		float tongDiem = 0;
		int tongTC = 0;
		for (ScoresTable scoresTable : sinhVien.getScoresTableList()) {
			if (scoresTable.getScoreAverage() != null) {
				tongDiem += scoresTable.getScoreAverage() * scoresTable.getSubject().getNumberOfCredits();
				tongTC += scoresTable.getSubject().getNumberOfCredits();
			}

		}
		return tongDiem / tongTC;
	}

	@Override
	public void getDataChart(List<String> label, List<Float> point, List<ScoresTable> scoresTables) {
		label.add("Giỏi");

		point.add(tinhXepLoai(scoresTables, 8.5, 10.0));

		label.add("Khá");
		point.add(tinhXepLoai(scoresTables, 7.0, 8.4));

		label.add("Trung bình");
		point.add(tinhXepLoai(scoresTables, 5.5, 6.9));

		label.add("Trung bình yếu");
		point.add(tinhXepLoai(scoresTables, 4.0, 5.4));

		label.add("Kém");
		point.add(tinhXepLoai(scoresTables, 0.0, 3.9));

	}

	private float tinhXepLoai(List<ScoresTable> scoresTables, double d, double e) {
		float sumHS = scoresTables.size();
		float sumXL = 0;
		for (ScoresTable scoresTable : scoresTables) {
			if (scoresTable.getScoreAverage() >= d && scoresTable.getScoreAverage() <= e) {
				sumXL++;
			}
		}
		DecimalFormat df = new DecimalFormat("#.##");

		float kq = (sumXL / sumHS) * 100;
		return Float.parseFloat(df.format(kq));
	}
}
