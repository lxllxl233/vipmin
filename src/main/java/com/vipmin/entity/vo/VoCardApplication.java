package com.vipmin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoCardApplication {
    private Integer id;
    private Integer uId;
    private Integer sId;
    private Integer cId;
    private String cardName;
    private Float price;
    private Float cardDiscount;
    private Integer cardCount;
    private Integer cardType;
    private Date cardExpiredTime;
    private String name;
    private String phone;
    private Integer count;
    private Float discount;
}
