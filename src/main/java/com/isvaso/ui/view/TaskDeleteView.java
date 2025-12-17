package com.isvaso.ui.view;

import com.isvaso.domain.model.Task;
import com.isvaso.domain.model.TaskState;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskDeleteView extends BaseView {

    @Override
    public String render(List<Task> dataModel) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < dataModel.size(); i ++) {
            String command = "%s.".formatted(i);
            String renderedTask = renderTask(dataModel.get(i));
            stringBuilder.append(command).append(" ").append(renderedTask).append("\n");
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
