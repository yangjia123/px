package com.pixivx.www.Service;

import com.pixivx.www.Entity.Feed;
import com.pixivx.www.Mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    @Autowired private FeedMapper feedMapper;

    public List<Feed> selectFeedByUserid(String user_id){
        return feedMapper.selectFeedByUserid(user_id);
    }
}
