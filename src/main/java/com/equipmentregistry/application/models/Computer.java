package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "computers")
public class Computer extends StaffEntity {
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
    private String category;
    private String processorType;
    private boolean availability;

    public Computer() {}

    public Computer(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                    boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price,
                    String category, String processorType, boolean availability) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.category = category;
        this.processorType = processorType;
        this.availability = availability;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", countryOfManufacturer='" + countryOfManufacturer + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", onlineOrderingPossibility=" + onlineOrderingPossibility +
                ", installmentPlanPossibility=" + installmentPlanPossibility +
                ", serialNumber='" + serialNumber + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", processorType='" + processorType + '\'' +
                ", availability=" + availability +
                '}';
    }
}
