package com.ISCAS.OneBeltOneRoad.entity.data.chart;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationDataChart {
    private String caption;
    private AnnotationDataChartOptions options;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public AnnotationDataChartOptions getOptions() {
        return options;
    }

    public void setOptions(AnnotationDataChartOptions options) {
        this.options = options;
    }
}
