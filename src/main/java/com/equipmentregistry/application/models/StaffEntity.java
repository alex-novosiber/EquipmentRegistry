package com.equipmentregistry.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class StaffEntity {

    /**
     * Наименование товара
     */
    private String title;
    /**
     * Страна производитель
     */
    @JsonProperty("countryOfManufacturer")
    private String countryOfManufacturer;
    /**
     * Производитель
     */
    @JsonProperty("manufacturer")
    private String manufacturer;
    /**
     * Возможность онлайн заказа
     */
    @JsonProperty("onlineOrderingPossibility")
    private boolean onlineOrderingPossibility;
    /**
     * Возможность оформления рассрочки
     */
    @JsonProperty("installmentPlanPossibility")
    private boolean  installmentPlanPossibility;

    @JsonProperty("entities")
    private List<? extends StaffEntity> entities;

    public StaffEntity() {}

    public StaffEntity(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                       boolean installmentPlanPossibility) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountryOfManufacturer() {
        return countryOfManufacturer;
    }

    public void setCountryOfManufacturer(String countryOfManufacturer) {
        this.countryOfManufacturer = countryOfManufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isOnlineOrderingPossibility() {
        return onlineOrderingPossibility;
    }

    public void setOnlineOrderingPossibility(boolean onlineOrderingPossibility) {
        this.onlineOrderingPossibility = onlineOrderingPossibility;
    }

    public boolean isInstallmentPlanPossibility() {
        return installmentPlanPossibility;
    }

    public void setInstallmentPlanPossibility(boolean installmentPlanPossibility) {
        this.installmentPlanPossibility = installmentPlanPossibility;
    }

    public List<? extends StaffEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<? extends StaffEntity> entities) {
        this.entities = entities;
    }
}
