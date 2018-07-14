package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.Topic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicMapper {

    @Select("SELECT * FROM topic WHERE user_id = #{user_id}")
    public List<Topic> selectTopicByUserid(@Param("user_id")int user_id);

    @Insert("insert into Topic set topic_name=#{topic_name},description = #{description},user_id = #{user_id},submit_time = unix_timestamp(now())")
    int AddTopic(@Param("topic_name") String topic_name, @Param("description") String description, @Param("user_id") int user_id);

    @Select("select * from topic order by submit_time DESC limit #{limits}")
    List<Topic> findTopicOrderBySubmitTime(int limits);
}
