package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonFeatureItemsGeometry {
    private String type;
    private Object coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[][][] coordinates) {
        this.coordinates = coordinates;
    }
    public void setCoordinates(Double[][][][] coordinates) {
        this.coordinates = coordinates;
    }
}
