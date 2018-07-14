package com.pixivx.www.Service;

import com.pixivx.www.Entity.User;
import com.pixivx.www.Mapper.UserManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManageService {
    @Autowired
    public UserManageMapper userManageMapper;

    public User selectUserByPhone(String phone,String password)
    {
        return userManageMapper.selectUserByPhone(phone,password);
    }

    public void insertUser(User user)
    {
        userManageMapper.insertUser(user);
    }

    public void updateUserPassword(String phone,String password){
        userManageMapper.modifyUserPasswordByPhone(phone,password);
    }

    public User selectPhone(String phone){
        return userManageMapper.selectPhone(phone);
    }

    public User selectNickname(String nickname){
        return userManageMapper.selectNickname(nickname);
    }

    public User findUserById(int user_id){
        return userManageMapper.findUserById(user_id);
    }

    public void updateUserHeadpic(String nickname,String head_pic_url){
        userManageMapper.updateUserHeadpic(nickname,head_pic_url);
    }
}
