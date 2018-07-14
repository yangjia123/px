package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OfficalMessage {
    @Id
    private String offical_message_id;
    private String title;
    private String description;
    private String pic_url;
    private long submit_time;

    public String getOffical_message_id() {
        return offical_message_id;
    }

    public void setOffical_message_id(String offical_message_id) {
        this.offical_message_id = offical_message_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public long getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(long submit_time) {
        this.submit_time = submit_time;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
