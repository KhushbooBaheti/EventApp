package com.example.user.eventapp.java_models;

import java.util.Date;

/**
 * Created by user on 8/5/17.
 */

public class Conference {
    private int confId;
    private String confTopic;
    private Date confDate;
    private String venue;
    private String confChair;
    private int days;
    private int feesList;
    private int feesPart;
    private String confAbout;
    private String schedule;
    private String imageURL;
    private boolean readCond;


    public Conference(int id,String name,Date date){
        confId=id;
        confTopic=name;
        confDate=date;

    }
    public Conference(int id,String topic,Date date,
                      String venue,String confChair,
                      int days,int feesList,int feesPart,
                      String confAbout,String schedule,String imageURL){

        confId=id;
        confTopic=topic;
        confDate=date;
        this.venue=venue;
        this.confChair=confChair;
        this.days=days;
        this.feesList=feesList;
        this.feesPart=feesPart;
        this.confAbout=confAbout;
        this.schedule=schedule;
        this.imageURL=imageURL;
    }

    public int getConfId() {
        return confId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }



    public void setConfId(int confId) {
        this.confId = confId;
    }

    public void setConfName(String confTopic) {
        this.confTopic = confTopic;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }

    public void setConfDescription(String confAbout) {
        this.confAbout = confAbout;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getConfChair() {
        return confChair;
    }

    public void setConfChair(String confChair) {
        this.confChair = confChair;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getFeesList() {
        return feesList;
    }

    public void setFeesList(int feesList) {
        this.feesList = feesList;
    }

    public int getFeesPart() {
        return feesPart;
    }

    public void setFeesPart(int feesPart) {
        this.feesPart = feesPart;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageId(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getConfName() {

        return confTopic;

    }

    public Date getConfDate() {
        return confDate;
    }

    public String getConfDescription() {
        return confAbout;
    }
}
