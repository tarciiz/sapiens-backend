package com.sapiens.sapiens.domain.student;

import java.util.List;

public record Subject(
		String disciplineCode,
		String disciplineName,
		int manyLessons,
		int completedLessons,
		int lessonsAttended,
		int lessonsMissed,
		double attendancePercentage,
		String status,
		double finalGrade,
		List<SubjectGrade> grades) {
}