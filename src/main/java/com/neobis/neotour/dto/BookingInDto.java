package com.neobis.neotour.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingInDto {
    private String phone;
    private String comment;
    private Integer peopleAmount;
    private Long tripId;
}
