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
@TableName("tb_consumer_log")
public class TbConsumerLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "用户的 id")
	private Integer uId;
	@ApiModelProperty(value = "使用卡片的id")
	private Integer cId;
	@ApiModelProperty(value = "商家id")
	private Integer sId;
	@ApiModelProperty("是否已审核")
	private Integer isApprove;
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "最后一次更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	@ApiModelProperty(value = "优惠前金额")
	private Float beforeMoney;
	@ApiModelProperty(value = "优惠后金额")
	private Float afterMoney;
}