package com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage;

import com.cbu.assignment.warehouse_inventory_system.Model.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CustomerOrderTemp {
    private Long quantity;

    // joing this table with User table
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    // methods

    public CustomerOrderTemp() {
    };


    public CustomerOrderTemp(Long quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    };


    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


};
