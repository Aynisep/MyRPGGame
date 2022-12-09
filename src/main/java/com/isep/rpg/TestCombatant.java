package com.isep.rpg;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Classe de test pour vérifier les différents types d'actions
 */
public class TestCombatant {

    // pour les messsages de debuggage
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger( Combatant.class );

    // pour afficher les messages
    private static StringBuffer sb;

    /**
     * procédure de test d'attaque/de defense/de soin d'un Warrior et d'un Healer
     * @param theWarrior celui qui attaque
     * @param theHealer celui qui est attaque
     */
    private void testFonctionSoigner (Warrior theWarrior, Healer theHealer){
        //on test l'action se défendre et l'action de soin du Healer

        sb = new StringBuffer(500) ;
        sb.append("my combatants = ").append("\n") ;
        sb.append("\tl'attaquant\n = ").append(theWarrior).append("\n") ;
        sb.append("\tle Healer\n = ").append(theHealer).append("\n") ;

        theWarrior.doMyAction(Constant.ACTION_MEELE_ATTACK, theWarrior.getMaxAttackPoints(), theHealer);

        sb.append("\tle Healer après une attaque \n= ").append(theHealer).append("\n") ;

        theHealer.doMyAction(Constant.ACTION_DEFENSE,0,theHealer); // il met sa defense
        theWarrior.doMyAction(Constant.ACTION_MEELE_ATTACK, theWarrior.getMaxAttackPoints(), theHealer);

        sb.append("\tle Healer après deuxième attaque (il s'est defendu) \n = ").append(theHealer).append("\n") ;

        theHealer.doMyAction(Constant.ACTION_HEAL,theHealer.getMaxMagicPoints(), theHealer); //test l'action de soigner quelqu'un
        sb.append("\tle Healer soin 1 \n  = ").append(theHealer).append("\n") ;

        theHealer.doMyAction(Constant.ACTION_HEAL,theHealer.getMaxMagicPoints(), theHealer);
        sb.append("\tle Healer soin 2 \n = ").append(theHealer).append("\n") ;
        theHealer.doMyAction(Constant.ACTION_HEAL,theHealer.getMaxMagicPoints(), theHealer);
        sb.append("\tle Healer soin 3 \n = ").append(theHealer).append("\n") ;

        LOGGER.warn(sb);

    }

    /**
     * procédure de test d'attaque du TrollKing sur un héro
     * @param theWarrior le héro qui va se faire attaquer
     * @param theTrollKing le boss du 5ieme niveau
     */
    private void testFonctionBoss (Warrior theWarrior, TrollKing theTrollKing) {
        //

        sb = new StringBuffer(500);
        sb.append("my combatants = ").append("\n");
        sb.append("\tl'attaquant\n = ").append(theWarrior).append("\n");
        sb.append("\tle TrollKing\n = ").append(theTrollKing).append("\n");

        theWarrior.doMyAction(Constant.ACTION_MEELE_ATTACK, theWarrior.getMaxAttackPoints(),theTrollKing);

        sb.append("\tle TrollKing après une attaque \n= ").append(theTrollKing).append("\n");

        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theWarrior);
        sb.append("\t le Warrior après six attaque \n =").append(theWarrior);

