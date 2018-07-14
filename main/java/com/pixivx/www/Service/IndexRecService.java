package com.pixivx.www.Service;

import com.pixivx.www.Entity.IndexRec;
import com.pixivx.www.Mapper.IndexRecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexRecService {
    @Autowired
    public IndexRecMapper indexRecMapper;

    public List<IndexRec> findAll(){
        return indexRecMapper.findAll();
    }

    public IndexRec findById(int index_rec_id){
        return indexRecMapper.findById(index_rec_id);
    }
}
