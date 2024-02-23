package com.equipmentregistry.application.models;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "vacuum_cleaners")
public class VacuumCleaner extends StaffEntity {
    @Id
    private Long id;
    private String title;
    private String serialNumber;
    private String color;
    // размер, цена, объём пылесборника, количество режимов, наличие товара (Да/Нет);
    private String size;
    private BigDecimal price;
    private Integer dustContainerVolume;
    private Integer modesCount;
    private boolean availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
