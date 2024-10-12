package com.sapiens.sapiens.services;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sapiens.sapiens.domain.schedule.Schedule;
import com.sapiens.sapiens.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ResponseEntity<?> save(Schedule schedule) {
        return ResponseEntity.ok().body(scheduleRepository.save(schedule));
    }

    public ResponseEntity<?> saveMany(Iterable<Schedule> schedules) {
        return ResponseEntity.ok().body(scheduleRepository.saveAll(schedules));
    }

    public ResponseEntity<?> saveManyForDiscipline(Iterable<Schedule> schedules, String code) {
        HashSet<Schedule> newSchedules = StreamSupport.stream(schedules.spliterator(), false)
                .collect(Collectors.toCollection(HashSet::new));

        HashSet<Schedule> currentSchedules = new HashSet<>(scheduleRepository.findByDisciplineCode(code));

        currentSchedules.removeAll(newSchedules);

        if (!currentSchedules.isEmpty()) {
            scheduleRepository.deleteAll(currentSchedules);
        }

        return ResponseEntity.ok().body(scheduleRepository.saveAll(newSchedules));
    }

    public ResponseEntity<?> update(Schedule schedule) {
        return ResponseEntity.ok().body(scheduleRepository.save(schedule));
    }

    public ResponseEntity<?> delete(Long id) {
        scheduleRepository.deleteById(id);
        return ResponseEntity.ok().body("Horário excluído com sucesso.");
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(scheduleRepository.findAll());
    }

    public ResponseEntity<?> findByDisciplineCode(String code) {
        return ResponseEntity.ok().body(scheduleRepository.findByDisciplineCode(code));
    }

}
