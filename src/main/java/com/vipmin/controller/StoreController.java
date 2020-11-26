package com.vipmin.controller;

import com.vipmin.entity.TbConsumerLogEntity;
import com.vipmin.entity.TbStoreEntity;
import com.vipmin.entity.TbStoreVipEntity;
import com.vipmin.entity.TbUserCardsEntity;
import com.vipmin.entity.dto.DtoApprove;
import com.vipmin.entity.dto.DtoApproveConsumer;
import com.vipmin.entity.dto.DtoLogin;
import com.vipmin.entity.dto.DtoLookUsers;
import com.vipmin.entity.vo.VoCardApplication;
import com.vipmin.entity.vo.VoConsumer;
import com.vipmin.entity.vo.VoLookUsers;
import com.vipmin.resp.ResponseUtil;
import com.vipmin.service.TbConsumerLogService;
import com.vipmin.service.TbStoreService;
import com.vipmin.service.TbStoreVipService;
import com.vipmin.service.TbUserCardsService;
import com.vipmin.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "店家的操作")
@RestController
public class StoreController {

    @Autowired
    private TbStoreService tbStoreService;
    @Autowired
    private TbStoreVipService tbStoreVipService;
    @Autowired
    private TbUserCardsService tbUserCardsService;
    @Autowired
    private TbConsumerLogService tbConsumerLogService;

    //申请入驻
    @ApiOperation("商家申请入驻")
    @PostMapping("/api/store/registered")
    public ResponseUtil registered(@RequestBody TbStoreEntity tbStoreEntity){
        tbStoreEntity.setShopIsEffective(0);//默认没有通过
        TbStoreEntity storeEntity = tbStoreService.registered(tbStoreEntity);
        if (storeEntity.getShopIsEffective().equals(0)){
            return ResponseUtil.ok("申请已提交，请耐心等待").data("success",true);
        }else {
            return ResponseUtil.ok("该账号已经申请并已经通过").data("success",true);
        }
    }

    @ApiOperation("商家登录")
    @PostMapping("/api/store/login")
    public ResponseUtil login(@RequestBody DtoLogin dtoLogin, HttpServletRequest request){
        TbStoreEntity tbStoreEntity = tbStoreService.login(dtoLogin);
        if (null == tbStoreEntity){
            return ResponseUtil.error("登录失败").data("token",null);
        }else {
            if (tbStoreEntity.getShopIsEffective().equals(0)){
                return ResponseUtil.error("正在审批中，请耐心等待").data("token",null);
            }
            String ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip)) {
                ip = request.getRemoteAddr();
                if (StringUtils.isEmpty(ip)) {
                    ip = "127.0.0.1";
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("username",dtoLogin.getPhone());
            map.put("password",dtoLogin.getPassword());
            return ResponseUtil.ok("登录成功，签发7天token").data("token", TokenUtil.encode(tbStoreEntity.getId() + "", map, ip));
        }
    }

    //创建卡卷
    @ApiOperation("创建新卡卷 cardType卡片的类型1次数卡0折扣卡 ")
    @PostMapping("/api/store/createCard")
    public ResponseUtil createCard(@RequestBody TbStoreVipEntity tbStoreVipEntity){
        if (tbStoreVipEntity.getCardType()==0 || tbStoreVipEntity.getCardType()==1){
            tbStoreVipService.save(tbStoreVipEntity);
            return ResponseUtil.ok("创建卡卷成功").data("card",tbStoreVipEntity);
        }
        return ResponseUtil.error("创建卡卷失败，请传入有效的类型").data("card",tbStoreVipEntity);
    }
    //审批办卡
    @ApiOperation("查看用户的申请，传入商家的 id")
    @GetMapping("/api/store/getApplications")
    public ResponseUtil getApplications(int sId,int currentPage,int maxSize){
        List<VoCardApplication> applications = tbUserCardsService.getApplications(sId,currentPage,maxSize);
        return ResponseUtil.ok("获取成功").data("list",applications);
    }

    @ApiOperation("审批用户办卡")
    @PostMapping("/api/store/approveCard")
    public ResponseUtil approve(@RequestBody DtoApprove dtoApprove){
        if (dtoApprove.getCardIsEffective().equals(0) || dtoApprove.getCardIsEffective().equals(1)){
            TbUserCardsEntity byId = tbUserCardsService.getById(dtoApprove.getId());
            byId.setCardIsEffective(dtoApprove.getCardIsEffective());
            Integer cId = byId.getCId();
            TbStoreVipEntity byCID = tbStoreVipService.getById(cId);
            byId.setExpiredTime(
                    new Date(byCID.getCardExpiredTime().getTime() + new Date().getTime())
            );
            byId.setCount(byCID.getCardCount());
            byId.setDiscount(byCID.getCardDiscount());
            boolean b = tbUserCardsService.updateById(byId);
            return ResponseUtil.ok("审批成功").data("success",b);
        }
        return ResponseUtil.error("请传入有效的 审批值").data("success",false);
    }

    //查看自己店家的用户
    @ApiOperation("查看办了某张卡的本店客户")
    @PostMapping("/api/store/lookUsers")
    public ResponseUtil lookUsers(@RequestBody DtoLookUsers dtoLookUsers){
        List<VoLookUsers> list = tbUserCardsService.lookUsers(dtoLookUsers.getCId(),dtoLookUsers.getSId());
        return ResponseUtil.ok("获取成功").data("list",list);
    }

    @ApiOperation("用户在本店的消费查询")
    @GetMapping("/api/store/log")
    public ResponseUtil log(Integer sId,Integer currentPage,Integer maxSize){
        List<VoConsumer> list = tbConsumerLogService.getLog(sId,currentPage,maxSize);
        return ResponseUtil.ok("获取成功").data("list",list);
    }
    @ApiOperation("审核消费")
    @PostMapping("/api/store/approveConsumer")
    public ResponseUtil approveConsumer(@RequestBody DtoApproveConsumer dtoApproveConsumer){
        TbConsumerLogEntity byId = tbConsumerLogService.getById(dtoApproveConsumer.getLId());
        if (null != byId){
            byId.setIsApprove(dtoApproveConsumer.getIsApprove());
            tbConsumerLogService.updateById(byId);
            return ResponseUtil.ok("获取成功").data("success",true);
        }
        return ResponseUtil.error("无记录").data("success",false);
    }

    //导入 excel

}
