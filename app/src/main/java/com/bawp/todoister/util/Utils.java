package com.bawp.todoister.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bawp.todoister.adapter.RecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM d");

        return simpleDateFormat.format(date);
    }
    public static  void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
