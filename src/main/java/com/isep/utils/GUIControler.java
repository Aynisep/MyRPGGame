package com.isep.utils;

import com.isep.rpg.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.util.*;

public class GUIControler {
    @FXML
    private Label welcomeText;
    @FXML
    private ChoiceBox cbNumberOfHeroes;
    @FXML
    private Label texteSelectionHero;
    @FXML
    private GridPane myHeroesList;
    @FXML
    private ChoiceBox cbHero1;
    @FXML
    private ChoiceBox cbHero2;
    @FXML
    private ChoiceBox cbHero3;
    @FXML
    private ChoiceBox cbHero4;

    @FXML
    private ChoiceBox cbHeroCare1;
    @FXML
    private ChoiceBox cbHeroCare2;
    @FXML
    private ChoiceBox cbHeroCare3;
    @FXML
    private ChoiceBox cbHeroCare4;

    @FXML
    private GridPane myBoardGame;

    @FXML
    private VBox vbMyHeroesCare; // contient les options de mise à jour des héros après une manche

    @FXML
    private Button bAttack;
    @FXML
    private Button bSpecialAttack;
    @FXML
    private Button bDefense;
    @FXML
    private Button bFood;
    @FXML
    private Button bPotion;
    @FXML
    private Button bFight;
    private static final Logger LOGGER = LogManager.getLogger(GUIControler.class);

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Enemy[] myEnemies;

    private int nbOfTurn = 0;
    private int attaquantEnCours = 0;

    private Combatant[] myHeroes;

    private List<Combatant> myAttackList;  // contient la liste des heros mélanges, morts et vivants

    private List <Combatant> myAttackListEnCours;  // contient la liste des heros vivants, ceux qui vont attaquer

    private final static String MAGE_FILE = "mage.png";
    private final static String WARRIOR_FILE = "warrior.png";
    private final static String HEALER_FILE = "healer.png";
    private final static String HUNTER_FILE = "hunter.png";
    private final static String ORC_FILE = "orc.png";
    private final static String TROLLKING_FILE = "trollking.png";

    private final static String RIP_FILE = "rip.png";
    private final static String COLOR_BACKGROUND = "#FFCCCC";

    private final static String ACTION_CARE_RESET = "Rest";

    private final static String ACTION_CARE_ATTACK = "+1 Attack";


    private boolean bFinDesAttaques = false;
    private boolean bFinDelaManche = false;
    private boolean bFinDuJeu = false;

    private boolean bFirstAttackOfTheTurn = true;

    /**
     * nombre maximum de combats
     */
    private final int NB_TOUR_MAX = 5;

    private int nbCombatsEffectues = 0;
    private final String[][] hero = {{"Warrior", WARRIOR_FILE}, {"Hunter", HUNTER_FILE}, {"Mage", MAGE_FILE}, {"Healer", HEALER_FILE}};

    /**
     * Affiche l'image du combatant
     * @param myImage : l'image qui correspond au combatant
     * @param column la colonne où est affiché le combatant
     * @param row la ligne où sont affichées les statistiques du combatant
     */
    private void drawImage(String myImage, int column, int row) {
        GridPane gridpane = null;
        Image myImageTmp = null;
        ImageView myImageView = null;

       try{
            myImageTmp = new Image(getClass().getResourceAsStream(myImage), 120, 120, true, true);

           gridpane = (GridPane) getNodeFromGridPane(myBoardGame, column, (row));

           if (gridpane.getChildren()!= null && (gridpane.getChildren().size() > 0)) {
               myImageView = (ImageView) gridpane.getChildren().get(0);
               myImageView = new ImageView(myImageTmp);           }
           else {
               gridpane = new GridPane();
               gridpane.add(new ImageView(myImageTmp), 0, 0);
               myBoardGame.add(gridpane, column, row);
           }
       }
       catch(Exception e) {
           gridpane = new GridPane();
           gridpane.add(new ImageView(myImageTmp), 0, 0);
           myBoardGame.add(gridpane, column, row);
       }
    }

    /**
     * Affiche les statistiques du combatant
     * @param myHero : le combatant ami ou ennemi
     * @param colunm la colonne où est affiché le combatant
     * @param row la ligne où sont affichées les statistiques du combatant
     */
    private void drawLabel(Object myHero, int colunm, int row) {

        Node myNode = getNodeFromGridPane(myBoardGame, colunm, (row));
        if (myNode != null  && myNode.getClass().equals(Label.class)) {
            ((Label) myNode).setText(myHero.toString());
            myNode = null;
       //     myBoardGame.getChildren().remove(0);
        }
        else{
            Label myLabel = new Label(myHero.toString());
            myBoardGame.add(myLabel, colunm, row);
        }

    }

