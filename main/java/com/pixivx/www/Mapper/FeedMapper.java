package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.Feed;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedMapper {

    @Select("SELECT * FROM feed WHERE user_id=#{user_id} ORDER BY submit_time")
    List<Feed> selectFeedByUserid(@Param("user_id")String user_id);

}
