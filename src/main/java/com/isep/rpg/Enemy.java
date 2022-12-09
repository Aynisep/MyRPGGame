package com.isep.rpg;
import com.isep.rpg.Constant;
/**
 * Classe abstraite Enemy definit les methodes et attributs des differents enemy : Orc, TrollKing
 */
public abstract class Enemy extends Combatant {

    /**
     * Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     *
     * @param maxNumberOfArrows nombre maximun de fleches a l'initialisation du jeu
     * @param maxManaPoints     nombre maximun de points de mana a l'initialisation du jeu
     * @param maxHealthPoints   nombre maximun de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximun de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximun de points de defense a l'initialisation du jeu
     */

    protected Enemy(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxNumberOfArrows, int maxManaPoints) {
        super(maxHealthPoints, maxAttackPoints, maxDefensePoints,0,0, 0, 0, 0, 0, 0);
    }
}
