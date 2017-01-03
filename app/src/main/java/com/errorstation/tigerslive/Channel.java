
package com.errorstation.tigerslive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("channelname")
    @Expose
    private String channelname;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
