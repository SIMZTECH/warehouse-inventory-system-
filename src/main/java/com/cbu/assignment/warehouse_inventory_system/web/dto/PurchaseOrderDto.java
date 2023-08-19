package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class PurchaseOrderDto {

    private Integer Quantity;
    private double unitPrice;
    private double totalPrice;
    private String productType;
    private Boolean productVerification;
    private Long productId;


    // contsructor
    public PurchaseOrderDto() {
    }


    public PurchaseOrderDto(Integer Quantity, double unitPrice, double totalPrice, String productType, Boolean productVerification, Long productId) {
        this.Quantity = Quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.productType = productType;
        this.productVerification = productVerification;
        this.productId = productId;
    }
    

    // getters and setters

    public Integer getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Boolean getProductVerification() {
        return this.productVerification;
    }

    public void setProductVerification(Boolean productVerification) {
        this.productVerification = productVerification;
    }

     public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
