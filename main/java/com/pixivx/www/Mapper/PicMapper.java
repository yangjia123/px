package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.Pic;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicMapper {
    @Select("SELECT * FROM pic WHERE pic_id = #{pic_id}")
    Pic findPicById(int pic_id);

    @Select("SELECT * FROM pic WHERE pic_group_id = #{pic_group_id}")
    List<Pic> findPicAllByPicGroupId(int pic_group_id);

}
