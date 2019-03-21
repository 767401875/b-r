package com.ISCAS.OneBeltOneRoad.entity.item;

import com.ISCAS.OneBeltOneRoad.entity.annotations.AnnotationMenuData;
import com.ISCAS.OneBeltOneRoad.entity.annotations.AnnotationRef;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AnnotationMenuItem {
    private String caption;
    private String description;
    private AnnotationMenuData items;
    private AnnotationRef annotationRef;
    private boolean timeRelated;
    private boolean showUp;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnnotationMenuData getItems() {
        return items;
    }

    public void setItems(AnnotationMenuData items) {
        this.items = items;
    }

    public AnnotationRef getAnnotationRef() {
        return annotationRef;
    }

    public void setAnnotationRef(AnnotationRef annotationRef) {
        this.annotationRef = annotationRef;
    }

    public boolean isTimeRelated() {
        return timeRelated;
    }

    public void setTimeRelated(boolean timeRelated) {
        this.timeRelated = timeRelated;
    }

    public boolean isShowUp() {
        return showUp;
    }

    public void setShowUp(boolean showUp) {
        this.showUp = showUp;
    }
}
