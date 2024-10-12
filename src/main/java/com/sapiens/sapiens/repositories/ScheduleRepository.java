package com.sapiens.sapiens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sapiens.sapiens.domain.schedule.Schedule;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDisciplineCode(String code);

}
