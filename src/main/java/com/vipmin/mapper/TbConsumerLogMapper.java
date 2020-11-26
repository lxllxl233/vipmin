package com.vipmin.mapper;

import com.vipmin.entity.TbConsumerLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vipmin.entity.vo.VoConsumer;
import com.vipmin.entity.vo.VoUserConsumer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbConsumerLogMapper extends BaseMapper<TbConsumerLogEntity> {

    List<VoConsumer> getLog(Integer sId, Integer currentPage, Integer maxSize);

    List<VoUserConsumer> getUserLog(Integer uId);
}