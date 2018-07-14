package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IndexRec {
    @Id
    private int index_rec_id;
    private int pic_group_id;
    private int submit_time;

    public int getIndex_rec_id() {
        return index_rec_id;
    }

    public void setIndex_rec_id(int index_rec_id) {
        this.index_rec_id = index_rec_id;
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
