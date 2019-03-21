package com.ISCAS.OneBeltOneRoad.entity.maps;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Annotation {
    private AnnotationLayerObject[][] layers;

    public AnnotationLayerObject[][] getLayers() {
        return layers;
    }

    public void setLayers(AnnotationLayerObject[][] layers) {
        this.layers = layers;
    }
}
