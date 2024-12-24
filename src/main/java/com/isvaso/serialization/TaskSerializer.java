package com.isvaso.serialization;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskSerializer {

    public String serialize(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(serialize(task));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String serialize(Task task) {
        return new StringBuilder()
                .append(task.getName())
                .append(SerializationConfig.DELIMITER)
                .append(task.getState())
                .toString();
    }

    public List<Task> deserialize(List<String> string) {
        return string.stream()
                .map(this::deserialize)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Task deserialize(String string) {
        String[] stringValues = string.split(SerializationConfig.DELIMITER);
        if(stringValues.length < 2) {
            // TODO: should throw exception
            return null;
        }
        String name = stringValues[0];
        TaskState state = TaskState.valueOf(stringValues[1]);
        return Task.builder()
                .name(name)
                .state(state)
                .build();
    }

}
