package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.PicCollect;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicCollectMapper {

    @Select("SELECT * FROM pic_collect WHERE user_id = #{user_id} ORDER BY submit_time")
    public List<PicCollect> selectPicCollectByUserid(@Param("user_id")int user_id);
}
