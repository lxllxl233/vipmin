package com.vipmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoCardInfo {
    private Integer id;//卡的存储id
    private Integer cId;
    private Integer uId;
    private Integer sId;
    private Integer cardIsEffective;
    private String name;
    private String cardName;
    private Integer cardCount;
    private Integer cardType;
    private Date expiredTime;
    private Float price;
    private Float cardDiscount;
    private Integer count;
    private Float discount;
}
