package com.vipmin.service;

import com.vipmin.entity.TbConsumerLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vipmin.entity.vo.VoConsumer;
import com.vipmin.entity.vo.VoUserConsumer;

import java.util.List;

public interface TbConsumerLogService extends IService<TbConsumerLogEntity> {

    List<VoConsumer> getLog(Integer sId, Integer currentPage, Integer maxSize);

    List<VoUserConsumer> getUserLog(Integer uId);
}