package com.sapiens.sapiens.domain.student;

import java.util.List;

public record Report(String studentName, String matriculation, List<Subject> subjects) {
}
