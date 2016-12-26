
package com.errorstation.sportstv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {

    @SerializedName("team1logo")
    @Expose
    private String team1logo;
    @SerializedName("team1runs")
    @Expose
    private String team1runs;
    @SerializedName("team1wickets")
    @Expose
    private String team1wickets;
    @SerializedName("team1overs")
    @Expose
    private String team1overs;
    @SerializedName("team2logo")
    @Expose
    private String team2logo;
    @SerializedName("team2runs")
    @Expose
    private String team2runs;
    @SerializedName("team2wickets")
    @Expose
    private String team2wickets;
    @SerializedName("team2overs")
    @Expose
    private String team2overs;

    public String getTeam1logo() {
        return team1logo;
    }

    public void setTeam1logo(String team1logo) {
        this.team1logo = team1logo;
    }

    public String getTeam1runs() {
        return team1runs;
    }

    public void setTeam1runs(String team1runs) {
        this.team1runs = team1runs;
    }

    public String getTeam1wickets() {
        return team1wickets;
    }

    public void setTeam1wickets(String team1wickets) {
        this.team1wickets = team1wickets;
    }

    public String getTeam1overs() {
        return team1overs;
    }

    public void setTeam1overs(String team1overs) {
        this.team1overs = team1overs;
    }

    public String getTeam2logo() {
        return team2logo;
    }

    public void setTeam2logo(String team2logo) {
        this.team2logo = team2logo;
    }

    public String getTeam2runs() {
        return team2runs;
    }

    public void setTeam2runs(String team2runs) {
        this.team2runs = team2runs;
    }

    public String getTeam2wickets() {
        return team2wickets;
    }

    public void setTeam2wickets(String team2wickets) {
        this.team2wickets = team2wickets;
    }

    public String getTeam2overs() {
        return team2overs;
    }

    public void setTeam2overs(String team2overs) {
        this.team2overs = team2overs;
    }

}
