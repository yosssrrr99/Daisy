/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import edu.cine.entities.Film;
import edu.cine.entities.Reservation;
import edu.cine.services.FilmCRUD;
import edu.cine.services.ReservationCRUD;
import edu.cine.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReservaController implements Initializable {

    @FXML
    private TableView<Reservation> table;
    @FXML
    private Button btnUpdate;
    private Button btnDelete;
    @FXML
    private Button btnInsert;
    @FXML
    private Label label;
    @FXML
    private TableColumn<Reservation, Integer> fxres;
    @FXML
    private TableColumn<Reservation, String> fxcat;
    @FXML
    private TableColumn<Reservation, Integer> fxF;
    @FXML
    private TableColumn<Reservation, Integer>  fxNb;
    @FXML
    private TableColumn<Reservation, String> fxDeb;
    @FXML
    private TableColumn<Reservation, String> fxFin;
    @FXML
    private TableColumn<Reservation, Integer>  fxSa;
    @FXML
    private TableColumn<Reservation, Integer>  fxEv;
    @FXML
    private TextField deb;
    @FXML
    private TextField fin;
    @FXML
    private Label laboula;
    @FXML
    private Label laboola;
    @FXML
    private TextField nbpl;
    @FXML
    private TextField cat;
    @FXML
    private TextField fifi;
    @FXML
    private TextField sa;
    @FXML
    private TextField evev;
    @FXML
    private TextField idid;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showResr();
        
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
           updateRecord();
        }else if(event.getSource() == btnDelete){
          deleteButton();
        }
    }
       Connection cnx = MyConnection.getInstance().getCnx();
    public ObservableList<Reservation> afficheRes() {
       ObservableList<Reservation> listeRes = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * FROM Reservation";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Reservation r = new Reservation();
               r.setIdRes(res.getInt("idRes"));
               r.setCategorie(res.getString("Categorie"));
               r.setIdEv(res.getInt("IdEv"));
               r.setIdF(res.getInt("IdF"));
               r.setIdSa(res.getInt("idSa"));
               r.setNbPlace(res.getInt("NbPlace"));
               r.setDateDeb(res.getString("DateDeb"));
               r.setDateFin(res.getString("DateFin"));
             
              
               r.setIdSa(res.getInt("idSa"));
               listeRes.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeRes;
    }
    
     public void showResr(){
        ObservableList<Reservation> list = afficheRes();
        
        fxres.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idRes"));
        fxcat.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Categorie"));
        fxF.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idF"));
        fxNb.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("NbPlace"));
        fxDeb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateDeb"));
        fxFin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateFin"));
        fxSa.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idSa"));
        fxEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idEv"));
        
        table.setItems(list);
    }

   /* private void getDate(ActionEvent event) {
         
  LocalDate myDate = deb.getValue();
  String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
 laboula.setText(myFormattedDate);
   
 
    }

    @FXML
    private void getDate2(ActionEvent event) {
        LocalDate myDate2 = fin.getValue();
  String myFormattedDate2 = myDate2.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
  laboola.setText(myFormattedDate2);
    }*/
    public void insertRecord(){
     
        String cat2=cat.getText();
        int even=Integer.parseInt(evev.getText());
        int f=Integer.parseInt(fifi.getText());
        int nb=Integer.parseInt(nbpl.getText());
        int sa3=Integer.parseInt(sa.getText());
        String de =deb.getText();
        String fi=fin.getText();
       
    
        Reservation r = new Reservation(cat2, even, f, sa3, nb, de, fi);
        ReservationCRUD rc = new ReservationCRUD();
        rc.ajouterRes(r);
        showResr();
        
    }
       private void updateRecord(){
           int id=Integer.parseInt(idid.getText());
            String cat2=cat.getText();
        int even=Integer.parseInt(evev.getText());
        int f=Integer.parseInt(fifi.getText());
        int nb=Integer.parseInt(nbpl.getText());
        int sa3=Integer.parseInt(sa.getText());
        String de =deb.getText();
        String fi=fin.getText();
           
        Reservation r = new Reservation(cat2, even, f, sa3, nb, de, fi);
        ReservationCRUD rc = new ReservationCRUD();

        rc.modifierRes(id, cat2, even, f, sa3, nb, de, fi);
        showResr();
        
        
    }
       private void deleteButton(){
        int id=Integer.parseInt(idid.getText());
        ReservationCRUD rc = new ReservationCRUD();

        rc.annulerRes(id);
        showResr();
    }

    
}

