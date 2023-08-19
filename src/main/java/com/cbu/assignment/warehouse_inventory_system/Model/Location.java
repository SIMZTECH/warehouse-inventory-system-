package com.cbu.assignment.warehouse_inventory_system.Model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String location_name;
    // list of products
    // private Collection<StockProducts> listofProducts;
    // TODO: join the table


    public Location() {
    }

   

     public Location(String location_name) {
        this.location_name = location_name;
    }


    public Long getId() {
        return this.id;
    }

    public String getLocation_name() {
        return this.location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
 
}
