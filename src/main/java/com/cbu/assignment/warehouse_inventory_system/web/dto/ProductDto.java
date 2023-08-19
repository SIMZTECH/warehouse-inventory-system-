package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class ProductDto {
    // class attribute and methods
    private String name;
    private int quantity;
    private int price;
    private String locationName;

    // class methods

    public ProductDto(String name, int quantity, int price, String location) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.locationName = location;
    }

    public ProductDto() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

}
