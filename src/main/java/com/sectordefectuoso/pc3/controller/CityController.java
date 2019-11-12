package com.sectordefectuoso.pc3.controller;

import com.sectordefectuoso.pc3.model.City;
import com.sectordefectuoso.pc3.model.Country;
import com.sectordefectuoso.pc3.service.CityService;
import com.sectordefectuoso.pc3.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    @GetMapping(value = {"/city","/country/list-cities/"})
    public String getList(Model model) {
        List<City> citys = cityService.findAll();
        model.addAttribute("citys", citys);
        return "city";
    }

    @PostMapping("/city/save")
    public String save(City city, Model model) {
        cityService.create(city);
        getList(model);
        return "city";
    }

    @GetMapping("/city/add")
    public String add(Model model) {
        List<Country> currentCountries = countryService.findAll();
        model.addAttribute("city", new City());
        model.addAttribute("countrys", currentCountries);
        return "city-add";
    }

    @GetMapping("/city/edit/{id}")
    public String getForUpdate(@PathVariable Long id, Model model) {
        City currentCity = cityService.findById(id);
        List<Country> currentCountries = countryService.findAll();
        model.addAttribute("city", currentCity);
        model.addAttribute("countrys", currentCountries);
        return "city-edit";
    }

    @PostMapping("/city/update/{id}")
    public String update(@PathVariable Long id, City city, Model model) {
        city.setCityId(id);
        cityService.update(city);
        return getList(model);
    }

    @GetMapping("/city/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        cityService.delete(id);
        return getList(model);
    }
}
