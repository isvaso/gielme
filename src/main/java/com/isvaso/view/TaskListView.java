package com.isvaso.view;

import com.isvaso.controller.TaskListController;
import com.isvaso.model.Task;
import com.isvaso.model.TaskState;

import java.util.List;

public class TaskListView extends BaseView {

    @Override
    public TaskListController getController() {
        return new TaskListController(this);
    }

    @Override
    public String render() {
        List<Task> tasks = getController().getAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            String renderedTask = renderTask(task);
            stringBuilder.append(renderedTask).append("\n");
        }
        return LOGO +
                """
                    
                    
                """
                + stringBuilder +
                """
                    
                B. Back
                Q. Quit
                """;
    }

    private String renderTask(Task task) {
        boolean isSolved = TaskState.SOLVED.equals(task.getState());
        String isSolvedMarker = isSolved ? "x" : " ";
        String taskName = task.getName();
        return "[%s] %s".formatted(isSolvedMarker, taskName);
    }
}
