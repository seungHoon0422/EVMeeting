package com.ssafy.statistics.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GenderDto {
    int totalCount;
    int MaleCount;
    int FemaleCount;
    double MalePercent;
    double FemalePercent;
}
