package com.neobis.neotour.dto;

import com.neobis.neotour.enums.Continent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDtoIn {
    private String name;
    private Continent continent;
}
