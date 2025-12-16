package com.isvaso.serialization;

import com.isvaso.exception.SerializerException;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import com.isvaso.util.StringValidator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskSerializerTest {

    private final TaskSerializer taskSerializer = new TaskSerializer();

    private static final String SERIALIZED_TASK_TEMPLATE = "%s%s%s";

    private static final String SERIALIZED_TASK_FOR_LIST_TEMPLATE = "%s%s";

    @Nested
    class Serialize {

        @Test
        void shouldThrowSerializerException_whenTaskIsNull() {
            Task task = null;

            assertThrows(SerializerException.class, () -> taskSerializer.serialize(task));
        }

        @Test
        void shouldThrowSerializerException_whenTaskNameIsNull() {
            Task task = new Task(null, TaskState.UNSOLVED);

            assertThrows(SerializerException.class, () -> taskSerializer.serialize(task));
        }

        @Test
        void shouldThrowSerializerException_whenTaskNameIsEmpty() {
            Task task = new Task("", TaskState.UNSOLVED);

            assertThrows(SerializerException.class, () -> taskSerializer.serialize(task));
        }

        @Test
        void shouldThrowSerializerException_whenTaskNameIsBlank() {
            Task task = new Task("  ", TaskState.UNSOLVED);

            assertThrows(SerializerException.class, () -> taskSerializer.serialize(task));
        }

        @Test
        void shouldThrowSerializerException_whenTaskStateIsNull() {
            Task task = new Task("Task name", null);

            assertThrows(SerializerException.class, () -> taskSerializer.serialize(task));
        }

        @Test
        void shouldSerializeTaskByTemplate_whenTaskIsValid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.UNSOLVED;
            Task task = new Task(taskName, taskState);
            String expectedSerializedTask = SERIALIZED_TASK_TEMPLATE.formatted(
                    taskName, SerializationProperties.FIELD_DELIMITER, taskState
            );

            String actualSerializedTask = taskSerializer.serialize(task);

            assertEquals(expectedSerializedTask, actualSerializedTask);
        }
    }

    @Nested
    class SerializeList {

        @Test
        void shouldThrowSerializerException_whenTasksIsNull() {
            List<Task> tasks = null;

            assertThrows(SerializerException.class, () -> taskSerializer.serializeList(tasks));
        }

        @Test
        void shouldReturnEmptyString_whenTasksIsEmpty() throws SerializerException {
            List<Task> tasks = new ArrayList<>();

            String serializedTasks = taskSerializer.serializeList(tasks);

            assertTrue(StringValidator.isEmpty(serializedTasks));
        }

        @Test
        void shouldSerializeSingleTaskList_whenTaskIsValid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.UNSOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> tasks = List.of(task);
            String expectedSerializedTask = SERIALIZED_TASK_TEMPLATE.formatted(
                    taskName, SerializationProperties.FIELD_DELIMITER, taskState
            );
            String expectedSerializedTaskForList = SERIALIZED_TASK_FOR_LIST_TEMPLATE.formatted(
                    expectedSerializedTask,
                    SerializationProperties.ELEMENT_DELIMITER
            );

            String actualSerializedTasks = taskSerializer.serializeList(tasks);

            assertEquals(expectedSerializedTaskForList, actualSerializedTasks);
        }

        @Test
        void shouldSerializeTwoTaskList_whenTaskIsValid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.UNSOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> tasks = List.of(task, task);
            String expectedSerializedTask = SERIALIZED_TASK_TEMPLATE.formatted(
                    taskName, SerializationProperties.FIELD_DELIMITER, taskState
            );
            String expectedSerializedTaskForList = SERIALIZED_TASK_FOR_LIST_TEMPLATE.formatted(
                    expectedSerializedTask,
                    SerializationProperties.ELEMENT_DELIMITER
            );
            String expectedSerializedTasks = new StringBuilder()
                    .append(expectedSerializedTaskForList)
                    .append(expectedSerializedTaskForList)
                    .toString();

            String actualSerializedTasks = taskSerializer.serializeList(tasks);

            assertEquals(expectedSerializedTasks, actualSerializedTasks);
        }

    }

    @Nested
    class Deserialize {

        @Test
        void shouldThrowSerializerException_whenStringIsNull() {
            String serializedTask = null;

            assertThrows(SerializerException.class, () -> taskSerializer.deserialize(serializedTask));
        }

        @Test
        void shouldThrowSerializerException_whenInvalidString() {
            String serializedTask = "Invalid string";

            assertThrows(SerializerException.class, () -> taskSerializer.deserialize(serializedTask));
        }

        @Test
        void shouldThrowSerializerException_whenEmptyTaskName() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            String serializedTask = taskSerializer.serialize(task);
            String serializedTaskWithInvalidState = serializedTask.replace(taskName, "");

            assertThrows(SerializerException.class, () -> taskSerializer.deserialize(serializedTaskWithInvalidState));
        }

        @Test
        void shouldThrowSerializerException_whenInvalidTaskState() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            String serializedTask = taskSerializer.serialize(task);
            String serializedTaskWithInvalidState = serializedTask.replace(TaskState.SOLVED.toString(), "INVALID");

            assertThrows(SerializerException.class, () -> taskSerializer.deserialize(serializedTaskWithInvalidState));
        }

        @Test
        void shouldDeserialize_whenValidString() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task expectedDeserializedTask = new Task(taskName, taskState);
            String serializedTask = taskSerializer.serialize(expectedDeserializedTask);

            Optional<Task> actualDeserializedTask = taskSerializer.deserialize(serializedTask);

            assertTrue(actualDeserializedTask.isPresent());
            assertEquals(expectedDeserializedTask, actualDeserializedTask.get());
        }
    }

    @Nested
    class DeserializeList {

        @Test
        void shouldThrowSerializerException_whenStringIsNull() {
            String serializedTaskList = null;

            assertThrows(SerializerException.class, () -> taskSerializer.deserializeList(serializedTaskList));
        }

        @Test
        void shouldThrowSerializerException_whenSerializedTasksFormatIsInvalid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> expectedDeserializedTasks = List.of(task);
            String serializedTasks = taskSerializer.serializeList(expectedDeserializedTasks);
            String invalidSerializedTasks = serializedTasks.replace(SerializationProperties.FIELD_DELIMITER, "");

            assertThrows(SerializerException.class, () -> taskSerializer.deserializeList(invalidSerializedTasks));
        }

        @Test
        void shouldThrowSerializerException_whenSingleSerializedTaskIsInvalid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> expectedDeserializedTasks = List.of(task);
            String serializedTasks = taskSerializer.serializeList(expectedDeserializedTasks);
            String invalidSerializedTasks = serializedTasks.replaceFirst(SerializationProperties.FIELD_DELIMITER, "");

            assertThrows(SerializerException.class, () -> taskSerializer.deserializeList(invalidSerializedTasks));
        }

        @Test
        void shouldThrowSerializerException_whenInvalidString() {
            String invalidSerializedTasks = "Invalid string";

            assertThrows(SerializerException.class, () -> taskSerializer.deserializeList(invalidSerializedTasks));
        }

        @Test
        void shouldReturnEmptyList_whenStringIsEmpty() throws SerializerException {
            String serializedTaskList = "";

            List<Task> deserializedTasks = taskSerializer.deserializeList(serializedTaskList);

            assertTrue(deserializedTasks.isEmpty());
        }

        @Test
        void shouldDeserializeSingleTaskList_whenStringIsValid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> expectedDeserializedTasks = List.of(task);
            String serializedTasks = taskSerializer.serializeList(expectedDeserializedTasks);

            List<Task> actualDeserializedTasks = taskSerializer.deserializeList(serializedTasks);

            assertEquals(expectedDeserializedTasks, actualDeserializedTasks);
        }

        @Test
        void shouldDeserializeTwoTaskList_whenStringIsValid() throws SerializerException {
            String taskName = "Task name";
            TaskState taskState = TaskState.SOLVED;
            Task task = new Task(taskName, taskState);
            List<Task> expectedDeserializedTasks = List.of(task, task);
            String serializedTasks = taskSerializer.serializeList(expectedDeserializedTasks);

            List<Task> actualDeserializedTasks = taskSerializer.deserializeList(serializedTasks);

            assertEquals(expectedDeserializedTasks, actualDeserializedTasks);
        }
    }
}