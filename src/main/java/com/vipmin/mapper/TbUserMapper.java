package com.vipmin.mapper;

import com.vipmin.entity.TbUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbUserMapper extends BaseMapper<TbUserEntity> {

}