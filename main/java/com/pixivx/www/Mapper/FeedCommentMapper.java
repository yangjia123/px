package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.FeedComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedCommentMapper {

    @Select("SELECT * FROM feed_comment WHERE feed_id=#{feed_id} ORDER BY submit_time")
    List<FeedComment> selectFeedCommentByFeedid(@Param("feed_id")String feed_id);
}
