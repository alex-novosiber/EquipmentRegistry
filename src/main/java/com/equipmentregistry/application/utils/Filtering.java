package com.equipmentregistry.application.utils;

import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Filtering {

    public static List<Tv> filterTvByParameters(List<Tv> input, Map<String, String> paramsMap) {
        List<Tv> result = new ArrayList<>();
//        StaffEntity t;
        for (Tv t : input) {
            boolean sameColor = false;
            boolean samePrice = false;
            boolean sameSize = false;
            boolean sameCountryOfManufacturer = false;
            boolean sameManufacturer = false;
            boolean onlineOrderingPossibility = false;
            boolean installmentPlanPossibility = false;
            if (paramsMap.get("color") != null && paramsMap.get("color").equals(t.getColor())) {
                sameColor = true;
            }
            if (paramsMap.get("price") != null) {
                String[] pr = paramsMap.get("price").split("-");
                BigDecimal price = t.getPrice();
                BigDecimal low = BigDecimal.valueOf(Long.parseLong(pr[0]));
                BigDecimal high = BigDecimal.valueOf(Long.parseLong(pr[1]));
                if (low.compareTo(price) <= 0 && high.compareTo(price) >= 0) {
                    samePrice = true;
                }
            }
            if (paramsMap.get("size") != null && t.getSize().contains(paramsMap.get("size"))) {
                sameSize = true;
            }
            if (paramsMap.get("countryOfManufacturer") != null && paramsMap.get("countryOfManufacturer").equals(t.getCountryOfManufacturer())) {
                sameCountryOfManufacturer = true;
            }
            if (paramsMap.get("manufacturer") != null && paramsMap.get("manufacturer").equals(t.getManufacturer())) {
                sameManufacturer = true;
            }
            if (paramsMap.get("onlineOrderingPossibility") != null && t.isOnlineOrderingPossibility()) {
                onlineOrderingPossibility = true;
            }
            if (paramsMap.get("installmentPlanPossibility") != null && t.isInstallmentPlanPossibility()) {
                installmentPlanPossibility = true;
            }
            if ((paramsMap.get("color") == null || sameColor) &&
                    (paramsMap.get("price") == null || samePrice) &&
                    (paramsMap.get("size") == null || sameSize) &&
                    (paramsMap.get("countryOfManufacturer") == null || sameCountryOfManufacturer) &&
                    (paramsMap.get("manufacturer") == null || sameManufacturer) &&
                    (paramsMap.get("onlineOrderingPossibility") == null || onlineOrderingPossibility) &&
                    (paramsMap.get("installmentPlanPossibility") == null || installmentPlanPossibility)
            ) result.add(t);
        }
        return result;
    }

    public static Wrapper fillRelevantFields(Map<String, String> map) {


        boolean onlineOrderingPossibility = false;
        boolean installmentPlanPossibility = false;
        boolean availability = false;
        String title;
        String countryOfManufacturer;
        String manufacturer;
        String serialNumber;
        String color;
        String size;
        Wrapper res = null;

        String response = Wrapper.checkAllWrapperFields(map);

        if (response.equals("ok")) {
            if (map.get("onlineOrderingPossibility").equals("true")) {
                onlineOrderingPossibility = true;
            }
            if (map.get("installmentPlanPossibility").equals("true")) {
                installmentPlanPossibility = true;
            }
            if (map.get("availability").equals("true")) {
                availability = true;
            }
            title = map.get("title");
            countryOfManufacturer = map.get("countryOfManufacturer");
            manufacturer = map.get("manufacturer");
            serialNumber = map.get("serialNumber");
            color = map.get("color");
            size = map.get("size");
            BigDecimal price = NumberUtils.parseNumber(map.get("price"), BigDecimal.class);
            res = new Wrapper(title,
                    countryOfManufacturer,
                    manufacturer,
                    onlineOrderingPossibility,
                    installmentPlanPossibility,
                    serialNumber,
                    color, size, price, availability);


        }
        return res;
    }


//    public StaffEntity create(int param, int param2, String param3) {
//        if (param < 3) {
//            return new MyClassA(param, param2);
//        } else if (param > 3) {
//            return new MyClassB(param, param3);
//        } else {
//            return null;
//        }
//    }

}

