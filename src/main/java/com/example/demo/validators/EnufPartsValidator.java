package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static ApplicationContext myContext;

    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if (context == null) return true;
        if (context != null) myContext = context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);

        // Get the updated product from the form
        Product myProduct = repo.findById((int) product.getId());

        // Check if the product is being updated and has associated parts
        if (myProduct != null) {
            // Calculate the change in product inventory
            int productInvChange = product.getInv() - myProduct.getInv();

            // If the product inventory is increasing, validate part inventory
            if (productInvChange > 0) {
                for (Part p : myProduct.getParts()) {
                    // Check if the new part inventory would fall below the minimum
                    if (p.getInv() - productInvChange < p.getMinInventory()) {
                        constraintValidatorContext.disableDefaultConstraintViolation();
                        constraintValidatorContext.buildConstraintViolationWithTemplate("Adding this product would lower the part inventory below the minimum.").addConstraintViolation();
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
