
package com.errorstation.tigerslive;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVChannel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("starttime")
    @Expose
    private String starttime;
    @SerializedName("livestatus")
    @Expose
    private String livestatus;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;
    @SerializedName("matchdata")
    @Expose
    private List<Matchdatum> matchdata = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getLivestatus() {
        return livestatus;
    }

    public void setLivestatus(String livestatus) {
        this.livestatus = livestatus;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public List<Matchdatum> getMatchdata() {
        return matchdata;
    }

    public void setMatchdata(List<Matchdatum> matchdata) {
        this.matchdata = matchdata;
    }

}