    private void prepareBoardGame() {
        myHeroes = new Combatant[Integer.parseInt(cbNumberOfHeroes.getValue().toString())];
        if (cbHero1.getValue().toString().equalsIgnoreCase(hero[0][0])) {
            myHeroes[0] = new Warrior(200, 125, 20, 5, 15, 0);
            drawImage(hero[0][1], 0, 3);
        } else if (cbHero1.getValue().toString().equalsIgnoreCase(hero[1][0])) {
            myHeroes[0] = new Hunter(125, 10, 7, 50, 5, 10, 0);
            drawImage(hero[1][1], 0, 3);
        } else if (cbHero1.getValue().toString().equalsIgnoreCase(hero[2][0])) {
            myHeroes[0] = new Mage(100, 5, 5, 50, 20, 3, 10, 5, 15, 0);
            drawImage(hero[2][1], 0, 3);
        } else {
            myHeroes[0] = new Healer(80, 4, 4, 60, 15, 5, 10, 1, 15, 0);
            drawImage(hero[3][1], 0, 3);
        }
        drawLabel(myHeroes[0], 0, 4);
        if (!cbHero2.isDisable()) {
            if (cbHero2.getValue().toString().equalsIgnoreCase(hero[0][0])) {
                myHeroes[1] = new Warrior(200, 25, 20, 5, 15, 1);
                drawImage(hero[0][1], 1, 3);
            } else if (cbHero2.getValue().toString().equalsIgnoreCase(hero[1][0])) {
                myHeroes[1] = new Hunter(125, 10, 7, 50, 5, 10, 1);
                drawImage(hero[1][1], 1, 3);
            } else if (cbHero2.getValue().toString().equalsIgnoreCase(hero[2][0])) {
                myHeroes[1] = new Mage(100, 5, 5, 50, 20, 3, 10, 5, 15, 1);
                drawImage(hero[2][1], 1, 3);
            } else {
                myHeroes[1] = new Healer(80, 4, 4, 60, 15, 5, 10, 1, 15, 1);
                drawImage(hero[3][1], 1, 3);
            }

            drawLabel(myHeroes[1], 1, 4);
        }

        if (!cbHero3.isDisable()) {
            if (cbHero3.getValue().toString().equalsIgnoreCase(hero[0][0])) {
                myHeroes[2] = new Warrior(200, 25, 20, 5, 15, 2);
                drawImage(hero[0][1], 3, 3);
            } else if (cbHero3.getValue().toString().equalsIgnoreCase(hero[1][0])) {
                myHeroes[2] = new Hunter(125, 10, 7, 50, 5, 10, 2);
                drawImage(hero[1][1], 3, 3);
            } else if (cbHero3.getValue().toString().equalsIgnoreCase(hero[2][0])) {
                myHeroes[2] = new Mage(100, 5, 5, 50, 20, 3, 10, 5, 15, 2);
                drawImage(hero[2][1], 3, 3);
            } else {
                myHeroes[2] = new Healer(80, 4, 4, 60, 15, 5, 10, 1, 15, 2);
                drawImage(hero[3][1], 3, 3);
            }
            drawLabel(myHeroes[2], 3, 4);
        }
        if (!cbHero4.isDisable()) {
            if (cbHero4.getValue().toString().equalsIgnoreCase(hero[0][0])) {
                myHeroes[3] = new Warrior(200, 25, 20, 5, 15, 3);
                drawImage(hero[0][1], 4, 3);
            } else if (cbHero4.getValue().toString().equalsIgnoreCase(hero[1][0])) {
                myHeroes[3] = new Hunter(125, 10, 7, 50, 5, 10, 3);
                drawImage(hero[1][1], 4, 3);
            } else if (cbHero4.getValue().toString().equalsIgnoreCase(hero[2][0])) {
                myHeroes[3] = new Mage(100, 5, 5, 50, 20, 3, 10, 5, 15, 3);
                drawImage(hero[2][1], 4, 3);
            } else {
                myHeroes[3] = new Healer(80, 4, 4, 60, 15, 5, 10, 1, 15, 3);
                drawImage(hero[3][1], 4, 3);
            }
            drawLabel(myHeroes[3], 4, 4);
        }
    }

    private void enemyOrcBoardGame() {
        Random nbEnnemy = new Random();
        int nbOfEnnemies = nbEnnemy.nextInt(5);
        if (nbOfEnnemies==0){
            nbOfEnnemies = 1;
        }

        buildEnnemyBoardGame(nbOfEnnemies, Combatant.S_ORC);
    }

    private void enemyTrollKingBoardGame() {
        buildEnnemyBoardGame(1, Combatant.S_TROLL);
    }
    private void buildEnnemyBoardGame(int nbOfEnnemies, String ennemyType) {
        myEnemies = new Enemy[nbOfEnnemies];

        for (int i= 0; i < nbOfEnnemies; i++) {

            if (ennemyType.equalsIgnoreCase(Combatant.S_ORC) ){
                myEnemies[i] = new Orc(200, 25, 20);
                drawImage(ORC_FILE, i,0);
                drawLabel(myEnemies[i], i, 1);
            }
            else {
                myEnemies[i] = new TrollKing(300,15,100);
                drawImage(TROLLKING_FILE, i,0);
                drawLabel(myEnemies[i], i, 1);
            }
        }
    }


    /**
     * décide de l'ordre d'attaque
     */
    private void startFight() {
        LOGGER.warn("début des combats, tout premier tour");

        Combatant ctmp = myAttackListEnCours.get(0);
        LOGGER.warn("le premier attaquant est : " + ctmp.toString());

        int myAttaquantCollumn = ctmp.getPosition();

        if (myAttaquantCollumn>=2)
        {
            myAttaquantCollumn++;   // trouver la colonne de l'attaquant sur le grid
        }

        Node myNode = getNodeFromGridPane(myBoardGame, myAttaquantCollumn, 3);   // l'image de l'attaquant

        GridPane gridpane = (GridPane)myNode;

        gridpane.setStyle("-fx-background-color: "+COLOR_BACKGROUND+";");  // posionner le fond en rosé, on prepare les boutons

        // bAttack : mélée attaque
        // bouton 2 : attaque speciale
        // bouton 3 : defense
        // bouton 4 : food
        // bouton 5 : potion
        // désactive les buttons inutiles par rapport au premier attaquant : ctmp
        if (ctmp.getMyName().equals(Combatant.S_HUNTER)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(true);
        } else if (ctmp.getMyName().equals(Combatant.S_MAGE)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(false);
        } else if (ctmp.getMyName().equals(Combatant.S_HEALER)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(false);
        } else {  // WARRIOR
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(true);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(true);
        }

        attaquantEnCours = 0; // le premier attaquant de la liste  est le premier de la liste myAttackList


        bFinDesAttaques = false;
    }

