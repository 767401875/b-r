package com.ISCAS.OneBeltOneRoad.entity.maps;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MapsParam {
    private MapsItem[] items;
    private Integer init;
    private String projection;
    private Double[] center;
    private  Integer[] leftBottom;
    private Integer[] rightTop;

    public MapsItem[] getItems() {
        return items;
    }

    public void setItems(MapsItem[] items) {
        this.items = items;
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

    public Double[] getCenter() {
        return center;
    }

    public void setCenter(Double[] center) {
        this.center = center;
    }

    public Integer[] getLeftBottom() {
        return leftBottom;
    }

    public void setLeftBottom(Integer[] leftBottom) {
        this.leftBottom = leftBottom;
    }

    public Integer[] getRightTop() {
        return rightTop;
    }

    public void setRightTop(Integer[] rightTop) {
        this.rightTop = rightTop;
    }
}
