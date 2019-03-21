package com.ISCAS.OneBeltOneRoad.entity.data.style;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class CurTimeStyle {
    private String type;
    private String caption;
    private StyleDef Default;
    private StyleDef curTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public StyleDef getDefault() {
        return Default;
    }

    public void setDefault(StyleDef aDefault) {
        Default = aDefault;
    }

    public StyleDef getCurTime() {
        return curTime;
    }

    public void setCurTime(StyleDef curTime) {
        this.curTime = curTime;
    }
}
