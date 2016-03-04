package com.geek.schoolmate.parser;

import android.content.Context;
import android.util.Log;

import com.geek.schoolmate.model.Blog;
import com.geek.schoolmate.model.Task;
import com.geek.schoolmate.utils.JSONConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ry41071 on 16-02-2016.
 */
public class JSONParser {
    private final String TAG = "JSONParser";
    private Context mContext;

    public JSONParser(Context context) {
        mContext = context;
    }

    public ArrayList<Blog> parseGetBlogsListResponse(String jsonString) throws JSONException {
        Log.d(TAG, "parseGetBlogsListResponse()");
        ArrayList<Blog> blog = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        if (jsonObject == null) {
            Log.e(TAG, "parseGetBlogsListResponse(response) failed, jsonObject is null");
            throw new JSONException("parseGetBlogsListResponse(response) failed, jsonObject is null");
        }
        JSONObject jsonObjectError = jsonObject.getJSONObject(JSONConstants.KEY_ERROR);
        int statusCode = jsonObjectError.getInt(JSONConstants.KEY_STATUS_CODE);
        if (statusCode != 0) {
            Log.e(TAG, "Internal server error occurred.");
            throw new JSONException("Internal server error occurred.");
        }
        JSONArray jsonArrayData = jsonObject.getJSONArray(JSONConstants.KEY_DATA);
        for (int i = 0; i < jsonArrayData.length(); i++) {
            JSONObject jsonObjectBlog = jsonArrayData.getJSONObject(i);
            Blog singleBlog;
            try {
                singleBlog = parseBlogJSON(jsonObjectBlog);
            } catch (JSONException e) {
                throw new JSONException(e.getMessage());
            }
            blog.add(singleBlog);
        }
        return blog;
    }

    private Blog parseBlogJSON(JSONObject jsonObjectBlog) throws JSONException {
        Log.d(TAG, "parseBlogJSON()");
        Blog blog = new Blog();
        blog.setUserName(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_USERNAME));
        blog.setUserDesignation(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_USER_DESIG));
        blog.setUserProfilePhotoUrl(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_USER_DP_URL));
        blog.setBlogTitle(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_TITLE));
        blog.setBlogDesc(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_DESC));
        blog.setBlogPostDate(jsonObjectBlog.getString(JSONConstants.KEY_BLOG_POST_DATE));
        blog.setViews(jsonObjectBlog.getInt(JSONConstants.KEY_BLOG_VIEWS));
        JSONArray jsonArrayImageUrls = jsonObjectBlog.getJSONArray(JSONConstants.KEY_BLOG_IMAGE_URLS);
        ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < jsonArrayImageUrls.length(); i++) {
            JSONObject jsonObjectImageUrl = jsonArrayImageUrls.getJSONObject(i);
            String imageUrl = jsonObjectImageUrl.getString(JSONConstants.KEY_BLOG_IMAGE_URL);
            imageUrls.add(imageUrl);
        }
        blog.setBlogImageUrl(imageUrls);
        return blog;
    }

    public ArrayList<Task> parseGetTaskListResponse(String jsonString) throws JSONException {
        Log.d(TAG, "parseGetTaskListResponse()");
        ArrayList<Task> task = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(jsonString);
        if (jsonObject == null) {
            Log.e(TAG, "parseGetTaskListResponse(response) failed, jsonObject is null");
            throw new JSONException("parseGetTaskListResponse(response) failed, jsonObject is null");
        }
        JSONObject jsonObjectError = jsonObject.getJSONObject(JSONConstants.KEY_ERROR);
        int statusCode = jsonObjectError.getInt(JSONConstants.KEY_STATUS_CODE);
        if (statusCode != 0) {
            Log.e(TAG, "Internal server error occurred.");
            throw new JSONException("Internal server error occurred.");
        }
        JSONArray jsonArrayData = jsonObject.getJSONArray(JSONConstants.KEY_DATA);
        for (int i = 0; i < jsonArrayData.length(); i++) {
            JSONObject jsonObjectTask = jsonArrayData.getJSONObject(i);
            Task singleTask;
            try {
                singleTask = parseTaskJSON(jsonObjectTask);
            } catch (JSONException e) {
                throw new JSONException(e.getMessage());
            }
            task.add(singleTask);
        }
        return task;
    }

    private Task parseTaskJSON(JSONObject jsonObjectTask) throws JSONException {
        Log.d(TAG, "parseTaskJSON()");
        Task task = new Task();
        task.setTaskCreatorName(jsonObjectTask.getString(JSONConstants.KEY_BLOG_USERNAME));
        task.setTaskCreatorDesig(jsonObjectTask.getString(JSONConstants.KEY_BLOG_USER_DESIG));
        task.setTaskCreatorDpUrl(jsonObjectTask.getString(JSONConstants.KEY_BLOG_USER_DP_URL));
        task.setTaskTitle(jsonObjectTask.getString(JSONConstants.KEY_TASK_TITLE));
        task.setTaskDescription(jsonObjectTask.getString(JSONConstants.KEY_TASK_DESC));
        task.setMarks(jsonObjectTask.getInt(JSONConstants.KEY_TASK_MARKS));
        task.setTaskSubmissionDate(jsonObjectTask.getString(JSONConstants.KEY_TASK_SUBM_DATE));
        task.setSubject(jsonObjectTask.getString(JSONConstants.KEY_TASK_SUBJECT));
        task.setTaskPostDate(jsonObjectTask.getString(JSONConstants.KEY_TASK_POST_DATE));
        return task;
    }
}
