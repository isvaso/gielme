package com.isvaso.ui.view;

import com.isvaso.domain.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaskCreateView extends BaseView {

    @Override
    public String render(List<Task> dataModel) {
        return LOGO +
               """
                   
                   
               Enter new task name
                   
               B. Back
               Q. Quit
               """;
    }
}
