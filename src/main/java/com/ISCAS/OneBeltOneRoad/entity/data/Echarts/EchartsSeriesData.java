package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsSeriesData {
    private Double[][]coords;
    private String toName;
    private String fromName;
    private EchartsLineStyle lineStyle;

    public Double[][] getCoords() {
        return coords;
    }

    public void setCoords(Double[][] coords) {
        this.coords = coords;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public EchartsLineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(EchartsLineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }
}
