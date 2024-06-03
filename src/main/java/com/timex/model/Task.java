    package com.timex.model;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.*;
    import javax.validation.constraints.NotBlank;
    import javax.validation.constraints.NotNull;
    import javax.validation.constraints.Pattern;
    import javax.validation.constraints.Size;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;

    @Entity
    @Table(name = "TASK_TBL")
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long taskId;

        @NotBlank(message = "Task name is required")
        @Size(max = 255, message = "Task name must be less than or equal to 255 characters")
        private String task;

        @NotBlank(message = "Description is required")
        @Size(max = 1000, message = "Description must be less than or equal to 1000 characters")
        private String description;

        @NotNull(message = "Date is required")
        private Date date;

        @NotBlank(message = "Project name is required")
        @Size(max = 255, message = "Project name must be less than or equal to 255 characters")
        private String project;

        @NotBlank(message = "Client name is required")
        @Size(max = 255, message = "Client name must be less than or equal to 255 characters")
        private String client;

        @Pattern(regexp = "[0-8]", message = "Hour must be a less than 8")
        private String hour;

        @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
        private List<TimeEntry> timeEntries = new ArrayList<>();

    }
