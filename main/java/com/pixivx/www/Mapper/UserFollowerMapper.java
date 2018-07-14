package com.pixivx.www.Mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowerMapper {
    @Select("SELECT user_id_2 FROM user_follower WHERE user_id_1 = #{user_id}")
    public List<String> selectUserFollower(@Param("user_id")int user_id);
}
