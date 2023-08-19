package com.cbu.assignment.warehouse_inventory_system.service.PurchaseOrderService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cbu.assignment.warehouse_inventory_system.Model.Product;
import com.cbu.assignment.warehouse_inventory_system.Model.PurchaseOrder;

import com.cbu.assignment.warehouse_inventory_system.repository.PurchaseOrderRepository.PurchaseOrderRepo;
import com.cbu.assignment.warehouse_inventory_system.service.productsService.ProductService;
import com.cbu.assignment.warehouse_inventory_system.web.dto.PurchaseOrderDto;

@Service
public class PurchaseOrderServiceImplementation implements IPurchaseOrder{

    private PurchaseOrderRepo purchaseOrderRepo;
    private ProductService productService;


    public PurchaseOrderServiceImplementation(PurchaseOrderRepo purchaseOrderRepo, ProductService productRepo) {
        this.purchaseOrderRepo = purchaseOrderRepo;
        this.productService = productRepo;
    }

    
    @Override
    public void AddPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        var product = productService.getProductById(purchaseOrderDto.getProductId());

        var _purchaseOrderObject=new PurchaseOrder(
            purchaseOrderDto.getQuantity(),
            purchaseOrderDto.getUnitPrice(),
            purchaseOrderDto.getTotalPrice(),
            product,
            purchaseOrderDto.getProductType(),
            false,
            false
        );

        this.purchaseOrderRepo.save(_purchaseOrderObject);
        
    };

    @Override
    public Boolean UpdateProductVerification(Long id) {
        var purchaseOrderOptional =  this.purchaseOrderRepo.findById(id);
        if(!purchaseOrderOptional.isPresent()){
           return false;
        }

        var purchaseOrder = purchaseOrderOptional.get();
        purchaseOrder.setProductVerification(true);
        purchaseOrderRepo.save(purchaseOrder);

        return true;
    };


    @Override
    public List<PurchaseOrder> getAllUnverifiedPurchaseOrders() {
        return this.purchaseOrderRepo.findAllProductsByProductVerification(false);
    };

    


    @Override
    public PurchaseOrder getPuchaseOrder(Long id) {
        var res = purchaseOrderRepo.findById(id);
        if(res.isEmpty()){
            return null;
        }
        return res.get(); 
    }


    @Override
    public List<PurchaseOrder> getAllVerifiedPurchaseOrders() {
        var productVerificationList =  this.purchaseOrderRepo.findAllProductsByProductVerification(true);
        List<PurchaseOrder> productOrderList = new ArrayList<>();

        for (PurchaseOrder item : productVerificationList) {
            if(!item.getIsProductStocked() && item.getProductType().equals("NEW_STOCK")){
                productOrderList.add(item);
            }
        }
        return productOrderList;
    }


    @Override
    public Boolean UpdateProductStocked(Long id) {
        // TODO Auto-generated method stub
        var res = getPuchaseOrder(id);

        res.setIsProductStocked(true);

        purchaseOrderRepo.save(res);

        productService.UpdateQuantity(res.getProduct().getId(), res.getQuantity(), "add");

        return true;
       
    };
    
}
