package com.isvaso.domain.service;

import com.isvaso.exception.RepositoryException;
import com.isvaso.domain.model.Task;
import com.isvaso.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Optional<Task> add(Task task) {
        try {
            return repository.add(task);
        } catch (RepositoryException exception) {
            log.error("Error while add task", exception);
        }
        return Optional.empty();
    }

    public List<Task> get() {
        return repository.get();
    }

    public List<Task> update(List<Task> tasks) {
        try {
            return repository.update(tasks);
        } catch (RepositoryException exception) {
            log.error("Error while updating tasks", exception);
        }
        return Collections.emptyList();
    }
}
