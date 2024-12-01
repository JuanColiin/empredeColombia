package com.ecosistemadigital.emprendeco.repository;

import com.ecosistemadigital.emprendeco.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}
