package com.equipmentregistry.application.models;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "refrigerators")
public class Refrigerator extends StaffEntity {
    @Id
    private Long id;
    private String title;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private Integer doorsCount;
    private String compressorType;
    private boolean availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
