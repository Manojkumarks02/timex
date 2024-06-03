package com.timex.service;

import com.timex.model.Attendance;

import java.util.List;

public interface AttendanceService {

    void clockIn();

    void clockOut();

    boolean isClockedIn();

    List<Attendance> getAllAttendance();
}
