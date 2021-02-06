package com.yohann.dict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author Yohann
 * @since 2021-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DataDict对象", description = "数据字典")
public class DataDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 UUID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id 一级字典父id为0")
    private Long parentId;

    @ApiModelProperty(value = "字典名")
    private String name;

    @ApiModelProperty(value = "字典码")
    private String code;

    @ApiModelProperty(value = "字典键")
    private String dictKey;

    @ApiModelProperty(value = "字典值")
    private String dictValue;

    @ApiModelProperty(value = "字典类型  0:键值对映射(默认)  1:数组集合")
    private Boolean dictType;

    @ApiModelProperty(value = "字典排序")
    private Integer sort;

    @ApiModelProperty(value = "启用状态 0:禁用 1:启用")
    private Boolean status;

    @ApiModelProperty(value = "字典描述")
    private String description;
}
