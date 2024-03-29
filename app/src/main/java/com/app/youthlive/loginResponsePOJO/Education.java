package com.app.youthlive.loginResponsePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/20/2017.
 */

public class Education {

    @SerializedName("educationId")
    @Expose
    private String educationId;
    @SerializedName("schoolTitle")
    @Expose
    private String schoolTitle;
    @SerializedName("time")
    @Expose
    private String time;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getSchoolTitle() {
        return schoolTitle;
    }

    public void setSchoolTitle(String schoolTitle) {
        this.schoolTitle = schoolTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