        LOGGER.warn(sb);
    }

    /**
     * procédure de test d'attaque sur un Orc
     * @param theWarrior le héro qui va attaquer
     * @param theOrc l'ennemi
     */
    private void testFonctionOrc (Warrior theWarrior, Orc theOrc) {
        //

        sb = new StringBuffer(500);
        sb.append("testFonctionOrc\n my combatants = ").append("\n");
        sb.append("\tl'attaquant\n = ").append(theWarrior).append("\n");
        sb.append("\tle TrollKing\n = ").append(theOrc).append("\n");

        theWarrior.doMyAction(Constant.ACTION_MEELE_ATTACK, theWarrior.getMaxAttackPoints(),theOrc);

        sb.append("\tl'Orc après une attaque \n= ").append(theOrc).append("\n");

        theOrc.doMyAction(Constant.ACTION_MEELE_ATTACK, theOrc.getMaxAttackPoints(),theWarrior);

        sb.append("\t fin du tour \n\n \tl'orc=").append(theOrc);
        sb.append("\tle warrior=").append(theWarrior);

        LOGGER.warn(sb);
    }

    /**
     * procédure de test d'attaque du TrollKing sur plusieurs héros
     * @param theCombatants les héros qui vont se faire attaquer
     * @param theTrollKing le boss du 5ieme niveau
     */
    private void testFonctionBossMulti (List<Combatant> theCombatants, TrollKing theTrollKing) {
        //

        sb = new StringBuffer(500);
        sb.append("my combatants = ").append("\n");
        sb.append("\tl'attaquant\n = ").append(theCombatants).append("\n");
        sb.append("\tle TrollKing\n = ").append(theTrollKing).append("\n");

        theTrollKing.doMyAction(theTrollKing.getMaxAttackPoints(),theCombatants);

        sb.append("\t les heros \n =").append(theCombatants);
        LOGGER.warn(sb);
    }


    /**
     * procédure de test de l'usage de nouriture et de potion
     * @param myHealer les héros qui vont se faire attaquer
     */
    private void testPotionAndFoodForHeal (Healer myHealer) {
        //

        sb = new StringBuffer(500);
        sb.append("mon Heal avant application des items = ").append("\n");
        sb.append("\tmyHealer= ").append(myHealer).append("\n");
        LOGGER.warn(sb);

        myHealer.setHealthPoints(-15);
        myHealer.setManaPoints(-5);

        sb = new StringBuffer(500);
        sb.append("mon Heal apres avoir baissé santé et mana = ").append("\n");
        sb.append("\tmyHealer= ").append(myHealer).append("\n");
        LOGGER.warn(sb);


        if (myHealer.hasFood()){
            myHealer.useItem(Constant.FOOD);
        }
        sb = new StringBuffer(500);
        sb.append("mon Heal apres avoir mangé de la nouriture = ").append("\n");
        sb.append("\tmyHealer= ").append(myHealer).append("\n");
        LOGGER.warn(sb);



        if (myHealer.hasPotion()){
            myHealer.useItem(Constant.POTION);
        }
        sb = new StringBuffer(500);
        sb.append("mon Heal apres avoir bu une potion = ").append("\n");
        sb.append("\tmyHealer= ").append(myHealer).append("\n");
        LOGGER.warn(sb);

    }

    /**
         * Fonction principale pour tester le programme Combatant
         * @param args arguments du programme principal. Aucun argument ici
         */
    public static void main (String[] args){

        TestCombatant tc = new TestCombatant();

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);

        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(Level.WARN);
        ctx.updateLoggers();  // This causes all Loggers to refetch information from their LoggerConfig.


        Mage myMage = new Mage (100, 5, 5, 50, 20, 3,10,5,15);
        Healer myHealer = new Healer (80, 4, 4, 60, 15,5,10,1,15);
        Warrior myWarrior = new Warrior(200, 25 , 20,5,15);
        Hunter myHunter = new Hunter (125,10, 7, 50,5,10);
        TrollKing myTrollKing = new TrollKing(300,15,100);
        Orc myOrc = new Orc (30, 5, 5);

        // affiche le contenu des combatants
        LOGGER.debug("\nmyMage"+ myMage);
        LOGGER.debug("\nmyMage"+ myHealer);
//bug ici        LOGGER.debug("\nmyMage"+ myWarrior);
//bug ici        LOGGER.debug("\nmyMage"+ myHunter);

        // test le melange des combatants et affiche la liste
        Combatant[] myCombatants = new Combatant[4];   // nombre de combatants
        myCombatants[0] = myMage;
        myCombatants[1] = myHealer;
        myCombatants[2] = myWarrior;
        myCombatants[3] = myHunter;
        List<Combatant> intList = Arrays.asList(myCombatants);
        Collections.shuffle(intList);
        intList.toArray(myCombatants);

//        tc.testFonctionSoigner(myWarrior, myHealer);

        myMage = new Mage (100, 5, 5, 50, 20, 3,10,5,15);
        myHealer = new Healer (80, 4, 4, 60, 15,5,10,1,15);
        myWarrior = new Warrior(200, 25 , 20,5,15);
        myHunter = new Hunter (125,10, 7, 50,5,10);
        myTrollKing = new TrollKing(300,15,100);
 //       tc.testFonctionBoss(myWarrior,myTrollKing);

        myMage = new Mage (100, 5, 5, 50, 20, 3,10,5,15);
        myHealer = new Healer (80, 4, 4, 60, 15,5,10,1,15);
        myWarrior = new Warrior(200, 25 , 20,5,15);
        myHunter = new Hunter (125,10, 7, 50,5,10);
        myTrollKing = new TrollKing(300,15,100);
 //       tc.testFonctionBossMulti(intList, myTrollKing);

        myWarrior = new Warrior(200, 25 , 20,5,15);
        myOrc = new Orc(45,7,4);
  //      tc.testFonctionOrc(myWarrior, myOrc);

        myHealer = new Healer (80, 4, 4, 60, 15,7,10,9,15);
        tc.testPotionAndFoodForHeal(myHealer);
    }
}
