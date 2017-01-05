
package com.errorstation.cricbd;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("newsfeed")
    @Expose
    private List<Newsfeed> newsfeed = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Newsfeed> getNewsfeed() {
        return newsfeed;
    }

    public void setNewsfeed(List<Newsfeed> newsfeed) {
        this.newsfeed = newsfeed;
    }

}
