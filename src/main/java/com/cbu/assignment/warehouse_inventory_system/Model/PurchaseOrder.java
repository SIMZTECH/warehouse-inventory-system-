package com.cbu.assignment.warehouse_inventory_system.Model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="purchaseOrder")
public class PurchaseOrder extends OrderBase{
    // class attributes
    private String productType;
    private Boolean productVerification;
    @Column(columnDefinition = "boolean default false")
    private Boolean isProductStocked;

   
   

    public PurchaseOrder( Integer Quantity, double unitPrice, double totalPrice, Product product,String productType, Boolean productVerification, Boolean isProductStocked) {
        super(null, Quantity,unitPrice, totalPrice, product);
        this.productType = productType;
        this.productVerification = productVerification;
        this.isProductStocked = isProductStocked;
        
    }

    public PurchaseOrder() {
    }

    // getters and setters

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Boolean isProductVerification() {
        return this.productVerification;
    }

    public Boolean getProductVerification() {
        return this.productVerification;
    }

    public void setProductVerification(Boolean productVerification) {
        this.productVerification = productVerification;
    }

     public Boolean getIsProductStocked() {
        return this.isProductStocked;
    }

    public void setIsProductStocked(Boolean isProductStocked) {
        this.isProductStocked = isProductStocked;
    }

}
