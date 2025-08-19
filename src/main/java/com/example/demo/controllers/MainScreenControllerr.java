package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainScreenControllerr {
    private PartService partService;
    private ProductService productService;
    private List<Part> theParts;
    private List<Product> theProducts;

    public MainScreenControllerr(PartService partService, ProductService productService){
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @Param("partkeyword") String partkeyword, @Param("productkeyword") String productkeyword){
        List<Part> partList = partService.listAll(partkeyword);
        theModel.addAttribute("parts", partList);
        theModel.addAttribute("partkeyword", partkeyword);
        List<Product> productList = productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword", productkeyword);
        return "mainscreen";
    }
// added the buy product button and logic needed for the usage in the main controller and main page
    @GetMapping("/buyproduct")
    public String buyProduct(@RequestParam("productID") int theId, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(theId);

        if (product != null) {
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);//decrement inventory by 1
                productService.save(product); //save
                redirectAttributes.addFlashAttribute("message", "Purchase successful! Enjoy your new surfboard!");
            } else {// messages for success and fail
                redirectAttributes.addFlashAttribute("message", "Purchase failed. Product is out of stock.");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found. Please try again.");
        }

        return "redirect:/mainscreen";
    }
}