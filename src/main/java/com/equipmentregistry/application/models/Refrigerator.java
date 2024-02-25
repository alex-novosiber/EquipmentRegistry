package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "refrigerators")
public class Refrigerator extends StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String countryOfManufacturer;
    private String manufacturer;
    private boolean onlineOrderingPossibility;
    private boolean  installmentPlanPossibility;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer doorsCount;
    private String compressorType;
    private boolean availability;

    public Refrigerator() {}

    public Refrigerator(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                        boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price,
                        Integer doorsCount, String compressorType, boolean availability) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.doorsCount = doorsCount;
        this.compressorType = compressorType;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public Integer getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(Integer doorsCount) {
        this.doorsCount = doorsCount;
    }

    public String getCompressorType() {
        return compressorType;
    }

    public void setCompressorType(String compressorType) {
        this.compressorType = compressorType;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }




}