    private ArrayList<Combatant> getListCombantsAlive(){

        ArrayList myListEnCours = new ArrayList<>();
        for (Combatant c : myAttackList) {
            if (c!=null && c.isAlive()){
                myListEnCours.add(c);
            }

        }
        return myListEnCours;
    }
    private void ennemyAttack(){
        bAttack.setDisable(false);
        bSpecialAttack.setDisable(false);
        bDefense.setDisable(false);
        bFood.setDisable(false);
        bPotion.setDisable(false);

        bFinDesAttaques = false;

        for (Enemy myEnnemy : myEnemies)
        {
            if ((myEnnemy != null) && (myEnnemy.isAlive()))
            {

                int positionDansLaListe = trouverUnAlly();
                if (positionDansLaListe<0){
                    // mettre un ecran de perdu  #TODO y a un popup de perdu
                    resetDefense();
                }

                int column = myHeroes[positionDansLaListe].getPosition();

                if (column>=2)
                {
                    column++;
                }

                Combatant myAlly =(Combatant) myHeroes[positionDansLaListe];
                LOGGER.warn("l'ennemy "+ myEnnemy.toString() + " attaque l'ally "+ myAlly.toString());
                 myEnnemy.doMyAction(Constant.ACTION_MEELE_ATTACK, myEnnemy.getMaxAttackPoints(),myAlly);

                drawAfterFightEnemy(myAlly, column, 3);
                LOGGER.warn("après l'attaque l'ally "+ myAlly.toString() + " colonne = "+ column);

            }
        }

        resetDefense();

        myAttackListEnCours = getListCombantsAlive();   // la liste de mes combatants hero qui vont etre attaque


        if (myAttackListEnCours!=null && myAttackListEnCours.size()>0)
        {
            int positionDansLaListe = 0;

            int column = myHeroes[positionDansLaListe].getPosition();

            if (column >= 2) {
                column++;
            }
            Combatant myAlly = (Combatant) myHeroes[positionDansLaListe];

            LOGGER.warn("le premier attaquant du deuxieme tour est : " + myAlly.toString());

            Node myNode = getNodeFromGridPane(myBoardGame, column, 3);

            GridPane gridpane = (GridPane) myNode;

            gridpane.setStyle("-fx-background-color: " + COLOR_BACKGROUND + ";");

            // bAttack : mélée attaque
            // bouton 2 : attaque speciale
            // bouton 3 : defense
            // bouton 4 : food
            // bouton 5 : potion
            // désactive les buttons inutiles par rapport au premier attaquant : ctmp
            if (myAlly.getMyName().equals(Combatant.S_HUNTER)) {
                bAttack.setDisable(false);
                bSpecialAttack.setDisable(false);
                bDefense.setDisable(false);
                bFood.setDisable(false);
                bPotion.setDisable(true);
            } else if (myAlly.getMyName().equals(Combatant.S_MAGE)) {
                bAttack.setDisable(false);
                bSpecialAttack.setDisable(false);
                bDefense.setDisable(false);
                bFood.setDisable(false);
                bPotion.setDisable(false);
            } else if (myAlly.getMyName().equals(Combatant.S_HEALER)) {
                bAttack.setDisable(false);
                bSpecialAttack.setDisable(false);
                bDefense.setDisable(false);
                bFood.setDisable(false);
                bPotion.setDisable(false);
            } else {  // WARRIOR
                bAttack.setDisable(false);
                bSpecialAttack.setDisable(true);
                bDefense.setDisable(false);
                bFood.setDisable(false);
                bPotion.setDisable(true);
            }
        }
        else
        {
            this.bFinDelaManche = true;

            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(false);

            myAttackListEnCours = getListCombantsAlive();
            if (myAttackListEnCours.size()==0){
                // on a gagne la manche
                // on a perdu la partie
                // on affiche le message de fin de jeu
                // on affiche le message de fin de jeu
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fin de la partie, Vous avez perdu la partie ");
                alert.setContentText("Vous avez perdu la partie");
                alert.showAndWait();
                System.exit(0);
            }
        }

    }

    private void resetButton(){
        int firstLivingAlly = trouverFirstLivingAlly();


        Combatant myHero = myHeroes[firstLivingAlly];

        LOGGER.warn("le premier attaquant toujours vivant est : " + myHero.toString());

        bAttack.setVisible(true);
        bSpecialAttack.setVisible(true);
        bDefense.setVisible(true);
        bFood.setVisible(true);
        bPotion.setVisible(true);

        if (myHero.getMyName().equals(Combatant.S_HUNTER)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(true);

        } else if (myHero.getMyName().equals(Combatant.S_MAGE)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(false);
        } else if (myHero.getMyName().equals(Combatant.S_HEALER)) {
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(false);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(false);
        } else {  // WARRIOR
            bAttack.setDisable(false);
            bSpecialAttack.setDisable(true);
            bDefense.setDisable(false);
            bFood.setDisable(false);
            bPotion.setDisable(true);
        }

    }

