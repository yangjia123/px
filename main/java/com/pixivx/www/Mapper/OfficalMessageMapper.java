package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.OfficalMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficalMessageMapper {
    @Select("SELECT * FROM offical_message")
    List<OfficalMessage> findAll();

    @Select("SELECT * FROM offical_message WHERE offical_message_id = #{offical_message_id}")
    OfficalMessage getOfficalMessageById(@Param("offical_message_id")String offical_message_id);
}
