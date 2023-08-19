package com.cbu.assignment.warehouse_inventory_system.repository.OrderFulfilmentRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cbu.assignment.warehouse_inventory_system.Model.User;
import com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage.OrderFulfilment;

public interface OrderFulfilmentRepo extends JpaRepository<OrderFulfilment, Long> {
    // Special Querry
 @Query("SELECT o FROM OrderFulfilment o WHERE o.status = :status")
    List<OrderFulfilment> findAllByStatus( @Param("status") String status);

     @Query("SELECT o FROM OrderFulfilment o WHERE o.user = :user")
    List<OrderFulfilment> findAllByUser(@Param("user") User User);
    

};
