package com.pixivx.www.Service;

import com.pixivx.www.Mapper.UserFollowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFollowerService {
    @Autowired private UserFollowerMapper userFollowerMapper;

    public List<String> getUserFollowerByUserid(int user_id){
        return userFollowerMapper.selectUserFollower(user_id);
    }
}
