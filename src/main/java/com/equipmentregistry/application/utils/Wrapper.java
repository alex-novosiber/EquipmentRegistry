package com.equipmentregistry.application.utils;

import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Map;

public class Wrapper {
    private String title;
    private String countryOfManufacturer;
    private String manufacturer;
    private boolean onlineOrderingPossibility;
    private boolean  installmentPlanPossibility;
    private String serialNumber;
    private String color;
    private String size;
    private BigDecimal price;
    private boolean availability;

    public Wrapper() {
    }

    public Wrapper(String title, String countryOfManufacturer, String manufacturer, boolean onlineOrderingPossibility, boolean installmentPlanPossibility, String serialNumber, String color, String size, BigDecimal price, boolean availability) {
        this.title = title;
        this.countryOfManufacturer = countryOfManufacturer;
        this.manufacturer = manufacturer;
        this.onlineOrderingPossibility = onlineOrderingPossibility;
        this.installmentPlanPossibility = installmentPlanPossibility;
        this.serialNumber = serialNumber;
        this.color = color;
        this.size = size;
        this.price = price;
        this.availability = availability;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountryOfManufacturer() {
        return countryOfManufacturer;
    }

    public void setCountryOfManufacturer(String countryOfManufacturer) {
        this.countryOfManufacturer = countryOfManufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isOnlineOrderingPossibility() {
        return onlineOrderingPossibility;
    }

    public void setOnlineOrderingPossibility(boolean onlineOrderingPossibility) {
        this.onlineOrderingPossibility = onlineOrderingPossibility;
    }

    public boolean isInstallmentPlanPossibility() {
        return installmentPlanPossibility;
    }

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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public static String checkAllWrapperFields(Map<String, String> map){
        String response = "ok";
        if(map.get("title") == null || map.get("title").isEmpty()){
            return  "error in title field";
        }
        if(map.get("countryOfManufacturer") == null || map.get("countryOfManufacturer").isEmpty()){
            return  "error in countryOfManufacturer field";
        }
        if(map.get("manufacturer") == null || map.get("manufacturer").isEmpty()){
            return  "error in manufacturer field";
        }
        if(map.get("onlineOrderingPossibility") == null || map.get("onlineOrderingPossibility").isEmpty() ||
                (!map.get("onlineOrderingPossibility").equals("true") && !map.get("onlineOrderingPossibility").equals("false"))){
            return  "error in onlineOrderingPossibility field";
        }
        if(map.get("installmentPlanPossibility") == null || map.get("installmentPlanPossibility").isEmpty() ||
        (!map.get("installmentPlanPossibility").equals("true") && !map.get("installmentPlanPossibility").equals("false"))){
            return  "error in installmentPlanPossibility field";
        }
        if(map.get("serialNumber") == null || map.get("serialNumber").isEmpty()){
            return  "error in serialNumber field";
        }
        if(map.get("color") == null || map.get("color").isEmpty()){
            return  "error in color field";
        }
        if(map.get("size") == null || map.get("size").isEmpty()){
            return  "error in size field";
        }

        BigDecimal price = new BigDecimal("0.0");
        if(map.get("price") != null && !map.get("price").isEmpty()){
            try{
                price = NumberUtils.parseNumber(map.get("price"), BigDecimal.class);
            }catch (NumberFormatException e){
                return "NumberFormatException in price field";
            }
        }else{
            return  "absent price field";
        }
        if(map.get("availability") == null || map.get("availability").isEmpty() ||
        (!map.get("availability").equals("true") && !map.get("availability").equals("false"))){
            return  "error in availability field";
        }
        return response;
    }
}
