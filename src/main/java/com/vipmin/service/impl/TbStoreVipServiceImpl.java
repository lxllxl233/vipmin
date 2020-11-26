package com.vipmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vipmin.entity.TbStoreVipEntity;
import com.vipmin.mapper.TbStoreMapper;
import com.vipmin.mapper.TbStoreVipMapper;
import com.vipmin.service.TbStoreVipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbStoreVipServiceImpl extends ServiceImpl<TbStoreVipMapper, TbStoreVipEntity> implements TbStoreVipService {

    @Autowired
    private TbStoreVipMapper tbStoreVipMapper;

    @Override
    public List<TbStoreVipEntity> getMycards(int sid) {
        QueryWrapper<TbStoreVipEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("s_id",sid);
        List<TbStoreVipEntity> tbStoreVipEntities = tbStoreVipMapper.selectList(wrapper);
        return tbStoreVipEntities;
    }
}