package com.vipmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoUserConsumer {
    private Integer id;
    private Integer uId;
    private Integer cId;
    private Integer sId;
    private Integer isApprove;
    private Date createTime;
    private Date updateTime;
    private Float beforeMoney;
    private Float afterMoney;
    private String name;
    private String phone;
    private String location;
    private String cardName;
    private String cardType;
}
