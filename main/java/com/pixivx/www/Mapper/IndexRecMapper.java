package com.pixivx.www.Mapper;

import com.pixivx.www.Entity.IndexRec;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexRecMapper {
    @Select("SELECT * FROM index_rec")
    List<IndexRec> findAll();

    @Select("SELECT * FROM index_rec WHERE index_rec_id=#{index_rec_id} ")
    IndexRec findById(int index_rec_id);
}
