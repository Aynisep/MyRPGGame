package com.isep.rpg;

/**
 * Correspond aux consomables des heros : Food, Potion
 */
public abstract class Consumable extends Item{
    /**
     * recupere la quantité de consomable
     */
    @Override
    public int getQuantity() {
        return quantity;
    }
    /**
     * modifie la quantité de consomable
     */
    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * met a jour la quantité de consommable
     * @param nbConsumed nombre de consommable consomme
     */

    public void updateQuantity(int nbConsumed) {
        this.quantity = this.quantity+nbConsumed;
    }
    /**
     * @return la puissance du consommable
     */
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
