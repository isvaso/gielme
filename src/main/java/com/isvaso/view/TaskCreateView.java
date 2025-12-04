package com.isvaso.view;

import com.isvaso.model.Task;
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
