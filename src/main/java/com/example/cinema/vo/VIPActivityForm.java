package com.example.cinema.vo;

import javafx.util.Pair;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class VIPActivityForm {
    private String Name;
    private int costInNeed;
    private int fixedDiscount;
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

    public int getCostInNeed() {
        return costInNeed;
    }

    public void setCostInNeed(int costInNeed) {
        this.costInNeed = costInNeed;
    }

    public int getFixedDiscount() {
        return fixedDiscount;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setFixedDiscount(int fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }
}