    private void startHeroTurn() {
        try{
            /// a verifier sauvegarde de la liste d'attaque initiale

            Combatant currentCombatant = myAttackListEnCours.get(0);

            LOGGER.warn("combien de combatants dans la liste " + myAttackListEnCours.size());

            if (bFirstAttackOfTheTurn)
            {
                // on remet le background a blanc
                int myCurrentColumn = currentCombatant.getPosition();

                if (myCurrentColumn>=2){
                    myCurrentColumn++;
                }

                Node myNode = getNodeFromGridPane(myBoardGame, myCurrentColumn, 3);
                GridPane gridpane = (GridPane)myNode;
                gridpane.setStyle("-fx-background-color: #f5f4f5;");
                bFirstAttackOfTheTurn = false;
            }

            currentCombatant = myAttackListEnCours.get(0);

            if (currentCombatant!=null){
                myAttackListEnCours.remove(0);
            }

            int myCurrentColumn = currentCombatant.getPosition();

            if (myCurrentColumn>=2){
                myCurrentColumn++;
            }

           // on remet le background a blanc
            Node myNode = getNodeFromGridPane(myBoardGame, myCurrentColumn, 3);
            GridPane gridpane = (GridPane)myNode;
            gridpane.setStyle("-fx-background-color: #f5f4f5;");

            Combatant newCombatant  = null;

            boolean bStop = false;
            int iCounter = myAttackListEnCours.size();

            if (iCounter==0)
            {
                bFinDesAttaques = true;  // on passe a l'ennemi
            }
            else {
                int i = 0;
                while (!bStop && (i <= iCounter)) {
                    if (myAttackListEnCours.get(i) != null && myAttackListEnCours.get(i).isAlive()) {
                        newCombatant = myAttackListEnCours.get(i);
                        bStop = true;
                    }
                    i++;
                }

                myCurrentColumn = newCombatant.getPosition();

                if (myCurrentColumn >= 2) {
                    myCurrentColumn++;
                }

                myNode = getNodeFromGridPane(myBoardGame, myCurrentColumn, 3);

                gridpane = (GridPane) myNode;

                gridpane.setStyle("-fx-background-color: " + COLOR_BACKGROUND + ";");

                // bAttack : mélée attaque
                // bouton 2 : attaque speciale
                // bouton 3 : defense
                // bouton 4 : food
                // bouton 5 : potion
                // désactive les buttons inutiles par rapport au premier attaquant : ctmp
                if (newCombatant.getMyName().equals(Combatant.S_HUNTER)) {
                    bAttack.setDisable(false);
                    bSpecialAttack.setDisable(false);
                    bDefense.setDisable(false);
                    bFood.setDisable(false);
                    bPotion.setDisable(true);
                } else if (newCombatant.getMyName().equals(Combatant.S_MAGE)) {
                    bAttack.setDisable(false);
                    bSpecialAttack.setDisable(false);
                    bDefense.setDisable(false);
                    bFood.setDisable(false);
                    bPotion.setDisable(false);
                } else if (newCombatant.getMyName().equals(Combatant.S_HEALER)) {
                    bAttack.setDisable(false);
                    bSpecialAttack.setDisable(false);
                    bDefense.setDisable(false);
                    bFood.setDisable(false);
                    bPotion.setDisable(false);
                } else {  // WARRIOR
                    bAttack.setDisable(false);
                    bSpecialAttack.setDisable(true);
                    bDefense.setDisable(false);
                    bFood.setDisable(false);
                    bPotion.setDisable(true);
                }
            }

        }
        catch (Exception e)
        {
            LOGGER.warn("--------------------probleme de miose a jour : " + e.toString());
        }
        LOGGER.warn("------> fin du tour " + bFinDesAttaques);

        bFinDelaManche = !isEnnemyAlive();

        Alert alert = null;

        if (bFinDelaManche) {
            nbOfTurn++;
            if (nbOfTurn<NB_TOUR_MAX){
        //    if (nbOfTurn<1){   // pour les tests on ne fait que 1 tour avant le boss  // TODO : a enlever

                LOGGER.warn("on cache les écrans pour les mises à jour des personnages");
                myHeroesList.setVisible(false);
                myBoardGame.setVisible(false);

                    vbMyHeroesCare.setStyle("-fx-background-color:#cef76f");

                // on recherche les vivants
                int compteur = 0;
                Combatant c[] =  myHeroes;

                 if (c!=null) {
                    for (int i=0;i<c.length;i++) {
                        LOGGER.warn("-----------------------> Ally en cours " + c[i]);

                        switch (c[i].getPosition()){
                            case 0 : cbHeroCare1.setDisable(!c[i].isAlive()) ;
                                break;
                            case 1 : cbHeroCare2.setDisable(!c[i].isAlive()) ;
                                break;
                            case 2 : cbHeroCare3.setDisable(!c[i].isAlive()) ;
                                break;
                            case 3 : cbHeroCare4.setDisable(!c[i].isAlive()) ;
                                break;                            
                        }
                    }
                }

                vbMyHeroesCare.setVisible(true);

            }
            else if (!bFinDuJeu){

                LOGGER.warn("on cache les écrans pour les mises à jour des personnages");
                myHeroesList.setVisible(false);
                myBoardGame.setVisible(false);

          //      vbMyHeroesCare.setStyle("-fx-background-color:#cef76f");

                // on recherche les vivants
                int compteur = 0;
                Combatant c[] =  myHeroes;

                if (c!=null) {
                    for (int i=0;i<c.length;i++) {
                        LOGGER.warn("Ally en cours " + c[i]);

                        switch (c[i].getPosition()){
                            case 0 : cbHeroCare1.setDisable(!c[i].isAlive()) ;
                                break;
                            case 1 : cbHeroCare2.setDisable(!c[i].isAlive()) ;
                                break;
                            case 2 : cbHeroCare3.setDisable(!c[i].isAlive()) ;
                                break;
                            case 3 : cbHeroCare4.setDisable(!c[i].isAlive()) ;
                                break;
                        }
                    }
                }

                vbMyHeroesCare.setVisible(true);
                bFinDuJeu=true;
            }
            else {
                // on a fini
                  alert = new Alert(Alert.AlertType.CONFIRMATION);
                  alert.setTitle("Vous avez gagné le jeu ");
                  alert.setContentText("Vous avez gagné le jeu");
                  alert.showAndWait();
                  System.exit(0);
            }


            // reset du board ennemy et choix Potion / Food
        }
    }

