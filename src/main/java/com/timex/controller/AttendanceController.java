package com.timex.controller;

import com.timex.model.Attendance;
import com.timex.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService=attendanceService;
    }

    @PostMapping("/clock-in")
    public ResponseEntity<String> clockIn() {
        if (attendanceService.isClockedIn()) {
            return ResponseEntity.badRequest().body("You are already clocked in. Please clock out first.");
        } else {
            attendanceService.clockIn();
            return ResponseEntity.ok("You have successfully logged-In");
        }
    }

    @PostMapping("/clock-out")
    public ResponseEntity<String> clockOut() {
        if (!attendanceService.isClockedIn()) {
            return ResponseEntity.badRequest().body("You are not currently clocked in. Please clock in first.");
        } else {
            attendanceService.clockOut();
            return ResponseEntity.ok("You have successfully logged-Out");
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<Attendance>> getAttendanceRecords() {
        List<Attendance> attendanceRecords = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendanceRecords);
    }

}
