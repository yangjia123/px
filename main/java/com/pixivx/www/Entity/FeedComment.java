package com.pixivx.www.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeedComment {
    @Id
    private String feed_comment_id;
    private String description;
    private String feed_id;
    private String user_id;
    private int submit_time;

    public String getFeed_comment_id() {
        return feed_comment_id;
    }

    public void setFeed_comment_id(String feed_comment_id) {
        this.feed_comment_id = feed_comment_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public int getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(int submit_time) {
        this.submit_time = submit_time;
    }

    @Override
    public String toString() {
        return "FeedCommentMapper{" +
                "feed_comment_id='" + feed_comment_id + '\'' +
                ", description='" + description + '\'' +
                ", feed_id='" + feed_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", submit_time=" + submit_time +
                '}';
    }
}

