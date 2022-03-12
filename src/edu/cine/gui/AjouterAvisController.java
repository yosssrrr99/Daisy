/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.services.AvisCRUD;
import edu.cine.services.FilmCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private TableView<Avis> table;
    @FXML
    private TableColumn<Avis, Integer> fxida;
    @FXML
    private TableColumn<Avis, Integer>  fxidc;
    @FXML
    private TableColumn<Avis, Integer>  fxnb;
    @FXML
    private TableColumn<Avis, String>  fxc;
    @FXML
    private TextField a;
    @FXML
    private TextField c;
    @FXML
    private TextField nb;
    @FXML
    private TextField com;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void saveFilm(ActionEvent event) {
        
        int Num =Integer.parseInt(a.getText());
        int num2=Integer.parseInt(c.getText());
        
        int nbb=Integer.parseInt(nb.getText());
         String img =com.getText();
        
        
        Avis a = new Avis(Num, num2, nbb, img);
        AvisCRUD fc = new AvisCRUD();
        fc.ajouteAvis(a);
        
    
}

   

    
}
