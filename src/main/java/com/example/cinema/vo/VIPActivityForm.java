package com.example.cinema.vo;

import javafx.util.Pair;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class VIPActivityForm {
    private String Name;
    private float costInNeed;
    private float fixedDiscount;
    private float discountPercentage;

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public float getCostInNeed() {
        return costInNeed;
    }

    public void setCostInNeed(float costInNeed) {
        this.costInNeed = costInNeed;
    }

    public float getFixedDiscount() {
        return fixedDiscount;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setFixedDiscount(float fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }
}