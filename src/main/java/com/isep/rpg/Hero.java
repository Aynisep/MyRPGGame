package com.isep.rpg;
import com.isep.rpg.Constant;

import java.util.List;

/**
 * Classe abstraite Hero definit les methodes et attributs des differents heros : Warrior, Hunter, Mage, Healer
 */
public abstract class Hero extends Combatant {


    /**
     * Correspond aux fleches, aux potions et à la nourriture possedes par les heros
     */
    private List<Item> myItems ;

    /**
     * Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     *
     * @param maxNumberOfArrows nombre maximun de fleches a l'initialisation du jeu
     * @param maxManaPoints     nombre maximun de points de mana a l'initialisation du jeu
     * @param maxMagicPoints nombre maximun de points de magie a l'initialisation du jeu
     * @param maxHealthPoints   nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     * @param numberOfPotions quantite de potion a l'initialisation du jeu
     * @param foodQuantity quantite de nourriture a l'initialisation du jeu
     * @param potionPower puissance de la potion a l'initialisation du jeu
     * @param foodPower puissance de la nourriture a l'initialisation du jeu
     */
    protected Hero (int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxManaPoints, int maxMagicPoints, int maxNumberOfArrows, int numberOfPotions, int potionPower,int foodQuantity, int foodPower) throws ArithmeticException {
        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, maxManaPoints, maxMagicPoints,maxNumberOfArrows, numberOfPotions, potionPower, foodQuantity, foodPower);
        this.myItems = myItems ;
    }

    /**
     * sauvegarde mes items
     * @param myItems ma liste d'item
     */
    public void setMyItems(List<Item> myItems) {
        this.myItems = myItems ;
    }

    /**
     * @return tous mes items
     */
    public List<Item> getMyItems() {
        return myItems;
    }

    /**
     * on va utiliser une potion ou de la nouriture ou des fleches
     * @param typeOfPotion type de potion
     */
    public void useItem(String typeOfPotion){
        List<Item> myItems = this.getMyItems();
        if ((myItems == null) || (typeOfPotion==null)) return;

        for (Item myItem : myItems) {
            if (myItem.getClass().getSimpleName().equalsIgnoreCase(typeOfPotion) && (myItem.getQuantity()>0)){
                this.setManaPoints(myItem.getPower());
                myItem.setQuantity(myItem.getQuantity()-1);
            }
            else {
                // rien
            }
        }
    }


}
