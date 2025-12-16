package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import com.isvaso.util.StringValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TaskSerializer {

    public String serializeList(List<Task> tasks) throws SerializerException {
        if (tasks == null)
            throw new SerializerException("Tasks list cannot be null");
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(serialize(task));
            stringBuilder.append(SerializationProperties.ELEMENT_DELIMITER);
        }
        return stringBuilder.toString();
    }

    public String serialize(Task task) throws SerializerException {
        if (task == null)
            throw new SerializerException("Task cannot be null");
        if (StringValidator.isBlankOrNull(task.getName()))
            throw new SerializerException("Task name cannot be empty or null");
        if (task.getState() == null)
            throw new SerializerException("Task state cannot be null");
        return new StringBuilder()
                .append(task.getName())
                .append(SerializationProperties.FIELD_DELIMITER)
                .append(task.getState())
                .toString();
    }

    public List<Task> deserializeList(String string) throws SerializerException {
        if (string == null)
            throw new SerializerException("String with tasks list cannot be null");
        List<String> serializedTasks = Arrays.stream(string.split(SerializationProperties.ELEMENT_DELIMITER)).toList();
        List<Task> resultTasks = new ArrayList<>();
        for (String serializedTask : serializedTasks) {
            if (StringValidator.isBlankOrNull(serializedTask))
                continue;
            Optional<Task> deserializedTask = deserialize(serializedTask);
            deserializedTask.ifPresent(resultTasks::add);
        }
        return resultTasks;
    }

    public Optional<Task> deserialize(String string) throws SerializerException {
        String[] stringValues = string.split(SerializationProperties.FIELD_DELIMITER);
        if (stringValues.length < 2)
            throw new SerializerException("Invalid data for Task deserialization: %s".formatted(string));
        try {
            String name = stringValues[0];
            TaskState state = TaskState.valueOf(stringValues[1]);
            return Optional.ofNullable(Task.builder()
                    .name(name)
                    .state(state)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new SerializerException("Invalid data for Task deserialization: %s".formatted(string));
        }
    }
}
