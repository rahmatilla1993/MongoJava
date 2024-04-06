package com.example.springbootadvanced.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
}
