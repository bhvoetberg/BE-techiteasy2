package com.example.demo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TelevisionRequestDto {

    @NotBlank
    @Size(min=1, max=50)
    private String brand;

    @NotBlank
    @Size(min=1, max=10)
    private String name;

    @NotBlank
    @Size(min=1, max=50)
    private String type;

    @NotBlank
    private Double price;

//    private Double availableSize;
//    private Double refreshRate;
//    private String screenType;
//    private String screenQuality;
//    private Boolean smartTv;
//    private Boolean wifi;
//    private Boolean voiceControl;
//    private Boolean hdr;
//    private Boolean bluetooth;
//    private Boolean ambiLight;
//    private Integer originalStock;
//    private Integer sold;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
