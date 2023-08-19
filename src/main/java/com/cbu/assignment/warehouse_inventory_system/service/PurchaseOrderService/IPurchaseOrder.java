package com.cbu.assignment.warehouse_inventory_system.service.PurchaseOrderService;

import java.util.List;

import com.cbu.assignment.warehouse_inventory_system.Model.PurchaseOrder;
import com.cbu.assignment.warehouse_inventory_system.web.dto.PurchaseOrderDto;

public interface IPurchaseOrder {
   void AddPurchaseOrder(PurchaseOrderDto purchaseOrderDto);
   Boolean UpdateProductVerification(Long id);

   List<PurchaseOrder> getAllUnverifiedPurchaseOrders();
   List<PurchaseOrder> getAllVerifiedPurchaseOrders();

   // receiving clerk methods
   PurchaseOrder getPuchaseOrder(Long id);

   // stocking method
   Boolean UpdateProductStocked(Long id);


}
