
package com.errorstation.cricbd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Newsfeed {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imagelink")
    @Expose
    private String imagelink;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("createdon")
    @Expose
    private String createdon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

}
