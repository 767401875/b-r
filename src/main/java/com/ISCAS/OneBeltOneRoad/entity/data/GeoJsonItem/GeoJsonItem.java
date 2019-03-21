package com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GeoJsonItem {
    private String type;
    private GeoJsonItemSource source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoJsonItemSource getSource() {
        return source;
    }

    public void setSource(GeoJsonItemSource source) {
        this.source = source;
    }
}
