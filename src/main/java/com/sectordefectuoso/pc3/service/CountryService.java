package com.sectordefectuoso.pc3.service;

import com.sectordefectuoso.pc3.model.Country;
import com.sectordefectuoso.pc3.repository.JdbcCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements GenericService<Country, Long> {
    @Autowired
    JdbcCountryRepository repository;
    @Override
    public void create(Country country) {
        repository.create(country);
    }

    @Override
    public void update(Country country) {
        repository.update(country);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public List<Country> findAll() {
        try {
            return repository.findAll();
        }catch (Exception ex){
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public Country findById(Long id) {
        return repository.findById(id);
    }
}
