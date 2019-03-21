package com.ISCAS.OneBeltOneRoad.entity.data.time;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationTimeData {
    private String type;
    private String caption;
    private Integer initTime;
    private AnnotationTimeDataSequence[] sequence;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getInitTime() {
        return initTime;
    }

    public void setInitTime(Integer initTime) {
        this.initTime = initTime;
    }

    public AnnotationTimeDataSequence[] getSequence() {
        return sequence;
    }

    public void setSequence(AnnotationTimeDataSequence[] sequence) {
        this.sequence = sequence;
    }
}
