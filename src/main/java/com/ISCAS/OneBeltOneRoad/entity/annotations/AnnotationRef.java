package com.ISCAS.OneBeltOneRoad.entity.annotations;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationRef {
    private String dataName;
    AnnotationRefQueryParam queryParam;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public AnnotationRefQueryParam getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(AnnotationRefQueryParam queryParam) {
        this.queryParam = queryParam;
    }
}
