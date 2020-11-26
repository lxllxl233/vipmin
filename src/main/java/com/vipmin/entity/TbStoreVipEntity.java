package com.vipmin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_store_vip")
public class TbStoreVipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "卡片所属店家的id")
	private Integer sId;
	@ApiModelProperty(value = "")
	private String cardName;
	@ApiModelProperty(value = "卡可以使用的次数")
	private Integer cardCount;
	@ApiModelProperty(value = "卡的打折比9.5折这样")
	private Float cardDiscount;
	@ApiModelProperty(value = "卡卷价格")
	private Float price;
	@ApiModelProperty(value = "卡片的有效时间，例如一年，俩年这样")
	private Date cardExpiredTime;
	@ApiModelProperty(value = "卡片的类型 1次数卡,0折扣卡")
	private Integer cardType;
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "最后一次更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}