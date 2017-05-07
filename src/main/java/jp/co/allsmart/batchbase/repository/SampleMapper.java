package jp.co.allsmart.batchbase.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import jp.co.allsmart.batchbase.entity.SampleEntity;

@Mapper
public interface SampleMapper {

    @Select("SELECT sample_id,sample_data FROM sample")
    List<SampleEntity> selectAll();

}
