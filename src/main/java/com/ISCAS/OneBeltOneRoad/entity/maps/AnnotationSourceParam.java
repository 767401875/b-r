package com.ISCAS.OneBeltOneRoad.entity.maps;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationSourceParam {
   private String layers;

    public String getLayers() {
        return layers;
    }

    public void setLayers(String layers1) {
        this.layers = layers1;
    }
}
