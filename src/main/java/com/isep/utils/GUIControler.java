package com.isep.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.isep.rpg.Healer;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUIControler {
    @FXML
    private Label welcomeText;

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


    // Récupération de notre logger.
    private static final Logger LOGGER = LogManager.getLogger( GUIControler.class );

    @FXML
    protected void onHelloButtonClick() {
        LOGGER.info("Hello button clicked!");
        welcomeText.setText("Hello World!");

    }

    private String[] hero = {"Warrior","Hunter","Mage","Healer"};

    private ArrayList<ChoiceBox> myCbHeroes= new ArrayList<ChoiceBox>(4);

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
        implementation du listener sur la choix du Héros
    */
    @FXML
    protected void onCbButtonClick(Event e) {
            ChoiceBox<String> box = (ChoiceBox<String>) e.getSource();
            LOGGER.warn("on a selectionne " + box.getValue());

            // on recupere la ligne du choix du héros
            int nbOfHeroes=1;
            try {
                nbOfHeroes = Integer.parseInt(box.getValue());
            } catch (NumberFormatException ex) {
                LOGGER.error("erreur de conversion de la chaine en entier");
            }

            if (cbHero1==null || cbHero2==null || cbHero3==null || cbHero4==null) {
                cbHero1 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 0) ;
                cbHero2 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 1) ;
                cbHero3 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 2) ;
                cbHero4 = (ChoiceBox) getNodeFromGridPane(myHeroesList, 1, 3) ;
                LOGGER.warn("cbHero2 = " + cbHero2 + " cbHero3 = " + cbHero3 + " cbHero4 = " + cbHero4);
            }

        LOGGER.warn("mise à jour de la liste possible de Héros  "  );

        cbHero2.setDisable(true);
        cbHero3.setDisable(true);
        cbHero4.setDisable(true);

        switch (nbOfHeroes) {
            case 4:
                cbHero4.setDisable(false);
            case 3:
                cbHero3.setDisable(false);
            case 2:
                cbHero2.setDisable(false);
            case 1:
                break;
            default:
                break;
        }

       }


        /**
         implementation du listener sur la choix du Héros
         */
        @FXML
        protected void myHeroUpdateProfile(Event e) {
            try {
                Label labelTitle = new Label("hero 1");
                TextField fieldUserName = new TextField();
                fieldUserName.setText("test");
                GridPane gp = (GridPane) e.getSource();

                gp.isVisible();
                gp.setVisible(true);

                gp.add(labelTitle, 0, 0);

                LOGGER.warn("affiche le grid" + gp.isVisible());


            }
            catch (Exception ex) {
                // ignore
            }

        }
    public class Controller implements Initializable{

        @FXML
        private Label myLabel;

        @FXML
        private ChoiceBox<String> myChoiceBox;


        @Override
        public void initialize(URL arg0, ResourceBundle arg1) {

            myChoiceBox.getItems().addAll(hero);
            myChoiceBox.setOnAction(this::getHero);

        }

        public void getHero(ActionEvent event) {

            String myHero = myChoiceBox.getValue();
            myLabel.setText(myHero);
        }

    }

}
