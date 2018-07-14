package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PicCollect {
    @Id
    private int pic_collect_id;
    private int user_id;
    private int pic_group_id;
    private int submit_time;

    public int getPic_collect_id() {
        return pic_collect_id;
    }

    public void setPic_collect_id(int pic_collect_id) {
        this.pic_collect_id = pic_collect_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPic_group_id() {
        return pic_group_id;
    }

    public void setPic_group_id(int pic_group_id) {
        this.pic_group_id = pic_group_id;
    }

    public int getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(int submit_time) {
        this.submit_time = submit_time;
    }
}
