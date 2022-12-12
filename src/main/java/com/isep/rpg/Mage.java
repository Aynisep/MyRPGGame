package com.isep.rpg;

import com.isep.rpg.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Classe Mage qui indique les differentes statistiques et les actions possibles
 */

public class Mage extends SpellCaster {

    private String myName = Combatant.S_MAGE;

    // Récupération de notre logger.
    private static final Logger LOGGER = LogManager.getLogger( Healer.class );

    // quantité de nouriture initiale
    private Food myFood = null;

    public void setMyPotion(Potion myPotion) {
        this.myPotion = myPotion;
    }

    // quantité de potion initiale
    private Potion myPotion = null;

    /**
     *
     * @return le type de combatant
     */
    public String getMyName() {
        return this.myName;
    }

    /**
     * Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     *
     * @param maxManaPoints     nombre maximun de points de mana a l'initialisation du jeu
     * @param maxHealthPoints nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     * @param maxMagicPoints nombre maximun de points de magie a l'initialisation du jeu
     * @param numberOfPotions quantite de potion a l'initialisation du jeu
     * @param foodQuantity quantite de nourriture a l'initialisation du jeu
     * @param potionPower puissance de la potion a l'initialisation du jeu
     * @param foodPower puissance de la nourriture a l'initialisation du jeu
     */
    public Mage(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxManaPoints, int maxMagicPoints, int numberOfPotions, int potionPower,int foodQuantity, int foodPower, int position) throws ArithmeticException {

        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, maxManaPoints, maxMagicPoints,numberOfPotions,potionPower,foodQuantity,foodPower, position);

        if ((maxAttackPoints < 0) || (maxHealthPoints < 0) || (maxDefensePoints < 0) || (maxManaPoints < 0) || (maxMagicPoints < 0)) {
            throw new ArithmeticException();
        }

        Food myFood = null;

        try {
            myFood = new Food(foodQuantity, foodPower);
        }
        catch (IndexOutOfBoundsException e){
            myFood = new Food(0,0);
        }

        Potion myPotion = null;

        try {
            myPotion = new Potion(numberOfPotions, potionPower);
        }
        catch (IndexOutOfBoundsException e){
            myPotion = new Potion(0,0);
        }

        // on ajoute la nouriture et les potions
        Item[] myItems = new Item[2];   // nombre de combatants
        myItems[0] = myFood;
        myItems[1] = myPotion;
        List<Item> intList = Arrays.asList(myItems);

        this.setMyItems(intList);
    }

    /**
     * Test pour etre sur d avoir la nourriture disponible et de la consommer
     * @return true si la nourriture est disponible
     */

    public boolean hasFood(){
        List<Item> myItems = this.getMyItems();
        if (myItems == null){
            return  false;
        }

        for (Item myItem : myItems) {
            if (myItem!=null && myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.FOOD) && (myItem.getQuantity()>0)){
                LOGGER.warn("on a encore de la nouriture");
                return true;

            }
            else {
                // rien
            }
        }
        return false;
    }

    public Potion getMyPotion(){
        List<Item> myItems = this.getMyItems();
        if (myItems == null){
            return  null;
        }

        for (Item myItem : myItems) {
            if (myItem!=null && myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.POTION)){
                LOGGER.warn("on a des potions " + myItem);
                return (Potion)myItem;

            }
            else {
                // rien
            }
        }
        return null;
    }
    /**
     * @return Test pour etre sur d'avoir des potions disponible et de les consommer
     */

    public boolean hasPotion(){
        List<Item> myItems = this.getMyItems();
        if (myItems == null){
            return  false;
        }
        for (Item myItem : myItems) {
            if (myItem!=null && myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.POTION) && (myItem.getQuantity()>0)){
                LOGGER.warn("on a encore de la potion");
                return true;
            }
            else {
                // rien
            }
        }
        return false;
    }


    /**
     * Différentes actions possibles par la classe: Boire une potion, manger de la nourriture, lancer un sort sur une cible, attaquer une cible et se défendre
     * @param myTarget cible du soin ou de l'attaque
     * @param quantity quantite de soin ou de degats
     * @param typeOfAction determine l'action effectuer
     */
    public void doMyAction(int typeOfAction, int quantity, Combatant myTarget){
        if (! this.isAlive()) {
            return; // impossible il est mort
        }

        switch (typeOfAction) {
            case Constant.ACTION_POTION:
                this.setManaPoints(quantity);
                break;
            case Constant.ACTION_FOOD:
                this.setHealthPoints(quantity);
                break;
            case Constant.ACTION_CAST:
                if (this.getManaPoints()>0) {
                    if (!myTarget.isDefending()) {
                        myTarget.setHealthPoints(-quantity) ;
                    } //il perd du mana si l'ennemi est en train de se défendre

                    this.setManaPoints(-1);
                    myTarget.setDefending(false);
                }
                break;
            case Constant.ACTION_MEELE_ATTACK:
                if (!myTarget.isDefending()) {
                    myTarget.setHealthPoints(-quantity);
                }
                myTarget.setDefending(false);
                break;
            case Constant.ACTION_DEFENSE:
                setDefending(true);
                break;
        }

    }


    /*
     * Affiche le contenu de l'objet Combatant
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("\tVie = ").append(this.getHealthPoints()).append("\n") ;
        sb.append("\tMana = ").append(this.getManaPoints()).append("\n") ;
        sb.append("\tAttaque = ").append(this.getMaxAttackPoints()).append("\n") ;
        sb.append("\tDefense = ").append(this.getDefensePoints()).append("\n") ;
        sb.append("\tMagie = ").append(this.getMaxMagicPoints()).append("\n") ;

        List <Item> myItems = this.getMyItems();
        if (myItems!=null) {

            for (Item myItem : myItems) {
                if (myItem!=null) {
                    if (myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.POTION)){
                        sb.append("\tPotion = ").append(myItem.getQuantity()).append("\n");
                    }
                    else if (myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.FOOD)){
                        sb.append("\tNouriture = ").append(myItem.getQuantity()).append("\n");
                    }
                }
            }
        }

        return sb.toString();
    }
}