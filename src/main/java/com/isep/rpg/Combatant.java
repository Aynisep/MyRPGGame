package com.isep.rpg ;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe abstraite Combatant definit les methodes et attributs des differents combatants
 */

public abstract class Combatant {

    // Récupération de notre logger.
    private static final Logger LOGGER = LogManager.getLogger( Combatant.class );
    /**
     * nombre de points de magie maximum correspondant a la classe spellcaster
     */
    private int maxMagicPoints;
    /**
     * nombre de points de magie actuel
     */
    private int magicPoints ;

    /**
     * points de vie du combatant
     */
    private int healthPoints ;

    /**
     * nombre de points de vie maximum correspondant a la classe du combatant
     */
    private int maxHealthPoints ;

    /**
     * points de mana du combatant
     */
    private int manaPoints ;

    /**
     * nombre de points de mana maximum correspondant a la classe du combatant 0 si le combattant n'est pas un SpellCaster
     */
    private int maxManaPoints ;

    /**
     * nombre actuel de fleches du Hunter
     */
    private int numberOfArrows ;

    /**
     * nombre maximum de fleches du Hunter au debut d'un nouveau combat
     */
    private int maxNumberOfArrows ;

    /**
     * nombre actuel de points d'attaque
     */
    private int attackPoints ;

    /**
     * nombre maximum de points d'attaque
     */
    private int maxAttackPoints ;
    /**
     *
     * @return les points de defense
     */
    public int getDefensePoints() {
        return defensePoints;
    }
    /**
     *
     * @param defensePoints nombre de points de defense
     */
    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    /**
     * nombre actuel de points de defense
     */
    private int defensePoints ;

    /**
     * nombre maximum de points de defense
     */
    private int maxDefensePoints ;

    /**
     * indique si la defense est active
     */
    private boolean defending ;

    private String myName = "Combatant" ;


    /**
     *  Constructeur de la classe retourne une exception arithmetique si l'un des arguments est negatif
     * @param maxNumberOfArrows nombre maximum de fleches a l'initialisation du jeu
     * @param maxManaPoints     nombre maximum de points de mana a l'initialisation du jeu
     * @param maxHealthPoints nombre maximum de points de vie a l'initialisation du jeu
     * @param maxAttackPoints nombre maximum de points d'attaque a l'initialisation du jeu
     * @param maxDefensePoints nombre maximum de points de defense a l'initialisation du jeu
     * @param maxMagicPoints nombre maximum de points de magie a l'initialisation du jeu
     * @param numberOfPotions quantite de potion a l'initialisation du jeu
     * @param foodQuantity quantite de nourriture a l'initialisation du jeu
     * @param potionPower puissance de la potion a l'initialisation du jeu
     * @param foodPower puissance de la nourriture a l'initialisation du jeu
     */
    protected Combatant(int maxHealthPoints, int maxAttackPoints, int maxDefensePoints, int maxManaPoints, int maxMagicPoints, int maxNumberOfArrows, int numberOfPotions, int potionPower,int foodQuantity, int foodPower) {

        this.maxHealthPoints = maxHealthPoints ;
        this.healthPoints = maxHealthPoints ;

        this.maxManaPoints = maxManaPoints ;
        this.manaPoints = maxManaPoints ;

        this.maxNumberOfArrows = maxNumberOfArrows ;
        this.numberOfArrows = maxNumberOfArrows ;

        this.maxAttackPoints = maxAttackPoints ;
        this.attackPoints = maxAttackPoints ;

        this.maxDefensePoints = maxDefensePoints ;
        this.defensePoints = maxDefensePoints ;

        this.maxMagicPoints = maxMagicPoints ;
        this.magicPoints = maxMagicPoints ;


        this.defending = false ; // par convention à la creation d'un hero il ne peut pas avoir active sa defense

        if (healthPoints<0) {
            throw new ArithmeticException ();
        }

        if (manaPoints<0) {
            throw new ArithmeticException ();
        }

        if (numberOfArrows<0) {
            throw new ArithmeticException ();
        }

    }

    /*
     * Affiche le contenu de l'objet Combatant
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(200) ;
        sb.append("Classe = ").append(getMyName()).append("\n") ;
        sb.append("\tPoints de vie = ").append(healthPoints).append("\n") ;
        sb.append("\tPoints de mana = ").append(manaPoints).append("\n") ;
        sb.append("\tNombres de fleches = ").append(numberOfArrows).append("\n") ;
        sb.append("\tPoints d'attaque = ").append(attackPoints).append("\n") ;
        sb.append("\tPoints de defense = ").append(defensePoints).append("\n") ;
        return sb.toString();
    }


    /**
     *
     * @return le type de combatant
     */
    public String getMyName() {
        return myName;
    }

    /**
     *
     * @return le nombre maximum de points de vie
     */
    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    /**
     * met a jour la valeur max des points de vie, a utiliser apres une victoire
     * @param maxHealthPoints valeur maximum des points de vie
     */
    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    /**
     *
     * @return le nombre maximum de mana
     */
    public int getMaxManaPoints() {
        return maxManaPoints;
    }


