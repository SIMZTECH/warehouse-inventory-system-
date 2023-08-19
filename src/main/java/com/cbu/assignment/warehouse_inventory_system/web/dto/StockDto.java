package com.cbu.assignment.warehouse_inventory_system.web.dto;

public class StockDto {

    private Long productID;
    private int productQuantity;



    public StockDto() {
    }


    public StockDto(Long productID, int productQuantity) {
        this.productID = productID;
        this.productQuantity = productQuantity;
    }


    public Long getProductID() {
        return this.productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public int getProductQuantity() {
        return this.productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


}
