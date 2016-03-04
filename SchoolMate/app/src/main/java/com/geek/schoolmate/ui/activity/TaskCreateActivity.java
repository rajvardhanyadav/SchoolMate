package com.geek.schoolmate.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.geek.schoolmate.R;

public class TaskCreateActivity extends BaseActivity {
    private static final String TAG = "TaskCreateActivity";
    private EditText editTextMarks;
    private Spinner spinnerSubjects;
    private Spinner spinnerClasses;
    String[] subjects = {"SUBJECT", "HR", "FINANCE", "MARKETING"};
    String[] classes = {"CLASS", "MBA 1", "MBA 2", "MBA 3", "MBA 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        editTextMarks = (EditText) findViewById(R.id.editTextMarks);
        spinnerSubjects = (Spinner) findViewById(R.id.spinnerSubject);
        spinnerClasses = (Spinner) findViewById(R.id.spinnerClass);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextMarks.getWindowToken(), 0);
        }

        ArrayAdapter<String> adapterSubjects = new ArrayAdapter<>(this,
                R.layout.row_spinner_heading, subjects);
        adapterSubjects.setDropDownViewResource(R.layout.row_spinner);
        spinnerSubjects.setAdapter(adapterSubjects);

        ArrayAdapter<String> adapterClasses = new ArrayAdapter<>(this,
                R.layout.row_spinner_heading, classes);
        adapterClasses.setDropDownViewResource(R.layout.row_spinner);
        spinnerClasses.setAdapter(adapterClasses);
    }
}