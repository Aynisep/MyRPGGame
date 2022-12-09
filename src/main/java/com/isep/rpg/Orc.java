package com.isep.rpg;


import com.isep.rpg.Combatant;
import org.apache.logging.log4j.LogManager;

/**
 * Classe Orc qui indique les differentes statistiques et les actions possibles
 */

public class Orc extends Enemy {

    private String myName = "Orc" ;

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Orc.class);

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
     * @param maxHealthPoints nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     */
    public Orc(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints) throws ArithmeticException {

        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, 0, 0);

        if ((maxAttackPoints < 0) || (maxHealthPoints < 0) || (maxDefensePoints < 0)) {
            throw new ArithmeticException();
        }
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

            case Constant.ACTION_MEELE_ATTACK:
                if (!myTarget.isDefending()) {
                    myTarget.setHealthPoints(-quantity);
                }
                myTarget.setDefending(false);
                LOGGER.debug("attaque pas un orc");
                break;
            case Constant.ACTION_DEFENSE:
                setDefending(true);
                break;
        }

    }
}