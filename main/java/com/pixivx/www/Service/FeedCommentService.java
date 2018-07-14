package com.pixivx.www.Service;

import com.pixivx.www.Entity.FeedComment;
import com.pixivx.www.Mapper.FeedCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedCommentService {
    @Autowired private FeedCommentMapper feedCommentMapper;

    public List<FeedComment> selectFeedCommentByFeedid(String feed_id){
        return feedCommentMapper.selectFeedCommentByFeedid(feed_id);
    }
}
