package com.equipmentregistry.application.services;

import com.equipmentregistry.application.models.Tv;
import com.equipmentregistry.application.repositories.TvRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return tvRepository.findAllByTitleContainsIgnoreCase(title);
    }

    /**
     * реализовать поиск по наименованию,
     * вне зависимости от регистра, а также реализовать фильтрацию по виду техники,
     * цвету, цене (от/до).
     * Остальные фильтры сделать зависимыми от выбора вида техники
     * и фильтровать по атрибутам моделей.
     * @param paramsMap
     * @return
     */
    //    SELECT a FROM EntityName a WHERE LOWER(a.payeeName) LIKE :searchpayeename
    public List getTvEntitiesByParamsMap(Map<String, String> paramsMap) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Tv FROM Tv e WHERE 1=1 ");
        if (paramsMap.get("title") != null) {
            sql.append("AND LOWER(e.title) LIKE " + paramsMap.get("title").toLowerCase() +" ");
            params.put("title", "%:" + paramsMap.get("title") + "%");
        }
        if (paramsMap.get("color") != null) {
//            sql.append("AND e.color > :color ");
            sql.append("AND e.color ILIKE " + paramsMap.get("color") +" ");
            params.put("color", paramsMap.get("color"));
        }
        sql.append("ORDER BY e.title DESC");


//        Query query = entityManager.createNativeQuery(sql.toString(), String.valueOf(Tv.class));
//        for (Map.Entry<String, Object> param : params.entrySet()) {
//            query.setParameter(param.getKey(), param.getValue());
//        }

        //todo add if statements
        Query query = session.createQuery("from Tv where title = :title and color = :color");
        query.setParameter("title", paramsMap.get("title"));
        query.setParameter("color", paramsMap.get("color"));
        List list = query.getResultList();




//        CriteriaBuilder builder = session.getCriteriaBuilder();
//// Create CriteriaQuery
//        CriteriaQuery<Tv> criteria = builder.createQuery(Tv.class);


        //   where lower(f.question) like lower(:query)
        String hql = "FROM Tv WHERE lower(title) = :title AND color = :color";
        Query query1 = session.createQuery(hql);
        query1.setParameter("title", paramsMap.get("title").toLowerCase());
        query1.setParameter("color", paramsMap.get("color"));
        List list2 = query1.getResultList();
        System.out.println("LIST2 === ");
        for (Object t: list2) {
            Tv tv00 = (Tv)t;
            System.out.println(tv00.getTitle());
        }




        return list2;
    }


    public Optional<Tv> findById(Long id) {
        return tvRepository.findById(id);
    }
}
