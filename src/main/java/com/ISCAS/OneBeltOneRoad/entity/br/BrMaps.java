package com.ISCAS.OneBeltOneRoad.entity.br;

public class BrMaps {
    private Integer mapId;
    private String mapUuid;
    private String caption;
    private String annotation;
    private Integer init;
    private String projection;
    private Object center;
    private Object leftBottom;
    private Object rightTop;
    private String description;

    public BrMaps(){

    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public String getMapUuid() {
        return mapUuid;
    }

    public void setMapUuid(String mapUuid) {
        this.mapUuid = mapUuid;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getInit() {
        return init;
    }

    public void setInit(Integer init) {
        this.init = init;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public Object getCenter() {
        return center;
    }

    public void setCenter(Object center) {
        this.center = center;
    }

    public Object getLeftBottom() {
        return leftBottom;
    }

    public void setLeftBottom(Object leftBottom) {
        this.leftBottom = leftBottom;
    }

    public Object getRightTop() {
        return rightTop;
    }

    public void setRightTop(Object rightTop) {
        this.rightTop = rightTop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
