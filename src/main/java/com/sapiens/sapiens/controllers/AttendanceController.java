package com.sapiens.sapiens.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.attendance.Attendance;
import com.sapiens.sapiens.services.AttendanceService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/save-one")
    public ResponseEntity<?> save(@RequestBody Attendance attendance) {
        return attendanceService.save(attendance);
    }

    @PostMapping("/save-many")
    public ResponseEntity<?> saveMany(@RequestBody Iterable<Attendance> attendances) {
        return attendanceService.saveMany(attendances);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return attendanceService.delete(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return attendanceService.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findByStudentId(@PathVariable("id") Long id) {
        return attendanceService.findByStudentId(id);
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<?> findByLessonId(@PathVariable("id") Long id) {
        return attendanceService.findByLessonId(id);
    }

}
