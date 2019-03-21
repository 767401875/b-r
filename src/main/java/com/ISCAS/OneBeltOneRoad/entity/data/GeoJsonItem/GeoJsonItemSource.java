package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonItemSource {
    private Object feature;

    public Object getFeature() {
        return feature;
    }

    public void setFeature(Object feature) {
        this.feature = feature;
    }
}
