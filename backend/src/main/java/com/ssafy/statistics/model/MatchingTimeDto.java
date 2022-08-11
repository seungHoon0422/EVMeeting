package com.ssafy.statistics.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchingTimeDto {
    int time;
    int matchingCount;
    double matchingPercent;
}
