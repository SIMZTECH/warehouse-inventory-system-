package com.cbu.assignment.warehouse_inventory_system.web.customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.service.OrderFulfilmentService.OrderFulfilmentServiceImplementation;
import com.cbu.assignment.warehouse_inventory_system.service.productsService.ProductService;
import com.cbu.assignment.warehouse_inventory_system.web.dto.CustomerOderDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.TemporalCustomerOrderDto;

@Controller
@RequestMapping("/customer")
public class CusomerController {

    private ProductService productService;
    private OrderFulfilmentServiceImplementation orderFulfilmentServiceImplementation;
    protected Log logger = LogFactory.getLog(this.getClass());

    public CusomerController(ProductService productService, OrderFulfilmentServiceImplementation orderFulfilmentServiceImplementation) {
        this.productService = productService;
        this.orderFulfilmentServiceImplementation = orderFulfilmentServiceImplementation;
    }

   

    @GetMapping("/")
    public String getCustomerDashboard(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "customer/index";
    };

    
    @GetMapping("/cart")
    public String getCartPage(Model model){
        model.addAttribute("Confirm",new CustomerOderDto());
        return "customer/cart";
    };

    
    @PostMapping("/post-order-db")
    public String postProducts(@ModelAttribute("Confirm") CustomerOderDto customerOderDto ){
        orderFulfilmentServiceImplementation.saveCustomerOrder(customerOderDto,"");

        // logger.info(customerOderDto);
        // for (TemporalCustomerOrderDto t :customerOderDto.getTemporalCustomerOrderDtos()) {
        //     logger.info(t.getProductId());
        // }
        return "redirect:/customer/cart?Success";
    };

    @GetMapping("/order")
    public String getOrderStatusPage(Model model){
        model.addAttribute("orderFulfill",orderFulfilmentServiceImplementation.getOrdersByCustomer());
        return "customer/orders";
    };



};
