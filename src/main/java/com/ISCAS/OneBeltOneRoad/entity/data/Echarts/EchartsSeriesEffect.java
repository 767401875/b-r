package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsSeriesEffect {
    private boolean show;
    private String color;
    private Double period;
    private String symbol;
    private Double symbolSize;
    private Double trailLength;
    private Double constantSpeed;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPeriod() {
        return period;
    }

    public void setPeriod(Double period) {
        this.period = period;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Double symbolSize) {
        this.symbolSize = symbolSize;
    }

    public Double getTrailLength() {
        return trailLength;
    }

    public void setTrailLength(Double trailLength) {
        this.trailLength = trailLength;
    }

    public Double getConstantSpeed() {
        return constantSpeed;
    }

    public void setConstantSpeed(Double constantSpeed) {
        this.constantSpeed = constantSpeed;
    }
}
