
package com.errorstation.cricbd;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiveScores {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("team1")
    @Expose
    private String team1;
    @SerializedName("team2")
    @Expose
    private String team2;
    @SerializedName("scores")
    @Expose
    private List<Score> scores = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

}
