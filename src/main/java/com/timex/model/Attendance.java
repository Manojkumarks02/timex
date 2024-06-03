package com.timex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "ATTENDANCE_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime clockInTime;

    private LocalDateTime clockOutTime;

    private final Duration totalWorkHours = Duration.ofHours(8);

    private Duration actualWorkHours;

    private Duration shortfallHours;

    private Duration excessHours;
}
