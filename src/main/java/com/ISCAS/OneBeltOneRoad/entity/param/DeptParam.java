package com.ISCAS.OneBeltOneRoad.entity.param;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class DeptParam {
    private Integer id;
    @NotBlank(message = "部门名称不能为空")
    @Length(min = 2, max = 15, message = "部门名称长度需要在2-15个字之间")
    private String name;
    private Integer parentId = 0;

    @NotNull(message = "展现顺序不可以为空")
    private Integer seq;
    @Length(max = 150, message = "备注长度需要在150个以内")
    private String remark;

    public DeptParam(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
