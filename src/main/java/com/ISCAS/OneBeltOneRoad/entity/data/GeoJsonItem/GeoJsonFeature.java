package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonFeature {
    private String type;
    private GeoJsonFeatureItems[] features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoJsonFeatureItems[] getFeatures() {
        return features;
    }

    public void setFeatures(GeoJsonFeatureItems[] features) {
        this.features = features;
    }
}
