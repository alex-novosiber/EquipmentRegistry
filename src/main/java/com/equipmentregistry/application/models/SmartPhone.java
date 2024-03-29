package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "smartphones")
public class SmartPhone extends StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование товара
     */
    private String title;
    private String countryOfManufacturer;
    private String manufacturer;
    private boolean onlineOrderingPossibility;
    private boolean  installmentPlanPossibility;
    /**
     * Серийный номер
     */
    private String serialNumber;
    /**
     * Цвет
     */
    private String color;
    /**
     * Размер
     */
    private String size;
    /**
     * Цена
     */
    private BigDecimal price;
    /**
     * Кол-во камер
     */
    private Integer camerasCount;
    /**
     * Объём памяти
     */
    private Integer memorySize;
    /**
     * доступность для заказа
     */
    private boolean availability;

    public SmartPhone() {}

    public SmartPhone(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                      boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price,
                      Integer camerasCount, Integer memorySize, boolean availability) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.camerasCount = camerasCount;
        this.memorySize = memorySize;
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

    public Integer getCamerasCount() {
        return camerasCount;
    }

    public void setCamerasCount(Integer camerasCount) {
        this.camerasCount = camerasCount;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
