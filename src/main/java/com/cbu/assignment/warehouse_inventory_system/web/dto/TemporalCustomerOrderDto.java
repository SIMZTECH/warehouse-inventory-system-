package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class TemporalCustomerOrderDto {
    private Long productId;
    private Long productQuantity;

    public TemporalCustomerOrderDto(Long productId, Long productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public TemporalCustomerOrderDto() {
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }  
}
