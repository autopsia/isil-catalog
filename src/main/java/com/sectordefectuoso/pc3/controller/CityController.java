package com.sectordefectuoso.pc3.controller;

import com.sectordefectuoso.pc3.model.City;
import com.sectordefectuoso.pc3.service.CityService;
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

    @GetMapping("/city")
    public String getList(Model model) {
        List<City> citys = cityService.findAll();
        model.addAttribute("citys", citys);
        return "city";
    }

    @PostMapping("/city/save")
    public String save(City city, Model model) {
        cityService.create(city);
        return "city";
    }

    @GetMapping("/citys/add")
    public String add(Model model) {
        model.addAttribute("city", new City());
        return "city-add";
    }

    @GetMapping("/city/edit/{id}")
    public String getForUpdate(@PathVariable String id, Model model) {
        City currentCity = cityService.findById(id);
        model.addAttribute("city", currentCity);
        return "city-edit";
    }

    @PostMapping("/city/update/{id}")
    public String update(@PathVariable String id, City city, Model model) {
        cityService.update(city);
        return "city";
    }

    @GetMapping("/city/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        cityService.delete(Long.parseLong(id));
        return "city";
    }
}
