package com.example.cinema.vo;


import com.example.cinema.po.VIPActivity;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class VIPActivityForm {
    private String name;
    private float costInNeed; //固定金额减免所需要的最低消费
    private float fixedDiscount;    //固定减免
    private float discountPercentage;   //百分比减免Dd
    private int id;
    private float bonusBalance;
    private String description;
    public VIPActivityForm(VIPActivity activity){
        name=activity.getName();
        costInNeed=activity.getCost_in_need();
        fixedDiscount=activity.getFixed_discount();
        discountPercentage=activity.getDiscount_percentage();
        id=activity.getId();
        bonusBalance=activity.getBonus_balance();
        description=activity.getDescription();
    }
    public float getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(float bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}