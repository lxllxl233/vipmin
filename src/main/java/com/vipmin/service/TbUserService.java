package com.vipmin.service;

import com.vipmin.entity.TbUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vipmin.entity.dto.DtoLogin;
import com.vipmin.mapper.TbUserMapper;

public interface TbUserService extends IService<TbUserEntity> {

    TbUserEntity isHaveUser(String phone);

    TbUserEntity login(DtoLogin dtoLogin);
}