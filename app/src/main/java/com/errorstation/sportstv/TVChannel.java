
package com.errorstation.sportstv;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVChannel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("channels")
    @Expose
    private List<Channel> channels = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

}
