/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Produit;
import edu.cinePro.services.ProduitMethods;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ShowProductDetailsController implements Initializable {

    private ImageView productDetailsImage;
    @FXML
    private TextField productDetailsID;
    @FXML
    private TextField productDetailsDesignation;
    @FXML
    private TextArea productDetailsDescription;
    @FXML
    private TextField productDetailsPA;
    @FXML
    private TextField productDetailPV;
    @FXML
    private TextField productDetailsStock;
    @FXML
    private TextField productDetailsProfitabilité;

    ProduitMethods PM = new ProduitMethods();
    @FXML
    private ImageView IMAGEviewID;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setProductID(Produit P) throws FileNotFoundException{
        
        productDetailsID.setText(String.valueOf(P.getIDProduit()));
        productDetailsDesignation.setText(P.getDesignation());
        productDetailsDescription.setText(P.getDescription());
        productDetailsPA.setText(String.valueOf(P.getPrixAchatUnit()));
        productDetailPV.setText(String.valueOf(P.getPrixVenteUnit()));
        productDetailsProfitabilité.setText(String.valueOf(P.calculateProductProfit(P)));
        productDetailsStock.setText(String.valueOf(P.getQuantiteEnStock()));
        InputStream stream = new FileInputStream(P.getImage());
        Image image = new Image(stream);
        IMAGEviewID.setImage(image);
        
    }
    
}


