package com.pixivx.www.Service;

import com.pixivx.www.Entity.PicCollect;
import com.pixivx.www.Mapper.PicCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicCollectServcie {
    @Autowired private PicCollectMapper picCollectMapper;

    public List<PicCollect> selectPicCollectByUserid(int user_id){
        return picCollectMapper.selectPicCollectByUserid(user_id);
    }
}
