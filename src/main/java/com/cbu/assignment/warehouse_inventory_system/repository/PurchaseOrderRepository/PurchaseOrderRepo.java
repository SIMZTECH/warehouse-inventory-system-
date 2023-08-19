package com.cbu.assignment.warehouse_inventory_system.repository.PurchaseOrderRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cbu.assignment.warehouse_inventory_system.Model.PurchaseOrder;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {

    @Query("SELECT p FROM PurchaseOrder p WHERE p.productVerification = :productVerification")
    List<PurchaseOrder> findAllProductsByProductVerification(@Param("productVerification") Boolean value);
    
}
