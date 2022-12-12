package com.isep.rpg;

/**
 * Correspond au nombre de nourriture disponible et sa puissance
 */
public class Food extends Consumable{
    /**
     * Constructeur de la class Weapon
     * @param quantity quantite initial de nourriture
     * @param power puissance initial des nourritures
     */
    public Food(int quantity,int power) throws IndexOutOfBoundsException {
        if ((quantity<0)||(power<0)) {
            throw new IndexOutOfBoundsException();  // la quantité de nouriture ou sa puissance ne peut etre negative
        }

        setPower(power);
        setQuantity(quantity); ;
    }


    /*
     * Affiche le contenu de l'objet Food
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("Classe = ").append(this.getClass().getName()).append("\n") ;
        sb.append("\tQuantité = ").append(getQuantity()).append("\n") ;
        sb.append("\tPuissance = ").append(getPower()).append("\n") ;
        return sb.toString();
    }

}

