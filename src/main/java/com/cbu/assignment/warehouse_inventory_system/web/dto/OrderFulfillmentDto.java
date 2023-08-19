package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class OrderFulfillmentDto {
    private Long id;


    public OrderFulfillmentDto() {
    };


    public OrderFulfillmentDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    };

    public void setId(Long id) {
        this.id = id;
    };

    
}
