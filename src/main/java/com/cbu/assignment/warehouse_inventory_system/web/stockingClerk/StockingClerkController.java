package com.cbu.assignment.warehouse_inventory_system.web.stockingClerk;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.service.PurchaseOrderService.PurchaseOrderServiceImplementation;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductVerificationDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.StockDto;


@Controller()
@RequestMapping("stockingClerk")
public class StockingClerkController {

    private PurchaseOrderServiceImplementation purchaseOrderServiceImplementation;
    


    public StockingClerkController(PurchaseOrderServiceImplementation purchaseOrderServiceImplementation) {
        this.purchaseOrderServiceImplementation = purchaseOrderServiceImplementation;
    };


    @GetMapping("/")
    public String getStockingClerkDashbord(Model model){
        model.addAttribute("verifiedStock",purchaseOrderServiceImplementation.getAllVerifiedPurchaseOrders());
        return "stocking-clerk/index";
    };

    @GetMapping("/stock/{id}")
    public String getStockingConfirmation(Model model , @PathVariable Long id){
        model.addAttribute("purchaseOrder",purchaseOrderServiceImplementation.getPuchaseOrder(id));
        model.addAttribute("confirm", new StockDto());
        return "stocking-clerk/stock";
    };

    @PostMapping("stocking-db")
    public String stockProducts(@ModelAttribute("confirm") StockDto stockDto){
        if(!purchaseOrderServiceImplementation.UpdateProductStocked(stockDto.getProductID())){

            return "redirect:/stockingClerk/?Error";
        }

        return "redirect:/stockingClerk/?Success";

    };

    // public String postConfirmProduct(@ModelAttribute("confirm") ProductVerificationDto productVerificationDto){
    //     if(!purchaseOrderService.UpdateProductVerification(productVerificationDto.getId())){
    //         return "redirect:/receivingClerk/confirm/{"+productVerificationDto.getId()+"}?Error";
    //     }
    //     return "redirect:/receivingClerk/?Success";
    // };
    
}
