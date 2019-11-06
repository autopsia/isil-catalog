package com.sectordefectuoso.pc3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country {
    private Long countryId;
    private String name;
    private List<City> cities;

    public Country(Long countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }
}
