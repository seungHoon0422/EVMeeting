package com.ssafy.statistics.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id = null;

    Long userid1;
    Long userid2;

    String date;
    int dateYear;
    int dateMonth;
    int dateDay;

    String startTime;
    int startTimeHour;
    int startTimeMin;
    int startTimeSec;

    int usedTime;

    @ColumnDefault("false")
    Boolean matched;
}
