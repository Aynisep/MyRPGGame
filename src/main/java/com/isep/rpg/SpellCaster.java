package com.isep.rpg;

import java.util.Arrays;
import java.util.List;

/**
 * Classe abstraite SpellCaster definit les methodes et attributs des differents heros : Mage, Healer
 */

public  abstract class SpellCaster extends Hero {


    /**
     * nombre maximum de points de magie
     */
    private int maxMagicPoints ;

    /**
     * @return le nombre maximum de points de magie
     */
    public int getMaxMagicPoints() {
        return maxMagicPoints;
    }

    /**
     * défini le nombre maximum de points de magie
     * @param maxMagicPoints nombre maximum de points de magie
     */
    public void setMaxMagicPoints(int maxMagicPoints) {
        this.maxMagicPoints = maxMagicPoints;
    }

    /**
     * Affiche le contenu de l'objet SpellCaster
     * @Override toString de la classe Combatant
     * @author  A. N.
     * @version 1.0
     * */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("Classe = ").append(getMyName()).append("\n") ;
        sb.append("\tPoints de vie = ").append(this.getHealthPoints()).append("\n") ;
        sb.append("\tPoints de mana = ").append(this.getManaPoints()).append("\n") ;
        sb.append("\tPoints d'attaque = ").append(this.getMaxAttackPoints()).append("\n") ;
        sb.append("\tPoints de defense = ").append(this.getDefensePoints()).append("\n") ;
        sb.append("\tPoints de magie = ").append(maxMagicPoints).append("\n") ;
        return sb.toString();
    }

    /**
     * Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     *
     * @param maxManaPoints     nombre maximun de points de mana a l'initialisation du jeu
     * @param maxHealthPoints   nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     * @param maxMagicPoints nombre maximun de points de magie a l'initialisation du jeu
     * @param numberOfPotions quantite de potion a l'initialisation du jeu
     * @param foodQuantity quantite de nourriture a l'initialisation du jeu
     * @param potionPower puissance de la potion a l'initialisation du jeu
     * @param foodPower puissance de la nourriture a l'initialisation du jeu
     * @param position  position du hero sur la grid
     * @author  A. N.
     * @version 1.0
     */
    protected SpellCaster(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxManaPoints, int maxMagicPoints, int numberOfPotions, int potionPower,int foodQuantity, int foodPower, int position) {

        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, maxManaPoints,maxMagicPoints,0,numberOfPotions,potionPower,foodQuantity,foodPower, position);

        Food myFood = new Food(foodQuantity,foodPower) ;
        Potion myPotions = new Potion(numberOfPotions,potionPower) ;

        Item[] myItems = new Item[2];
        myItems[0] = myFood;
        myItems[1] = myPotions;
        List<Item> intList = Arrays.asList(myItems);


        this.maxMagicPoints = maxMagicPoints ;
    }
}
