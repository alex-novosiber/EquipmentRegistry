package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.*;
import com.equipmentregistry.application.repositories.SmartphoneRepository;
import com.equipmentregistry.application.utils.Filtering;
import com.equipmentregistry.application.utils.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;

    public SmartphoneService(SmartphoneRepository smartphoneRepository) {
        this.smartphoneRepository = smartphoneRepository;
    }

    public List<SmartPhone> findAll() {
        return smartphoneRepository.findAll();
    }

    public List<SmartPhone> findByTitle(String title) {
        if(title != null && !title.isEmpty()) {
            return smartphoneRepository.findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(title);
        }else{
            return smartphoneRepository.findAll();
        }
    }

    public StaffEntity findByTitleAndParameters(Map<String, String> paramsMap) {
        List<SmartPhone> resultList;
        List<SmartPhone> smartPhoneList;
        if (!paramsMap.isEmpty() && paramsMap.containsKey("title")) {
            smartPhoneList = findByTitle(paramsMap.get("title"));
            resultList = filterByParameters(smartPhoneList, paramsMap);
        } else {
            smartPhoneList = smartphoneRepository.findAllByAvailabilityIsTrue();
            resultList = filterByParameters(smartPhoneList, paramsMap);
        }
        return generateStaffEntity(paramsMap, resultList);
    }

    List<SmartPhone> filterByParameters(List<SmartPhone> input, Map<String, String> paramsMap) {
        List<SmartPhone> result = new ArrayList<>();
        for (SmartPhone t : input) {
            boolean sameColor = false;
            boolean samePrice = false;
            boolean sameSize = false;
            boolean sameCamerasCount = false;
            boolean sameMemorySize = false;
            boolean sameCountryOfManufacturer = false;
            boolean sameManufacturer = false;
            boolean onlineOrderingPossibility = false;
            boolean installmentPlanPossibility = false;
            if (paramsMap.get("color") != null && paramsMap.get("color").equalsIgnoreCase(t.getColor())) {
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
            if (paramsMap.get("camerasCount") != null && t.getCamerasCount().toString().equals(paramsMap.get("camerasCount"))) {
                sameCamerasCount = true;
            }
            if (paramsMap.get("memorySize") != null && t.getMemorySize().toString().equals(paramsMap.get("memorySize"))) {
                sameMemorySize = true;
            }
            if (paramsMap.get("countryOfManufacturer") != null && paramsMap.get("countryOfManufacturer").equalsIgnoreCase(t.getCountryOfManufacturer())) {
                sameCountryOfManufacturer = true;
            }
            if (paramsMap.get("manufacturer") != null && paramsMap.get("manufacturer").contains(t.getManufacturer())) {
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
                    (paramsMap.get("camerasCount") == null || sameCamerasCount) &&
                    (paramsMap.get("memorySize") == null || sameMemorySize) &&
                    (paramsMap.get("countryOfManufacturer") == null || sameCountryOfManufacturer) &&
                    (paramsMap.get("manufacturer") == null || sameManufacturer) &&
                    (paramsMap.get("onlineOrderingPossibility") == null || onlineOrderingPossibility) &&
                    (paramsMap.get("installmentPlanPossibility") == null || installmentPlanPossibility)
            ) result.add(t);
        }
        return result;
    }

    StaffEntity generateStaffEntity(Map<String, String> paramsMap, List<? extends StaffEntity> staffList) {
        StaffEntity se = new StaffEntity();
        se.setTitle("smartphone");
        if (paramsMap.get("countryOfManufacturer") != null) {
            se.setCountryOfManufacturer(paramsMap.get("countryOfManufacturer"));
        }
        if (paramsMap.get("manufacturer") != null) {
            se.setManufacturer(paramsMap.get("manufacturer"));
        }
        if (paramsMap.get("onlineOrderingPossibility") != null && paramsMap.get("onlineOrderingPossibility").equals("true")) {
            se.setOnlineOrderingPossibility(true);
        }
        if (paramsMap.get("installmentPlanPossibility") != null && paramsMap.get("installmentPlanPossibility").equals("true")) {
            se.setInstallmentPlanPossibility(true);
        }
        se.setEntities(staffList);
        return se;
    }


    public String addNewSmartphone(Map<String, String> map) {
        String response = "error";
        SmartPhone smartPhone = null;
        try {
            Wrapper c = Objects.requireNonNull(Filtering.fillRelevantFields(map));
            smartPhone = new SmartPhone(c.getTitle(), c.getCountryOfManufacturer(), c.getManufacturer(),
                    c.isOnlineOrderingPossibility(), c.isInstallmentPlanPossibility(), c.getSerialNumber(),
                    c.getColor(), c.getSize(), c.getPrice(), 0, 0, c.isAvailability());
        } catch (NullPointerException e) {
            response = "Error, check input parameters";
            return response;
        }

        int camerasCount = 0;
        if (map.get("camerasCount") != null && !map.get("camerasCount").isEmpty()) {
            try {
                camerasCount = NumberUtils.parseNumber(map.get("camerasCount"), Integer.class);
            } catch (NumberFormatException e) {
                return response = "NumberFormatException in camerasCount field";
            }

        }
        int memorySize = 0;
        if (map.get("memorySize") != null && !map.get("memorySize").isEmpty()) {
            try {
                memorySize = NumberUtils.parseNumber(map.get("memorySize"), Integer.class);
            } catch (NumberFormatException e) {
                return response = "NumberFormatException in memorySize field";
            }

        }
        smartPhone.setCamerasCount(camerasCount);
        smartPhone.setMemorySize(memorySize);
        SmartPhone sp = smartphoneRepository.save(smartPhone);
        if (sp.equals(smartPhone)) {
            response = "success";
        }
        return response;
    }
}
