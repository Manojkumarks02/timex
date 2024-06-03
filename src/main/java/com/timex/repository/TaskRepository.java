package com.timex.repository;

import com.timex.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

    //Page<Task> findByProjectStartingWithIgnoreCase(String search, PageRequest asc);

    Page<Task> findByProjectContainingIgnoreCaseOrClientContainingIgnoreCaseOrTaskContainingIgnoreCase(String search, String search1, String search2, PageRequest asc);
}
