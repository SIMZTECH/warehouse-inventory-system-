package com.cbu.assignment.warehouse_inventory_system.service.productsService;

import java.util.List;
import org.springframework.stereotype.Service;

import com.cbu.assignment.warehouse_inventory_system.Model.Location;
import com.cbu.assignment.warehouse_inventory_system.Model.Product;
import com.cbu.assignment.warehouse_inventory_system.repository.ProductRepository.ProductRepo;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductDto;

@Service
public class ProductService implements IProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    };

    @Override
    public Product AddProducts(ProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getQuantity(),
                productDto.getPrice(),
                new Location(productDto.getLocationName()));

        return productRepo.save(product);
    };

    @Override
    public void UpdateQuantity(Long id, int qty, String control) {
        var res = productRepo.findById(id);
        var product = res.get();

        if(control=="add"){
            product.setQuantity(product.getQuantity() + qty);
        }else{
            product.setQuantity(product.getQuantity() - qty);

        }
        productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        // TODO Auto-generated method stub
        return this.productRepo.findById(id).get();
    }

}
