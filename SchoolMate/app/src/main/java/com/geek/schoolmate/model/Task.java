package com.geek.schoolmate.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ry41071 on 16-02-2016.
 */
public class Task implements Parcelable {
    int taskId;
    String taskTitle;
    String taskDescription;
    int marks;
    String taskSubmissionDate;

    public String getTaskPostDate() {
        return taskPostDate;
    }

    public void setTaskPostDate(String taskPostDate) {
        this.taskPostDate = taskPostDate;
    }

    String taskPostDate;
    String subject;
    String taskCreatorName;
    String taskCreatorDesig;
    String taskCreatorDpUrl;

    public Task() {
        super();
    }

    protected Task(Parcel in) {
        taskId = in.readInt();
        taskTitle = in.readString();
        taskDescription = in.readString();
        marks = in.readInt();
        taskSubmissionDate = in.readString();
        taskPostDate = in.readString();
        subject = in.readString();
        taskCreatorName = in.readString();
        taskCreatorDesig = in.readString();
        taskCreatorDpUrl = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(taskId);
        dest.writeString(taskTitle);
        dest.writeString(taskDescription);
        dest.writeInt(marks);
        dest.writeString(taskSubmissionDate);
        dest.writeString(taskPostDate);
        dest.writeString(subject);
        dest.writeString(taskCreatorName);
        dest.writeString(taskCreatorDesig);
        dest.writeString(taskCreatorDpUrl);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getTaskSubmissionDate() {
        return taskSubmissionDate;
    }

    public void setTaskSubmissionDate(String taskSubmissionDate) {
        this.taskSubmissionDate = taskSubmissionDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTaskCreatorName() {
        return taskCreatorName;
    }

    public void setTaskCreatorName(String taskCreatorName) {
        this.taskCreatorName = taskCreatorName;
    }

    public String getTaskCreatorDesig() {
        return taskCreatorDesig;
    }

    public void setTaskCreatorDesig(String taskCreatorDesig) {
        this.taskCreatorDesig = taskCreatorDesig;
    }

    public String getTaskCreatorDpUrl() {
        return taskCreatorDpUrl;
    }

    public void setTaskCreatorDpUrl(String taskCreatorDpUrl) {
        this.taskCreatorDpUrl = taskCreatorDpUrl;
    }
}
