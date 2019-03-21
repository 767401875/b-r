package com.ISCAS.OneBeltOneRoad.entity.data.style;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class StyleDef {
    private FillDef fill;
    private StrokeDef stroke;
    private Integer pointRadius;

    public FillDef getFill() {
        return fill;
    }

    public void setFill(FillDef fill) {
        this.fill = fill;
    }

    public StrokeDef getStroke() {
        return stroke;
    }

    public void setStroke(StrokeDef stroke) {
        this.stroke = stroke;
    }

    public Integer getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(Integer pointRadius) {
        this.pointRadius = pointRadius;
    }
}
