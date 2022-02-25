/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import com.jfoenix.validation.RequiredFieldValidator;
import edu.cinePro.entities.Produit;
import edu.cinePro.services.ProduitMethods;
import static java.awt.Event.ENTER;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane id;
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

    /**
     * Initializes the controller class.
     */
    private boolean EDIT = false, ADD = true;
    private int productID;
    Produit produit;
    ProduitMethods PM = new ProduitMethods();
    String PathProductImage = "";
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
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] Possible_Suggestions = {"HelloWorld", "jkeuu"};
    private Set<String> possibleSuggestions = new HashSet<>(Arrays.asList(Possible_Suggestions));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    public boolean containsOnlyNumbers(String text) {
        Pattern pattern = Pattern.compile("([+-]{0,1})?[\\d]*");
        Matcher matcher = pattern.matcher(text);
        boolean isMatch = matcher.matches();
        return isMatch;

    }

    private void validate() {

        StringBuilder errors = new StringBuilder();

        ObservableList<String> styleClassDesignation = designationProductText.getStyleClass();

        if (designationProductText.getText().trim().length() == 0) {
            errors.append("- Veuillez entrer une désignation.\n");
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
        } else if (!containsOnlyNumbers(quantiteStockText.getText().trim())) {
            errors.append("- Veuillez entrer un nombre dans le champs de quantité en stock\n");

        } else if ((parseFloat(quantiteStockText.getText().trim()) < 0) && (containsOnlyNumbers(quantiteStockText.getText().trim()))) {
            errors.append("- Le champs de quantité en stock ne doit pas contenir de valeur négatif\n");

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
        } else if (!containsOnlyNumbers(prixAchatProductText.getText().trim())) {
            errors.append("- Veuillez entrer un nombre dans le champs de prix d'achat\n");

        } else if ((parseFloat(prixAchatProductText.getText().trim()) < 0) && (containsOnlyNumbers(prixAchatProductText.getText().trim()))) {
            errors.append("- Le champs de prix d'achat ne doit pas contenir de valeur négatif\n");

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
        } else if (!containsOnlyNumbers(prixVenteProductText.getText().trim())) {
            errors.append("- Veuillez entrer un nombre dans le champs de prix d'achat\n");

        } else if ((parseFloat(prixVenteProductText.getText().trim()) < 0) && (containsOnlyNumbers(prixVenteProductText.getText().trim()))) {
            errors.append("- Le champs de prix d'achat ne doit pas contenir de valeur négatif\n");

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
        }  else {
            // remove all occurrences:
            styleClassImage.remove("error");

        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();

        }

    }

    @FXML
    private void saveProduct() {

        validate();
//             
//        String Designation = designationProductText.getText();
//        String Description = descriptionProductText.getText();
//        int quantiteEnStock = parseInt(quantiteStockText.getText());
//        float prixAchatUnit = Float.parseFloat(prixAchatProductText.getText());
//        float prixVenteUnit = Float.parseFloat(prixVenteProductText.getText());
//        String Image = PathProductImage;
//
//        if (EDIT) {
//
//            PM.modifierProduit(productID, Designation, Description, Image, quantiteEnStock, prixAchatUnit, prixVenteUnit, false);
//        } else if (ADD) {
//            Produit P = new Produit(Designation, Description, Image, quantiteEnStock, prixAchatUnit, prixVenteUnit, false);
//            PM.ajouterProduit(P);
//        }
//
//        designationProductText.setText("");
//        descriptionProductText.setText("");
//        quantiteStockText.setText(String.valueOf(""));
//        prixAchatProductText.setText(String.valueOf(""));
//        prixVenteProductText.setText(String.valueOf(""));
//        PathProductImage = "";
//
//        refreshTable();
//        ADD = true;
    }

    @FXML
    private void printReportProduct() {
        //JasperDesign jDesign= new
    }

    @FXML
    private void addNewProduct() {
        designationProductText.setText("");
        descriptionProductText.setText("");
        quantiteStockText.setText(String.valueOf(""));
        prixAchatProductText.setText(String.valueOf(""));
        prixVenteProductText.setText(String.valueOf(""));
        PathProductImage = "";
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
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("showProductDetails.fxml"));

        try {
            Parent root = Loader.load();
            ShowProductDetailsController productDetails = Loader.getController();
            Produit selected = TableViewProducts.getSelectionModel().getSelectedItem();
            productID = selected.getIDProduit();
            productDetails.setProductID(PM.recupProduit(productID));
            Scene productDetailScene = new Scene(root);
            Stage cineStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            cineStage.setScene(productDetailScene);

        } catch (IOException ex) {
            Logger.getLogger(ShowProductDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
