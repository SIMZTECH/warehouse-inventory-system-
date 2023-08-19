package com.cbu.assignment.warehouse_inventory_system.web.receivingClerk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.Model.PurchaseOrder;
import com.cbu.assignment.warehouse_inventory_system.service.PurchaseOrderService.PurchaseOrderServiceImplementation;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductVerificationDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.PurchaseOrderDto;

@Controller
@RequestMapping("/receivingClerk")
public class ReceivingClerkController {
    // class attributes and methods
    private PurchaseOrderServiceImplementation purchaseOrderService;

    public ReceivingClerkController(PurchaseOrderServiceImplementation purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/")
    public String getRecievingClerkDashbord(Model model){
        model.addAttribute("unverifiedProducts", purchaseOrderService.getAllUnverifiedPurchaseOrders());
        return "receiving-clerk/index";
    };

    @GetMapping("/confirm/{id}")
    public String getConfirmProduct(Model model , @PathVariable Long id){
        model.addAttribute("purchaseOrder",purchaseOrderService.getPuchaseOrder(id));
        model.addAttribute("confirm", new ProductVerificationDto());
        return "receiving-clerk/confirm";
    };

    @PostMapping("comfirm-db")
    public String postConfirmProduct(@ModelAttribute("confirm") ProductVerificationDto productVerificationDto){
        if(!purchaseOrderService.UpdateProductVerification(productVerificationDto.getId())){
            return "redirect:/receivingClerk/confirm/{"+productVerificationDto.getId()+"}?Error";
        }
        return "redirect:/receivingClerk/?Success";
    };

    
}
