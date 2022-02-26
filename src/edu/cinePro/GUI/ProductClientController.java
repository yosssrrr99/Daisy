/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Produit;
import edu.cinePro.services.ProduitMethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProductClientController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Produit P;
    public String[] mColors = {
        "39add1", // light blue
        "3079ab", // dark blue
        "c25975", // mauve
        "e15258", // red
        "f9845b", // orange
        "838cc7", // lavender
        "7d669e", // purple
        "53bbb4", // aqua
        "51b46d", // green
        "e0ab18", // mustard
        "637a91", // dark gray
        "f092b0", // pink
        "b7c0c7" // light gray
    };

    private ProduitMethods PM = new ProduitMethods();
    private List<Produit> listProduit = PM.affichageProduits();
    private MyListener myListener;
    @FXML
    private Text productDescription;
    @FXML
    private TextField searchProductClient_ID;
    @FXML
    private Button ajouterProduitPanier;
    @FXML
    private Label outOfStockLabel;

    private void setChosenFruit(Produit P) {
        String color = "";

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];

        InputStream stream = null;
        try {
            fruitNameLable.setText(P.getDesignation());
            fruitPriceLabel.setText(String.valueOf(new DecimalFormat("##.##").format(P.getPrixVenteUnit()) + " TD"));
            productDescription.setText(P.getDescription());
            stream = new FileInputStream(P.getImage());
            Image image = new Image(stream);
            fruitImg.setImage(image);
            chosenFruitCard.setStyle("-fx-background-color: " + color + ";\n"
                    + "    -fx-background-radius: 30;");
            if (P.getQuantiteEnStock() == 0) {
                ajouterProduitPanier.setDisable(true);
                outOfStockLabel.setVisible(true);
                outOfStockLabel.setText("Stock épuisé");
            }else if (P.getQuantiteEnStock() < 5) {
                ajouterProduitPanier.setDisable(false);
                outOfStockLabel.setVisible(true);
                outOfStockLabel.setText(P.getQuantiteEnStock()+" en stock");
            }  else {
                ajouterProduitPanier.setDisable(false);
                outOfStockLabel.setVisible(false);
            }
            randomNumber = 0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(ProductClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String keyWord = "";
        searchProductClient_ID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        String keyWord = searchProductClient_ID.getText().trim().toLowerCase();
                        Produit P = PM.recupProduitByDesignation_Description_PV(keyWord);
                        System.out.println(P);
                        if ((P.getDescription() != null) && (P.getDesignation() != null) && (P.getPrixVenteUnit() != 0.0)) {
                            setChosenFruit(P);
                        } else {
                            setChosenFruit(listProduit.get(0));

                        }
                        break;
                    default:
                        break;

                }
            }

        });

        System.out.println(keyWord);

        System.out.println(listProduit);
        if (listProduit.size() > 0) {

            setChosenFruit(listProduit.get(0));
            myListener = new MyListener() {

                @Override
                public void onClickListener(int IDproduit) {

                    Produit P = PM.recupProduit(IDproduit);
                    setChosenFruit(P);

                }
            };
        }
        // listProduit.addAll(getData());
        int column = 0;
        int row = 1;
        for (Produit P : listProduit) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("UnProduit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                UnProduitController unProduitController = fxmlLoader.getController();
                unProduitController.setData(P, myListener);
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                System.out.println("ereur here");
            }
        }

    }

    @FXML
    private void searchProduct(ActionEvent event) {
        String keyWord = searchProductClient_ID.getText().trim().toLowerCase();
        Produit P = PM.recupProduitByDesignation_Description_PV(keyWord);
        System.out.println(P);
        if ((P.getDescription() != null) && (P.getDesignation() != null) && (P.getPrixVenteUnit() != 0.0)) {
            setChosenFruit(P);
        } else {
            setChosenFruit(listProduit.get(0));

        }
    }

    @FXML
    private void ajouterProduitPanier(ActionEvent event) {
    }
}
