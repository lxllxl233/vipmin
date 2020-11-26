package com.vipmin.service.impl;

import com.vipmin.entity.TbConsumerLogEntity;
import com.vipmin.entity.vo.VoConsumer;
import com.vipmin.entity.vo.VoUserConsumer;
import com.vipmin.mapper.TbConsumerLogMapper;
import com.vipmin.service.TbConsumerLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbConsumerLogServiceImpl extends ServiceImpl<TbConsumerLogMapper, TbConsumerLogEntity> implements TbConsumerLogService {

    @Autowired
    private TbConsumerLogMapper tbConsumerLogMapper;

    @Override
    public List<VoConsumer> getLog(Integer sId, Integer currentPage, Integer maxSize) {
        currentPage-=1;
        List<VoConsumer> list = tbConsumerLogMapper.getLog(sId,currentPage,maxSize);
        return list;
    }

    @Override
    public List<VoUserConsumer> getUserLog(Integer uId) {
        List<VoUserConsumer> list = tbConsumerLogMapper.getUserLog(uId);
        return list;
    }
}