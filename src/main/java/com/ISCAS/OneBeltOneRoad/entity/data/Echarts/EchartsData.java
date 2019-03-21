package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsData {
    private EchartsSeries[] series;
    private EchartsToolTip tooltip;

    public EchartsSeries[] getSeries() {
        return series;
    }

    public void setSeries(EchartsSeries[] series) {
        this.series = series;
    }

    public EchartsToolTip getTooltip() {
        return tooltip;
    }

    public void setTooltip(EchartsToolTip tooltip) {
        this.tooltip = tooltip;
    }
}
