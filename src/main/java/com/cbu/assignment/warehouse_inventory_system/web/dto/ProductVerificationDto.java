package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class ProductVerificationDto {
    private Long id;


    public ProductVerificationDto() {
    }


    public ProductVerificationDto(Long id) {
        this.id = id;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
}
