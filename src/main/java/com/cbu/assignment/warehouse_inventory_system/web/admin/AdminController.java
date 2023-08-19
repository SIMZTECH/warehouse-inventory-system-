package com.cbu.assignment.warehouse_inventory_system.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbu.assignment.warehouse_inventory_system.Model.Product;
import com.cbu.assignment.warehouse_inventory_system.Model.User;
import com.cbu.assignment.warehouse_inventory_system.service.UserService;
import com.cbu.assignment.warehouse_inventory_system.service.PurchaseOrderService.PurchaseOrderServiceImplementation;
import com.cbu.assignment.warehouse_inventory_system.service.productsService.ProductService;
import com.cbu.assignment.warehouse_inventory_system.web.dto.ProductDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.PurchaseOrderDto;
import com.cbu.assignment.warehouse_inventory_system.web.dto.UserRegistrationDto;
import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {

     private UserService userService;
     private ProductService _productService;
     private PurchaseOrderServiceImplementation _PurchaseOrderServiceImplementation;

     public AdminController(UserService userService, ProductService productservice, PurchaseOrderServiceImplementation purchaseOrderServiceImplementation){
        super();
        this.userService = userService;
        this._productService = productservice;
        this._PurchaseOrderServiceImplementation = purchaseOrderServiceImplementation;
     }


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @ModelAttribute("employees")
    public List<User> GetAllEmployees(){
        return userService.getAllUsers();
    }


    @GetMapping("/")
    public String GetAdminHomePage(){
        return "admin/index";
    }


    @GetMapping("/add-employee")
    public String GetEmployeeFormPage(){
        return "admin/add-employee";
    }

    @PostMapping("add-employee-db")
    public String AddEmployee(@ModelAttribute("user") UserRegistrationDto userRegistrationDto){
        if(userService.findUserByEmail(userRegistrationDto.getEmail())){
             return "redirect:/admin/add-employee?errorEmail";
        }
         this.userService.save(userRegistrationDto);
         return "redirect:/admin/add-employee?success";
    }

    // add purchase order
    @GetMapping("add-purchase-orders")
    public String GetPurchaseOrderPage(Model model){
        model.addAttribute("products", this._productService.getAllProducts());
        model.addAttribute("purchaseOrder", new PurchaseOrderDto());
        return "admin/add-purchase-orders";
    }

    // post purchase order
    @PostMapping("add-purchase-orders-db")
    public String AddProduct(@ModelAttribute("purchaseOrder") PurchaseOrderDto purchaseOrderDto){
        this._PurchaseOrderServiceImplementation.AddPurchaseOrder(purchaseOrderDto);
        return "redirect:/admin/add-purchase-orders?success";
    }


    @GetMapping("add-product")
    public String GetPurchaseFormPage(Model model){
        model.addAttribute("product", new ProductDto());
        return "admin/add-product";
    }

    @PostMapping("add-product-db")
    public String AddProduct(@ModelAttribute("product") ProductDto product){
        // try {
            this._productService.AddProducts(product);
            return "redirect:/admin/add-product?success";
    }

    
}
