package com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage;

import java.util.ArrayList;
import java.util.List;

import com.cbu.assignment.warehouse_inventory_system.Model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orderFulfilment")
public class OrderFulfilment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status; 

    @ElementCollection(targetClass = CustomerOrderTemp.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customerOrderTemp", joinColumns = @JoinColumn(name = "orderFulfilment_id"))
    private List<CustomerOrderTemp> customerOrderTemp = new ArrayList<>();

    // joing this table with User table
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    public OrderFulfilment() {
    }


    public OrderFulfilment(Long id, String status, List<CustomerOrderTemp> customerOrderTemp, User user) {
        this.id = id;
        this.status = status;
        this.customerOrderTemp = customerOrderTemp;
        this.user = user;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerOrderTemp> getCustomerOrderTemp() {
        return this.customerOrderTemp;
    }

    public void setCustomerOrderTemp(List<CustomerOrderTemp> customerOrderTemp) {
        this.customerOrderTemp = customerOrderTemp;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


};
