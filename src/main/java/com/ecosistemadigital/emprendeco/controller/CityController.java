package com.ecosistemadigital.emprendeco.controller;

import com.ecosistemadigital.emprendeco.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecosistemadigital.emprendeco.service.ICityService;


import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    private final ICityService iCityService;

    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city) {
        return ResponseEntity.ok(iCityService.save(city));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> city = iCityService.findById(id);

        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody City city) {
        iCityService.update(city);
        return ResponseEntity.ok("El proyecto se actualizó correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        iCityService.delete(id);
        return ResponseEntity.ok("El proyecto se eliminó correctamente");
    }

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok(iCityService.findAll());
    }
}
