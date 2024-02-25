package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.SmartPhone;
import com.equipmentregistry.application.models.StaffEntity;
import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.repositories.TvRepository;
import com.equipmentregistry.application.utils.Filtering;
import com.equipmentregistry.application.utils.Wrapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.*;

@Service
public class TvService {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceContext
    private Session session;

    private final TvRepository tvRepository;

    public TvService(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }


    public List<Tv> findAllTv() {
        return tvRepository.findAll();
    }

    public boolean saveNewTv(Tv newTv) {
        tvRepository.save(newTv);
        return true;
    }

    public List<Tv> findByTitle(String title) {
        if(title != null && !title.isEmpty()) {
            return tvRepository.findAllByTitleContainsIgnoreCaseAndAvailabilityIsTrue(title);
        }else{
            return tvRepository.findAll();
        }
    }

    /**
     * реализовать поиск по наименованию,
     * вне зависимости от регистра, а также реализовать фильтрацию по виду техники,
     * цвету, цене (от/до).
     * Остальные фильтры сделать зависимыми от выбора вида техники
     * и фильтровать по атрибутам моделей.
     */



    public Optional<Tv> findById(Long id) {
        return tvRepository.findById(id);
    }


    public StaffEntity findByTitleAndParameters(Map<String, String> paramsMap) {
        List<Tv> resultList;
        List<Tv> tvList;
        if (!paramsMap.isEmpty() && paramsMap.containsKey("title")) {
            tvList = findByTitle(paramsMap.get("title"));
            resultList = filterByParameters(tvList, paramsMap);
        } else {
            tvList = tvRepository.findAllByAvailabilityIsTrue();
            resultList = filterByParameters(tvList, paramsMap);
        }
        StaffEntity se = new StaffEntity();
        se.setTitle("tv");
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
        se.setEntities(resultList);
        return se;
    }

    List<Tv> filterByParameters(List<Tv> input, Map<String, String> paramsMap) {
        List<Tv> result = new ArrayList<>();
        for (Tv t : input) {
            boolean sameColor = false;
            boolean sameSize = false;
            boolean samePrice = false;
            boolean sameCategory = false;
            boolean sameTechnology = false;
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
            if (paramsMap.get("category") != null && t.getCategory().contains(paramsMap.get("category"))) {
                sameCategory = true;
            }
            if (paramsMap.get("technology") != null && t.getTechnology().contains(paramsMap.get("technology"))) {
                sameTechnology = true;
            }
            if (paramsMap.get("countryOfManufacturer") != null && paramsMap.get("countryOfManufacturer").equalsIgnoreCase(t.getCountryOfManufacturer())) {
                sameCountryOfManufacturer = true;
            }
            if (paramsMap.get("manufacturer") != null && paramsMap.get("manufacturer").equalsIgnoreCase(t.getManufacturer())) {
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
                    (paramsMap.get("technology") == null || sameTechnology) &&
                    (paramsMap.get("countryOfManufacturer") == null || sameCountryOfManufacturer) &&
                    (paramsMap.get("manufacturer") == null || sameManufacturer) &&
                    (paramsMap.get("onlineOrderingPossibility") == null || onlineOrderingPossibility) &&
                    (paramsMap.get("installmentPlanPossibility") == null || installmentPlanPossibility)
            ) result.add(t);
        }
        return result;
    }

    public String addNewTv(Map<String, String> map) {
        String response = "error";
        Tv tv;
        try {
            Wrapper c = Objects.requireNonNull(Filtering.fillRelevantFields(map));
            tv = new Tv(c.getTitle(), c.getCountryOfManufacturer(), c.getManufacturer(),
                    c.isOnlineOrderingPossibility(), c.isInstallmentPlanPossibility(), c.getSerialNumber(),
                    c.getColor(), c.getSize(), c.getPrice(), "0", "0", c.isAvailability());
        } catch (NullPointerException e) {
            return response = "Error, check input parameters";
        }

        String category;
        if (map.get("category") != null && !map.get("category").isEmpty()) {
                category = map.get("category");
        }else{
            return response = "absent category field";
        }
        int memorySize = 0;
        String technology;
        if (map.get("technology") != null && !map.get("technology").isEmpty()) {
            technology = map.get("technology");
        }else{
            return response = "absent technology field";
        }
        tv.setCategory(category);
        tv.setTechnology(technology);
        Tv st = tvRepository.save(tv);
        if (st.equals(tv)) {
            response = "success";
        }
        return response;
    }
}
