package com.ISCAS.OneBeltOneRoad.entity.br;

public class BrAnnotations {
    private Integer id;
    private String caption;
    private String description;
    private boolean timeRelated;
    private boolean showUp;
    private Integer annotationRefId;
    private Integer parentId;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getAnnotationRefId() {
        return annotationRefId;
    }

    public void setAnnotationRefId(Integer annotationRefId) {
        this.annotationRefId = annotationRefId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
