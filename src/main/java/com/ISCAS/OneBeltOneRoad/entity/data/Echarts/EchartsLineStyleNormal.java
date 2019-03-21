package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsLineStyleNormal {
    private String color;
    private Double width;
    private Double opacity;
    private Double curveness;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getOpacity() {
        return opacity;
    }

    public void setOpacity(Double opacity) {
        this.opacity = opacity;
    }

    public Double getCurveness() {
        return curveness;
    }

    public void setCurveness(Double curveness) {
        this.curveness = curveness;
    }
}
