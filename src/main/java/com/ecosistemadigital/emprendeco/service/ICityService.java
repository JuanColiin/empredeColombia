package com.ecosistemadigital.emprendeco.service;

import com.ecosistemadigital.emprendeco.entity.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    City save (City project);
    Optional<City> findById(Long id);
    void  update(City city);
    void delete(Long id) throws Exception;
    List<City> findAll();
}
