/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.entities.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UnProduitController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    private MyListener myListener;
    private Produit P;
    //private Produit P = new Produit("55", "654", "oded", 55, 5.6, 6.5, true);
    @FXML
    private AnchorPane produit;
    @FXML
    private TextField productID;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(parseInt(productID.getText()));
        //System.out.println(productID.getText());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setData(Produit P,MyListener myListener) {
        InputStream stream = null;
        try {
            this.myListener = myListener;
            productID.setText(String.valueOf(P.getIDProduit()));
            nameLabel.setText(P.getDesignation());
            priceLable.setText(String.valueOf(new DecimalFormat("##.##").format(P.getPrixVenteUnit())+" TD"));
            stream = new FileInputStream(P.getImage());
            Image image = new Image(stream);
            img.setImage(image);
            System.out.println(P);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UnProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(UnProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
