/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;



import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Realisateur;

import edu.cine.utils.MyConnection;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterRController implements Initializable {

   

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Realisateur realisateur = null;

    ObservableList<Realisateur> ReaList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Realisateur, String> editcol;
    @FXML
    private TableView<Realisateur> table;
    @FXML
    private TableColumn<Realisateur, String> name;
    @FXML
    private TableColumn<Realisateur, String> Nom;
    @FXML
    private TableColumn<Realisateur, Integer> Num;
   

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }
 
    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ajouterR.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private void refreshTable() {

       
 try {

            ReaList.clear();

            query = "SELECT r.NumRea ,c.userName, r.NomOrg FROM client c,realisateur r WHERE r.idC=c.idC";
               
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Realisateur r = new Realisateur();
               r.setNumRea(resultSet.getInt("NumRea"));
               r.setUserName(resultSet.getString("userName"));
               r.setNomOrg(resultSet.getString("NomOrg"));
                ReaList.add(r);
                table.setItems(ReaList);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

              
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

   

              
    
    
    private void loadDate() {
        cnx = MyConnection.getInstance().getCnx();
          refreshTable();
   
       Num.setCellValueFactory(new PropertyValueFactory<>("NumRea"));
       name.setCellValueFactory(new PropertyValueFactory<>("UserName"));
         Nom.setCellValueFactory(new PropertyValueFactory<>("NomOrg"));
        
    

        Callback<TableColumn<Realisateur, String>, TableCell<Realisateur, String>> cellFoctory = (TableColumn<Realisateur, String> param) -> {
            final TableCell<Realisateur, String> cell = new TableCell<Realisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        //FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        //FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
//                        editIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#000000;"
//                        );

//                        viewIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#000000;"
//                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("houniiiiiiii");
                            try {
                                realisateur = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM realisateur  WHERE NumRea = " +realisateur.getNumRea();

                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.executeUpdate();
                                refreshTable();
                               

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }

                        });
                       // editIcon.setOnMouseClicked((MouseEvent event1) -> {
//
//                           reservation= table.getSelectionModel().getSelectedItem();
//                            FXMLLoader loader = new FXMLLoader ();
//                            loader.setLocation(getClass().getResource("AddReserv.fxml"));
//                            try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                System.out.println(ex.getMessage());
//                            }
//                            
//                            AddReservController addReservController = loader.getController();
//                            addReservController.setUpdate(true);
//                          addReservController.set
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
                           // });

//                            viewIcon.setOnMouseClicked((MouseEvent event0) -> {
//
//                           reservation = table.getSelectionModel().getSelectedItem();
//                          FXMLLoader loader = new FXMLLoader ();
//                           loader.setLocation(getClass().getResource("AfficherPubAdmin.fxml"));
//                           try {
//                               loader.load();
//                            } catch (IOException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                            
////                             afficherPubAdminController = loader.getController();
////                            afficherPubAdminController.setUpdate(true);
////                            
//                            Parent parent = loader.getRoot();
//                            Stage stage = new Stage();
//                            stage.setScene(new Scene(parent));
//                            stage.initStyle(StageStyle.UTILITY);
//                            stage.show();
//
//                          });


                        HBox managebtn = new HBox( deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                  // HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(ReaList);

    }

    
    
}
