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
@TableName("tb_store")
public class TbStoreEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "")
	private String name;
	@ApiModelProperty(value = "")
	private String phone;
	@ApiModelProperty(value = "")
	private String password;
	@ApiModelProperty(value = "店家头像")
	private String avatar;
	@ApiModelProperty(value = "店家位置")
	private String location;
	@ApiModelProperty(value = "店家简介")
	private String introduction;
	@ApiModelProperty(value = "是否有效 0无效，１有效")
	private Integer shopIsEffective;
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "最后一次更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}