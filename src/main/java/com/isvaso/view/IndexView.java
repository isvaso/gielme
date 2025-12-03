package com.isvaso.view;

import com.isvaso.model.Task;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class IndexView extends BaseView {

    @Override
    public String render(List<Task> dataModel) {
        return LOGO +
               """
                   
                   
               L. List
               C. Create
               S. Solve
               D. Delete
                   
               Q. Quit
               """;
    }
}
