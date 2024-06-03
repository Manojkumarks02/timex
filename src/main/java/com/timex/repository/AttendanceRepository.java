package com.timex.repository;

import com.timex.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findTopByOrderByIdDesc();
}
