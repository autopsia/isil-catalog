package com.sectordefectuoso.pc3.service;

import com.sectordefectuoso.pc3.model.City;
import com.sectordefectuoso.pc3.repository.JdbcCityRepository;
import com.sectordefectuoso.pc3.repository.JdbcCountryRepository;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CityService implements GenericService<City, String> {

    JdbcCityRepository repository;
    @Override
    public void create(City city) {
        repository.create(city);
    }

    @Override
    public void update(City city) {
        repository.update(city);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<City> findAll() {
        try {
            return repository.findAll();
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public City findById(String id) {
        return repository.findById(Long.parseLong(id));
    }
}
