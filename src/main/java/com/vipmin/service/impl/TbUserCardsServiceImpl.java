package com.vipmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vipmin.entity.TbUserCardsEntity;
import com.vipmin.entity.vo.VoCardApplication;
import com.vipmin.entity.vo.VoCardInfo;
import com.vipmin.entity.vo.VoLookUsers;
import com.vipmin.mapper.TbUserCardsMapper;
import com.vipmin.service.TbUserCardsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbUserCardsServiceImpl extends ServiceImpl<TbUserCardsMapper, TbUserCardsEntity> implements TbUserCardsService {
    @Autowired
    private TbUserCardsMapper tbUserCardsMapper;
    @Override
    public List<VoCardInfo> myCards(int uId) {
        List<VoCardInfo> list = tbUserCardsMapper.myCards(uId);
        return list;
    }

    @Override
    public List<VoCardApplication> getApplications(int sId, int currentPage, int maxSize) {
        currentPage -= 1;
        List<VoCardApplication> list = tbUserCardsMapper.getApplications(sId,currentPage,maxSize);
        return list;
    }

    @Override
    public List<VoLookUsers> lookUsers(Integer cId, Integer sId) {
        List<VoLookUsers> list = tbUserCardsMapper.lookUsers(cId,sId);
        return list;
    }

    @Override
    public TbUserCardsEntity getCard(Integer cId, Integer sId, Integer uId) {
        QueryWrapper<TbUserCardsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("c_id",cId);
        wrapper.eq("s_id",sId);
        wrapper.eq("u_id",uId);
        TbUserCardsEntity cardsEntity = tbUserCardsMapper.selectOne(wrapper); 
        return cardsEntity;
    }
}