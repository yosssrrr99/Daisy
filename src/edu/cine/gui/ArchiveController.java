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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ArchiveController implements Initializable {

    @FXML
    private TableView<Film> table;
    @FXML
    private TableColumn<Film, Integer> fxid;
    @FXML
    private TableColumn<Film, String> nom;
    @FXML
    private TableColumn<Film, String> gen;
    @FXML
    private TableColumn<Film, String> des;
    @FXML
    private TableColumn<Film, Integer> dure;
    @FXML
    private TableColumn<Film, String> editcol;
    

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

            query = "SELECT * FROM film where Archive=1";

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
    
     public List<Film> afficheFilm2() {
        List<Film> listeFilm = new ArrayList();
        try {
            String requete = "SELECT * FROM Film  ";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Film f = new Film();

               f.setNomF(res.getString("NomF"));
               f.setGenre(res.getString("Genre"));
               f.setArchive(res.getBoolean("Archive"));
               f.setEtatAcc(res.getString("EtatAcc"));
               f.setNumRea(res.getInt("NumRea"));
               f.setImage(res.getString("Image"));
               f.setDescription(res.getString("Description"));
               f.setDateDispo(res.getTimestamp("DateDispo"));
                f.setDuree(res.getInt("duree"));
               listeFilm.add(f);
            }
        } catch (SQLException ex) {
            System.out.println("mochkla");
            System.out.println(ex.getMessage());
        }
        return listeFilm;
     }
    public void archiveFilm() {
        List<Film> listeFilm = new ArrayList<Film>();
        listeFilm = afficheFilm2();

        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());
        System.out.println(currentDate.getYear());
        
        listeFilm.stream()
                // si depasser akther men aam 
                .filter(f -> f.getDateDispo().getYear() - currentDate.getYear() >1)
                .forEach((P) -> {
                    
                    try {
                        
                        String requete = "UPDATE film SET "
                                + "Archive= 1 ";

                        Statement st = cnx.createStatement();
                        st.executeUpdate(requete);
                        
                        

                    } catch (SQLException e) {
                        System.out.println("erreur");
                    }
                });
    }

    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadDate() {
        cnx = MyConnection.getInstance().getCnx();

   refreshTable();
          fxid.setCellValueFactory(new PropertyValueFactory<Film, Integer>("idF"));
        
        nom.setCellValueFactory(new PropertyValueFactory<Film, String>("nomF"));
        gen.setCellValueFactory(new PropertyValueFactory<Film, String>("Genre"));
        
        des.setCellValueFactory(new PropertyValueFactory<Film, String>("Description"));
        dure.setCellValueFactory(new PropertyValueFactory<Film, Integer>("duree"));
       

    

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

                        //FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        //FontAwesomeIconView edittIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView viewIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

////                        deleteIcon.setStyle(
////                                " -fx-cursor: hand ;"
////                                + "-glyph-size:28px;"
////                                + "-fx-fill:#8a0a0a;"
////                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#000000;"
                        );
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
                        editIcon.setOnMouseClicked((MouseEvent event1) -> {
                               
                           try {
                           
            String requete = "SELECT AVG(MoyenneAvis) FROM Avis where idF=16";
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(requete);

            
                Avis a = new Avis();
                a.setMoyenneAvis(res.getFloat("MoyenneAvis"));
                               System.out.println(a);
               //listeAv.add(a);
            
      
                  
            
        
             //  moy.setText(a.toString());

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            });
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
    
                        });


                        HBox managebtn = new HBox( viewIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
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
        table.setItems(filmListe);

    }

    @FXML
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

    @FXML
    private void getarch(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader ();
         loader.setLocation(getClass().getResource("Archive.fxml"));
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
