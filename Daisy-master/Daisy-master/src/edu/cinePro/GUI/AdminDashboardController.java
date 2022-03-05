/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinePro.GUI;

import edu.cinePro.services.BilletMethods;
import edu.cinePro.services.ProduitMethods;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

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
     

              
        HashMap<String, Integer> produit_QuantiteVendueMap = PM.recupQuantiteVendu_Par_Produit();

        XYChart.Series series2 = new XYChart.Series();

        for (Map.Entry<String, Integer> element : produit_QuantiteVendueMap.entrySet()) {

            series2.getData().add(new XYChart.Data(element.getKey(), element.getValue()));

        }

        quantiteVendu_produit_barChart.getData().addAll(series2);
    }

}
