package com.isep.rpg;

/**
 * Classe abstraite Item definit les Weapon et les Consumable
 */

public abstract class Item {
    /**
     * Defini la quantite d'un Item
     * Si c'est Weapon c'est la quantite de fleches
     * Si c'est un Consumable c'est la quantite de nourriture ou de potions
     */
    protected int quantity ;

    /**
     * la quantite ne peut pas etre negatif
     * @return la quantité de l'Item qu'on possede
     */
    public int getQuantity() {
        return quantity ;
    }

    /**
     * met a jour la valeur de la quantite
     * @param quantity valeur de la nouvelle quantite
     */
    public void setQuantity(int quantity) {this.quantity = quantity;}

    /**
     * Defini la puissance des fleches
     */
    protected int power ;

    /**
     * la puissance ne peut pas etre negatif
     * @return la puissance des fleches
     */
    public int getPower() {
        return power;
    }
    /**
     * met a jour la valeur de la puissance
     * @param power valeur de la nouvelle puissance
     */
    public void setPower(int power) {
        this.power = power;
    }
}