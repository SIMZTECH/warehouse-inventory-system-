package com.cbu.assignment.warehouse_inventory_system.service.OrderFulfilmentService;

import java.util.List;

import com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage.OrderFulfilment;
import com.cbu.assignment.warehouse_inventory_system.web.dto.CustomerOderDto;

public interface IOrderFulfilmentService {
    // methods
    Boolean saveCustomerOrder(CustomerOderDto customerOderDto, String userEmail);
    OrderFulfilment getByID(Long id);

    List <OrderFulfilment> getAllOrderByStatus(String status);
    Boolean UpdateProductQuantity(Long id);

    List<OrderFulfilment> getOrdersByCustomer();
};
