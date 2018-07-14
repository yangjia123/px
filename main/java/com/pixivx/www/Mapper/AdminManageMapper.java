package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.OfficalMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminManageMapper {
    @Insert("INSERT INTO offical_message(offical_message_id,title,description,pic_url,submit_time) VALUES(#{officalMessage.offical_message_id},#{officalMessage.title},#{officalMessage.description},#{officalMessage.pic_url},#{officalMessage.submit_time})")
    void insertOfficalMessage(@Param("officalMessage") OfficalMessage officalMessage);
}
