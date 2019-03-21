package com.ISCAS.OneBeltOneRoad.entity.data.chart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationDataChartOptions {
    private AnnotationDataChartOptionsX xAxis;
    private AnnotationDataChartOptionsY yAxis;
    private AnnotationDataChartOptionsSeries[] series;

    public AnnotationDataChartOptionsX getxAxis() {
        return xAxis;
    }

    public void setxAxis(AnnotationDataChartOptionsX xAxis) {
        this.xAxis = xAxis;
    }

    public AnnotationDataChartOptionsY getyAxis() {
        return yAxis;
    }

    public void setyAxis(AnnotationDataChartOptionsY yAxis) {
        this.yAxis = yAxis;
    }

    public AnnotationDataChartOptionsSeries[] getSeries() {
        return series;
    }

    public void setSeries(AnnotationDataChartOptionsSeries[] series) {
        this.series = series;
    }
}
