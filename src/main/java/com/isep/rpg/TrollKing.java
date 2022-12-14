package com.isep.rpg;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import com.isep.rpg.Combatant;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

/**
 * Classe TrollKing qui indique les differentes statistiques et les actions possibles
 */

public class TrollKing extends Enemy {

    private String myName = S_TROLL;


    /**
     * @return le type de combatant
     */
    public String getMyName() {
        return this.myName;
    }

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TrollKing.class);

    /**
     * Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     *
     * @param maxHealthPoints  nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints  nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     */
    public TrollKing(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints) throws ArithmeticException {

        super(maxHealthPoints, maxAttackPoints, maxDefensePoints, 0, 0);

        if ((maxAttackPoints < 0) || (maxHealthPoints < 0) || (maxDefensePoints < 0)) {
            throw new ArithmeticException();
        }
    }

    /**
     * Différentes actions possibles par la classe: Manger de la nourriture, attaquer une cible et se défendre
     *
     * @param myTarget     cible du soin ou de l'attaque
     * @param quantity     quantite de soin ou de degats
     */
    public void doMyAction(int quantity, Combatant myTarget) {
        if (!this.isAlive()) {
            return; // impossible il est mort
        }
        Random obj = new Random();

        if (!myTarget.isDefending()) {
            if (obj.nextInt(5) < 3) {
                myTarget.setHealthPoints(-quantity);
                LOGGER.debug("vient de faire une attaque normale sur une seule cible");
            } else {
                myTarget.setHealthPoints(-2 * quantity); //coup critique
                LOGGER.warn("coup critique");
            }

        }
        myTarget.setDefending(false);
    }

    /**
     * Différentes actions possibles par la classe: Manger de la nourriture, attaquer une cible et se défendre
     *
     * @param myTargets     cible du soin ou de l'attaque
     * @param quantity     quantite de soin ou de degats
     */
    public void doMyAction(int quantity, List<Combatant> myTargets) {
        if (!this.isAlive() || myTargets == null) {
            return; // impossible il est mort
        }
        for (Combatant myComb : myTargets) {
            if (!myComb.isDefending()) {
                myComb.setHealthPoints(-Math.round(quantity/myTargets.toArray().length));
                LOGGER.debug("vient de faire une attaque de zone");
            }
            myComb.setDefending(false);
        }
    }

    /**
     * Affiche le contenu de l'objet TrollKing
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("\tVie = ").append(this.getHealthPoints()).append("\n") ;
        sb.append("\tAttaque = ").append(this.getMaxAttackPoints()).append("\n") ;
        sb.append("\tDefense = ").append(this.getDefensePoints()).append("\n") ;

        return sb.toString();
    }
}
