package com.app.youthlive.loginResponsePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/8/2017.
 */

public class Data {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("verificationCode")
    @Expose
    private String verificationCode;
    @SerializedName("userImage")
    @Expose
    private String userImage;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pid")
    @Expose
    private String pid;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("youthLiveId")
    @Expose
    private String youthLiveId;
    @SerializedName("followings")
    @Expose
    private String followings;
    @SerializedName("fans")
    @Expose
    private String fans;
    @SerializedName("friends")
    @Expose
    private String friends;
    @SerializedName("topFans")
    @Expose
    private List<TopFan> topFans = null;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("userCategory")
    @Expose
    private String userCategory;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("education")
    @Expose
    private List<Education> education = null;
    @SerializedName("career")
    @Expose
    private List<Career> career = null;
    @SerializedName("coverImage")
    @Expose
    private List<CoverImage> coverImage = null;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("isMobile")
    @Expose
    private String isMobile;
    @SerializedName("isFbConnected")
    @Expose
    private String isFbConnected;
    @SerializedName("isGoogleConnected")
    @Expose
    private String isGoogleConnected;
    @SerializedName("isTwitterConnected")
    @Expose
    private String isTwitterConnected;
    @SerializedName("isPhoneVerified")
    @Expose
    private String isPhoneVerified;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getYouthLiveId() {
        return youthLiveId;
    }

    public void setYouthLiveId(String youthLiveId) {
        this.youthLiveId = youthLiveId;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    public List<TopFan> getTopFans() {
        return topFans;
    }

    public void setTopFans(List<TopFan> topFans) {
        this.topFans = topFans;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Career> getCareer() {
        return career;
    }

    public void setCareer(List<Career> career) {
        this.career = career;
    }

    public List<CoverImage> getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(List<CoverImage> coverImage) {
        this.coverImage = coverImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    public String getIsFbConnected() {
        return isFbConnected;
    }

    public void setIsFbConnected(String isFbConnected) {
        this.isFbConnected = isFbConnected;
    }

    public String getIsGoogleConnected() {
        return isGoogleConnected;
    }

    public void setIsGoogleConnected(String isGoogleConnected) {
        this.isGoogleConnected = isGoogleConnected;
    }

    public String getIsTwitterConnected() {
        return isTwitterConnected;
    }

    public void setIsTwitterConnected(String isTwitterConnected) {
        this.isTwitterConnected = isTwitterConnected;
    }

    public String getIsPhoneVerified() {
        return isPhoneVerified;
    }

    public void setIsPhoneVerified(String isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

}
