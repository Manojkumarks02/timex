package com.timex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "TIME_ENTRY_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    private Duration duration;
}
