package com.sns.sns.domain.post.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.*;

public record DailyPostCountRequest(
        Long memberId,
        @DateTimeFormat(iso = DATE)
        LocalDate firstDate,
        @DateTimeFormat(iso = DATE)
        LocalDate lastDate){
}
