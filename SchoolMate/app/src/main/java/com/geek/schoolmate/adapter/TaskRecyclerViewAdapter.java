package com.geek.schoolmate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geek.schoolmate.R;
import com.geek.schoolmate.model.Task;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by ry41071 on 16-02-2016.
 */
public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Task> tasks;
    private ImageLoader imageLoader;
    private View.OnClickListener onClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTaskTitle;
        TextView textViewTaskDesc;
        TextView textViewTaskPostDate;
        TextView textViewTaskMarks;
        TextView textViewTaskSubmissionDate;
        TextView textViewSubject;

        public ViewHolder(View view) {
            super(view);
            textViewTaskTitle = (TextView) view.findViewById(R.id.textViewTitle);
            textViewTaskDesc = (TextView) view.findViewById(R.id.textViewTaskDesc);
            textViewTaskPostDate = (TextView) view.findViewById(R.id.textViewTaskPostDate);
            textViewTaskMarks = (TextView) view.findViewById(R.id.textViewMarks);
            textViewTaskSubmissionDate = (TextView) view.findViewById(R.id.textViewTaskSubmissionDate);
            textViewSubject = (TextView) view.findViewById(R.id.textViewSubject);
        }
    }

    public TaskRecyclerViewAdapter(ArrayList<Task> tasks, ImageLoader imageLoader, View.OnClickListener onClickListener) {
        this.tasks = tasks;
        this.imageLoader = imageLoader;
        this.onClickListener = onClickListener;
    }

    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View view = holder.itemView;
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        Task task = tasks.get(position);
        holder.textViewTaskTitle.setText(task.getTaskTitle());
        holder.textViewTaskDesc.setText(task.getTaskDescription());
        holder.textViewTaskPostDate.setText(task.getTaskPostDate());
        holder.textViewTaskMarks.setText(task.getMarks() + " marks");
        holder.textViewTaskSubmissionDate.setText("Submission date : " + task.getTaskSubmissionDate());
        holder.textViewSubject.setText(task.getSubject());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }
}

