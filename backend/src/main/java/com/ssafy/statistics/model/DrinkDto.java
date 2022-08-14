package com.ssafy.statistics.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DrinkDto {

    String type;
    int count;
    double percent;
}
