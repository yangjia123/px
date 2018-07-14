package com.pixivx.www.Service;

import com.pixivx.www.Entity.OfficalMessage;
import com.pixivx.www.Mapper.OfficalMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficalMessageService {
    @Autowired OfficalMessageMapper officalMessageMapper;

    public List<OfficalMessage> findAll(){
        return officalMessageMapper.findAll();
    }

    public OfficalMessage getOfficalMessageById(String offical_message_id){
        return officalMessageMapper.getOfficalMessageById(offical_message_id);
    }

}
