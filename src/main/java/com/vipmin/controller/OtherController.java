package com.vipmin.controller;

import com.vipmin.entity.TbStoreEntity;
import com.vipmin.entity.TbStoreVipEntity;
import com.vipmin.resp.ResponseUtil;
import com.vipmin.service.TbStoreService;
import com.vipmin.service.TbStoreVipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "其他的一些操作")
@RestController
public class OtherController {

    @Autowired
    private TbStoreVipService tbStoreVipService;
    @Autowired
    private TbStoreService tbStoreService;

    @ApiOperation("查看某个商家的卡卷")
    @GetMapping("/api/other/myCard")
    public ResponseUtil myCard(int sid){
        List<TbStoreVipEntity> list = tbStoreVipService.getMycards(sid);
        return ResponseUtil.ok("获取成功").data("list",list);
    }

    @ApiOperation("查看平台现有店家")
    @GetMapping("/api/other/stores")
    public ResponseUtil stores(){
        List<TbStoreEntity> list = tbStoreService.list();
        return ResponseUtil.ok("获取成功").data("list",list);
    }
}
