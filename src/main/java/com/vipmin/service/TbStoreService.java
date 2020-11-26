package com.vipmin.service;

import com.vipmin.entity.TbStoreEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vipmin.entity.dto.DtoLogin;

public interface TbStoreService extends IService<TbStoreEntity> {

    TbStoreEntity registered(TbStoreEntity tbStoreEntity);

    TbStoreEntity login(DtoLogin dtoLogin);
}