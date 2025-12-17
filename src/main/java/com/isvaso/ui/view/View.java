package com.isvaso.ui.view;

import com.isvaso.domain.model.Task;

import java.util.List;

public interface View {
    String render(List<Task> dataModel);
}
