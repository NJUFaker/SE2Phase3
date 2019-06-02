package com.example.cinema.po;

public class VIPActivity{
    private String name;
    private float cost_in_need; //固定金额减免所需要的最低消费
    private float fixed_discount;    //固定减免
    private float discount_percentage;   //百分比减免Dd
    private int id;
    private float bonus_balance;
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public float getCost_in_need() {
        return cost_in_need;
    }

    public void setCost_in_need(float cost_in_need) {
        this.cost_in_need = cost_in_need;
    }

    public float getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(float discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public float getBonus_balance() {
        return bonus_balance;
    }

    public void setBonus_balance(float bonus_balance) {
        this.bonus_balance = bonus_balance;
    }

    public float getFixed_discount() {

        return fixed_discount;
    }

    public void setFixed_discount(float fixed_discount) {
        this.fixed_discount = fixed_discount;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
