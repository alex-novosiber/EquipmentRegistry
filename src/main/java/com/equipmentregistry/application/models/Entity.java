package com.equipmentregistry.application.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;


public class Entity {


    /**
     * Страна производитель
     */
    private String countryOfManufacturer;
    /**
     * Производитель
     */
    private String manufacturer;
    /**
     * Возможность онлайн заказа
     */
    private boolean onlineOrderingPossibility;
    /**
     * Возможность оформления рассрочки
     */
    private boolean  installmentPlanPossibility;

    private List<? extends Entity> entities;

}
