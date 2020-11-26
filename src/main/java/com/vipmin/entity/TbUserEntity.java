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
@TableName("tb_user")
public class TbUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	@ApiModelProperty(value = "")
	private String name;
	@ApiModelProperty(value = "")
	private String phone;
	@ApiModelProperty(value = "用户头像")
	private String avatar;
	@ApiModelProperty(value = "password")
	private String password;
	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@ApiModelProperty(value = "最后一次更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}