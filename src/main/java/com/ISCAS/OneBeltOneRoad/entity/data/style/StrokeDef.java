package com.ISCAS.OneBeltOneRoad.entity.data.style;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StrokeDef {
    private String color;
    private Double opacity;
    private Integer width;
    private Integer zIndex;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getzIndex() {
        return zIndex;
    }

    public void setzIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }
}
