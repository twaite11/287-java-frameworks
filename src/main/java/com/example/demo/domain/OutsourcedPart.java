package com.example.demo.domain;

import com.example.demo.validators.ValidPartInventory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 *
 *
 */
@Entity
@ValidPartInventory
@DiscriminatorValue("2")
public class OutsourcedPart extends Part{
String companyName;

    public OutsourcedPart() {
    }//outsourcepart constructor defined and variables set
    public OutsourcedPart(String name, double price, int inv, int minInventory, int maxInventory, String companyName) {
        super(name, price, inv);
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
