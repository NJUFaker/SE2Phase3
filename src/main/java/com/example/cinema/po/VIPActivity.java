package com.example.cinema.po;

public class VIPActivity{
    private int id;
    private String name;
    private String description;
    private float cost_in_need; //固定金额减免所需要的最低消费（满减所需金额）
    private float bonus_balance;//减的金额




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
    public float getBonus_balance() {
        return bonus_balance;
    }

    public void setBonus_balance(float bonus_balance) {
        this.bonus_balance = bonus_balance;
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
