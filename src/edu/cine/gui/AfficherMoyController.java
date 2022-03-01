/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Avis;
import edu.cine.entities.Film;
import edu.cine.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class AfficherMoyController implements Initializable {

   
    @FXML
    private TableColumn<Avis, Integer> nb;
   
    @FXML
    private TableView<Avis> table;
    @FXML
    private TableColumn<Avis, Integer> fxidf;
    @FXML
    private TextField moymoy;
    @FXML
    private TableColumn<Avis, String> editcol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    
  String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Film film = null;
      ObservableList<Film> filmListe = FXCollections.observableArrayList();
ObservableList<Avis> avisListe = FXCollections.observableArrayList();
 private void refreshTable() {

       
 try {

            filmListe.clear();

            query = "SELECT idF,nbEtoile,MoyenneAvis FROM avis";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Avis a = new Avis();
               
                
               a.setIdF(resultSet.getInt("idF"));
               a.setNbEtoile(resultSet.getInt("nbEtoile"));
             
                avisListe.add(a);
                table.setItems(avisListe);

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
          fxidf.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("idF"));
        
        nb.setCellValueFactory(new PropertyValueFactory<Avis, Integer>("nbEtoile"));
        
       
       

    

        Callback<TableColumn<Avis, String>, TableCell<Avis, String>> cellFoctory = (TableColumn<Avis, String> param) -> {
            final TableCell<Avis, String> cell = new TableCell<Avis, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        //FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        //FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        //FontAwesomeIconView edittIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

////                        deleteIcon.setStyle(
////                                " -fx-cursor: hand ;"
////                                + "-glyph-size:28px;"
////                                + "-fx-fill:#8a0a0a;"
////                        );
//                        editIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#000000;"
//                        );
//                         edittIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#000000;"
//                        );


                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        
//                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
//                            System.out.println("houniiiiiiii");
//                            try {
//                               film = table.getSelectionModel().getSelectedItem();
//                                query = "DELETE FROM film WHERE idF =" + film.getIdF();
//
//                                preparedStatement = cnx.prepareStatement(query);
//                                preparedStatement.executeUpdate();
//                                refreshTable();
//
//                            } catch (SQLException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//
//                        });
//                        editIcon.setOnMouseClicked((MouseEvent event1) -> {
//                               
//                           try {
//                           
//            String requete = "SELECT AVG(MoyenneAvis) FROM Avis where idF=16";
//            Statement st = cnx.createStatement();
//            ResultSet res = st.executeQuery(requete);
//
//            
//                Avis a = new Avis();
//                a.setMoyenneAvis(res.getFloat("MoyenneAvis"));
//                               System.out.println(a);
//               listeAv.add(a);
//            
//      
//                  
//            
//        
//               
//
//                            } catch (SQLException ex) {
//                                System.err.println(ex.getMessage());
//                            }
//                            });
////                        edittIcon.setOnMouseClicked((MouseEvent event1) -> {
//////
////                          
////                            try {
////                               film= table.getSelectionModel().getSelectedItem();
////                                query = "UPDATE film SET Archive=1 WHERE idF= " + film.getIdF();
////
////                                preparedStatement = cnx.prepareStatement(query);
////
////                                preparedStatement.executeUpdate();
////
////                                refreshTable();
////
////                            } catch (SQLException ex) {
////                                System.err.println(ex.getMessage());
////                            }
////                            });

                            viewIcon.setOnMouseClicked((MouseEvent event0) -> {

                            moymoy.setText(String.valueOf(50));
    
                        });


                        HBox managebtn = new HBox(viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

       editcol.setCellFactory(cellFoctory);
      table.setItems(avisListe);

    }

    private void fetmoy(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AfficherMoy.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            
//                            ReservaController addReservController = loader.getController();
//                            addReservController. insertRecord();
                                                     
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
           
    }
     
  
}
