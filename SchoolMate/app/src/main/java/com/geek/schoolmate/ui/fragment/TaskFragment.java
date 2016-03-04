package com.geek.schoolmate.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geek.schoolmate.R;
import com.geek.schoolmate.adapter.SimpleDividerItemDecoration;
import com.geek.schoolmate.adapter.TaskRecyclerViewAdapter;
import com.geek.schoolmate.callback.IGetTasksList;
import com.geek.schoolmate.model.Task;
import com.geek.schoolmate.ui.activity.BlogDetailsActivity;
import com.geek.schoolmate.ui.activity.MainActivity;
import com.geek.schoolmate.ui.activity.TaskDetailsActivity;
import com.geek.schoolmate.utils.Constants;

import java.util.ArrayList;

/**
 * Created by ry41071 on 15-02-2016.
 */
public class TaskFragment extends Fragment implements IGetTasksList, View.OnClickListener {
    public static final String TAG = "TaskFragment";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Task> mTask;

    public static TaskFragment newInstance() {
        Log.d(TAG, "newInstance()");
        TaskFragment fragment = new TaskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getNetworkManager().getTaskList(this);
    }

    @Override
    public void onGetTasksListSuccessCallback(Object response) {
        Log.d(TAG, "onGetTasksListSuccessCallback()");
        mTask = (ArrayList<Task>) response;
        Log.d(TAG, "Total mBlog " + mTask.size());
        mAdapter = new TaskRecyclerViewAdapter(mTask, ((MainActivity) getActivity()).getImageLoader(), this);
//        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGetTasksListFailureCallback(Object response) {
        Log.d(TAG, "onGetTasksListFailureCallback()");
        Toast.makeText(getActivity(), "Unable to fetch mBlog.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.linearLayoutRootTask:
                int position = (int) v.getTag();
                Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
                intent.putExtra(Constants.PARC_TASK, mTask.get(position));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}