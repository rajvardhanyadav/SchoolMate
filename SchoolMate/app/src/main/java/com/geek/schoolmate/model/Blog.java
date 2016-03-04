package com.geek.schoolmate.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ry41071 on 15-02-2016.
 */
public class Blog implements Parcelable {
    String userName;
    String userDesignation;
    String userProfilePhotoUrl;
    String blogTitle;
    String blogDesc;
    ArrayList<String> blogImageUrl;
    String blogPostDate;
    int views;

    public Blog(){
        super();
    }

    protected Blog(Parcel in) {
        userName = in.readString();
        userDesignation = in.readString();
        userProfilePhotoUrl = in.readString();
        blogTitle = in.readString();
        blogDesc = in.readString();
        blogPostDate = in.readString();
        views = in.readInt();
        blogImageUrl = in.createStringArrayList();
    }

    public static final Creator<Blog> CREATOR = new Creator<Blog>() {
        @Override
        public Blog createFromParcel(Parcel in) {
            return new Blog(in);
        }

        @Override
        public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesignation() {
        return userDesignation;
    }

    public void setUserDesignation(String userDesignation) {
        this.userDesignation = userDesignation;
    }

    public String getUserProfilePhotoUrl() {
        return userProfilePhotoUrl;
    }

    public void setUserProfilePhotoUrl(String userProfilePhotoUrl) {
        this.userProfilePhotoUrl = userProfilePhotoUrl;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDesc() {
        return blogDesc;
    }

    public void setBlogDesc(String blogDesc) {
        this.blogDesc = blogDesc;
    }

    public ArrayList<String> getBlogImageUrl() {
        return blogImageUrl;
    }

    public void setBlogImageUrl(ArrayList<String> blogImageUrl) {
        this.blogImageUrl = new ArrayList<>();
        this.blogImageUrl = blogImageUrl;
    }

    public String getBlogPostDate() {
        return blogPostDate;
    }

    public void setBlogPostDate(String blogPostDate) {
        this.blogPostDate = blogPostDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getUserName());
        dest.writeString(getUserDesignation());
        dest.writeString(getUserProfilePhotoUrl());
        dest.writeString(getBlogTitle());
        dest.writeString(getBlogDesc());
        dest.writeString(getBlogPostDate());
        dest.writeInt(getViews());
        dest.writeStringList(getBlogImageUrl());
    }
}
