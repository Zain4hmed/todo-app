package com.bawp.todoister;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText enterTodo;
    private ImageButton calenderButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioGroup selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Date dueDate;
    Calendar calendar = Calendar.getInstance();

    public BottomSheetFragment(){

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calenderGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calenderButton = view.findViewById(R.id.today_calendar_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        saveButton = view.findViewById(R.id.save_todo_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        Chip todayChip = view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);


        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calenderButton.setOnClickListener(view12 ->{
            calenderGroup.setVisibility(calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        });

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year,month,dayOfMonth);
            dueDate = calendar.getTime();
//            Log.d("Cal", "onViewCreated: ===> month : "+(month+1)+" , day: "+dayOfMonth);

        });

        saveButton.setOnClickListener(view1 -> {
            String task = enterTodo.getText().toString().trim();
            if (!TextUtils.isEmpty(task) && dueDate != null) {
                Task myTask = new Task(task, Priority.HIGH, dueDate ,Calendar.getInstance().getTime(), false);
                TaskViewModel.insert(myTask);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.today_chip) {
            calendar.clear();
            //set date for today
            calendar.add(Calendar.DAY_OF_YEAR,0);
            dueDate = calendar.getTime();
            Log.d("TIME", "onClick: "+dueDate.toString());
        } else if (id == R.id.tomorrow_chip) {
            //set date for tomrrow
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dueDate = calendar.getTime();
            Log.d("TIME", "onClick: "+dueDate.toString());
        } else if (id == R.id.next_week_chip) {
            //set date for next week
            calendar.add(Calendar.DAY_OF_YEAR,7);
            dueDate = calendar.getTime();
            Log.d("TIME", "onClick: "+dueDate.toString());
        }
    }
}