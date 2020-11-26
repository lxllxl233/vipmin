package com.vipmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoConsumption {
    private Integer uCardId;
    private Integer cId;
    private Integer uId;
    private Integer sId;
    private Integer cardType;
    private Integer count;
    private Float money;
}
