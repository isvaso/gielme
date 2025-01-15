package com.isvaso.serialization;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskSerializer {

    public String serializeList(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(serialize(task));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String serialize(Task task) {
        return new StringBuilder()
                .append(task.getName())
                .append(SerializationConfig.DELIMITER)
                .append(task.getState())
                .toString();
    }

    public List<Task> deserializeList(String string) {
        return Arrays.stream(string.split("\n"))
                .map(this::deserialize)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Task deserialize(String string) {
        String[] stringValues = string.split(SerializationConfig.DELIMITER);
        if (stringValues.length < 2) {
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
