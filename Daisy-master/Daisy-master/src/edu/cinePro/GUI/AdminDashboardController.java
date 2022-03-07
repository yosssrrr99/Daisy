/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.services.BilletMethods;
import edu.cinePro.services.ProduitMethods;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private PieChart Pie_Produit_stock;
    @FXML
    private BarChart<?, ?> barChart_ProfitabilitéProduit;
    @FXML
    private NumberAxis profitabilité;
    @FXML
    private CategoryAxis produit;
    @FXML
    private StackedBarChart<?, ?> billetVendu_stackedChart;
    @FXML
    private NumberAxis billetVendu;
    @FXML
    private CategoryAxis film;
    @FXML
    private NumberAxis quantitéVendu_Produit;
    @FXML
    private CategoryAxis produitQVendu;
    @FXML
    private BarChart<?, ?> quantiteVendu_produit_barChart;
    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_right2;
    @FXML
    private AnchorPane anchorpane_right4;
    @FXML
    private AnchorPane anchorpane_right3;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private AnchorPane paneFarRight;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label dashboardPage;
    @FXML
    private Label FilmPage;
    @FXML
    private Label EvenementPage;
    @FXML
    private Label PressePage;
    @FXML
    private Label SalleID;
    @FXML
    private Label ProduitPage;
    @FXML
    private Label ProfilePage;
    @FXML
    private Label seDeconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         BilletMethods BM = new BilletMethods();
         System.out.println(BM.recupBilletVendu_Aujourdhui());

        //Pie chart for status stock
        ProduitMethods PM = new ProduitMethods();
        HashMap<Boolean, Integer> Produit_Stock = PM.recupProduit_Par_Stock();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();

        for (Map.Entry<Boolean, Integer> element : Produit_Stock.entrySet()) {
            if (element.getKey() == true) {
                System.out.println("Produit en stock: " + element.getKey() + "  =>  Nombre de produits en stock: " + element.getValue());
                pieChartData.add(new PieChart.Data("Produit en stock: ", element.getValue()));

            } else if (element.getKey() == false) {
                System.out.println("Produit epuisé : " + element.getKey() + "  =>  Nombre de produits epuisé: " + element.getValue());
                ;
                pieChartData.add(new PieChart.Data("Produit epuisé : ", element.getValue()));

            }
        }

        Pie_Produit_stock.setData(pieChartData);
        Pie_Produit_stock.setTitle("Status Stock");
        
        
         //bar chart for profitabilité produit

        HashMap<String, Double> profitabilitéMap = PM.recupProfitabilité_Par_Produit();

        XYChart.Series series1 = new XYChart.Series();

        for (Map.Entry<String, Double> element : profitabilitéMap.entrySet()) {

            series1.getData().add(new XYChart.Data(element.getKey(), element.getValue() * 100));

        }

        barChart_ProfitabilitéProduit.getData().addAll(series1);
        
        
        //StackedBarChart Billet vendues et restants

       
        HashMap<String, Integer> billetVendu_film = BM.recupVente_Par_Film();
        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(1000, 300000, 1000);
        xaxis.setLabel("Film");
        yaxis.setLabel("Billets Vendus et restants");

         
        billetVendu_stackedChart.setTitle("Vente Billet Par Film");

 
        XYChart.Series billetVendu = new XYChart.Series<>();
        billetVendu.setName("Billets Vendus");
        for (Map.Entry<String, Integer> element : billetVendu_film.entrySet()) {
            billetVendu.getData().add(new XYChart.Data<>(element.getKey(), element.getValue()));

        }

        billetVendu_stackedChart.getData().add(billetVendu);

        HashMap<String, Integer> billetRestant_film = BM.recupBilletRestant_Par_Film();

        XYChart.Series billetsRestants = new XYChart.Series<>();
        billetsRestants.setName("Billets restants");
        for (Map.Entry<String, Integer> element : billetRestant_film.entrySet()) {
            billetsRestants.getData().add(new XYChart.Data<>(element.getKey(), element.getValue()));
        }

           
        billetVendu_stackedChart.getData().add(billetsRestants);
        
        
        //bar chart for quantité produits vendues
     
         quantiteVendu_produit_barChart.setTitle("Quantité vendue par produit");
              
        HashMap<String, Integer> produit_QuantiteVendueMap = PM.recupQuantiteVendu_Par_Produit();

        XYChart.Series series2 = new XYChart.Series();

        for (Map.Entry<String, Integer> element : produit_QuantiteVendueMap.entrySet()) {

            series2.getData().add(new XYChart.Data(element.getKey(), element.getValue()));

        }

        quantiteVendu_produit_barChart.getData().addAll(series2);
    }

    @FXML
    private void showDashboardPage(MouseEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("adminDashboard.fxml"));

        try {
            Parent root = Loader.load();
            AdminDashboardController dashboard = Loader.getController();
            quantiteVendu_produit_barChart.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showFilmPage(MouseEvent event) {
    }

    @FXML
    private void showEvenementPage(MouseEvent event) {
    }

    @FXML
    private void showPressePage(MouseEvent event) {
    }

    @FXML
    private void showSallePage(MouseEvent event) {
    }

    @FXML
    private void showProduitPage(MouseEvent event) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("tableView.fxml"));

        try {
            Parent root = Loader.load();
            TestController produits = Loader.getController();
            quantiteVendu_produit_barChart.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(TestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showProfilePage(MouseEvent event) {
    }

    @FXML
    private void signOut(MouseEvent event) {
    }

}
