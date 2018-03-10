package com.cbrothercoder.demo.common.dao;


import com.cbrothercoder.demo.common.bussiness.enums.BoolEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
public abstract class CommonPo implements Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    public abstract Date getModifyTime();

    public abstract void setModifyTime(Date modifyTime);

    public abstract Boolean getIsDeleted();

    public abstract void setIsDeleted(Boolean deleted);

    public abstract Long getLogno();

    public abstract void setLogno(Long logno);

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + getId() +
                ", createTime=" + getCreateTime() +
                ", modifyTime=" + getModifyTime() +
                ", isDeleted=" + getIsDeleted() +
                ", logno=" + getLogno() +
                '}';
    }

    public Date createCommon(Long id) {
        return createCommon(id, null, null);
    }

    public Date createCommon(Long id, Date date) {
        return createCommon(id, date, null);
    }

    public Date createCommon(Long id, Long logno) {
        return createCommon(id, null, logno);
    }

    public Date createCommon(Long id, Date date, Long logno) {
        if (date == null) date = new Date();
        this.setId(id);
        this.setCreateTime(date);
        this.setIsDeleted(BoolEnum.F);
        this.setLogno(logno);
        return date;
    }

    public void modifyCommon(CommonPo oldPo) {
        modifyCommon(oldPo, null, null);
    }

    public void modifyCommon(CommonPo oldPo, Date date) {
        modifyCommon(oldPo, date, null);
    }

    public void modifyCommon(CommonPo oldPo, Long logno) {
        modifyCommon(oldPo, null, logno);
    }

    public void modifyCommon(CommonPo oldPo, Date date, Long logno) {
        if (date == null) date = new Date();

        this.setId(oldPo.getId());
        this.setModifyTime(date);
        this.setLogno(logno);
    }
}
