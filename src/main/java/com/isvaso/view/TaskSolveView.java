package com.isvaso.view;

import com.isvaso.controller.TaskListController;
import com.isvaso.controller.TaskSolveController;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.List;

public class TaskSolveView implements View {

    @Override
    public TaskSolveController getController() {
        return new TaskSolveController();
    }

    @Override
    public String render() {
        List<Task> tasks = getController().getAll();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < tasks.size(); i ++) {
            String command = "%s.".formatted(i + 2);
            String renderedTask = renderTask(tasks.get(i));
            stringBuilder.append(command).append(" ").append(renderedTask).append("\n");
        }
        stringBuilder.append("\n").append("1. Back").append("\n").append("0. Exit");
        return stringBuilder.toString();
    }

    private String renderTask(Task task) {
        boolean isSolved = TaskState.SOLVED.equals(task.getState());
        String isSolvedMarker = isSolved ? "x" : " ";
        String taskName = task.getName();
        return "[%s] %s".formatted(isSolvedMarker, taskName);
    }
}
