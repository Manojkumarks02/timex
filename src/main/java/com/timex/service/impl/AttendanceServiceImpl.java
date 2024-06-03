package com.timex.service.impl;

import com.timex.model.Attendance;
import com.timex.repository.AttendanceRepository;
import com.timex.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository){
        this.attendanceRepository=attendanceRepository;
    }


    @Override
    public void clockIn() {
        Attendance lastAttendanceRecord = attendanceRepository.findTopByOrderByIdDesc();
        if (lastAttendanceRecord == null || lastAttendanceRecord.getClockOutTime() != null) {
            Attendance attendanceIn = new Attendance();
            attendanceIn.setClockInTime(LocalDateTime.now());
            attendanceRepository.save(attendanceIn);
        }
    }

    @Override
    public void clockOut() {
        Attendance lastAttendanceRecord = attendanceRepository.findTopByOrderByIdDesc();
        if (lastAttendanceRecord != null && lastAttendanceRecord.getClockOutTime() == null) {
            lastAttendanceRecord.setClockOutTime(LocalDateTime.now());
            calculateWorkHours(lastAttendanceRecord);
            attendanceRepository.save(lastAttendanceRecord);
        }
    }

    private void calculateWorkHours(Attendance attendance) {
        LocalDateTime clockInTime = attendance.getClockInTime();
        LocalDateTime clockOutTime = attendance.getClockOutTime();
        Duration workDuration = Duration.between(clockInTime, clockOutTime);
        Duration totalWorkDuration = Duration.ofHours(8);

        if (workDuration.compareTo(totalWorkDuration) >= 0) {
            attendance.setActualWorkHours(workDuration);
            attendance.setShortfallHours(Duration.ZERO);
            attendance.setExcessHours(workDuration.minus(totalWorkDuration));
        } else {
            attendance.setActualWorkHours(workDuration);
            attendance.setShortfallHours(totalWorkDuration.minus(workDuration));
            attendance.setExcessHours(Duration.ZERO);
        }
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


    @Override
    public boolean isClockedIn() {
        Attendance lastAttendanceRecord = attendanceRepository.findTopByOrderByIdDesc();
        return lastAttendanceRecord != null && lastAttendanceRecord.getClockOutTime() == null;
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
