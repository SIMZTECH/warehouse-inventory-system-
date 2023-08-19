package com.cbu.assignment.warehouse_inventory_system.repository.ProductRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbu.assignment.warehouse_inventory_system.Model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    
    
};
