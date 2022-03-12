/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Film;

import edu.cine.entities.Reservation;
import edu.cine.services.FilmCRUD;

import edu.cine.services.ReservationCRUD;
import edu.cine.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author DELL
 */

public class ResController implements Initializable {
  
 
 
     String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Reservation reservation = null;
      ObservableList<Reservation> resListe = FXCollections.observableArrayList();
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, Integer> fxidS;
    @FXML
    private TableColumn<Reservation, Integer> fxNB;
    @FXML
    private TableColumn<Reservation, String> fxC;
    @FXML
    private TableColumn<Reservation, String> fxDeb;
    @FXML
    private TableColumn<Reservation, String> fxFin;
    @FXML
    private TableColumn<Reservation, String> editcol;
    @FXML
    private TableColumn<Reservation, Integer> fxRes;
    @FXML
    private TableColumn<Reservation, Integer> fxF;
    @FXML
    private TableColumn<Reservation, Integer> fxe;
    @FXML
    private TextField nono;
    @FXML
    private TextField coco;
    @FXML
    private TextField fifi;
    @FXML
    private TextField sasa;
    @FXML
    private TextField dodo;
    @FXML
    private TextField fofo;
    @FXML
    private TextField evev;
    @FXML
    private Label label;
    @FXML
    private Label laboola;
    @FXML
    private Label laboula;
    @FXML
    private TextField s;
    @FXML
    private FontAwesomeIconView refresh;
  

   



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
       
//       ComboBox comboBox = new ComboBox(resListe);
//       comboBox.setMaxHeight(30);
//        System.out.println(comboBox);
        
    
     loadDate();
    }    
