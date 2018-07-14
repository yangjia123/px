package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.PicGroup;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicGroupMapper {
    @Select("SELECT * FROM pic_group WHERE pic_group_id = #{pic_group_id}")
    PicGroup findById(int pic_group_id);

    @Select("SELECT * FROM pic_group")
    List<PicGroup> findAll();

    @Select("SELECT * FROM pic_group ORDER BY submit_time DESC LIMIT #{limits}")
    List<PicGroup> findOrderBySubmitTime( int limits);

    @Select("SELECT * FROM pic_group ORDER BY click_times DESC LIMIT #{limits}")
    List<PicGroup> findOrderByClickTimes( int limits);
    @Select("SELECT * FROM pic_group WHERE pic_label=#{pic_label} ORDER BY click_times DESC LIMIT 3")
    List<PicGroup> findByPicLabelOrderByClickTimes(String pic_label);
    @Select("select * from pic_group where user_id in(select user_id from user where gender = #{gender}) order by click_times DESC limit 3")
    List<PicGroup> findByGenderOrderByClickTimes(String gender);
    @Select("select * from pic_group order by rand() limit #{limits}")
    List<PicGroup> findRadom(int limits);

    @Select("SELECT * FROM pic_group WHERE user_id = #{user_id}")
    List<PicGroup> selectPicGroupByUserid(@Param("user_id")String user_id);

    //获取插画
    @Select("SELECT * FROM pic_group WHERE user_id = #{user_id} AND pic_label= 0")
    List<PicGroup> selectIconPicGroupByUserid(@Param("user_id")String user_id);

    //获取动图
    @Select("SELECT * FROM pic_group WHERE user_id = #{user_id} AND pic_label= 1")
    List<PicGroup> selectGifPicGroupByUserid(@Param("user_id")String user_id);
}
