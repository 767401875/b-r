package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonFeatureItems {
    private String id;
    private String type;
    private GeoJsonFeatureItemsGeometry geometry;
    private GeoJsonFeatureItemsProperties properties;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoJsonFeatureItemsGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonFeatureItemsGeometry geometry) {
        this.geometry = geometry;
    }

    public GeoJsonFeatureItemsProperties getProperties() {
        return properties;
    }

    public void setProperties(GeoJsonFeatureItemsProperties properties) {
        this.properties = properties;
    }
}
