// Etape2Controller.java
package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Etape2Controller {
    @FXML
    private TextField tfNomSommet;
    @FXML
    private ListView<String> lvSommets;
    @FXML
    private Button btnNext;

    private GeneralGraph graph;
    private int SommetsCount = 0;
    private int totalSommets;

    // Initialiser le graphe en passant le nombre de sommets
    public void initializeGraph(int totalSommets) {
        this.totalSommets = totalSommets;
        this.graph = new GeneralGraph(totalSommets); // Créer le graphe avec le nombre de sommets
    }

    @FXML
    private void onAddSommet() {
        String nomSommet = tfNomSommet.getText().trim();

        if (nomSommet.isEmpty() || SommetsCount >= totalSommets) {
            btnNext.setDisable(true);
            System.out.println("Nom invalide ou tous les sommets sont ajoutés.");
            return;
        }

        graph.addSommet(nomSommet, SommetsCount);
        lvSommets.getItems().add(nomSommet);
        tfNomSommet.clear();
        SommetsCount++;

//        if (SommetsCount == totalSommets) {
//            btnNext.setDisable(true);
//        }
    }
    public void onEnter(KeyEvent event) {
        // Vérifiez si la touche appuyée est "Entrée"
        if (event.getCode() == KeyCode.ENTER) {
            onAddSommet();  // Appeler la méthode de validation
        }
    }
    @FXML
    private void onNext() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("etape3.fxml"));
            Parent etape3Root = fxmlLoader.load();

            Etape3Controller etape3Controller = fxmlLoader.getController();
            etape3Controller.initializeGraph(); // Passer le graphe à l'étape suivante

            Stage stage = (Stage) tfNomSommet.getScene().getWindow();
            stage.setScene(new Scene(etape3Root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
