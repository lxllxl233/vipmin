package com.vipmin.service;

import com.vipmin.entity.TbUserCardsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vipmin.entity.vo.VoCardApplication;
import com.vipmin.entity.vo.VoCardInfo;
import com.vipmin.entity.vo.VoLookUsers;

import java.util.List;

public interface TbUserCardsService extends IService<TbUserCardsEntity> {

    List<VoCardInfo> myCards(int uId);

    List<VoCardApplication> getApplications(int sId, int currentPage, int maxSize);

    List<VoLookUsers> lookUsers(Integer cId, Integer sId);

    TbUserCardsEntity getCard(Integer cId, Integer sId, Integer uId);
}