package com.pixivx.www.Service;

import com.pixivx.www.Entity.PicGroup;
import com.pixivx.www.Mapper.PicGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicGroupService {
    @Autowired
    public PicGroupMapper picGroupMapper;

    public List<PicGroup> findPicGroupAll(){
        return  picGroupMapper.findAll();
    }

    public PicGroup findPicGroupById(int pic_group_id){
        return picGroupMapper.findById(pic_group_id);
    }

    /**
     * @param label
     * @param condition
     * @param limits
     * @return
     */
    public List<PicGroup> findOrder(int label, String condition, int limits) {
        if (label == 0) {
            switch (condition) {
                case "submit_time":
                    return picGroupMapper.findOrderBySubmitTime(limits);
                case "click_times":
                    return picGroupMapper.findOrderByClickTimes(limits);
                default:
                    return null;
            }
        } else {
            switch (label) {
                case 1:
                    return picGroupMapper.findByPicLabelOrderByClickTimes("0");
                case 2:
                    return picGroupMapper.findByPicLabelOrderByClickTimes("1");

                case 3:
                    return picGroupMapper.findByGenderOrderByClickTimes("男");
                case 4:
                    return picGroupMapper.findByGenderOrderByClickTimes("女");
            }
        }
        return null;
    }

    public List<PicGroup> findRadom(int limits) {
        return picGroupMapper.findRadom(limits);
    }

    /**
     * yangjia
     * 根据用户id获得作品组
     */
    public List<PicGroup> selectPicGroupByUserid(String user_id){
        return picGroupMapper.selectPicGroupByUserid(user_id);
    }

    /**
     * yangjia
     * 根据用户id获得插画作品
     */
    public List<PicGroup> selectIconPicGroupByUserid(String user_id){
        return picGroupMapper.selectIconPicGroupByUserid(user_id);
    }

    /**
     * yangjia
     * 根据用户id获得动图作品
     */
    public List<PicGroup> selectGifPicGroupByUserid(String user_id){
        return picGroupMapper.selectGifPicGroupByUserid(user_id);
    }
}
