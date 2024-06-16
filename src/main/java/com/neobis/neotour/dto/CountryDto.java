package com.neobis.neotour.dto;

import com.neobis.neotour.enums.Continent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDto {
    private Long id;
    private String name;
    private Continent continent;
    private Boolean deleted;
    private LocalDateTime createdAt;
}
