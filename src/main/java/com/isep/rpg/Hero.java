package com.isep.rpg;
import com.isep.rpg.Constant;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe abstraite Hero definit les methodes et attributs des differents heros : Warrior, Hunter, Mage, Healer
 */
public abstract class Hero extends Combatant {

    // Récupération de notre logger.
    private static final Logger LOGGER = LogManager.getLogger( Healer.class );

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
     * @param position l'emplacement du combatant dans la liste d'attaques (0 = premier a attaquer)
     */
    protected Hero (int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxManaPoints, int maxMagicPoints, int maxNumberOfArrows, int numberOfPotions, int potionPower,int foodQuantity, int foodPower, int position) throws ArithmeticException {
        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, maxManaPoints, maxMagicPoints,maxNumberOfArrows, numberOfPotions, potionPower, foodQuantity, foodPower, position);
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

    /**
     * donner la valeur de la nourriture
     * @return un nourriture
     */
    public Food getMyFood(){
        List<Item> myItems = this.getMyItems();
        if (myItems == null){
            return  null;
        }

        for (Item myItem : myItems) {
            if (myItem!=null && myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.FOOD)){
                LOGGER.warn("on a de la nourriture " + myItem);
                return (Food)myItem;

            }
            else {
                // rien
            }
        }
        return null;
    }

}
