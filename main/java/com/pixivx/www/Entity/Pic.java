package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pic {
    @Id
    private int pic_id;
    private int pic_group_id;
    private String url;
    private int submit_time;

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public int getPic_group_id() {
        return pic_group_id;
    }

    public void setPic_group_id(int pic_group_id) {
        this.pic_group_id = pic_group_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(int submit_time) {
        this.submit_time = submit_time;
    }
}
