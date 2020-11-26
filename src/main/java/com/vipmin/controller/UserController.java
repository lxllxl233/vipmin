package com.vipmin.controller;

import com.vipmin.entity.TbConsumerLogEntity;
import com.vipmin.entity.TbUserCardsEntity;
import com.vipmin.entity.TbUserEntity;
import com.vipmin.entity.dto.DtoApplicationCard;
import com.vipmin.entity.dto.DtoConsumption;
import com.vipmin.entity.dto.DtoLogin;
import com.vipmin.entity.vo.VoCardInfo;
import com.vipmin.entity.vo.VoUserConsumer;
import com.vipmin.resp.ResponseUtil;
import com.vipmin.service.TbConsumerLogService;
import com.vipmin.service.TbUserCardsService;
import com.vipmin.service.TbUserService;
import com.vipmin.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户操作")
@RestController
public class UserController {

    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private TbUserCardsService tbUserCardsService;
    @Autowired
    private TbConsumerLogService tbConsumerLogService;

    //注册平台，修改信息
    @ApiOperation("用户注册平台，只需要填写姓名，电话，头像url，密码")
    @PostMapping("/api/user/registered")
    public ResponseUtil registered(@RequestBody TbUserEntity tbUserEntity){
        //判断平台是否有该用户
        TbUserEntity user = tbUserService.isHaveUser(tbUserEntity.getPhone());
        if (null == user || user.getPassword()==null || user.getPassword().isEmpty()){
            //用户密码转md5
            tbUserEntity.setPassword(DigestUtils.md5DigestAsHex(tbUserEntity.getPassword().getBytes()));
            if (null == user){
                //新用户
                tbUserService.save(tbUserEntity);
                return ResponseUtil.ok("注册成功").data("user",tbUserEntity);
            }else {
                //商家导入用户
                Integer id = user.getId();
                BeanUtils.copyProperties(tbUserEntity,user);
                user.setId(id);
                tbUserService.updateById(user);
                return ResponseUtil.ok("注册成功").data("user",user);
            }
        }else {
            user.setPassword("*");
            return ResponseUtil.ok("该号码已经被注册，请登录").data("user",user);
        }
    }

    //用户登录
    @ApiOperation("用户登录")
    @PostMapping("/api/user/userLogin")
    public ResponseUtil userLogin(@RequestBody DtoLogin dtoLogin, HttpServletRequest request){
        TbUserEntity tbUserEntity = tbUserService.login(dtoLogin);
        if (null == tbUserEntity){
            return ResponseUtil.error("登录失败").data("token",null);
        }else {
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
            return ResponseUtil.ok("登录成功，签发7天token").data("token", TokenUtil.encode(tbUserEntity.getId() + "", map, ip));
        }
    }
    //申请办卡
    @ApiOperation("用户申请办卡")
    @PostMapping("/api/user/applicationCard")
    public ResponseUtil applicationCard(@RequestBody DtoApplicationCard dtoApplicationCard) {
        TbUserCardsEntity card = tbUserCardsService.getCard(dtoApplicationCard.getCId(), dtoApplicationCard.getSId(), dtoApplicationCard.getUId());
        TbUserCardsEntity tbUserCardsEntity = new TbUserCardsEntity();
        tbUserCardsEntity.setCardIsEffective(0);
        BeanUtils.copyProperties(dtoApplicationCard, tbUserCardsEntity);
        tbUserCardsService.save(tbUserCardsEntity);
        return ResponseUtil.ok("申请成功").data("success", true);
    }
    //@ApiOperation("")
    //查看自己的卡卷
    @ApiOperation("查看自己的卡卷")
    @GetMapping("/api/user/myCards")
    public ResponseUtil myCards(int uId){
        List<VoCardInfo> list = tbUserCardsService.myCards(uId);
        return ResponseUtil.ok("查询成功").data("cards",list);
    }

    @ApiOperation("用户查看自己的消费记录")
    @GetMapping("/api/user/getLog")
    public ResponseUtil getLog(Integer uId){
        List<VoUserConsumer> list = tbConsumerLogService.getUserLog(uId);
        return ResponseUtil.ok("查询成功").data("log",list);
    }

    @ApiOperation("用户扫码消费")
    @PostMapping("/api/user/consumption")
    public ResponseUtil consumption(@RequestBody DtoConsumption dtoConsumption){
        TbConsumerLogEntity tbConsumerLogEntity = new TbConsumerLogEntity();
        BeanUtils.copyProperties(dtoConsumption,tbConsumerLogEntity);
        tbConsumerLogEntity.setIsApprove(0);//未审核
        if (dtoConsumption.getCount() > 0 && dtoConsumption.getCardType().equals(1)){
            //如果是次数卡，且参数正确，先拿到卡卷检查
            TbUserCardsEntity tbUserCardsEntity =
                    tbUserCardsService.getById(dtoConsumption.getUCardId());
            if (tbUserCardsEntity.getCount() - dtoConsumption.getCount() >= 0){
                tbUserCardsEntity.setCount(tbUserCardsEntity.getCount() - dtoConsumption.getCount());
                tbUserCardsService.updateById(tbUserCardsEntity);//更新表
                //添加一条消费记录
                tbConsumerLogEntity.setBeforeMoney(dtoConsumption.getMoney());
                tbConsumerLogEntity.setAfterMoney(0f);
                tbConsumerLogService.save(tbConsumerLogEntity);
                return ResponseUtil.ok("扣除次数成功，应付金额:0").data("money",0);
            }else {
                BigDecimal multiply = new BigDecimal(dtoConsumption.getMoney())
                        .divide(new BigDecimal(dtoConsumption.getCount()))
                        .multiply(new BigDecimal(dtoConsumption.getCount() - tbUserCardsEntity.getCount()));
                tbUserCardsEntity.setCount(0);
                tbUserCardsService.updateById(tbUserCardsEntity);//更新表
                //添加一条消费记录
                tbConsumerLogEntity.setBeforeMoney(dtoConsumption.getMoney());
                tbConsumerLogEntity.setAfterMoney(multiply.floatValue());
                tbConsumerLogService.save(tbConsumerLogEntity);
                return ResponseUtil.ok("扣除次数成功，应付金额:"+multiply.floatValue()).data("money", multiply.floatValue());
            }
        } else if (dtoConsumption.getCardType().equals(1)){
            //如果是次数卡参数错误
            return ResponseUtil.error("次数卡次数有误").data("money",dtoConsumption.getMoney());
        } else if (dtoConsumption.getCardType().equals(0)){
            TbUserCardsEntity tbUserCardsEntity =
                    tbUserCardsService.getById(dtoConsumption.getUCardId());
            //如果是折扣卡
            //判断是否过期
            if (tbUserCardsEntity.getExpiredTime().getTime() >= new Date().getTime()) {
                BigDecimal multiply = new BigDecimal(dtoConsumption.getMoney()).multiply(new BigDecimal(tbUserCardsEntity.getDiscount()));
                //添加一条消费记录
                tbConsumerLogEntity.setBeforeMoney(dtoConsumption.getMoney());
                tbConsumerLogEntity.setAfterMoney(multiply.floatValue());
                tbConsumerLogService.save(tbConsumerLogEntity);
                return ResponseUtil.ok("扣除次数成功，应付金额:" + multiply.floatValue()).data("money", multiply.floatValue());
            }else {
                return ResponseUtil.ok("扣除次数成功，应付金额:" + dtoConsumption.getMoney()).data("money", dtoConsumption.getMoney());
            }
        } else {
            //其他情况
            return ResponseUtil.error("其他情况，参数有误").data("money",dtoConsumption.getMoney());
        }
    }
}
