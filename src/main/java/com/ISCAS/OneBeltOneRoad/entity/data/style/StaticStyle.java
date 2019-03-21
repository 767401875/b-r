package com.ISCAS.OneBeltOneRoad.entity.data.style;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StaticStyle {
    private String type;
    private String caption;
    private StyleDef Static;

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

    public StyleDef getStatic() {
        return Static;
    }

    public void setStatic(StyleDef aStatic) {
        Static = aStatic;
    }
}
