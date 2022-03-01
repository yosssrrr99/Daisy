/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cine.gui;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.cine.entities.Reservation;
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
public class ReservationnnController implements Initializable {

    @FXML
    private TableView<Reservation> table;
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

    /**
     * Initializes the controller class.
     */
    String query = null;
    Connection cnx = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Reservation reservation = null;

    ObservableList<Reservation> ResList = FXCollections.observableArrayList();
    
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private Button aj;
    @FXML
    private TableColumn<Reservation, String> editcoll;
   

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }

    private void getAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Reservationnn.fxml"));
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

            ResList.clear();

            query = "SELECT * FROM Reservation";

            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reservation r = new Reservation();
               r.setIdRes(resultSet.getInt("idRes"));
               r.setCategorie(resultSet.getString("Categorie"));
               r.setIdEv(resultSet.getInt("IdEv"));
               r.setIdF(resultSet.getInt("IdF"));
               r.setIdSa(resultSet.getInt("idSa"));
               r.setNbPlace(resultSet.getInt("NbPlace"));
               r.setDateDeb(resultSet.getString("DateDeb"));
               r.setDateFin(resultSet.getString("DateFin"));
                ResList.add(r);
                table.setItems(ResList);

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

        fxres.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("idRes"));
        fxcat.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Categorie"));
        fxF.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idF"));
        fxNb.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("NbPlace"));
        fxDeb.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateDeb"));
        fxFin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("DateFin"));
        fxSa.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idSa"));
        fxEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("idEv"));
    

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
                                reservation = table.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM Reservation WHERE idRes =" + reservation.getIdRes();

                                preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.executeUpdate();
                                refreshTable();

                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }

                        });
//                       


                        HBox managebtn = new HBox(deleteIcon);
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

        editcoll.setCellFactory(cellFoctory);
        table.setItems(ResList);

    }

    @FXML
    private void Add(ActionEvent event) {
                    reservation= table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Reserva.fxml"));
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
    private void refreshr(MouseEvent event) {
        refreshTable();
        
    }
    
}
