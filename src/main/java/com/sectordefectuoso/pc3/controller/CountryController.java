package com.sectordefectuoso.pc3.controller;

import com.sectordefectuoso.pc3.model.Country;
import com.sectordefectuoso.pc3.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public String getList(Model model) {
        List<Country> countrys = countryService.findAll();
        model.addAttribute("countrys", countrys);
        return "country";
    }

    @PostMapping("/country/save")
    public String save(Country country, Model model) {
        countryService.create(country);
        return "country";
    }

    @GetMapping("/countrys/add")
    public String add(Model model) {
        model.addAttribute("country", new Country());
        return "country-add";
    }

    @GetMapping("/country/edit/{id}")
    public String getForUpdate(@PathVariable String id, Model model) {
        Country currentCountry = countryService.findById(id);
        model.addAttribute("country", currentCountry);
        return "country-edit";
    }

    @PostMapping("/country/update/{id}")
    public String update(@PathVariable String id, Country country, Model model) {
        countryService.update(country);
        return "country";
    }

    @GetMapping("/country/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        countryService.delete(Long.parseLong(id));
        return "country";
    }
}
