package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "vacuum_cleaners")
public class VacuumCleaner extends StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String countryOfManufacturer;
    private String manufacturer;
    private boolean onlineOrderingPossibility;
    private boolean installmentPlanPossibility;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer dustContainerVolume;
    private Integer modesCount;
    private boolean availability;

    public VacuumCleaner(){}

    public VacuumCleaner(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                         boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price,
                         Integer dustContainerVolume, Integer modesCount, boolean availability) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.dustContainerVolume = dustContainerVolume;
        this.modesCount = modesCount;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCountryOfManufacturer() {
        return countryOfManufacturer;
    }

    @Override
    public void setCountryOfManufacturer(String countryOfManufacturer) {
        this.countryOfManufacturer = countryOfManufacturer;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean isOnlineOrderingPossibility() {
        return onlineOrderingPossibility;
    }

    @Override
    public void setOnlineOrderingPossibility(boolean onlineOrderingPossibility) {
        this.onlineOrderingPossibility = onlineOrderingPossibility;
    }

    @Override
    public boolean isInstallmentPlanPossibility() {
        return installmentPlanPossibility;
    }

    @Override
    public void setInstallmentPlanPossibility(boolean installmentPlanPossibility) {
        this.installmentPlanPossibility = installmentPlanPossibility;
    }

    public Integer getDustContainerVolume() {
        return dustContainerVolume;
    }

    public void setDustContainerVolume(Integer dustContainerVolume) {
        this.dustContainerVolume = dustContainerVolume;
    }

    public Integer getModesCount() {
        return modesCount;
    }

    public void setModesCount(Integer modesCount) {
        this.modesCount = modesCount;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
