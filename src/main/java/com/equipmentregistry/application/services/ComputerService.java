package com.equipmentregistry.application.services;


import com.equipmentregistry.application.models.Computer;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.repositories.ComputerRepository;
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
public class ComputerService {

    private final ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }


    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    public List<Computer> findByTitle(String title) {
        if(title != null && !title.isEmpty()) {
            return computerRepository.findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(title);
        }else{
            return computerRepository.findAll();
        }
    }

    public StaffEntity findByTitleAndParameters(Map<String, String> paramsMap) {
        List<Computer> resultList;
        List<Computer> computerList;
        if (!paramsMap.isEmpty() && paramsMap.containsKey("title")) {
            computerList = findByTitle(paramsMap.get("title"));
            resultList = filterByParameters(computerList, paramsMap);
        } else {
            computerList = computerRepository.findAllByAvailabilityIsTrue();
            resultList = filterByParameters(computerList, paramsMap);
        }
        return generateStaffEntity(paramsMap, resultList);
    }

    List<Computer> filterByParameters(List<Computer> input, Map<String, String> paramsMap) {
        List<Computer> result = new ArrayList<>();
        for (Computer t : input) {
            boolean sameColor = false;
            boolean sameSize = false;
            boolean samePrice = false;
            boolean sameCategory = false;
            boolean sameProcessorType = false;
            boolean sameCountryOfManufacturer = false;
            boolean sameManufacturer = false;
            boolean onlineOrderingPossibility = false;
            boolean installmentPlanPossibility = false;
            if (paramsMap.get("color") != null && paramsMap.get("color").equalsIgnoreCase(t.getColor())) {
                sameColor = true;
            }
            if (paramsMap.get("size") != null && t.getSize().contains(paramsMap.get("size"))) {
                sameSize = true;
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
            if (paramsMap.get("category") != null && t.getCategory().equals(paramsMap.get("category"))) {
                sameCategory = true;
            }
            if (paramsMap.get("processorType") != null && t.getProcessorType().contains(paramsMap.get("processorType"))) {
                sameProcessorType= true;
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
                    (paramsMap.get("category") == null || sameCategory) &&
                    (paramsMap.get("processorType") == null || sameProcessorType) &&
                    (paramsMap.get("countryOfManufacturer") == null || sameCountryOfManufacturer) &&
                    (paramsMap.get("manufacturer") == null || sameManufacturer) &&
                    (paramsMap.get("onlineOrderingPossibility") == null || onlineOrderingPossibility) &&
                    (paramsMap.get("installmentPlanPossibility") == null || installmentPlanPossibility)
            ) result.add(t);
        }
        return result;
    }

    StaffEntity generateStaffEntity(Map<String, String> paramsMap, List<? extends StaffEntity> staffList){
        StaffEntity se = new StaffEntity();
        se.setTitle("computer");
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

    public String addNewComputer(Map<String, String>map){
        String response = "error";

        Wrapper c;
        try {
            c = Objects.requireNonNull(Filtering.fillRelevantFields(map));
        } catch (NullPointerException e) {
            response = "Error, check input parameters";
            return response;
        }

        String category = "null";
        if (map.get("category") != null && !map.get("category").isEmpty()) {
            category = map.get("category");
        }else{
            return response = "absent category field";
        }
        String processorType = "null";
        if (map.get("processorType") != null && !map.get("processorType").isEmpty()) {
            processorType = map.get("processorType");
        }else{
            return response = "absent processorType field";
        }

        Computer computer = new Computer(c.getTitle(), c.getCountryOfManufacturer(), c.getManufacturer(),
                c.isOnlineOrderingPossibility(), c.isInstallmentPlanPossibility(), c.getSerialNumber(),
                c.getColor(), c.getSize(), c.getPrice(), category, processorType, c.isAvailability());
        Computer pc = computerRepository.save(computer);
        if(pc.equals(computer)) {
            response = "success";
        }
        return response;
    }
}
