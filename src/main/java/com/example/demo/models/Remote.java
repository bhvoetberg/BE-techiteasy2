package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "remotes")

public class Remote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String compatibleWidth;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompatibleWidth() {
        return compatibleWidth;
    }

    public void setCompatibleWidth(String compatibleWidth) {
        this.compatibleWidth = compatibleWidth;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

}
