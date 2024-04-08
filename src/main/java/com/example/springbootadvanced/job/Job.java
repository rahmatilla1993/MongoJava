package com.example.springbootadvanced.job;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    private Long id;
    private String title;
    private Long minSalary;
    private Long maxSalary;
}
