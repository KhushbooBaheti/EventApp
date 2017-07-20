package com.example.user.eventapp.java_models;

/**
 * Created by user on 20/7/17.
 */

public class Papers {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPapertopic() {
        return papertopic;
    }

    public void setPapertopic(String papertopic) {
        this.papertopic = papertopic;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Papers(int id, String topic, String papertopic, String status, String url) {
        this.id = id;
        this.topic = topic;
        this.papertopic = papertopic;
        Status = status;
        this.url = url;
    }

    private  int id;
    private  String topic;
    private  String papertopic;
    private  String Status;
    private  String url;
}
