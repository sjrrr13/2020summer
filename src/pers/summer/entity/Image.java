package pers.summer.entity;

import java.util.Date;

public class Image {
    private Integer imageID;
    private String title;
    private String description;
    private Integer latitude;
    private Integer longitude;
    private Long cityCode;
    private String country_RegionCodeISO;
    private Integer uID;
    private String pATH;
    private String content;
    private Date time;

    public Image() {
    }

    public Image(Integer imageID, String title, String description, Integer latitude, Integer longitude,
                 Long cityCode, String country_RegionCodeISO, Integer UID, String PATH, String content,
                 Date time) {
        imageID = imageID;
        title = title;
        description = description;
        latitude = latitude;
        longitude = longitude;
        cityCode = cityCode;
        country_RegionCodeISO = country_RegionCodeISO;
        this.uID = UID;
        this.pATH = PATH;
        content = content;
        time = time;
    }

    public Integer getImageID() {
        return imageID;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountry_RegionCodeISO() {
        return country_RegionCodeISO;
    }

    public void setCountry_RegionCodeISO(String country_RegionCodeISO) {
        this.country_RegionCodeISO = country_RegionCodeISO;
    }

    public Integer getUID() {
        return uID;
    }

    public void setUID(Integer uID) {
        this.uID = uID;
    }

    public String getPATH() {
        return pATH;
    }

    public void setPATH(String pATH) {
        this.pATH = pATH;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageID=" + imageID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", cityCode=" + cityCode +
                ", country_RegionCodeISO='" + country_RegionCodeISO + '\'' +
                ", uID=" + uID +
                ", pATH='" + pATH + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                '}';
    }
}
