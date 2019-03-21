package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsSeries {
    private EchartsSeriesData[] data;
    private String name;
    private String type;
    private EchartsSeriesEffect effect;
    private Integer zlevel;
    private EchartsLineStyle lineStyle;
    private Integer progressive;
    private String coordinateSystem;
    private Integer progressiveThreshold;
    private Double symbolSize;
    private Boolean silent;
    private Boolean polyline;

    public Boolean getSilent() {
        return silent;
    }

    public void setSilent(Boolean silent) {
        this.silent = silent;
    }

    public Boolean getPolyline() {
        return polyline;
    }

    public void setPolyline(Boolean polyline) {
        this.polyline = polyline;
    }

    public EchartsSeriesData[] getData() {
        return data;
    }

    public void setData(EchartsSeriesData[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EchartsSeriesEffect getEffect() {
        return effect;
    }

    public void setEffect(EchartsSeriesEffect effect) {
        this.effect = effect;
    }

    public Integer getZlevel() {
        return zlevel;
    }

    public void setZlevel(Integer zlevel) {
        this.zlevel = zlevel;
    }

    public EchartsLineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(EchartsLineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public Integer getProgressive() {
        return progressive;
    }

    public void setProgressive(Integer progressive) {
        this.progressive = progressive;
    }

    public String getCoordinateSystem() {
        return coordinateSystem;
    }

    public void setCoordinateSystem(String coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public Integer getProgressiveThreshold() {
        return progressiveThreshold;
    }

    public void setProgressiveThreshold(Integer progressiveThreshold) {
        this.progressiveThreshold = progressiveThreshold;
    }

    public Double getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(Double symbolSize) {
        this.symbolSize = symbolSize;
    }
}
