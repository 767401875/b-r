package com.ISCAS.OneBeltOneRoad.entity.data;

import com.ISCAS.OneBeltOneRoad.entity.data.chart.AnnotationDataChart;
import com.ISCAS.OneBeltOneRoad.entity.data.time.AnnotationTimeData;
import com.ISCAS.OneBeltOneRoad.entity.keypoint.KeyPointData;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationData {
    private Object[][] layers;
    private Object layerFeatures;
    private AnnotationTimeData time;
    private Object style;
    private AnnotationDataChart chart;
    private KeyPointData keyPoint;

    public Object[][] getLayers() {
        return layers;
    }

    public void setLayers(Object[][] layers) {
        this.layers = layers;
    }

    public Object getLayerFeatures() {
        return layerFeatures;
    }

    public void setLayerFeatures(Object layerFeatures) {
        this.layerFeatures = layerFeatures;
    }

    public AnnotationTimeData getTime() {
        return time;
    }

    public void setTime(AnnotationTimeData time) {
        this.time = time;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public AnnotationDataChart getChart() {
        return chart;
    }

    public void setChart(AnnotationDataChart chart) {
        this.chart = chart;
    }

    public KeyPointData getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(KeyPointData keyPoint) {
        this.keyPoint = keyPoint;
    }
}
