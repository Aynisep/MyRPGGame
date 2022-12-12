package com.isep.rpg;

import com.isep.rpg.Combatant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * Classe Warrior qui indique les differentes statistiques et les actions possibles
 */

public class Warrior extends Hero {

    private static final Logger LOGGER = LogManager.getLogger(Warrior.class );
    private String myName = Combatant.S_WARRIOR;


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
     * @param maxHealthPoints   nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     * @param foodQuantity quantite de nourriture a l'initialisation du jeu
     * @param foodPower puissance de la nourriture a l'initialisation du jeu
     */
    public Warrior(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints,int foodQuantity, int foodPower, int position) throws ArithmeticException {

        super(maxHealthPoints, maxAttackPoints, maxDefensePoints,0,0,0,0,0,foodQuantity,foodPower, position);

        if ((maxAttackPoints < 0) || (maxHealthPoints < 0) || (maxDefensePoints < 0)){
            throw new ArithmeticException();
        }

        Food myFood = null;

        try {
            myFood = new Food(foodQuantity, foodPower);
        }
        catch (IndexOutOfBoundsException e){
            myFood = new Food(0,0);
        }

        // on ajoute la nouriture
        Item[] myItems = new Item[2];   // nombre de combatants
        myItems[0] = myFood;
        List<Item> intList = Arrays.asList(myItems);

        this.setMyItems(intList);

    }

    /**
     * @return Test pour etre sur d avoir la nourriture disponible et de la consommer
     */
    public boolean hasFood(){
        List<Item> myItems = this.getMyItems();
        if (myItems == null){
            return  false;
        }

        for (Item myItem : myItems) {
            if (myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.FOOD) && (myItem.getQuantity()>0)){
                LOGGER.warn("on a encore de la nouriture");
                return true;

            }
            else {
                // rien
            }
        }
        return false;
    }

    /**
     * Différentes actions possibles par la classe: Manger de la nourriture, attaquer une cible et se défendre
     * @param myTarget cible du soin ou de l'attaque
     * @param quantity quantite de soin ou de degats
     * @param typeOfAction determine l'action effectuer
     */
    public void doMyAction(int typeOfAction, int quantity, Combatant myTarget){
       if (! this.isAlive()) {
           return; // impossible il est mort
       }

       switch (typeOfAction) {
           case Constant.ACTION_FOOD:
               this.setHealthPoints(quantity);

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
        sb.append("\tAttaque = ").append(this.getMaxAttackPoints()).append("\n") ;
        sb.append("\tDefense = ").append(this.getDefensePoints()).append("\n") ;

        List <Item> myItems = this.getMyItems();
        try {
            if (myItems!=null) {

                for (Item myItem : myItems) {
                    if (myItem!=null) {
                        if (myItem.getClass().getSimpleName().equalsIgnoreCase(Constant.FOOD)) {
                            sb.append("\tNouriture = ").append(myItem.getQuantity()).append("\n");
                        }
                    }                }
            }
        }
        catch (NullPointerException e){
            sb.append("\tNourriture = ").append(0).append("\n") ;
        }



        return sb.toString();
    }
}
