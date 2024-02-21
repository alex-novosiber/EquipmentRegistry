package com.equipmentregistry.application.models;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "computers")
public class Computer extends Entity {
    @Id
    private Long id;
    private String title;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private String category;
    private String processorType;
    private boolean availability;




}
