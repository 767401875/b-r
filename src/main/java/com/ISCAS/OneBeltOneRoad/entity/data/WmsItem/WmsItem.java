package com.ISCAS.OneBeltOneRoad.entity.data.WmsItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WmsItem {
    private String type;
    private WmsItemSource source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public WmsItemSource getSource() {
        return source;
    }

    public void setSource(WmsItemSource source) {
        this.source = source;
    }
}
