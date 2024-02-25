package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.*;
import com.equipmentregistry.application.repositories.VacuumCleanerRepository;
import com.equipmentregistry.application.utils.Filtering;
import com.equipmentregistry.application.utils.Wrapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class VacuumCleanerService {

    private final VacuumCleanerRepository vacuumCleanerRepository;

    public VacuumCleanerService(VacuumCleanerRepository vacuumCleanerRepository) {
        this.vacuumCleanerRepository = vacuumCleanerRepository;
    }

    public List<VacuumCleaner> findAll() {
        return vacuumCleanerRepository.findAllByAvailabilityIsTrue();
    }

    public List<VacuumCleaner> findByTitle(String title) {
        if(title != null && !title.isEmpty()) {
            return vacuumCleanerRepository.findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(title);
        }else{
            return vacuumCleanerRepository.findAll();
        }
    }

    public StaffEntity findByTitleAndParameters(Map<String, String> paramsMap) {
        List<VacuumCleaner> resultList;

        List<VacuumCleaner> vacuumCleanerList;
        if (!paramsMap.isEmpty() && paramsMap.containsKey("title")) {
            vacuumCleanerList = findByTitle(paramsMap.get("title"));
            resultList = filterByParameters(vacuumCleanerList, paramsMap);
        } else {
            vacuumCleanerList = vacuumCleanerRepository.findAllByAvailabilityIsTrue();
            resultList = filterByParameters(vacuumCleanerList, paramsMap);
        }
        return generateStaffEntity(paramsMap, resultList);
    }

    List<VacuumCleaner> filterByParameters(List<VacuumCleaner> input, Map<String, String> paramsMap) {
        List<VacuumCleaner> result = new ArrayList<>();
        for (VacuumCleaner t : input) {
            boolean sameColor = false;
            boolean samePrice = false;
            boolean sameSize = false;
            boolean sameDustContainerVolume = false;
            boolean sameModesCount = false;
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
            if (paramsMap.get("dustContainerVolume") != null && t.getDustContainerVolume().toString().equals(paramsMap.get("dustContainerVolume"))) {
                sameDustContainerVolume = true;
            }
            if (paramsMap.get("modesCount") != null && t.getModesCount().toString().equals(paramsMap.get("modesCount"))) {
                sameModesCount = true;
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
                    (paramsMap.get("dustContainerVolume") == null || sameDustContainerVolume) &&
                    (paramsMap.get("modesCount") == null || sameModesCount) &&
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
        se.setTitle("vacuumcleaner");
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


    public String addNewVacuumCleaner(Map<String, String> map) {
        String response = "error";
        VacuumCleaner vc;
        try {
            Wrapper c = Objects.requireNonNull(Filtering.fillRelevantFields(map));
            vc = new VacuumCleaner(c.getTitle(), c.getCountryOfManufacturer(), c.getManufacturer(),
                    c.isOnlineOrderingPossibility(), c.isInstallmentPlanPossibility(), c.getSerialNumber(),
                    c.getColor(), c.getSize(), c.getPrice(), 0, 0, c.isAvailability());
        } catch (NullPointerException e) {
            return response = "Error, check input parameters";
        }

        int dustContainerVolume;
        if (map.get("dustContainerVolume") != null && !map.get("dustContainerVolume").isEmpty()) {
            try {
                dustContainerVolume = Integer.parseInt(map.get("dustContainerVolume"));
            } catch (NumberFormatException e) {
                return response = "Error in dustContainerVolume field";
            }
        } else {
            return response = "absent dustContainerVolume field";
        }
        int modesCount = 0;
        if (map.get("modesCount") != null && !map.get("modesCount").isEmpty()) {
            try {
                modesCount = Integer.parseInt(map.get("modesCount"));
            } catch (NumberFormatException e) {
                return response = "Error in modesCount field";
            }
        } else {
            return response = "absent modesCount field";
        }
        vc.setDustContainerVolume(dustContainerVolume);
        vc.setModesCount(modesCount);
        VacuumCleaner sv = vacuumCleanerRepository.save(vc);
        if (sv.equals(vc)) {
            response = "success";
        }
        return response;
    }
}
