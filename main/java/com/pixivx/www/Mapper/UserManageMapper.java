package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
public interface UserManageMapper {

    @Select("SELECT * FROM user WHERE phone=#{phone} AND password=#{password}")
    User selectUserByPhone(@Param("phone") String phone, @Param("password") String password);

    @Select("SELECT * FROM user WHERE phone=#{phone}")
    User selectPhone(@Param("phone") String phone);

    @Select("SELECT * FROM user WHERE nickname=#{nickname}")
    User selectNickname(@Param("nickname") String nickname);

    @Insert("INSERT INTO user(nickname,phone,password,gender,birthday,register_time) VALUES(#{user.nickname},#{user.phone},#{user.password},#{user.gender},#{user.birthday},#{user.register_time})")
    void insertUser(@Param("user") User user);

    @Update("UPDATE user SET password=#{password} WHERE phone=#{phone}")
    void modifyUserPasswordByPhone(@Param("phone")String phone,@Param("password")String password);

    @Select("SELECT * FROM user WHERE user_id=#{user_id}")
    User findUserById(int user_id);

    @Select("SELECT * FROM user WHERE gender=#{gender}")
    User findUserByGender(String gender);

    @Update("UPDATE user SET head_pic=#{head_pic_url} WHERE nickname=#{nickname}")
    void updateUserHeadpic(@Param("nickname") String nickname,@Param("head_pic_url") String head_pic_url);

}
