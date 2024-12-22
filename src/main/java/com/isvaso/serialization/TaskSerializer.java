package com.isvaso.serialization;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

public class TaskSerializer {

    public String serialize(Task task) {
        return new StringBuilder()
                .append(SerializationConfig.DELIMITER)
                .append(task.getName())
                .append(SerializationConfig.DELIMITER)
                .append(task.getState())
                .append(SerializationConfig.DELIMITER)
                .toString();
    }

    public Task deserialize(String string) {
        String[] stringValues = string.split(SerializationConfig.DELIMITER, 3);
        String name = stringValues[0];
        TaskState state = TaskState.valueOf(stringValues[1]);
        return Task.builder()
                .name(name)
                .state(state)
                .build();
    }
}
