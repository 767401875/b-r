package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonFeatureItemsProperties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
