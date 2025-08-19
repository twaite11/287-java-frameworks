package com.example.demo.validators;

import com.example.demo.domain.Part;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartInventoryValidator implements ConstraintValidator<ValidPartInventory, Part> {

    @Override
    public void initialize(ValidPartInventory constraintAnnotation) {
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part.getInv() < part.getMinInventory()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory is below the minimum allowed value.").addPropertyNode("inv").addConstraintViolation();
            return false;
        }
        if (part.getInv() > part.getMaxInventory()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory exceeds the maximum allowed value.").addPropertyNode("inv").addConstraintViolation();
            return false;
        }
        return true;
    }
}