//    final ObservableList options = FXCollections.observableArrayList();
//    public void fill(){
//         try {
//             String query="select idF from film ";
//             PreparedStatement pst = cnx.prepareStatement(query);
//             ResultSet rs= pst.executeQuery();
//             while(rs.next()){
//                 options.add(rs.getInt("idF"));
//             }
//         } catch (SQLException ex) {
//             Logger.getLogger(ResController.class.getName()).log(Level.SEVERE, null, ex);
//         }
//        
//    }

    @FXML
    private void getFil(MouseEvent event) {
    }

    @FXML
    private void geta(MouseEvent event) {
    }

    @FXML
    private void search(ActionEvent event) {
        resListe.setAll();
        int n = 0;
        
            try {
                
                
                String req = "SELECT * FROM reservation WHERE idSa = ? ";
                PreparedStatement pst = cnx.prepareStatement(req);
                pst.setString(1, String.valueOf(s.getText()));
                ResultSet res = pst.executeQuery();
                
                while (res.next()) {
                    n = 2;
                    fxRes.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idRes"));
        fxC.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Categorie"));
        fxF.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idF"));
      fxNB.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("NbPlace"));
        fxDeb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateDeb"));
        fxFin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateFin"));
        fxidS.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idSa"));
        
        fxe.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idEv"));
          
                     
                Reservation r = new Reservation();
               r.setIdRes(res.getInt("idRes"));
               r.setCategorie(res.getString("Categorie"));
               r.setIdEv(res.getInt("IdEv"));
               r.setIdF(res.getInt("IdF"));
               r.setIdSa(res.getInt("idSa"));
               r.setNbPlace(res.getInt("NbPlace"));
               r.setDateDeb(res.getString("DateDeb"));
               r.setDateFin(res.getString("DateFin"));
                resListe.add(r);
                table.setItems(resListe);
                
                }
     
                table.setItems(resListe);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (n == 0) {
                Alert alt = new Alert(Alert.AlertType.ERROR, "il n'y a pas une réservation de ce nom", javafx.scene.control.ButtonType.OK);
                alt.showAndWait();
                loadDate();
            }
    }

    @FXML
    private void refreshr(MouseEvent event) {
        refreshTable();
    }

    @FXML
    private void getedit(MouseEvent event) {
         Reservation r = table.getSelectionModel().getSelectedItem();
        sasa.setText(String.valueOf(r.getIdSa()));
        coco.setText(r.getCategorie());
        fifi.setText(String.valueOf(r.getIdF()));
        evev.setText(String.valueOf(r.getIdEv()));
        nono.setText(String.valueOf(r.getNbPlace()));
        dodo.setText(String.valueOf(r.getDateDeb()));
        fofo.setText(String.valueOf(r.getDateFin()));
    }

    @FXML
    private void saveFilm(ActionEvent event) {
              if (coco.getText().isEmpty() || evev.getText().isEmpty()  || fifi.getText().isEmpty() || nono.getText().isEmpty() 
                        || sasa.getText().isEmpty() ||dodo.getText().isEmpty()||fofo.getText().isEmpty() )
        {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Les champs sont vides");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();

        } else {
         String cat2=coco.getText();
        int even=Integer.parseInt(evev.getText());
        int fi=Integer.parseInt(fifi.getText());
        int nb=Integer.parseInt(nono.getText());
        int sa3=Integer.parseInt(sasa.getText());
        String de =dodo.getText();
        String fii=fofo.getText();
       
   
        Reservation r = new Reservation(cat2, even, fi, sa3, nb, de, fii);
        ReservationCRUD rc = new ReservationCRUD();
        rc.ajouterRes(r);
        refreshTable();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection");

            alert.setHeaderText(null);
            alert.setContentText("votre publication a été ajoutée avec succées !");

            alert.showAndWait();
        }
    }

    @FXML
    private void updateF(ActionEvent event) {
         
         Reservation r = table.getSelectionModel().getSelectedItem();
                       String id = sasa.getText();
                       int iddd=Integer.parseInt(id);
                       String idf = fifi.getText();
                       int idd=Integer.parseInt(idf);
                       String idddd = evev.getText();
                       int ide=Integer.parseInt(idddd);
                       String nbb= nono.getText();
                       int nbo=Integer.parseInt(nbb);
                         String catt = coco.getText().toString();
                       String nom = dodo.getText().toString();
                        String fi = fofo.getText().toString();
                                
                               ReservationCRUD rc = new ReservationCRUD();
                               rc.modifierRes(r.getIdRes(),catt, ide, idd, iddd, nbo, nom, fi);
                              refreshTable();
    }
    private void refreshTable() {

       
 try {

            resListe.clear();

            query = "SELECT * FROM reservation";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Reservation r = new Reservation();
               r.setIdRes(resultSet.getInt("idRes"));
               r.setCategorie(resultSet.getString("Categorie"));
               r.setIdEv(resultSet.getInt("IdEv"));
               r.setIdF(resultSet.getInt("IdF"));
               r.setIdSa(resultSet.getInt("IdSa"));
               r.setNbPlace(resultSet.getInt("NbPlace"));
               r.setDateDeb(resultSet.getString("DateDeb"));
               r.setDateFin(resultSet.getString("DateFin"));
                resListe.add(r);
                table.setItems(resListe);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

              
    }
    private void loadDate() {
        cnx = MyConnection.getInstance().getCnx();

   refreshTable();

      fxRes.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idRes"));
        fxC.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Categorie"));
        fxF.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idF"));
      fxNB.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("NbPlace"));
        fxDeb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateDeb"));
        fxFin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateFin"));
        fxidS.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idSa"));
        fxe.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idEv"));
    

        Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFoctory = (TableColumn<Reservation, String> param) -> {
            final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("houniiiiiiii");
                             System.out.println("houniiiiiiii");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression la presse");
                            alert.setHeaderText(null);
                            alert.setContentText("Vouliez vous supprimer vraiment le film ? ");
                            Optional<ButtonType> action = alert.showAndWait();
                            

                            if (action.get() == ButtonType.OK) {
                            try {
                                reservation = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM Reservation WHERE idRes =" + reservation.getIdRes();

                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.executeUpdate();
                                refreshTable();

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            }

                        });
//                       


                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(resListe);

    }

    
}

  
//    @FXML
//    private void search(ActionEvent event) {
//        resListe.setAll();
//        int n = 0;
//        
//            try {
//                
//                
//                String req = "SELECT * FROM reservation WHERE idSa = ? ";
//                PreparedStatement pst = cnx.prepareStatement(req);
//                pst.setString(1, String.valueOf(s.getText()));
//                ResultSet res = pst.executeQuery();
//                Film f = new Film();
//                while (res.next()) {
//                    n = 2;
//                   
//                     
//                Reservation r = new Reservation();
//               r.setIdRes(res.getInt("idRes"));
//               r.setCategorie(res.getString("Categorie"));
//               r.setIdEv(res.getInt("IdEv"));
//               r.setIdF(res.getInt("IdF"));
//               r.setIdSa(res.getInt("idSa"));
//               r.setNbPlace(res.getInt("NbPlace"));
//               r.setDateDeb(res.getString("DateDeb"));
//               r.setDateFin(res.getString("DateFin"));
//                resListe.add(r);
//                table.setItems(resListe);
//                
//                }
//           catt.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Categorie"));
//        Arch.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idF"));
//       nb.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("NbPlace"));
//        dateDEb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateDeb"));
//        dateFin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateFin"));
//        idid.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idSa"));
//        edittt.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idEv"));
//                table.setItems(resListe);
//
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//            if (n == 0) {
//                Alert alt = new Alert(Alert.AlertType.ERROR, "il n'y a pas une réservation de ce nom", javafx.scene.control.ButtonType.OK);
//                alt.showAndWait();
//                loadDate();
//            }
//    }
//
//    @FXML
//    private void refreshr(MouseEvent event) {
//        refreshTable();
//    }
//
//    @FXML
//    private void getedit(MouseEvent event) {
//        Reservation r = table.getSelectionModel().getSelectedItem();
//        sa.setText(String.valueOf(r.getIdSa()));
//        cat.setText(r.getCategorie());
//        fifi.setText(String.valueOf(r.getIdF()));
//        evev.setText(String.valueOf(r.getIdEv()));
//        nbpl.setText(String.valueOf(r.getNbPlace()));
//        deb.setText(String.valueOf(r.getDateDeb()));
//        fin.setText(String.valueOf(r.getDateFin()));
//    }
//
//    @FXML
//    private void saveFilm(ActionEvent event) {
//        if (idid.getText().isEmpty() || catt.getText().isEmpty()  || nb.getText().isEmpty() || dateDEb.getText().isEmpty() 
//                        || dateFin.getText().isEmpty() )
//        {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Warning");
//            alert.setHeaderText("Les champs sont vides");
//            alert.setContentText("Veuillez remplir tous les champs");
//            alert.showAndWait();
//
//        } else {
//         String cat2=cat.getText();
//        int even=Integer.parseInt(evev.getText());
//        int fi=Integer.parseInt(fifi.getText());
//        int nb=Integer.parseInt(nbpl.getText());
//        int sa3=Integer.parseInt(sa.getText());
//        String de =deb.getText();
//        String fii=fin.getText();
//       
//   
//        Reservation r = new Reservation(cat2, even, fi, sa3, nb, de, fii);
//        ReservationCRUD rc = new ReservationCRUD();
//        rc.ajouterRes(r);
//        refreshTable();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Connection");
//
//            alert.setHeaderText(null);
//            alert.setContentText("votre publication a été ajoutée avec succées !");
//
//            alert.showAndWait();
//        }
//        
//    }
//
//    @FXML
//    private void updateF(ActionEvent event) {
//       
////        int id=Integer.parseInt(sa.getText());
//        String cat2=cat.getText();
//        int even=Integer.parseInt(evev.getText());
//        int f=Integer.parseInt(fifi.getText());
//        int nb=Integer.parseInt(nbpl.getText());
//        int sa3=Integer.parseInt(sa.getText());
//        String de =deb.getText();
//        String fi=fin.getText();
//           
//        Reservation r = new Reservation();
//        ReservationCRUD rc = new ReservationCRUD();
//
//        rc.modifierRes(r.getIdRes(),cat2, even, f, sa3, nb, de, fi);
//                                
//        refreshTable();
//    }
//    
//}
