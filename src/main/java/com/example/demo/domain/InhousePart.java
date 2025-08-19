package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 *
 *
 */
@Entity
@DiscriminatorValue("1")
public class InhousePart extends Part{
    int partId;

    public InhousePart() {
    } //inhouse part constructor defined and variables set
    public InhousePart(String name, double price, int inv, int minInventory, int maxInventory) {
        super(name, price, inv, minInventory, maxInventory);
    }
    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }
}