    /**
     * met a jour la valeur max des points de mana, a utiliser apres une victoire
     * @param maxManaPoints valeur maximum des points de mana
     */
    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    /**
     *
     * @return le nombre maximum de points d'attaque
     */
    public int getMaxAttackPoints() {
        return maxAttackPoints;
    }


    /**
     * met a jour la valeur max des points d'attaque', a utiliser apres une victoire
     * @param maxAttackPoints valeur maximum des points d'attaque'
     */
    public void setMaxAttackPoints(int maxAttackPoints) {
        this.maxAttackPoints = maxAttackPoints;
    }

    /**
     *
     * @return le nombre maximum de points de defense
     */
    public int getMaxDefensePoints() {
        return maxDefensePoints;
    }


    /**
     * met a jour la valeur max des points de defense', a utiliser apres une victoire
     * @param maxDefensePoints valeur maximum des points de defense
     */
    public void setMaxDefensePoints(int maxDefensePoints) {
        this.maxDefensePoints = maxDefensePoints;
    }

    /**
     *
     * @return le nombre de fleches
     */
    public int getNumberOfArrows() {
        return numberOfArrows;
    }

    /**
     * met a jour la valeur du nombre de fleches
     * @param numberOfArrows nombre de fleches
     */
    public void setNumberOfArrows(int numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }

    /**
     *
     * @return le nombre maximum de fleches
     */
    public int getMaxNumberOfArrows() {
        return maxNumberOfArrows;
    }

    /**
     * met a jour la valeur maximum du nombre de fleches apres une victoire
     * @param maxNumberOfArrows nombre maximum de fleches
     */
    public void setMaxNumberOfArrows(int maxNumberOfArrows) {
        this.maxNumberOfArrows = maxNumberOfArrows;
    }


    /**
     * Retourne la valeur courante des points de vie
     * @return un nombre compris entre 0 et maxHealthPoints du hero
     */
    public int getHealthPoints (){
        return healthPoints ;
    } ;

    /**
     * Mets a jour les points de vie, si c'est une attaque le parametre est negatif si c'est une recuperation le parametre est positif
     * @param healthPointsFlux nombre de points vie perdues de l'attaque subie ou la recuperation de points de vie
     */
    public void setHealthPoints (int healthPointsFlux){
        //on test s'il s'agit d'une attaque
        if (healthPointsFlux<0) {
            //on doit traiter le cas de l'armure
            if (defensePoints>0) {
                //on doit baisser l'armure d'un maximum de point correspondant à l'attaque et s'il reste des points d'attaque appliquer ceux si sur la santé
                if (defensePoints>(-healthPointsFlux)) {
                    defensePoints=defensePoints+healthPointsFlux ;
                }
                else {
                    int tmplocalAttackPoints = -healthPointsFlux;
                    tmplocalAttackPoints=tmplocalAttackPoints-defensePoints ;
                    defensePoints=0 ;
                    healthPoints=healthPoints-tmplocalAttackPoints ;
                }
            }
            else {
                healthPoints=healthPoints+healthPointsFlux ;
                if (healthPoints<0) {
                    healthPoints = 0;   // il est mort
                }
            }
        }
        else {
            this.healthPoints = this.healthPoints + healthPointsFlux;
        }

        if (healthPoints<0)  {
            this.healthPoints=0 ;
        }
        else if (healthPoints>maxHealthPoints)  {
            this.healthPoints=maxHealthPoints ;
        }

        LOGGER.debug ("Point de vie a la sortie"+this.healthPoints) ;
    }

    /**
     * Retourne la valeur courante des points de mana
     * @return un nombre compris entre 0 et maxManaPoints du hero
     */
    public int getManaPoints (){
        return manaPoints ;
    } ;

    /**
     * Mets a jour les points de mana, si c'est une attaque le parametre est nagatif si c'est une recuperation le parametre est positif
     * @param manaPointsFlux nombre de points mana perdues de l'attaque effectuee ou la recuperation de points de mana
     */
    public void setManaPoints (int manaPointsFlux){
        this.manaPoints=this.manaPoints+manaPointsFlux ;

        if (manaPoints<0)  {
            this.manaPoints=0 ;
        }
        else if (manaPoints>maxManaPoints)  {
            this.manaPoints=maxManaPoints ;
        }
    }

    /**
     * Indique si le combattant est en vie
     * @return true si le hero est toujours en vie sinon false
     */
    public boolean isAlive (){
        return (healthPoints>0) ;
    } ;

    /**
     * Indique si le combattant est en train de se defendre
     * @return true si le hero est en train de se defendre sinon false
     */
    public boolean isDefending (){
        return this.defending ;
    } ;

    /**
     * positonne la valeur de la variable
     * @param defending indique si le hero est en position de defense
     */
    public void setDefending (boolean defending) {
        this.defending = defending ;
    }


}

