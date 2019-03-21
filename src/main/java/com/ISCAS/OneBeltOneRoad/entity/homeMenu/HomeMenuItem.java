package com.ISCAS.OneBeltOneRoad.entity.homeMenu;

public class HomeMenuItem {
    public LogoItemParam logo;
    public String name;
    public String link;
    public Boolean available;
    public String description;

    public HomeMenuItem(){

    }

    public LogoItemParam getLogo() {
        return logo;
    }

    public void setLogo(LogoItemParam logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
