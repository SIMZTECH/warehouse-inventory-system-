package com.cbu.assignment.warehouse_inventory_system.service.productsService;

import java.util.List;

import com.cbu.assignment.warehouse_inventory_system.Model.Product;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductDto;

public interface IProductService {

     List<Product> getAllProducts();
    Product AddProducts(ProductDto productDto);
     void UpdateQuantity(Long id, int qty, String control);
     Product getProductById(Long id);
    
}
