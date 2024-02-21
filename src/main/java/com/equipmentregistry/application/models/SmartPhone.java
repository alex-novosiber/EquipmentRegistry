package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@javax.persistence.Entity
@Table(name = "smartphones")
public class SmartPhone extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование товара
     */
    private String title;
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
     * доступность для заказа
     */
    private boolean availability;



}
