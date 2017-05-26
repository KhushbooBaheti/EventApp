package com.example.user.eventapp.java_models;

import java.util.Date;

/**
 * Created by user on 8/5/17.
 */

public class Conference {
    private int confId;
    private String confName;
    private Date confDate;
    private String confDescription;
    private String schedule;
    private String Speakers;


    public int getConfId() {
        return confId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSpeakers() {
        return Speakers;
    }

    public void setSpeakers(String speakers) {
        Speakers = speakers;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }

    public void setConfDescription(String confDescription) {
        this.confDescription = confDescription;
    }

    public String getConfName() {

        return confName;
    }

    public Date getConfDate() {
        return confDate;
    }

    public String getConfDescription() {
        return confDescription;
    }
}
