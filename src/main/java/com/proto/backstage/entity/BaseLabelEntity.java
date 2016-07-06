package com.proto.backstage.entity;

/**
 * Created by yitao on 2016/5/11.
 */
public class BaseLabelEntity extends BaseDataEntity{
    String icon;
    String label;
    String value;
    String hint;
    String desc;

    public BaseLabelEntity() {
    }

    public BaseLabelEntity(String id,Long order,String icon, String label, String value, String hint, String desc) {
        this.id = id;
        this.order = order;
        this.icon = icon;
        this.label = label;
        this.value = value;
        this.hint = hint;
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
