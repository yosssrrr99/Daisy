/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Client;
import edu.cinePro.entities.Followingproduit;
import edu.cinePro.entities.Produit;
import edu.cinePro.services.ClientMethods;
import edu.cinePro.services.FollowingProduitMethods;
import edu.cinePro.services.ProduitMethods;
import static java.awt.Event.ENTER;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_add_new;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private Button btn_print_preview;
    @FXML
    private TableView<Produit> TableViewProducts;
    @FXML
    private TableColumn<Produit, String> product_ID_COL;
    @FXML
    private TableColumn<Produit, String> product_designation_COL;
    @FXML
    private TableColumn<Produit, Integer> product_quantite_en_stock_col;
    @FXML
    private TableColumn<Produit, Float> product_prix_achat_unit_col;
    @FXML
    private TableColumn<Produit, Float> product_prix_vente_unit_col;
    @FXML
    private TableColumn<Produit, String> description_productHidden_col;
    @FXML
    private TableColumn<Produit, String> imageHidden_col;
    @FXML
    private AnchorPane pane_top;
    @FXML
    private TextField designationProductText;
    @FXML
    private TextField prixAchatProductText;
    @FXML
    private TextField quantiteStockText;
    @FXML
    private TextArea descriptionProductText;
    @FXML
    private TextField prixVenteProductText;
    @FXML
    private Button ProduitImage;
    @FXML
    private Button showDetailsProduct;
    @FXML
    private TextField searchProductByID;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;

    /**
     * Initializes the controller class.
     */
    private boolean EDIT = false, ADD = true;
    private int productID;
    Produit produit;
    ProduitMethods PM = new ProduitMethods();
    String PathProductImage = "";

    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] Possible_Suggestions = {"Orange", "Kiwi"};
    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(Possible_Suggestions));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //CRON job remise 50% sur les produits lors de la periode du festival du JCC 
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(2022, 02, 01, 0, 0, 0);
//        Date dateDebutJCC = calendar1.getTime();
//        System.out.println(dateDebutJCC);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                PM.PromotionPrixVente();
//            }
//        }, dateDebutJCC, 360 * 24 * 60 * 60 * 1000);
        loadDate();
        refreshTable();

        btn_save.setOnAction(e -> {
            saveProduct();
        });
        btn_print_preview.setOnAction(e -> {
            printReportProduct();
        });
        btn_edit.setOnAction(e -> {
            ADD = false;
            EDIT = true;
            editProduct();
        });
        btn_add_new.setOnAction(e -> {
            EDIT = false;
            ADD = true;
            addNewProduct();
        });
        btn_delete.setOnAction(e -> {
            deleteProduct();
        });

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterVente = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        prixVenteProductText.setTextFormatter(formatterVente);
        TextFormatter formatterAchat = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        prixAchatProductText.setTextFormatter(formatterAchat);

        Pattern patternQuantite = Pattern.compile("\\d*");
        TextFormatter formatterQuantite = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQuantite.matcher(change.getControlNewText()).matches() ? change : null;
        });
        quantiteStockText.setTextFormatter(formatterQuantite);

        autoCompletionBinding = TextFields.bindAutoCompletion(searchProductByID, possibleSuggestions);
        searchProductByID.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        autoCompletionLearnWord(searchProductByID.getText().trim());
                        break;
                    default:
                        break;

                }
            }

        });

    }

    private void autoCompletionLearnWord(String trim) {
        possibleSuggestions.add(trim);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(searchProductByID, possibleSuggestions);
    }

    private void loadDate() {

        product_ID_COL.setCellValueFactory(new PropertyValueFactory<>("IDProduit"));
        product_designation_COL.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        product_quantite_en_stock_col.setCellValueFactory(new PropertyValueFactory<>("quantiteEnStock"));
        product_prix_achat_unit_col.setCellValueFactory(new PropertyValueFactory<>("prixAchatUnit"));
      
        product_prix_vente_unit_col.setCellValueFactory(new PropertyValueFactory<>("prixVenteUnit"));
        description_productHidden_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        description_productHidden_col.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    private void refreshTable() {

        List<Produit> mesProduits = PM.affichageProduits();
        ObservableList<Produit> ObservableListproduits = FXCollections.observableArrayList(mesProduits);
        TableViewProducts.setItems(ObservableListproduits);
        FilteredList<Produit> filteredProducts = new FilteredList<>(ObservableListproduits, b -> true);
        searchProductByID.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredProducts.setPredicate(p -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchProductKeyword = newValue.toLowerCase();
                if (String.valueOf(p.getIDProduit()).toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                } else if (p.getDesignation().toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                } else if (p.getDescription().toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                } else if (String.valueOf(p.getQuantiteEnStock()).toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                } else if (String.valueOf(p.getPrixAchatUnit()).toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                } else if (String.valueOf(p.getPrixVenteUnit()).toLowerCase().indexOf(searchProductKeyword) > -1) {
                    return true;
                }

                return false;
            });
        });

        SortedList<Produit> sortedProducts = new SortedList<>(filteredProducts);
        sortedProducts.comparatorProperty().bind(TableViewProducts.comparatorProperty());
        TableViewProducts.setItems(sortedProducts);

    }

    private void addProduct(ActionEvent event) {
        designationProductText.setText("");
        descriptionProductText.setText("");
        quantiteStockText.setText(String.valueOf(""));
        prixAchatProductText.setText(String.valueOf(""));
        prixVenteProductText.setText(String.valueOf(""));

    }

    @FXML
    private void editProduct() {
        ObservableList<String> styleClassDesignation = designationProductText.getStyleClass();
        styleClassDesignation.remove("error");
        ObservableList<String> styleClassDescription = descriptionProductText.getStyleClass();
        styleClassDescription.remove("error");
        ObservableList<String> styleClassQuantiteStock = quantiteStockText.getStyleClass();
        styleClassQuantiteStock.remove("error");
        ObservableList<String> styleClassPrixAchat = prixAchatProductText.getStyleClass();
        styleClassPrixAchat.remove("error");
        ObservableList<String> styleClassVenteAchat = prixVenteProductText.getStyleClass();
        styleClassVenteAchat.remove("error");
        ObservableList<String> styleClassImage = ProduitImage.getStyleClass();
        styleClassImage.remove("error");

        Produit selected = TableViewProducts.getSelectionModel().getSelectedItem();
        //txt_firstname.setText(selected.getpFirstname().get());
        productID = selected.getIDProduit();
        designationProductText.setText(selected.getDesignation());
        descriptionProductText.setText(selected.getDescription());
        quantiteStockText.setText(String.valueOf(selected.getQuantiteEnStock()));
        prixAchatProductText.setText(String.valueOf(selected.getPrixAchatUnit()));
        prixVenteProductText.setText(String.valueOf(selected.getPrixVenteUnit()));
        PathProductImage = selected.getImage();

    }

    @FXML
    private void deleteProduct() {
        Produit selected = TableViewProducts.getSelectionModel().getSelectedItem();
        productID = selected.getIDProduit();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppréssion produit");
        alert.setHeaderText(null);
        alert.setContentText("Vouliez vous supprimer le produit ? ");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {

            PM.supprimerProduit(productID);
            refreshTable();

        }
    }

    private boolean validate() {

        StringBuilder errors = new StringBuilder();
        boolean test = true;

        ObservableList<String> styleClassDesignation = designationProductText.getStyleClass();

        if (designationProductText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer une désignation.\n");
            if (!styleClassDesignation.contains("error")) {
                styleClassDesignation.add("error");

            }
        } else if ((PM.verifExistanceProduit(designationProductText.getText().trim()) == true) && (EDIT == false) && (ADD == true)) {
            errors.append("- Un produit ayant cette désignation existe.\n");
            if (!styleClassDesignation.contains("error")) {
                styleClassDesignation.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassDesignation.remove("error");
        }

        ObservableList<String> styleClassDescription = descriptionProductText.getStyleClass();

        if (descriptionProductText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer une description.\n");
            if (!styleClassDescription.contains("error")) {
                styleClassDescription.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassDescription.remove("error");

        }

        ObservableList<String> styleClassQuantiteStock = quantiteStockText.getStyleClass();

        if (quantiteStockText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer la quantité en stock.\n");
            if (!styleClassQuantiteStock.contains("error")) {
                styleClassQuantiteStock.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassQuantiteStock.remove("error");

        }

        ObservableList<String> styleClassPrixAchat = prixAchatProductText.getStyleClass();

        if (prixAchatProductText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer le prix d'achat.\n");
            if (!styleClassPrixAchat.contains("error")) {
                styleClassPrixAchat.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassPrixAchat.remove("error");

        }
        ObservableList<String> styleClassVenteAchat = prixVenteProductText.getStyleClass();

        if (prixVenteProductText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer le prix de vente.\n");
            if (!styleClassVenteAchat.contains("error")) {
                styleClassVenteAchat.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassVenteAchat.remove("error");

        }

        ObservableList<String> styleClassImage = ProduitImage.getStyleClass();

        if (PathProductImage.trim().length() == 0) {
            errors.append("- Veuillez choisir l'image du produit.\n");
            if (!styleClassImage.contains("error")) {
                styleClassImage.add("error");

            }
        } else {
            // remove all occurrences:
            styleClassImage.remove("error");

        }

//        if ((PM.verifExistanceProduit(designationProductText.getText().trim())==false) && (designationProductText.getText().trim().length() != 0)) {
//            errors.append("- Un produit ayant cette désignation existe déja.\n");
//            if (!styleClassDesignation.contains("error")) {
//                styleClassDesignation.add("error");
//
//            }
//        } else {
//            // remove all occurrences:
//            styleClassDesignation.remove("error");
//        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Erreurs dans la saisie des données");
            alert.setContentText(errors.toString());
            test = false;
            alert.showAndWait();

        }
        return test;
    }

    @FXML
    private void saveProduct() {

        if (validate()) {

            String Designation = designationProductText.getText();
            String Description = descriptionProductText.getText();
            int quantiteEnStock = parseInt(quantiteStockText.getText());
            float prixAchatUnit = Float.parseFloat(prixAchatProductText.getText());
            float prixVenteUnit = Float.parseFloat(prixVenteProductText.getText());
            String Image = PathProductImage;

            if (EDIT) {
                Produit selected = TableViewProducts.getSelectionModel().getSelectedItem();
                if ((selected.getQuantiteEnStock() == 0) && (quantiteEnStock > 0)) {
                    System.out.println("true");
                    try {
                        FollowingProduitMethods FPM = new FollowingProduitMethods();

                        List<Followingproduit> FP_list = FPM.affichageFollowingProduct();
                        FP_list.forEach((FP) -> {
                            if (FP.getIDProduit() == productID) {
                                ClientMethods CM = new ClientMethods();
                                Client C = CM.recupClient(FP.getIdClient());
                                System.out.println(C);

                                try {
                                    sendDispoEmail(C.getEmail());
                                } catch (Exception ex) {
                                    Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                FPM.supprimerFollowingproduit(productID, C.getIdClient());
                            }
                        });
                        // sendDispoEmail("yasmine.sliti@esprit.tn");

                        //1 est le idClient stat mais apres celui de la session connécté
                    } catch (Exception ex) {
                        Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("false");
                }
                PM.modifierProduit(productID, Designation, Description, Image, quantiteEnStock, prixAchatUnit, prixVenteUnit, true);
            } else if (ADD) {
                Produit P = new Produit(Designation, Description, Image, quantiteEnStock, prixAchatUnit, prixVenteUnit, true);
                PM.ajouterProduit(P);
            }

            refreshChampsSaisie();

            refreshTable();
            ADD = true;
        }
    }

    @FXML
    private void printReportProduct() {
        //JasperDesign jDesign= new
    }

    public void refreshChampsSaisie() {
        ObservableList<String> styleClassDesignation = designationProductText.getStyleClass();
        styleClassDesignation.remove("error");
        ObservableList<String> styleClassDescription = descriptionProductText.getStyleClass();
        styleClassDescription.remove("error");
        ObservableList<String> styleClassQuantiteStock = quantiteStockText.getStyleClass();
        styleClassQuantiteStock.remove("error");
        ObservableList<String> styleClassPrixAchat = prixAchatProductText.getStyleClass();
        styleClassPrixAchat.remove("error");
        ObservableList<String> styleClassVenteAchat = prixVenteProductText.getStyleClass();
        styleClassVenteAchat.remove("error");
        ObservableList<String> styleClassImage = ProduitImage.getStyleClass();
        styleClassImage.remove("error");

        designationProductText.setText("");
        descriptionProductText.setText("");
        quantiteStockText.setText(String.valueOf(""));
        prixAchatProductText.setText(String.valueOf(""));
        prixVenteProductText.setText(String.valueOf(""));
        PathProductImage = "";
    }

    @FXML
    private void addNewProduct() {
        refreshChampsSaisie();

    }

    @FXML
    private void chooseImageProduct(ActionEvent event
    ) {
        FileChooser FC = new FileChooser();
        FC.setInitialDirectory(new File("C:\\Users\\Asus\\Desktop\\CineProWithGUI\\ProductImages"));
        FC.getExtensionFilters().addAll(new ExtensionFilter("PNG files", "*.png"));
        File selectedFile = FC.showOpenDialog(null);
        PathProductImage = selectedFile.getAbsolutePath();
    }

    @FXML
    private void showDetailsProduct(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("showProductsDetails.fxml"));

        try {
            Parent root = Loader.load();
            ShowProductsDetailsController productDetails = Loader.getController();
            Produit selected = TableViewProducts.getSelectionModel().getSelectedItem();
            productID = selected.getIDProduit();
            productDetails.setProductID(PM.recupProduit(productID));
            Scene productDetailScene = new Scene(root);
            Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cineStage.setScene(productDetailScene);

        } catch (IOException ex) {
            Logger.getLogger(ShowProductsDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendDispoEmail(String receveur) throws Exception {

        System.out.println("Preparing to send email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "yasmin.slitti@gmail.com";
        String password = "azizaBalti1964*";

        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, receveur);
        Transport.send(message);
        System.out.println("message send successfully");

    }

    private Message prepareMessage(Session session, String myAccountEmail, String receveur) throws AddressException, MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receveur));
        message.setSubject("Disponibilité produit");
        Produit P = PM.recupProduit(productID);
        String htmlCode = "<h1> Chere Madame Yasmine SLITI </h1> <br/> <h2> <b> Le produit " + P.getDesignation() + " est de nouveau en stock . Merci d'utiliser notre application cinepro pour en commander. </b> </h2>";
        message.setContent(htmlCode, "text/html");
        return message;

    }
}
