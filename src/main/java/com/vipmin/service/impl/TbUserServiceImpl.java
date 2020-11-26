package com.vipmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vipmin.entity.TbUserEntity;
import com.vipmin.entity.dto.DtoLogin;
import com.vipmin.mapper.TbUserMapper;
import com.vipmin.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUserEntity> implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUserEntity isHaveUser(String phone) {
        QueryWrapper<TbUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        TbUserEntity tbUserEntity = tbUserMapper.selectOne(wrapper);
        return tbUserEntity;
    }

    @Override
    public TbUserEntity login(DtoLogin dtoLogin) {
        QueryWrapper<TbUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",dtoLogin.getPhone());
        wrapper.eq("password", DigestUtils.md5DigestAsHex(dtoLogin.getPassword().getBytes()));
        TbUserEntity tbUserEntity = tbUserMapper.selectOne(wrapper);
        return tbUserEntity;
    }
}