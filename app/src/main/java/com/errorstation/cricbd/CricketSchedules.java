
package com.errorstation.cricbd;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CricketSchedules {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("matchinfo")
    @Expose
    private List<Matchinfo> matchinfo = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Matchinfo> getMatchinfo() {
        return matchinfo;
    }

    public void setMatchinfo(List<Matchinfo> matchinfo) {
        this.matchinfo = matchinfo;
    }

}
