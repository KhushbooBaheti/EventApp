package com.example.user.eventapp.java_models;

import java.util.Date;

/**
 * Created by user on 8/5/17.
 */

public class Conference {
    private int confId;
    private String confName;
    private Date confDate;
    private String venue;
    private String confChair;
    private int days;
    private int feesList;
    private int feesPart;
    private String confDescription;
    private String schedule;
    private int imageId;


    public Conference(int id,String name,Date date){
        confId=id;
        confName=name;
        confDate=date;

    }
    public Conference(int id,String name,Date date,
                      String venue,String confChair,
                      int days,int feesList,int feesPart,
                      String confDescription,String schedule,int imageId){

        confId=id;
        confName=name;
        confDate=date;
        this.venue=venue;
        this.confChair=confChair;
        this.days=days;
        this.feesList=feesList;
        this.feesPart=feesPart;
        this.confDescription=confDescription;
        this.schedule=schedule;
        this.imageId=imageId;
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

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public void setConfDate(Date confDate) {
        this.confDate = confDate;
    }

    public void setConfDescription(String confDescription) {
        this.confDescription = confDescription;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
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
