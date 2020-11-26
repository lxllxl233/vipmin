package com.vipmin.service;

import com.vipmin.entity.TbStoreVipEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TbStoreVipService extends IService<TbStoreVipEntity> {

    List<TbStoreVipEntity> getMycards(int sid);
}