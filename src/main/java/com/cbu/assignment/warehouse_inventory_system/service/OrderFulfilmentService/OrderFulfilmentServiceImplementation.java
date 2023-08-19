package com.cbu.assignment.warehouse_inventory_system.service.OrderFulfilmentService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage.CustomerOrderTemp;
import com.cbu.assignment.warehouse_inventory_system.Model.OrderFulfilmentPackage.OrderFulfilment;
import com.cbu.assignment.warehouse_inventory_system.config.AuthenticatedUser;
import com.cbu.assignment.warehouse_inventory_system.repository.OrderFulfilmentRepo.OrderFulfilmentRepo;
import com.cbu.assignment.warehouse_inventory_system.service.UserService;
import com.cbu.assignment.warehouse_inventory_system.service.productsService.ProductService;
import com.cbu.assignment.warehouse_inventory_system.web.dto.CustomerOderDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.TemporalCustomerOrderDto;

@Service
public class OrderFulfilmentServiceImplementation implements IOrderFulfilmentService {

    private OrderFulfilmentRepo orderFulfilmentRepo;
    private ProductService productService;
    private UserService userService;


    public OrderFulfilmentServiceImplementation(OrderFulfilmentRepo orderFulfilmentRepo, ProductService productService, UserService userService) {
        this.orderFulfilmentRepo = orderFulfilmentRepo;
        this.productService = productService;
        this.userService = userService;
    }


    @Override
    public Boolean saveCustomerOrder(CustomerOderDto customerOderDto,String userEmail) {
        // TODO Auto-generated method stub
        var orderFulfilment= new OrderFulfilment();
        List <CustomerOrderTemp> customerOrderTemps = new ArrayList<>();
        for (TemporalCustomerOrderDto t : customerOderDto.getTemporalCustomerOrderDtos()) {
            var product = this.productService.getProductById(t.getProductId());

            customerOrderTemps.add(new CustomerOrderTemp(t.getProductQuantity(), product));
        }

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        var foundUser = userService.getUserByEmail(authenticatedUser.getUserEmail());

        orderFulfilment.setCustomerOrderTemp(customerOrderTemps);
        orderFulfilment.setUser(foundUser);
        orderFulfilment.setStatus("PENDING");

        this.orderFulfilmentRepo.save(orderFulfilment);

        return true;
    }

    @Override
    public OrderFulfilment getByID(Long id) {
        // TODO Auto-generated method stub
        return orderFulfilmentRepo.findById(id).get();
    };

    @Override
    public List<OrderFulfilment> getAllOrderByStatus(String status) {
        return orderFulfilmentRepo.findAllByStatus(status);
    }


    @Override
    public Boolean UpdateProductQuantity(Long id) {
        // TODO Auto-generated method stub
        // TODO:Search Products by ID
        var orderFulfillment = this.getByID(id);

        for (CustomerOrderTemp t : orderFulfillment.getCustomerOrderTemp()) {
            // update product content
            productService.UpdateQuantity(t.getProduct().getId(), t.getQuantity().intValue(), "remove");
        }
        
        orderFulfillment.setStatus("FULFILLED");

        this.orderFulfilmentRepo.save(orderFulfillment);
        return true;
    }


    @Override
    public List<OrderFulfilment> getOrdersByCustomer() {
        // TODO Auto-generated method stub
         AuthenticatedUser authenticatedUser = new AuthenticatedUser();

        var foundUser = userService.getUserByEmail(authenticatedUser.getUserEmail());

        return this.orderFulfilmentRepo.findAllByUser(foundUser);



    };

    
}
