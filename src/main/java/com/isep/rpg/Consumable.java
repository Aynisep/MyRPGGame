package com.isep.rpg;

/**
 * Correspond aux consomables des heros : Food, Potion
 */
public abstract class Consumable extends Item{

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void updateQuantity(int nbConsumed) {
        this.quantity = this.quantity+nbConsumed;
    }
    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    private int quantity = 0;

    private int power = 0;



}
