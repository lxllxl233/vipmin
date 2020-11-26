package com.vipmin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@TableName("tb_user_cards")
public class TbUserCardsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "用户的 id")
	private Integer uId;
	@ApiModelProperty(value = "店家的 id")
	private Integer sId;
	@ApiModelProperty(value = "卡卷的 id")
	private Integer cId;
	@ApiModelProperty(value = "卡片是否有效 1有效,0无效")
	private Integer cardIsEffective;
	@ApiModelProperty(value = "卡卷的有效时间")
	private Date expiredTime;
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty("用户账户剩余次数")
	private Integer count;
	@ApiModelProperty("用户折扣")
	private Float discount;
	@ApiModelProperty(value = "最后一次更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}