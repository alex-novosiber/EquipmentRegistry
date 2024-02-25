package com.equipmentregistry.application.models;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Телевизоры: наименование, серийный номер, цвет, размер, цена,
 * категория, технология, наличие товара (Да/Нет);
 */
@Entity
@Table(name = "tv")
public class Tv extends StaffEntity {
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
    private String technology;
    private boolean availability;

    public Tv() {}

    public Tv(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility,
                    boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price,
                    String category, String technology, boolean availability) {
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
        this.technology = technology;
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

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Tv{" +
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
                ", technology='" + technology + '\'' +
                ", availability=" + availability +
                '}';
    }
}
