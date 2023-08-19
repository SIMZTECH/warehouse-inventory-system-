package com.cbu.assignment.warehouse_inventory_system.web.OrderFulfilment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.service.OrderFulfilmentService.OrderFulfilmentServiceImplementation;
import com.cbu.assignment.warehouse_inventory_system.web.dto.OrderFulfillmentDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductVerificationDto;



@Controller
@RequestMapping("/OrderFulfilment")
public class OrderFulfilmentController {
    // class attributes and methods
    private OrderFulfilmentServiceImplementation orderFulfilmentServiceImplementation;


    public OrderFulfilmentController(OrderFulfilmentServiceImplementation orderFulfilmentServiceImplementation) {
        this.orderFulfilmentServiceImplementation = orderFulfilmentServiceImplementation;
    }


    @GetMapping("/")
    public String GetOrderFulfilmentPage(Model model){
        model.addAttribute("orderFulfill", orderFulfilmentServiceImplementation.getAllOrderByStatus("PENDING"));

        return "orderFulfilment/index";
    };

    @GetMapping("/Confirm/{id}")
    public String GetConfirmPage(@PathVariable Long id, Model model ){
        model.addAttribute("orderFulfillment", orderFulfilmentServiceImplementation.getByID(id));
        model.addAttribute("Confirm",new OrderFulfillmentDto());
        return "orderFulfilment/confirmOrder";
    };

    @PostMapping("/Confirm-Order-fulfiled-db")
    public String ConfirmOrderFulfilment(@ModelAttribute("Confirm") OrderFulfillmentDto orderFulfillmentDto){
        // TODO::order fulfilment logic
        if(this.orderFulfilmentServiceImplementation.UpdateProductQuantity(orderFulfillmentDto.getId())){

            return "redirect:/OrderFulfilment/?Success";
        }

        return "redirect:/OrderFulfilment/Confirm/"+orderFulfillmentDto.getId()+ "/?Error";

    };


};