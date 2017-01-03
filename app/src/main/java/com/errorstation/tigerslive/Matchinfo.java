
package com.errorstation.tigerslive;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Matchinfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seriesname")
    @Expose
    private String seriesname;
    @SerializedName("matchno")
    @Expose
    private String matchno;
    @SerializedName("team1")
    @Expose
    private String team1;
    @SerializedName("team1logo")
    @Expose
    private String team1logo;
    @SerializedName("team2")
    @Expose
    private String team2;
    @SerializedName("team2logo")
    @Expose
    private String team2logo;
    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("starttime")
    @Expose
    private String starttime;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("matchtype")
    @Expose
    private String matchtype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public String getMatchno() {
        return matchno;
    }

    public void setMatchno(String matchno) {
        this.matchno = matchno;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam1logo() {
        return team1logo;
    }

    public void setTeam1logo(String team1logo) {
        this.team1logo = team1logo;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam2logo() {
        return team2logo;
    }

    public void setTeam2logo(String team2logo) {
        this.team2logo = team2logo;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

}
