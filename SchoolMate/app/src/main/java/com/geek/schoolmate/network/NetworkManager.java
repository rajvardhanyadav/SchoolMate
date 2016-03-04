package com.geek.schoolmate.network;

import android.content.Context;
import android.util.Log;

import com.geek.schoolmate.callback.IGetBlogsList;
import com.geek.schoolmate.callback.IGetTasksList;
import com.geek.schoolmate.model.Blog;
import com.geek.schoolmate.model.Task;
import com.geek.schoolmate.parser.JSONParser;
import com.geek.schoolmate.utils.Utils;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ry41071 on 16-02-2016.
 */
public class NetworkManager {
    private final String TAG = "NetworkManager";
    private Context mContext;
    private JSONParser mJsonParser;

    public NetworkManager(Context context) {
        mContext = context;
        mJsonParser = new JSONParser(context);
    }

    public void getBlogList(final IGetBlogsList responseHandler) {
        Log.d(TAG, "getBlogList()");
        String jsonString = Utils.readExternalStorageFile("response_get_blog_list");
        try {
            ArrayList<Blog> blog = mJsonParser.parseGetBlogsListResponse(jsonString);
            if (blog != null) {
                responseHandler.onGetBlogsListSuccessCallback(blog);
                return;
            }
        } catch (JSONException e) {
            responseHandler.onGetBlogsListFailureCallback(e.getMessage());
        }
        return;
    }

    public void getTaskList(final IGetTasksList responseHandler) {
        Log.d(TAG, "getBlogList()");
        String jsonString = Utils.readExternalStorageFile("response_get_task_list");
        try {
            ArrayList<Task> task = mJsonParser.parseGetTaskListResponse(jsonString);
            if (task != null) {
                responseHandler.onGetTasksListSuccessCallback(task);
                return;
            }
        } catch (JSONException e) {
            responseHandler.onGetTasksListFailureCallback(e.getMessage());
        }
        return;
    }
}
