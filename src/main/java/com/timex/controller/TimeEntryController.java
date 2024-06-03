package com.timex.controller;


import com.timex.model.TimeEntry;
import com.timex.service.TimeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time-api")
public class TimeEntryController {

    @Autowired
    private TimeEntryService timeEntryService;


    @PostMapping("/record")
    @ResponseStatus(HttpStatus.CREATED)
    public TimeEntry recordTimeEntry(@RequestBody TimeEntry timeEntry){
        return timeEntryService.recordTimeEntry(timeEntry);
    }

}
