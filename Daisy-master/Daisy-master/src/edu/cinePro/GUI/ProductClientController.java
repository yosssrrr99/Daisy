/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Followingproduit;
import edu.cinePro.entities.Panier;
import edu.cinePro.entities.Produit;
import edu.cinePro.services.FollowingProduitMethods;
import edu.cinePro.services.PanierCRUD;
import edu.cinePro.services.ProduitMethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
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
    @FXML
    private Spinner<Integer> quantiteProduit;
    @FXML
    private Button avertirDispo;
    @FXML
    private TextField idProduitChosen;
    @FXML
    private Button nosFilmsButtons;
    @FXML
    private Button nosPublicationsButton;
    @FXML
    private Button nosEvenementsButton;
    @FXML
    private Button réservationsButton;
    @FXML
    private Button shouvenirShopButton;
    @FXML
    private Button parametreButton;
    @FXML
    private Button donationButton;
    @FXML
    private ImageView listeAchat;

    private int setChosenFruit(Produit P) {
        String color = "";
        int quantiteEnStock = P.getQuantiteEnStock();
        if (quantiteEnStock > 0) {
            quantiteProduit.setDisable(false);
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, quantiteEnStock, 0, 1);
            valueFactory.setValue(1);
            quantiteProduit.setValueFactory(valueFactory);
        } else {

            quantiteProduit.setDisable(true);

        }

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
            idProduitChosen.setText(String.valueOf(P.getIDProduit()));
            chosenFruitCard.setStyle("-fx-background-color: " + color + ";\n"
                    + "    -fx-background-radius: 30;");
            if (P.getQuantiteEnStock() == 0) {
                ajouterProduitPanier.setDisable(true);
                outOfStockLabel.setVisible(true);
                outOfStockLabel.setText("Stock épuisé");
                avertirDispo.setDisable(false);
                avertirDispo.setVisible(true);
            } else if (P.getQuantiteEnStock() < 5) {
                ajouterProduitPanier.setDisable(false);
                outOfStockLabel.setVisible(true);
                outOfStockLabel.setText(P.getQuantiteEnStock() + " en stock");
                avertirDispo.setDisable(true);
                avertirDispo.setVisible(false);
            } else {
                ajouterProduitPanier.setDisable(false);
                outOfStockLabel.setVisible(false);
                avertirDispo.setDisable(true);
                avertirDispo.setVisible(false);
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
        return P.getIDProduit();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ProduitMethods PM = new ProduitMethods();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PM.updateStockStatus();
            }
        }, 0, 1000);//1000 => une seconde delay entre l'execution du CRON mise à jour status stock 0 = epsuisé / 1 = en stock
        String keyWord = "";
        searchProductClient_ID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        String keyWord = searchProductClient_ID.getText().trim().toLowerCase();
                        Produit P = PM.recupProduitByDesignation_PV(keyWord);
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
        Collections.sort(listProduit);
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
        Produit P = PM.recupProduitByDesignation_PV(keyWord);
        System.out.println(P);
        if ((P.getDescription() != null) && (P.getDesignation() != null) && (P.getPrixVenteUnit() != 0.0)) {
            setChosenFruit(P);
        } else {
            setChosenFruit(listProduit.get(0));

        }
    }

    @FXML
    private void ajouterProduitPanier(ActionEvent event) {
        String idpan = "1";
        int idProduit = parseInt(this.idProduitChosen.getText());
        P = PM.recupProduit(idProduit);
        String idproduit = String.valueOf(P.getIDProduit());
        String idcl = "1";
        String idB = "1";
        String nompanier = "Panier";
        String stat = "0";
        String quantite = String.valueOf(quantiteProduit.getValue());

        Panier p1 = new Panier(idpan, idproduit, idcl, idB, nompanier, stat, quantite);
        PanierCRUD pc = new PanierCRUD();
        pc.ajouterPanier(p1);
        InputStream stream = null;
        try {
            stream = new FileInputStream("C:\\Users\\Asus\\Desktop\\CineProWithGUI\\src\\edu\\cinePro\\GUI\\images\\tick.png");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        Image image = new Image(stream);
        ImageView img = new ImageView();
        img.setImage(image);

        Notifications notificationBuilder = Notifications.create()
                .title("Produit ajoutée au panier avec succées. ")
                .text("Si vous voulez Consulter votre panier .Cliquez la-haut")
                .graphic(img)
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER);

        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void followProduct(ActionEvent event) {
        int idProduit = parseInt(this.idProduitChosen.getText());
        FollowingProduitMethods FPM = new FollowingProduitMethods();

        Followingproduit FP = new Followingproduit(idProduit, 1);
        FPM.ajouterFollowingproduit(FP);

    }

    @FXML
    private void showFilmsPage(ActionEvent event) {
    }

    @FXML
    private void showPublicationPage(ActionEvent event) {
    }

    @FXML
    private void showEvenementsPage(ActionEvent event) {
    }

    @FXML
    private void showReservationPage(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("billet.fxml"));

        try {
            Parent root = Loader.load();
            BilletController billets = Loader.getController();
            outOfStockLabel.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(BilletController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showSouvenirShopPage(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("productClient.fxml"));

        try {
            Parent root = Loader.load();
            ProductClientController products = Loader.getController();
            outOfStockLabel.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ProductClientController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showParametrePage(ActionEvent event) {
    }

    @FXML
    private void showDonationButton(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("don.fxml"));

        try {
            Parent root = Loader.load();
            DonController dons = Loader.getController();
            outOfStockLabel.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(DonController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void ConsulterListeAchat(MouseEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterAchat.fxml"));

        try {
            Parent root = loader.load();
            ConsulterAchatController ac = loader.getController();
            outOfStockLabel.getScene().setRoot(root);
        } catch (IOException ex) {
            //Logger.getLogger(ConsulterAchatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
