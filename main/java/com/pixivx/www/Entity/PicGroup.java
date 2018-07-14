package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PicGroup {
    @Id
    private int pic_group_id;
    private String title;
    private String description;
    private int user_id;
    private int click_times;
    private String review_status;
    private int review_time;
    private String pic_label;
    private String is_public_status;
    private int submit_time;

    public int getPic_group_id() {
        return pic_group_id;
    }

    public void setPic_group_id(int pic_group_id) {
        this.pic_group_id = pic_group_id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClick_times() {
        return click_times;
    }

    public void setClick_times(int click_times) {
        this.click_times = click_times;
    }

    public String getReview_status() {
        return review_status;
    }

    public void setReview_status(String review_status) {
        this.review_status = review_status;
    }

    public int getReview_time() {
        return review_time;
    }

    public void setReview_time(int review_time) {
        this.review_time = review_time;
    }

    public String getPic_label() {
        return pic_label;
    }

    public void setPic_label(String pic_label) {
        this.pic_label = pic_label;
    }

    public String getIs_public_status() {
        return is_public_status;
    }

    public void setIs_public_status(String is_public_status) {
        this.is_public_status = is_public_status;
    }

    public int getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(int submit_time) {
        this.submit_time = submit_time;
    }
}