    @FXML
    protected void onActionButtonMiseAJourCare(Event e){

        // on met à jour tous les personnages
        for (Combatant c : myHeroes){
            if (c!=null && c.isAlive()) {
                LOGGER.warn("avant mise a jour du personnage [["+ c.toString()+"]");

                if (cbHeroCare1.getValue().toString().equalsIgnoreCase(ACTION_CARE_RESET)){
                    c.setHealthPoints(c.getMaxHealthPoints());
                }
                else {
                    c.setMaxAttackPoints(c.getMaxAttackPoints()+1);
                }

                LOGGER.warn("apres mise à jour du personnage [["+ c.toString()+"]" );

                int myPositon = c.getPosition();
                if (myPositon>= 2) {
                    myPositon++;
                }
                drawLabel(c, myPositon, 4) ;
            }
        }

        // on doit rafraichir l'affichage des heros
        // on reselection le premier dans les checkbox
        cbHeroCare1.setValue(ACTION_CARE_RESET);
        cbHeroCare2.setValue(ACTION_CARE_RESET);
        cbHeroCare3.setValue(ACTION_CARE_RESET);
        cbHeroCare4.setValue(ACTION_CARE_RESET);

        resetEnemyBoard();

//        if (nbOfTurn<1){   // pour test de debug on ne fait que 1 tour avant le boss  // TODO : a enlever
        if (nbOfTurn<NB_TOUR_MAX){
            enemyOrcBoardGame();
        }
        else{
            enemyTrollKingBoardGame();
        }

        resetAllyBoard();
        myHeroesList.setVisible(false);
        vbMyHeroesCare.setVisible(false);
        myBoardGame.setVisible(true);

    }


    /**
     * cherche un enemy vivant
     * @return la position de l'ennemy dans la liste myEnnemies
     */
    private int trouverUnEnnemy(){
        int position = 0;
        boolean bTrouve = false;
        int tmpLoop = 0;
        while (!bTrouve && tmpLoop < 10){
            Random nbEnnemy = new Random();
            int nb = nbEnnemy.nextInt(myEnemies.length);
            if (myEnemies[nb].isAlive()){
                bTrouve = true;
                position = nb;
            }
            tmpLoop++;
        }
        if (bTrouve){
            return position;
        } else {
            return -1;
        }
   }

   private boolean isEnnemyAlive(){
        boolean bAlive = false;

           boolean bTrouve = false;
           int tmpLoop = 0;
           for (Enemy myEnnemy : myEnemies)
           {
               if ((myEnnemy != null ) && (myEnnemy.isAlive()))
               {
                   bAlive = true;
               }
          }
           return (bAlive);
   }

