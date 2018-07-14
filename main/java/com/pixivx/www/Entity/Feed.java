package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Feed {
    @Id
    private String feed_id;
    private String user_id;
    private String description;
    private int submit_time;

    public String getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(int submit_time) {
        this.submit_time = submit_time;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "feed_id='" + feed_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", description='" + description + '\'' +
                ", submit_time=" + submit_time +
                '}';
    }
}
