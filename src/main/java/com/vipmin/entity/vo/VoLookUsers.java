package com.vipmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoLookUsers {
    private Integer id;
    private Integer uId;
    private Integer sId;
    private Integer cId;
    private Integer cardIsEffective;
    private String name;
    private String phone;
    private String cardName;
    private Integer cardCount;
    private Float cardDiscount;
    private Date cardExpiredTime;
    private Date expiredTime;
    private Integer cardType;
    private Float price;
    private Integer count;
    private Float discount;
}
