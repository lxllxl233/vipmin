package com.vipmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vipmin.entity.TbStoreEntity;
import com.vipmin.entity.TbUserEntity;
import com.vipmin.entity.dto.DtoLogin;
import com.vipmin.mapper.TbStoreMapper;
import com.vipmin.service.TbStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbStoreServiceImpl extends ServiceImpl<TbStoreMapper, TbStoreEntity> implements TbStoreService {

    @Autowired
    private TbStoreMapper tbStoreMapper;

    @Override
    public TbStoreEntity registered(TbStoreEntity tbStoreEntity) {
        QueryWrapper<TbStoreEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",tbStoreEntity.getPhone());
        TbStoreEntity storeEntity = tbStoreMapper.selectOne(wrapper);
        if (null != storeEntity) {
            //已经注册过
            return storeEntity;
        }else {
            tbStoreEntity.setPassword(DigestUtils.md5DigestAsHex(tbStoreEntity.getPassword().getBytes()));
            tbStoreMapper.insert(tbStoreEntity);
            return tbStoreEntity;
        }
    }

    @Override
    public TbStoreEntity login(DtoLogin dtoLogin) {
        QueryWrapper<TbStoreEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",dtoLogin.getPhone());
        wrapper.eq("password", DigestUtils.md5DigestAsHex(dtoLogin.getPassword().getBytes()));
        TbStoreEntity tbStoreEntity = tbStoreMapper.selectOne(wrapper);
        return tbStoreEntity;
    }
}