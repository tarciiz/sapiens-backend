package com.sapiens.sapiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sapiens.sapiens.domain.schedule.Schedule;
import com.sapiens.sapiens.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/schedule")
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/save-one")
    public ResponseEntity<?> save(@RequestBody Schedule schedule) {
        return scheduleService.save(schedule);
    }

    @PostMapping("/save-many")
    public ResponseEntity<?> save(@RequestBody Iterable<Schedule> schedules) {
        return scheduleService.saveMany(schedules);
    }

    @PostMapping("/save-many/discipline/{code}")
    public ResponseEntity<?> save(@RequestBody Iterable<Schedule> schedules, @PathVariable("code") String code) {
        return scheduleService.saveManyForDiscipline(schedules, code);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Schedule schedule) {
        return scheduleService.update(schedule);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return scheduleService.delete(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/discipline/{code}")
    public ResponseEntity<?> findByDisciplineCode(@PathVariable("code") String code) {
        return scheduleService.findByDisciplineCode(code);
    }

}
