package com.isvaso.view;

import com.isvaso.model.Task;

import java.util.List;

public interface View {
    String render(List<Task> dataModel);
}
