package com.timex.service.impl;

import com.timex.model.Task;
import com.timex.model.TimeEntry;
import com.timex.repository.TimeEntryRepository;
import com.timex.service.TimeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeEntryServiceImpl implements TimeEntryService {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    @Override
    public TimeEntry recordTimeEntry(TimeEntry timeEntry) {
        validateTimeEntry(timeEntry); // Additional validation
        return timeEntryRepository.save(timeEntry);
    }

    private void validateTimeEntry(TimeEntry timeEntry) {
        LocalDateTime startTime = timeEntry.getStartTime();
        LocalDateTime endTime = timeEntry.getEndTime();
        Task task = timeEntry.getTask();

        if (startTime != null && endTime != null && startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (task == null) {
            throw new IllegalArgumentException("Task is required");
        }
    }
}