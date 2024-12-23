package com.isvaso.view;

import com.isvaso.controller.TaskSolveController;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.List;

public class TaskSolveView implements View {

    @Override
    public TaskSolveController getController() {
        return new TaskSolveController(this);
    }

    @Override
    public String render() {
        List<Task> tasks = getController().getAll();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < tasks.size(); i ++) {
            String command = "%s.".formatted(i);
            String renderedTask = renderTask(tasks.get(i));
            stringBuilder.append(command).append(" ").append(renderedTask).append("\n");
        }
        stringBuilder.append("\n").append("B. Back").append("\n").append("Q. Quit").append("\n");
        return stringBuilder.toString();
    }

    private String renderTask(Task task) {
        boolean isSolved = TaskState.SOLVED.equals(task.getState());
        String isSolvedMarker = isSolved ? "x" : " ";
        String taskName = task.getName();
        return "[%s] %s".formatted(isSolvedMarker, taskName);
    }
}
