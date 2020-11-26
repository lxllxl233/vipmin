package com.vipmin.mapper;

import com.vipmin.entity.TbAdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbAdminMapper extends BaseMapper<TbAdminEntity> {

}