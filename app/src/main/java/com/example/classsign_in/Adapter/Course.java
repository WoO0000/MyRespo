package com.example.classsign_in.Adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {
    public String getCollegeName() {return mCollegeName;}

    public void setTitle(String mCollegeName) {
        this.mCollegeName = mCollegeName;
    }

    public Integer getCourseId() {
        return mCourseId;
    }

    public void setSource(Integer mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public void setCourseName(String mCourseName) {
        this.mCourseName = mCourseName;
    }

    public String getCoursePhoto() {return mCoursePhoto;}

    public void setCoursePhoto(String mCoursePhoto) {
        this.mCoursePhoto = mCoursePhoto;
    }

    public Integer getId() {
        return mId;
    }

    public String getDate() {
        return mPublishTime;
    }

    public Course() {
    }

    @Expose(serialize = false, deserialize = false)
    private Integer mId;

    @SerializedName("CollegeName")
    private String mCollegeName;

    @SerializedName("CourseId")
    private Integer mCourseId;

    @SerializedName("CourseName")
    private String mCourseName;

    @SerializedName("CoursePhoto")
    private String mCoursePhoto;

    @SerializedName("ctime")
    private String mPublishTime;


}