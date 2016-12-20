
package com.errorstation.sportstv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("channelname")
    @Expose
    private String channelname;
    @SerializedName("starttime")
    @Expose
    private String starttime;
    @SerializedName("live")
    @Expose
    private String live;

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

}
