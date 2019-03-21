package com.ISCAS.OneBeltOneRoad.entity.maps;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationLayerObject {
    private String type;
    private AnnotationLayersSource source;

    public AnnotationLayerObject(){

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AnnotationLayersSource getSource() {
        return source;
    }

    public void setSource(AnnotationLayersSource source) {
        this.source = source;
    }
}
