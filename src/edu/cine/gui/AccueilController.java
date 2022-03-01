/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Film;
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
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
     
        String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Film film = null;

    ObservableList<Film> filmListe = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Film, String> editcol;
    @FXML
    private TableView<Film> table;
    @FXML
    private TableColumn<Film, Integer> idid;
    @FXML
    private TableColumn<Film, String>  nom;
    @FXML
    private TableColumn<Film, String>  gen;
    @FXML
    private TableColumn<Film, String>  etat;
    @FXML
    private TableColumn<Film, Integer> Rea;
    @FXML
    private TableColumn<Film, String> des;
    
    @FXML
    private TableColumn<Film, Integer> dure;
    @FXML
    private TableColumn<Film,Integer> Arch;
   
     


    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }
  

    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
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

            filmListe.clear();

            query = "SELECT * FROM film";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               Film f = new Film();
               f.setIdF(resultSet.getInt("idF"));
              f.setNomF(resultSet.getString("nomF"));
               f.setGenre(resultSet.getString("Genre"));
               f.setArchive(resultSet.getBoolean("Archive"));
               f.setEtatAcc(resultSet.getString("EtatAcc"));
               f.setNumRea(resultSet.getInt("NumRea"));
               f.setImage(resultSet.getString("Image"));
               f.setDescription(resultSet.getString("Description"));
               
               f.setDuree(resultSet.getInt("duree"));
                filmListe.add(f);
                table.setItems(filmListe);

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

        idid.setCellValueFactory(new PropertyValueFactory<Film,Integer>("idF"));
        nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
        gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
        etat.setCellValueFactory(new PropertyValueFactory<Film, String>("EtatAcc"));
        Rea.setCellValueFactory(new PropertyValueFactory<Film, Integer>("NumRea"));
        des.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
        dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
        Arch.setCellValueFactory(new PropertyValueFactory<Film,Integer>("Archive"));

    

        Callback<TableColumn<Film, String>, TableCell<Film, String>> cellFoctory = (TableColumn<Film, String> param) -> {
            final TableCell<Film, String> cell = new TableCell<Film, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView edittIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );
                         edittIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );


                        viewIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#8a0a0a;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            System.out.println("houniiiiiiii");
                            try {
                               film = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM film WHERE idF =" + film.getIdF();

                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.executeUpdate();
                                refreshTable();

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event1) -> {
//
                           try {
                               film= table.getSelectionModel().getSelectedItem();
                                query = "UPDATE film SET EtatAcc='Accepté' WHERE idF= " + film.getIdF();

                                preparedStatement = cnx.prepareStatement(query);

                                preparedStatement.executeUpdate();

                                refreshTable();

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            });
                        edittIcon.setOnMouseClicked((MouseEvent event1) -> {
//
                          
                            try {
                               film= table.getSelectionModel().getSelectedItem();
                                query = "UPDATE film SET Archive=1 WHERE idF= " + film.getIdF();

                                preparedStatement = cnx.prepareStatement(query);

                                preparedStatement.executeUpdate();

                                refreshTable();

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            });

                            viewIcon.setOnMouseClicked((MouseEvent event0) -> {

                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Reservationnn.fxml"));
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
    
                        });


                        HBox managebtn = new HBox(editIcon, deleteIcon,edittIcon,viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(viewIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };

        editcol.setCellFactory(cellFoctory);
        table.setItems(filmListe);

    }

   
}
