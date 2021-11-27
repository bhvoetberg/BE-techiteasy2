package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;

public class CIModuleRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private Double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
