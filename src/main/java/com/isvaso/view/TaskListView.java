package com.isvaso.view;

import com.isvaso.model.Task;
import com.isvaso.model.TaskState;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskListView extends BaseView {

    @Override
    public String render(List<Task> dataModel) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : dataModel) {
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
