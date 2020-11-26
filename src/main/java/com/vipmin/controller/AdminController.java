package com.vipmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vipmin.entity.TbStoreEntity;
import com.vipmin.entity.dto.DtoPass;
import com.vipmin.resp.ResponseUtil;
import com.vipmin.service.TbStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员操作")
@RestController
public class AdminController {

    @Autowired
    private TbStoreService tbStoreService;

    //查看所有店家
    @ApiOperation("查看所有的店家，分页 isEffective:-1查看全部,1未审核,0已审核")
    @GetMapping("/api/admin/getAllStore")
    public ResponseUtil getAllStore(int isEffective,int currentPage,int maxSize){
        IPage<TbStoreEntity> list = null;
        try{
            Page<TbStoreEntity> page = new Page<>(currentPage,maxSize);
            if (isEffective == -1){
                list = tbStoreService.page(page, null);
            }else{
                QueryWrapper<TbStoreEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("shop_is_effective",isEffective);
                list = tbStoreService.page(page, wrapper);
            }
            return ResponseUtil.ok("查询成功").data("list",list);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.error("查询失败").data("list",null);
        }
    }

    //审批店家
    @ApiOperation("审批， 0不通过 1通过")
    @PostMapping("/api/admin/isPass")
    public ResponseUtil isPass(@RequestBody DtoPass dtoPass){
        TbStoreEntity byId = tbStoreService.getById(dtoPass.getSId());
        if (null != byId){
            if (1 == dtoPass.getIsPass() || 0 == dtoPass.getIsPass()){
                byId.setShopIsEffective(dtoPass.getIsPass());
                tbStoreService.updateById(byId);
                return ResponseUtil.ok("审批成功").data("success",true);
            }else {
                return ResponseUtil.error("审批失败，请传入有效的 isPass值").data("success",false);
            }
        }else {
            return ResponseUtil.error("审批失败，请传入有效的 sId").data("success",false);
        }
    }


}
