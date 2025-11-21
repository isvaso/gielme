package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class TaskSerializer {

    public String serializeList(List<Task> tasks) {
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

    public List<Task> deserializeList(String string) {
        return Arrays.stream(string.split("\n"))
                .map(e -> tryDeserialize(e).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Optional<Task> tryDeserialize(String string) {
        try {
            return deserialize(string);
        } catch (SerializerException exception) {
            log.error("Error while deserializing Task", exception);
            return Optional.empty();
        }
    }

    public Optional<Task> deserialize(String string) throws SerializerException {
        String[] stringValues = string.split(SerializationConfig.DELIMITER);
        if (stringValues.length < 2) {
            throw new SerializerException("Invalid data for Task deserialization: %s".formatted(string));
        }
        String name = stringValues[0];
        TaskState state = TaskState.valueOf(stringValues[1]);
        return Optional.ofNullable(Task.builder()
                .name(name)
                .state(state)
                .build());
    }
}
