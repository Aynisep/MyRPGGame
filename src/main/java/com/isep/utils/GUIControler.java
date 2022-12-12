package com.isep.utils;

import com.isep.rpg.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.function.LongPredicate;

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
    private GridPane myBoardGame;
    @FXML
    private Button startFight;
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

    private int attaquantEnCours = 0;

    private Combatant[] myHeroes;

    private List<Combatant> myAttackList;

    private List <Combatant> myAttackListEnCours;
    private List <Combatant>  tmpMyAttackListEnCours;

    private final static String MAGE_FILE = "mage.png";
    private final static String WARRIOR_FILE = "warrior.png";
    private final static String HEALER_FILE = "healer.png";
    private final static String HUNTER_FILE = "hunter.png";
    private final static String ORC_FILE = "orc.png";

    private final static String RIP_FILE = "rip.png";
    private final static String COLOR_BACKGROUND = "#FFCCCC";

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

    private void drawImage(String myImage, int colunm, int row) {
        Image myImageTmp = new Image(getClass().getResourceAsStream(myImage), 125, 125, true, true);
        ImageView myImageView = new ImageView(myImageTmp);
        GridPane gridpane = new GridPane();
        gridpane.add(myImageView, 0, 0);
        myBoardGame.add(gridpane, colunm, row);
    }

    private void drawLabel(Object myHero, int colunm, int row) {
        Label myLabel = new Label();
        myLabel.setText(myHero.toString());
        myBoardGame.add(myLabel, colunm, row);
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

    protected void enemyBoardGame() {
        Random nbEnnemy = new Random();
        int nb = nbEnnemy.nextInt(5);
        if (nb==0){
            nb = 1;
        }

        myEnemies = new Enemy[nb];

        for (int i= 0; i < nb; i++) {

                myEnemies[i] = new Orc(200, 25, 20);
                drawImage(ORC_FILE, i,0);
                drawLabel(myEnemies[i], i, 1);
        }

    }

    /**
     * décide de l'ordre d'attaque
     */
    private void startFight() {
        LOGGER.warn("début des combats");

        myAttackList = Arrays.asList(myHeroes);
        Collections.shuffle(myAttackList);
        myAttackList.toArray(myHeroes);

        Combatant ctmp = myAttackList.get(0);
        LOGGER.warn("le premier attaquant est : " + ctmp.toString());

        int myAttaquantCollumn = ctmp.getPosition();

        if (myAttaquantCollumn>=2)
        {
            myAttaquantCollumn++;
        }
        Node myNode = getNodeFromGridPane(myBoardGame, myAttaquantCollumn, 3);

        GridPane gridpane = (GridPane)myNode;

        gridpane.setStyle("-fx-background-color: "+COLOR_BACKGROUND+";");

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

        attaquantEnCours = 0; // le premier attaquant est le premier de la liste myAttackList


        bFinDesAttaques = false;
        myAttackListEnCours = new ArrayList<>(myAttackList);
        tmpMyAttackListEnCours = new ArrayList<>(myAttackList);
        bFirstAttackOfTheTurn = false;
    }

    private void ennemyAttack(){

        bAttack.setDisable(false);
        bSpecialAttack.setDisable(false);
        bDefense.setDisable(false);
        bFood.setDisable(false);
        bPotion.setDisable(false);


        for (Enemy myEnnemy : myEnemies)
        {
            if ((myEnnemy != null) && (myEnnemy.isAlive()))
            {

                int positionDansLaListe = trouverUnAlly();
                if (positionDansLaListe<0){
                    // mettre un ecran de perdu  #TODO
                    resetDefense();
                }

                int column = myHeroes[positionDansLaListe].getPosition();

                if (column>=2)
                {
                    column++;
                }

                Combatant myAlly =(Combatant) myHeroes[positionDansLaListe];

                 myEnnemy.doMyAction(Constant.ACTION_MEELE_ATTACK, myEnnemy.getMaxAttackPoints(),myAlly);
                drawAfterFightEnemy(myAlly, column, 3);
            }
        }

        resetDefense();
        myAttackListEnCours = new ArrayList<Combatant>(myAttackList);   // la liste de mes combatants, si vide prend la liste initiale

        if (myHeroes!=null && myHeroes.length>0)
        {
            int positionDansLaListe = trouverFirstLivingAlly();
            LOGGER.warn("trouverFirstLivingAlly : " + positionDansLaListe);

            if (positionDansLaListe<0){
                // mettre un ecran de perdu  #TODO
                bFinDelaManche = true;
            }
            else {
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

        }
        else
        {
            this.bFinDelaManche = true;
        }

    }

    private void heroTurn() {
        try{
             Combatant currentCombatant = myAttackListEnCours.get(0);

            int myCurrentColumn = currentCombatant.getPosition();

            if (myCurrentColumn>=2){
                myCurrentColumn++;
            }

            // on remet le background a blanc
            Node myNode = getNodeFromGridPane(myBoardGame, myCurrentColumn, 3);
            GridPane gridpane = (GridPane)myNode;
            gridpane.setStyle("-fx-background-color: #FFFFFF;");

            Combatant newCombatant  = null;

            /// a verifier sauvegarde de la liste d'attaque initiale
            if (bFirstAttackOfTheTurn)
            {
                bFirstAttackOfTheTurn = false;
            }

            myAttackListEnCours.remove(0);

            boolean bStop = false;
            int iCounter = myAttackListEnCours.toArray().length;

            if (iCounter==0)
            {
                bFinDesAttaques = true;
            }


            int i = 0 ;
            while (!bStop && (i<=iCounter))
            {
                if (myAttackListEnCours.get(i)!=null && myAttackListEnCours.get(i).isAlive())
                {
                    newCombatant = myAttackListEnCours.get(i);
                    bStop=true;
                }
                i++;
            }

            myCurrentColumn = newCombatant.getPosition();

            if (myCurrentColumn>=2){
                myCurrentColumn++;
            }

            myNode = getNodeFromGridPane(myBoardGame, myCurrentColumn, 3);

            gridpane = (GridPane)myNode;

            gridpane.setStyle("-fx-background-color: "+COLOR_BACKGROUND+";");

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
        catch (Exception e)
        {
            bFinDesAttaques = true;
        }
        LOGGER.warn("------> fin du tour " + bFinDesAttaques);

        bFinDelaManche = !isEnnemyAlive();

        LOGGER.warn("------> finDelaManche  " + bFinDelaManche);


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

        if (!c.isAlive()){
            // il est mort
            drawImage(RIP_FILE, col, row);

            Image myImageTmp = new Image(getClass().getResourceAsStream(RIP_FILE), 125, 125, true, true);
            ImageView myImageView = new ImageView(myImageTmp);
            GridPane gridpane = (GridPane)getNodeFromGridPane(myBoardGame, col, (row));
            gridpane.getChildren().remove(0);
            gridpane.add(myImageView, 0, 0);
            myBoardGame.add(gridpane, col, row);

        }

        myNode = getNodeFromGridPane(myBoardGame, col, (row+1));

       LOGGER.warn("col=" + col + " row=" + row);
       LOGGER.warn("myNode=" + myNode );

       Label myLabel = (Label) myNode;

        myLabel.setText(c.toString());

       LOGGER.warn("le combatant = " + c.toString());

        myLabel.setText(c.toString());
        }


    @FXML
    protected void onActionButtonSpecial(ActionEvent event) {
        //on a le personnage de selectionné
        //on va attaquer un ennemi ou soigner un allié
        int positionDeLennemi = trouverUnEnnemy();    // position d'un ennemi vivant dans le tableau myEnemies
        Enemy myEnnemy = null;

        if (myAttackListEnCours==null || (myAttackListEnCours.size()==0)) {
            myAttackListEnCours = new ArrayList<Combatant>(myAttackList);   // la liste de mes combatants, si vide prend la liste initiale
        }

        Combatant ctmp = myAttackListEnCours.get(0);
        if (ctmp==null || ! ctmp.isAlive()){
            bFinDuJeu = true;
        }
        else {
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
                int positionDeLally = trouverUnAlly();
                LOGGER.warn("positionDeLally = " + positionDeLally);
                Combatant myAlly =(Combatant) myHeroes[positionDeLally];
                LOGGER.warn("myAlly=" + ctmp+ toString());
                Healer myHealer = (Healer) ctmp;
                ctmp.doMyAction(Constant.ACTION_HEAL,myHealer.getMaxMagicPoints(), myAlly);
                myHeroes[positionDeLally] = myAlly;


                if (positionDeLennemi<2){
                    drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi, 3);
                } else {
                    drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi+1, 3);
                }
            }
        }

        if(bFinDesAttaques || (bFinDelaManche))
        {
            // on affiche le message de fin de jeu
            // on affiche le message de fin de jeu
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de la manche ");
            alert.setHeaderText("Fin de la manche");
            alert.setContentText("Fin de la manche");
            alert.showAndWait();

            ennemyAttack();
        } else if (bFinDuJeu){
            // on affiche le message de fin de jeu
            // on affiche le message de fin de jeu
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin du jeu");
            alert.setHeaderText("Fin du jeu");
            alert.setContentText("Fin du jeu");
            alert.showAndWait();


        }
        else {
            heroTurn();
        }



    }


    @FXML
    protected void onActionButtonDefense(ActionEvent event) {

        Combatant ctmp =myAttackList.get(attaquantEnCours);
        ctmp.doMyAction(Constant.ACTION_DEFENSE, 0, null);


        for (Combatant c : myAttackList){
            LOGGER.warn("je defends : " + c.toString() +" mydefense="+c.isDefending());
        }
        heroTurn();


        if(bFinDesAttaques || (bFinDelaManche))
        {
            ennemyAttack();
        } else if (bFinDuJeu){}

    }
    private void resetDefense(){
        for (Combatant c : myAttackList){
            c.setDefending(false);
            LOGGER.warn("plus de defense : " + c.toString() +" mydefense="+c.isDefending());
        }
    }
    @FXML
    protected void onActionButtonAttack(ActionEvent event) {
        // on a le personnage de sélectionné
        // on va attaquer un ennemi
        int positionDeLennemi = trouverUnEnnemy();

        Enemy myEnnemy = null;

        try{
            if (myAttackListEnCours==null || (myAttackListEnCours.size()==0)) {
                myAttackListEnCours = new ArrayList<Combatant>(myAttackList);   // la liste de mes combatants, si vide prend la liste initiale
            }

            Combatant ctmp = myAttackListEnCours.get(0);

            LOGGER.warn("-->l'attaquant est : " + ctmp.toString());

            myEnnemy = (Enemy) myEnemies[positionDeLennemi];

            ctmp.doMyAction(Constant.ACTION_MEELE_ATTACK, ctmp.getMaxAttackPoints(), myEnnemy);

            myEnemies[positionDeLennemi] = myEnnemy;

            // on met à jour l'affichage
            drawAfterFightEnemy(myEnemies[positionDeLennemi], positionDeLennemi, 0 );

            LOGGER.warn("-->l'ennemi après l'attaque : " + myEnnemy.toString());
        }
        catch(Exception e){
            LOGGER.error("erreur : " + e.getMessage());
        }

        heroTurn();

        if(bFinDesAttaques || (bFinDelaManche))
        {
            // on affiche le message de fin de jeu
            // on affiche le message de fin de jeu
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de la manche ");
            alert.setContentText("Fin de la manche");
            alert.showAndWait();

            ennemyAttack();
        } else if (bFinDuJeu){
            // on affiche le message de fin de jeu
            // on affiche le message de fin de jeu
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin du jeu");
            alert.setContentText("Fin du jeu");
            alert.showAndWait();
        }
        else {
            heroTurn();
        }

    }
    @FXML
    protected void onActionButtonFood(ActionEvent event) {
        Combatant ctmp =myAttackList.get(attaquantEnCours);
        Hero my = (Hero)ctmp;
        Food myFood = my.getMyFood();
        myFood.updateQuantity(-1);
        ctmp.doMyAction(Constant.ACTION_FOOD, myFood.getPower(), null);
        drawAfterFightEnemy(ctmp, attaquantEnCours, 3);
        heroTurn();

        if(bFinDesAttaques || (bFinDelaManche))
        {
            ennemyAttack();
        } else if (bFinDuJeu){}

    }
    @FXML
    protected void onActionButtonPotion(ActionEvent event) {
        // on a le personnage de sélectionné
        // on va boire une potion

        Combatant ctmp =myAttackList.get(attaquantEnCours);

        if(ctmp.getClass().equals(Mage.class)){
            Mage my = (Mage)ctmp;
            Potion myPotions = my.getMyPotion();
            myPotions.updateQuantity(-1);
            ctmp.doMyAction(Constant.ACTION_POTION, myPotions.getPower(), null);
            LOGGER.warn("apres avoir bu une potion : " + ctmp.toString());
        } else if(ctmp.getClass().equals(Healer.class)){
            Healer my = (Healer) ctmp;
            Potion myPotions = my.getMyPotion();
            myPotions.updateQuantity(-1);
            ctmp.doMyAction(Constant.ACTION_POTION, myPotions.getPower(), null);
            LOGGER.warn("apres avoir bu une potion : " + ctmp.toString());

        }
        drawAfterFightEnemy(ctmp, attaquantEnCours, 3);
        heroTurn();


        if(bFinDesAttaques || (bFinDelaManche))
        {
            ennemyAttack();
        } else if (bFinDelaManche){}

    }

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
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
        LOGGER.warn("apres switch " + nbOfHeroes);

        myHeroesList.setVisible(false);
        myBoardGame.setVisible(true);

        bFight.setVisible(false);
        bFight.setDisable(true);
        texteSelectionHero.setVisible(false);
        texteSelectionHero.setDisable(true);
        cbNumberOfHeroes.setVisible(false);
        cbNumberOfHeroes.setDisable(true);

        prepareBoardGame();
        enemyBoardGame();

        // décide de l'ordre d'attaque
        startFight();

    }


    private ArrayList<ChoiceBox> myCbHeroes = new ArrayList<ChoiceBox>(4);

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

    /**
     * implementation du listener sur la choix du Héros
     */
    @FXML
    protected void onCbButtonClick(Event e) {

        LOGGER.error("onCbButtonClick " + e);


        // on recupere la ligne du choix du héros
        int nbOfHeroes = 1;
        try {
            nbOfHeroes = Integer.parseInt(cbNumberOfHeroes.getValue().toString());
        } catch (NumberFormatException ex) {
            LOGGER.error("erreur de conversion de la chaine en entier");
        }

        LOGGER.warn("------------------- cbHero1 = " + cbHero1 + " cbHero2 = " + cbHero2 + " cbHero3 = " + cbHero3 + " cbHero4 = " + cbHero4);

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
        LOGGER.warn("apres switch " + nbOfHeroes);
    }

    /**
     * implementation du listener sur la choix du Héros
     */
    @FXML
    protected void myHeroUpdateProfile(Event e) {
        try {
            Label labelTitle = new Label("hero 1");
            TextField fieldUserName = new TextField();
            fieldUserName.setText("test");
            GridPane gp = (GridPane) e.getSource();
            gp.setVisible(true);
            gp.add(labelTitle, 0, 0);
            LOGGER.warn("affiche le grid" + gp.isVisible());
        } catch (Exception ex) {
        }
    }

}
