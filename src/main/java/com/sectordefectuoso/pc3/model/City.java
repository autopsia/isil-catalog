package com.sectordefectuoso.pc3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class City {
    private Long cityId;
    private String  name;
    private Long countryId;
    private String countryName;



}
