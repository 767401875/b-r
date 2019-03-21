package com.ISCAS.OneBeltOneRoad.entity.data.WmsItem;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WmsItemSource {
    private String url;
    private WmsItemSourceParams params;
    private String serverType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WmsItemSourceParams getParams() {
        return params;
    }

    public void setParams(WmsItemSourceParams params) {
        this.params = params;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
}
