package com.musala.drone.data.common.repository;

import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

public abstract class CustomRepositoryImpl {

    protected EntityManager entityManager;

    protected CriteriaBuilder criteriaBuilder;

    private Integer topPageMaxResult = 500;

    private Integer topListMaxResult = 5000;


    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Integer getPageMaxResult(Pageable pageable){

        return pageable.getPageSize() <= this.topPageMaxResult ?  pageable.getPageSize() : topPageMaxResult;
    }

    public Integer getListMaxResult(Integer maxResult){

        return (maxResult == null || maxResult == 0 ||  maxResult > this.topListMaxResult) ? this.topListMaxResult : maxResult;
    }

    public String formatLikeStringSearch(String search){

        search = search.trim().toLowerCase();

        String[] strings = search.trim().split(" ");

        String stringSearch = "%";

        for (String s: strings) {

            stringSearch += s + "%";
        }
        return  stringSearch;
    }
}
