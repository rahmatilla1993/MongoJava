package com.example.springbootadvanced.product;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@ParameterObject
public class ProductCriteria {
    @Parameter(name = "page")
    private Long page = 0L;
    @Parameter(name = "size")
    private Long size = 5L;
}
