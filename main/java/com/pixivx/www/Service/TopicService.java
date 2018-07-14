package com.pixivx.www.Service;

import com.pixivx.www.Entity.Topic;
import com.pixivx.www.Mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired private TopicMapper topicMapper;
    public List<Topic> selectTopicByUserid(int user_id){
        return topicMapper.selectTopicByUserid(user_id);
    }

    public boolean AddTopic(String name,String desc,int id){
        int judge = topicMapper.AddTopic(name, desc, id);
        if(judge>0) return true;
        else return false;
    }
    public List<Topic> findTopicOrderBySubmitTime(int limits){
        return topicMapper.findTopicOrderBySubmitTime(limits);
    }
}
