package com.geek.schoolmate.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.model.Task;
import com.geek.schoolmate.utils.Constants;

public class TaskDetailsActivity extends BaseActivity {
    private Task task;
    private TextView textViewSubject;
    private TextView textViewMarks;
    private TextView textViewTaskPostDate;
    private TextView textViewTaskSubmissionDate;
    private TextView textViewTitle;
    private TextView textViewTaskDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        task = getIntent().getParcelableExtra(Constants.PARC_TASK);
        textViewSubject = (TextView) findViewById(R.id.textViewSubject);
        textViewMarks = (TextView) findViewById(R.id.textViewMarks);
        textViewTaskPostDate = (TextView) findViewById(R.id.textViewTaskPostDate);
        textViewTaskSubmissionDate = (TextView) findViewById(R.id.textViewTaskSubmissionDate);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewTaskDesc = (TextView) findViewById(R.id.textViewTaskDesc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (task != null) {
            textViewSubject.setText(task.getSubject());
            textViewMarks.setText(task.getMarks() + " marks");
            textViewTaskPostDate.setText(task.getTaskPostDate());
            textViewTaskSubmissionDate.setText("Submission date : " + task.getTaskSubmissionDate());
            textViewTitle.setText(task.getTaskTitle());
            textViewTaskDesc.setText(task.getTaskDescription());
        }
    }
}
