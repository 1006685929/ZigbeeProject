package com.nuc.zigbee.entity;

public class ErrorSensor {
    private Integer id;

    private String errorDate;

    private String errorTemp;

    private String errorLight;

    private String errorInfo;

    public ErrorSensor(){

    }

    public ErrorSensor(String errorDate, String errorTemp, String errorLight, String errorInfo) {
        this.errorDate = errorDate;
        this.errorTemp = errorTemp;
        this.errorLight = errorLight;
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.errorDate = errorDate;
    }

    public String getErrorTemp() {
        return errorTemp;
    }

    public void setErrorTemp(String errorTemp) {
        this.errorTemp = errorTemp;
    }

    public String getErrorLight() {
        return errorLight;
    }

    public void setErrorLight(String errorLight) {
        this.errorLight = errorLight;
    }
}
