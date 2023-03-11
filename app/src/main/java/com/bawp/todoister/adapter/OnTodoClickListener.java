package com.bawp.todoister.adapter;

import com.bawp.todoister.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(int adapterPosition , Task task);
    void onTodoRadioButtonClick(int adapterPostion);
}
