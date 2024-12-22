package com.isvaso.view;

import com.isvaso.controller.TaskListController;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.List;

public class TaskListView implements View {

    @Override
    public TaskListController getController() {
        return new TaskListController();
    }

    @Override
    public String render() {
        List<Task> tasks = getController().getAll();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < tasks.size(); i ++) {
            String renderedTask = renderTask(tasks.get(i));
            stringBuilder.append(renderedTask).append("\n");
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
