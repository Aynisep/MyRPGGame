package com.isep.rpg;

/**
 * Correspond au nombre de potion disponible et sa puissance
 */
public class Potion extends Consumable{
    /**
     * Constructeur de la class Weapon
     * @param quantity quantite initial de potion
     * @param power puissance initial des potions
     */
    public Potion(int quantity,int power) throws IndexOutOfBoundsException {
        if ((quantity<0)||(power<0)) {
            throw new IndexOutOfBoundsException();  // la quantité de potion ne peut etre negative
        }

        setQuantity(quantity);
        setPower(power);
    }


    /*
     * Affiche le contenu de l'objet Potion
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("\tQuantité = ").append(getQuantity()).append("\n") ;
        sb.append("\tPuissance = ").append(getPower()).append("\n") ;
        return sb.toString();
    }

}
