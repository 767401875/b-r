package com.ISCAS.OneBeltOneRoad.entity.data.layerFeatures;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LayerFeatures {
    private String id;
    private String type;
    private LayerFeaturesGeometry geometry;
    private LayerFeaturesProperties properties;

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

    public LayerFeaturesGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(LayerFeaturesGeometry geometry) {
        this.geometry = geometry;
    }

    public LayerFeaturesProperties getProperties() {
        return properties;
    }

    public void setProperties(LayerFeaturesProperties properties) {
        this.properties = properties;
    }
}