    private int trouverFirstLivingAlly(){
        int position = -1;
        boolean bTrouve = false;
        int tmpLoop = 0;
        while (!bTrouve && (tmpLoop<myHeroes.length)){
            if ((myHeroes[tmpLoop]!=null) & myHeroes[tmpLoop].isAlive()){
                bTrouve = true;
                position = tmpLoop;
            }
            tmpLoop++;
        }
        return (position);
    }
   private int trouverUnAlly(){
        int position = 0;
        boolean bTrouve = false;
        int tmpLoop = 0;
        while (!bTrouve && tmpLoop < 10){
            Random nbAlly = new Random();
            int nb = nbAlly.nextInt(myHeroes.length);
            if (myHeroes[nb].isAlive()){
                bTrouve = true;
                position = nb;
            }
            tmpLoop++;
        }
        return (position);
   }
   private void drawAfterFightEnemy(Combatant c, int col, int row){
       Node myNode = null ;
       Image myImageTmp = null;
       String imageName = null;

       if (!c.isAlive())
        {
            // il est mort
            imageName = RIP_FILE;
        }
       else {
           if (c.getMyName().equalsIgnoreCase(Combatant.S_WARRIOR)) {
               imageName = WARRIOR_FILE;
           } else if (c.getMyName().equalsIgnoreCase(Combatant.S_HEALER) ){
               imageName = HEALER_FILE;
           } else if (c.getMyName().equalsIgnoreCase(Combatant.S_MAGE)) {
               imageName = MAGE_FILE;
           } else if (c.getMyName().equalsIgnoreCase(Combatant.S_HUNTER)) {
               imageName = HUNTER_FILE;
           } else if (c.getMyName().equalsIgnoreCase(Combatant.S_ORC)) {
               imageName = ORC_FILE;
           } else if (c.getMyName().equalsIgnoreCase(Combatant.S_TROLL)) {
               imageName = TROLLKING_FILE;
           }
           else {
               LOGGER.error("combatant inconnu, on considere qu'il est mort");
               imageName = RIP_FILE;
           }
       }

       myImageTmp = new Image(getClass().getResourceAsStream(imageName), 120, 120, true, true);

       ImageView myImageView = null;
       GridPane gridpane = (GridPane)getNodeFromGridPane(myBoardGame, col, (row));
       LOGGER.warn("col = "+col+" row = "+row);
       if (gridpane==null){
           LOGGER.warn("-- la grid est nulle c'est bizarre ");
           gridpane = new GridPane();
       }

       if((gridpane.getChildren()!=null)) {
           //  TBD : il faut supprimer l'ancien
           gridpane.getChildren().remove(0);
           gridpane.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream(imageName), 120, 120, true, true)));
        }
        else{
          gridpane.add(new ImageView(new Image(getClass().getResourceAsStream(imageName), 120, 120, true, true)), col, (row));
        }

        myNode = getNodeFromGridPane(myBoardGame, col, (row+1));
       LOGGER.warn("-- myNode 11111111111111111111 " + myNode);
       if (myNode != null){
            ((Label)myNode).setText(c.toString());
        }
    }

    private void resetEnemyBoard(){

        /**for (int i = 0; i < 4; i++) {    // on efface le board, si marche pas mettre 4
            try {

                GridPane gridpane = (GridPane) getNodeFromGridPane(myBoardGame, i, 0);
                try{
                    gridpane.getChildren().removeAll();
                } catch (Exception e) {

                }

                Label myLabel = (Label) getNodeFromGridPane(myBoardGame, i, 1);
                if (myLabel!=null){
                    myLabel.setText("");
                }
            } catch (Exception e) {
                LOGGER.warn("resetEnemyBoard : " + e.toString());
            }
        }**/
        try {
            GridPane gridpane = (GridPane) getNodeFromGridPane(myBoardGame, 0, 0);
            if ( gridpane!=null && gridpane.getChildren()!=null) {
                LOGGER.warn("11111111111111111111111111 " + gridpane.getChildren());
                gridpane.getChildren().remove(0);
                LOGGER.warn("11111111111111111111111111 " + gridpane.getChildren());
            }

            gridpane = (GridPane) getNodeFromGridPane(myBoardGame, 1, 0);
            if ( gridpane!=null && gridpane.getChildren()!=null) {
                LOGGER.warn("22222222222222222222222222 " + gridpane.getChildren());
                gridpane.getChildren().remove(0);
                LOGGER.warn("22222222222222222222222222 " + gridpane.getChildren());
            }
            gridpane = (GridPane) getNodeFromGridPane(myBoardGame, 2, 0);
            if ( gridpane!=null && gridpane.getChildren()!=null) {
                LOGGER.warn("33333333333333333333333333 " + gridpane.getChildren());
                gridpane.getChildren().remove(0);
                LOGGER.warn("33333333333333333333333333 " + gridpane.getChildren());
            }
            gridpane = (GridPane) getNodeFromGridPane(myBoardGame, 3, 0);
            if ( gridpane!=null && gridpane.getChildren()!=null) {
                LOGGER.warn("44444444444444444444444444 " + gridpane.getChildren());
                gridpane.getChildren().remove(0);
                LOGGER.warn("44444444444444444444444444 " + gridpane.getChildren());
            }


            Label myLabel = (Label) getNodeFromGridPane(myBoardGame, 0, 1);
            myLabel.setText("");
            myLabel = (Label) getNodeFromGridPane(myBoardGame, 1, 1);
            myLabel.setText("");
            myLabel = (Label) getNodeFromGridPane(myBoardGame, 2, 1);
            myLabel.setText("");
            myLabel = (Label) getNodeFromGridPane(myBoardGame, 3, 1);
            myLabel.setText("");

        } catch (Exception e) {
            LOGGER.warn("resetEnemyBoard : " + e.toString());
        }
    }





    private void resetAllyBoard(){

        for (Combatant c : myHeroes) {    // on efface le board,si marche pas mettre 4
            try {
                int position = c.getPosition();
                if (position>=2){
                    position++;
                }

                if (c.isAlive()){
                    if (c.getMyName().equalsIgnoreCase(Combatant.S_WARRIOR)) {
                        drawImage(WARRIOR_FILE, position, 3);
                    }
                    else if (c.getMyName().equalsIgnoreCase(Combatant.S_HEALER) ){
                        drawImage(HEALER_FILE, position, 3);
                    } else if (c.getMyName().equalsIgnoreCase(Combatant.S_MAGE)) {
                        drawImage(MAGE_FILE, position, 3);
                    } else if (c.getMyName().equalsIgnoreCase(Combatant.S_HUNTER)) {
                        drawImage(HUNTER_FILE, position, 3);
                    }
                }
                else {
                    drawImage(RIP_FILE, position, 3);
                }
            } catch (Exception e) {
            }
        }
    }
    @FXML
    protected void onActionButtonSpecial(ActionEvent event) {
        //on a le personnage de selectionné
        //on va attaquer un ennemi ou soigner un allié
        int positionDeLennemi = trouverUnEnnemy();    // position d'un ennemi vivant dans le tableau myEnemies
        Enemy myEnnemy = null;


        Combatant ctmp = myAttackListEnCours.get(0);

        if (ctmp.getMyName().equals(Combatant.S_HUNTER)) {
            // on décoche une flèche sur l'ennemi
            myEnnemy = (Enemy) myEnemies[positionDeLennemi];
            ctmp.doMyAction(Constant.ACTION_SHOOT, 20, myEnnemy);
            myEnemies[positionDeLennemi] = myEnnemy;
            drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi, 0);

            if (ctmp.getPosition()<2){
                drawAfterFightEnemy(ctmp, ctmp.getPosition(), 3);
            } else {
                drawAfterFightEnemy(ctmp, ctmp.getPosition()+1, 3);
            }

        }else if(ctmp.getMyName().equals(Combatant.S_MAGE)) {
            // on lance un sort sur l'ennemi
            myEnnemy = (Enemy) myEnemies[positionDeLennemi];
            Mage myMage = (Mage) ctmp;
            ctmp.doMyAction(Constant.ACTION_CAST, myMage.getMaxMagicPoints(), myEnnemy);
            myEnemies[positionDeLennemi] = myEnnemy;
            drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi, 0);

            if (ctmp.getPosition()<2){
                drawAfterFightEnemy(ctmp, ctmp.getPosition(), 3);
            } else {
                drawAfterFightEnemy(ctmp, ctmp.getPosition()+1, 3);
            }

        }else if(ctmp.getMyName().equals(Combatant.S_HEALER)) {
            // on soigne un allié
            int positionDeLally = trouverUnAlly();  // position dans le tableau des ally

            Combatant myAlly =(Combatant) myHeroes[positionDeLally];

            positionDeLally = myAlly.getPosition();   // la position sur le board

            Healer myHealer = (Healer) ctmp;

            ctmp.doMyAction(Constant.ACTION_HEAL,myHealer.getMaxMagicPoints(), myAlly);

            if (positionDeLally<2){
                drawAfterFightEnemy(myAlly, positionDeLally, 3);
            } else {
                drawAfterFightEnemy(myAlly, positionDeLally+1, 3);
            }
        }


        startHeroTurn();
        if (bFinDesAttaques)
        {
            ennemyAttack();

        }

    }


    @FXML
    protected void onActionButtonDefense(ActionEvent event) {

        Combatant ctmp =myAttackList.get(attaquantEnCours);
        ctmp.doMyAction(Constant.ACTION_DEFENSE, 0, null);

        startHeroTurn();


        if (bFinDesAttaques)
        {
            ennemyAttack();

        }

    }

    /**
     * on passe au tour suivant, les combatants qui étaient en position défense perdent leur postion défense pour le tour suivant
     */
    private void resetDefense(){
        for (Combatant c : myAttackList){
            c.setDefending(false);
        }
    }
    @FXML
    protected void onActionButtonAttack(ActionEvent event) {
        // on a le personnage de sélectionné
        // on va attaquer un ennemi
        int positionDeLennemi = trouverUnEnnemy();

        Enemy myEnnemy = null;

        try{
            Combatant ctmp = myAttackListEnCours.get(0);   // l'allié qui va attaquer
            myEnnemy = (Enemy) myEnemies[positionDeLennemi]; // l'ennemi qui va être attaqué
            LOGGER.warn("-->l'attaquant : " + ctmp.toString() + " va attaquer l'ennemi : " + myEnnemy.toString());

            // on fait l'attaque
            ctmp.doMyAction(Constant.ACTION_MEELE_ATTACK, ctmp.getMaxAttackPoints(), myEnnemy);
            LOGGER.warn("-->l'ennemi après l'attaque : " + myEnnemy.toString());

            myEnemies[positionDeLennemi] = myEnnemy; // on sauvegarde le changement

            // on met à jour l'affichage de l'ennemi
            drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi, 0 );
        }
        catch(Exception e){
            LOGGER.error("erreur : " + e.getMessage());
        }

        startHeroTurn();

        if (bFinDesAttaques)
        {
            ennemyAttack();

        }
    }
    @FXML
    protected void onActionButtonFood(ActionEvent event) {
        Combatant ctmp =myAttackListEnCours.get(attaquantEnCours);
        Hero my = (Hero)ctmp;
        Food myFood = my.getMyFood();
        myFood.updateQuantity(-1);
        ctmp.doMyAction(Constant.ACTION_FOOD, myFood.getPower(), null);

        int position = ctmp.getPosition();
        if (position<2){
            drawAfterFightEnemy(ctmp, position, 3);
        } else {
            drawAfterFightEnemy(ctmp, position+1, 3);
        }

        startHeroTurn();

        if (bFinDesAttaques)
        {
            ennemyAttack();

        }

    }
    @FXML
    protected void onActionButtonPotion(ActionEvent event) {
        // on a le personnage de sélectionné
        // on va boire une potion

        Combatant ctmp =myAttackListEnCours.get(attaquantEnCours);

        if(ctmp.getClass().equals(Mage.class)){
            Mage my = (Mage)ctmp;
            Potion myPotions = my.getMyPotion();
            myPotions.updateQuantity(-1);
            ctmp.doMyAction(Constant.ACTION_POTION, myPotions.getPower(), null);
        } else if(ctmp.getClass().equals(Healer.class)){
            Healer my = (Healer) ctmp;
            Potion myPotions = my.getMyPotion();
            myPotions.updateQuantity(-1);
            ctmp.doMyAction(Constant.ACTION_POTION, myPotions.getPower(), null);
        }

        int position = ctmp.getPosition();
        if (position<2){
            drawAfterFightEnemy(ctmp, position, 3);
        } else {
            drawAfterFightEnemy(ctmp, position+1, 3);
        }

        startHeroTurn();

        LOGGER.warn("apres avoir bu  la potion " + ctmp.toString());

        if (bFinDesAttaques)
        {
            ennemyAttack();
        }
    }

    /**
     * prépare le board, instancie les personnages et lance le jeu pour un maximum de 5 tours
     * @param event correspond à l'action sur le bouton
     *
     * @author  A. N.
     * @version 1.0
     */

    @FXML
    protected void onStartFightButtonClick(ActionEvent event) {
        int nbOfHeroes = 1;
        try {
            nbOfHeroes = Integer.parseInt(cbNumberOfHeroes.getValue().toString());
        } catch (NumberFormatException ex) {
            LOGGER.error("erreur de conversion de la chaine en entier");
        }

        if (cbHero1 == null || cbHero2 == null || cbHero3 == null || cbHero4 == null) {
            cbHero1 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 0);
            cbHero2 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 1);
            cbHero3 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 2);
            cbHero4 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 3);
            LOGGER.warn("cbHero1 = " + cbHero1 + " cbHero2 = " + cbHero2 + " cbHero3 = " + cbHero3 + " cbHero4 = " + cbHero4);
        }

        LOGGER.warn("mise à jour de la liste possible de Héros, on a  " + nbOfHeroes + " héros");

        cbHero2.setVisible(false);
        cbHero2.setDisable(true);

        cbHero3.setVisible(false);
        cbHero3.setDisable(true);

        cbHero4.setVisible(false);
        cbHero4.setDisable(true);

        switch (nbOfHeroes) {

            case 4:
                cbHero4.setVisible(true);
                cbHero4.setDisable(false);

                cbHero3.setVisible(true);
                cbHero3.setDisable(false);

                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
            case 3:
                cbHero3.setVisible(true);
                cbHero3.setDisable(false);

                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
            case 2:
                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
        }

        // on permutte les grid pour afficher les combats
        myHeroesList.setVisible(false);
        myBoardGame.setVisible(true);

        bFight.setVisible(false);
        bFight.setDisable(true);

        texteSelectionHero.setVisible(false);
        texteSelectionHero.setDisable(true);
        cbNumberOfHeroes.setVisible(false);
        cbNumberOfHeroes.setDisable(true);

        prepareBoardGame();
        enemyOrcBoardGame();

        myAttackList = Arrays.asList(myHeroes);
        Collections.shuffle(myAttackList);
        myAttackList.toArray(myHeroes);   // on mélange les héros, ca servira pour tous les combats

        myAttackListEnCours = new ArrayList<>(myAttackList);

        // décide de l'ordre d'attaque
        startFight();

    }


    /**
     * retourne le Node d'un grid pane à l'emplement col / row
     * @param gridPane : le gridpane a être parcouru
     * @param col : la colonne
     * @param row : la ligne
     * @return le Node trouvé, null si le node n'existe pas

     *
     * @author  A. N.
     * @version 1.0
     */
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {


        for (Node node : gridPane.getChildren()) {
            try {
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {

                    return node;
                }
            } catch (Exception e) {
                //   LOGGER.error("Exception " + e);
            }

        }
        return null;
    }


    @FXML
    /**
     * lis le nombre de héros sélectionnés et active les lignes correspondantes
     * @param e : l'action du cliquer sur le menu déroulant
     *
     * @author  A. N.
     * @version 1.0
     */
    protected void onCbButtonClick(Event e) {

        int nbOfHeroes = 1;
        try {
            nbOfHeroes = Integer.parseInt(cbNumberOfHeroes.getValue().toString());
        } catch (NumberFormatException ex) {
            LOGGER.error("erreur de conversion de la chaine en entier");
        }

        if (cbHero1 == null || cbHero2 == null || cbHero3 == null || cbHero4 == null) {
            cbHero1 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 0);
            cbHero2 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 1);
            cbHero3 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 2);
            cbHero4 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 3);

        }

        LOGGER.warn("on a sélectionné [" + nbOfHeroes + "] héros pour la partie");

        // on cache les lignes inutiles
        cbHero2.setVisible(false);
        cbHero2.setDisable(true);
        cbHero3.setVisible(false);
        cbHero3.setDisable(true);
        cbHero4.setVisible(false);
        cbHero4.setDisable(true);

        // on affiche les lignes nécessaires
        switch (nbOfHeroes) {

            case 4:
                cbHero4.setVisible(true);
                cbHero4.setDisable(false);

                cbHero3.setVisible(true);
                cbHero3.setDisable(false);

                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
            case 3:
                cbHero3.setVisible(true);
                cbHero3.setDisable(false);

                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
            case 2:
                cbHero2.setVisible(true);
                cbHero2.setDisable(false);
                break;
        }
    }


}


/**+
 * petit bug affichage après attaque spéciale d'un heal ou d'un mage affiche mauvais label
 * un bug sur les attaques a verifier
 * quand on fait une potion ou un heal on affiche plus les bonnes stats, faut mettre a jour juste la cible
 */