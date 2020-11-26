package com.vipmin.mapper;

import com.vipmin.entity.TbUserCardsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vipmin.entity.vo.VoCardApplication;
import com.vipmin.entity.vo.VoCardInfo;
import com.vipmin.entity.vo.VoLookUsers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbUserCardsMapper extends BaseMapper<TbUserCardsEntity> {

    List<VoCardInfo> myCards(int uId);

    List<VoCardApplication> getApplications(int sId, int currentPage, int maxSize);

    List<VoLookUsers> lookUsers(Integer cId, Integer sId);
}