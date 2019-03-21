package com.ISCAS.OneBeltOneRoad.entity.data.Echarts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EchartsLineStyle {
    private EchartsLineStyleNormal normal;

    public EchartsLineStyleNormal getNormal() {
        return normal;
    }

    public void setNormal(EchartsLineStyleNormal normal) {
        this.normal = normal;
    }
}
