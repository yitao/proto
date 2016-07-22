package com.backstage.entity;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yitao on 2016/5/11.
 */
public class BaseDataEntity implements Serializable {
    @Id
    String id;
    Date ctime = new Date();
    Date mtime;
    Long order;
    Boolean show;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        } else {
            return this.getId().hashCode();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date lmtime) {
        this.mtime = mtime;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public boolean isNew(){
        return StringUtils.isBlank(this.id);
    }
}
