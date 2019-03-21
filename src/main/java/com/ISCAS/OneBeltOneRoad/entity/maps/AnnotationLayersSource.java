package com.ISCAS.OneBeltOneRoad.entity.maps;

import com.ISCAS.OneBeltOneRoad.entity.maps.AnnotationSourceParam;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationLayersSource {
    private String url;
    private String serverType;
    private AnnotationSourceParam params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public AnnotationSourceParam getParams() {
        return params;
    }

    public void setParams(AnnotationSourceParam params) {
        this.params = params;
    }
}
