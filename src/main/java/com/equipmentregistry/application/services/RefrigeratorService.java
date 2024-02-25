package com.equipmentregistry.application.services;


import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.models.Refrigerator;
import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.repositories.RefrigeratorRepository;
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
public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;

    public RefrigeratorService(RefrigeratorRepository refrigeratorRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
    }


    public List<Refrigerator> findAll() {
        return refrigeratorRepository.findAll();
    }

    public List<Refrigerator> findByTitle(String title) {
        if(title != null && !title.isEmpty()) {
            return refrigeratorRepository.findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(title);
        }else{
            return refrigeratorRepository.findAll();
        }
    }


    public StaffEntity findByTitleAndParameters(Map<String, String> paramsMap) {
        List<Refrigerator> resultList;

        List<Refrigerator> refrigeratorList;
        if (!paramsMap.isEmpty() && paramsMap.containsKey("title")) {
            refrigeratorList = findByTitle(paramsMap.get("title"));
            resultList = filterByParameters(refrigeratorList, paramsMap);
        } else {
            refrigeratorList = refrigeratorRepository.findAllByAvailabilityIsTrue();
            resultList = filterByParameters(refrigeratorList, paramsMap);
        }
        return generateStaffEntity(paramsMap, resultList);
    }

    List<Refrigerator> filterByParameters(List<Refrigerator> input, Map<String, String> paramsMap) {
        List<Refrigerator> result = new ArrayList<>();
        for (Refrigerator t : input) {
            boolean sameColor = false;
            boolean samePrice = false;
            boolean sameSize = false;
            boolean sameDoorsCount = false;
            boolean sameCompressorType = false;
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
            if (paramsMap.get("doorsCount") != null && t.getDoorsCount().toString().equals(paramsMap.get("doorsCount"))) {
                sameDoorsCount = true;
            }
            if (paramsMap.get("compressorType") != null && t.getCompressorType().equals(paramsMap.get("compressorType"))) {
                sameCompressorType = true;
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
                    (paramsMap.get("doorsCount") == null || sameDoorsCount) &&
                    (paramsMap.get("compressorType") == null || sameCompressorType) &&
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
        se.setTitle("refrigerator");
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


    public String addNewRefrigerator(Map<String, String> map) {
        String response = "error";
        Wrapper c;
        try {
            c = Objects.requireNonNull(Filtering.fillRelevantFields(map));
        } catch (NullPointerException e) {
            return response = "Error, check input parameters";
        }
        int doorsCount = 0;
        if (map.get("doorsCount") != null && !map.get("doorsCount").isEmpty()) {
            try {
                doorsCount = NumberUtils.parseNumber(map.get("doorsCount"), Integer.class);
            } catch (NumberFormatException e) {
                return response = "NumberFormatException in doorsCount field";
            }
        }else{
            return response = "absent doorsCount field";
        }
        String compressorType = "null";
        if (map.get("compressorType") != null && !map.get("compressorType").isEmpty()) {
            compressorType = map.get("compressorType");
        }else{
            return response = "absent compressorType field";
        }
        Refrigerator refrigerator = new Refrigerator(c.getTitle(), c.getCountryOfManufacturer(), c.getManufacturer(),
                c.isOnlineOrderingPossibility(), c.isInstallmentPlanPossibility(), c.getSerialNumber(),
                c.getColor(), c.getSize(), c.getPrice(), doorsCount, compressorType, c.isAvailability());
        Refrigerator ref = refrigeratorRepository.save(refrigerator);
        if (ref.equals(refrigerator)) {
            response = "success";
        }
        return response;
    }

}

