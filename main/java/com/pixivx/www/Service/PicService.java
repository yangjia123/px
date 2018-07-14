package com.pixivx.www.Service;

import com.pixivx.www.Entity.Pic;
import com.pixivx.www.Mapper.PicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicService {
    @Autowired
    public PicMapper picMapper;

    public List<Pic> findPicAllByPicGroupId(int pic_group_id){
        return picMapper.findPicAllByPicGroupId(pic_group_id);
    }

    public Pic findPicById(int pic_id){
        return picMapper.findPicById(pic_id);
    }


}
