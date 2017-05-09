package com.example.user.eventapp.basic;

import java.util.Date;

/**
 * Created by user on 8/5/17.
 */

public class Conference {
    private int confId;
    private String confName;
    private Date confDate;
    private String confDescription;

    public int getConfId() {
        return confId;
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
