package com.ecosistemadigital.emprendeco.service.impl;

import com.ecosistemadigital.emprendeco.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecosistemadigital.emprendeco.repository.ICityRepository;
import com.ecosistemadigital.emprendeco.service.ICityService;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CityService implements ICityService {

    private final ICityRepository cityRepository;


    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void update(City city) {
        cityRepository.save(city);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<City> cityTolookFor = cityRepository.findById(id);
        if (cityTolookFor.isPresent()) {
            cityRepository.delete(cityTolookFor.get());
        } else {
            throw new Exception("No existe la ciudad con ese id");
        }

    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
