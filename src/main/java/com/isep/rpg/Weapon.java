package com.isep.rpg;

/**
 * Correspond au nombre de fleche d'un Hunter
 */
public class Weapon extends Item {
    /**
     * Constructeur de la class Weapon
     * @param quantity quantite initial de fleche
     * @param power puissance initial des fleches
     */
    public Weapon(int quantity,int power){
        if ((quantity<0)||(power<0)) {
            throw new IndexOutOfBoundsException();  // la quantité de fléche ou leur puissance ne peut etre negative
        }

        this.quantity=quantity ;
        this.power=power ;
    }
}
