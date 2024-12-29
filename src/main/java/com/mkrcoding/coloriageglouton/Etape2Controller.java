package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Etape2Controller {

    @FXML
    private TextField tfNomSommet;
    @FXML
    private ListView<String> lvSommets;
    @FXML
    private Button btnNext;

    private GeneralGraph graph;
    private int sommetsCount = 0;
    private int totalSommets;

    public void initializeGraph(int totalSommets) {
        this.totalSommets = totalSommets;
        this.graph = new GeneralGraph(totalSommets);
    }

    @FXML
    private void onAddSommet() {
        String nomSommet = tfNomSommet.getText().trim();

        if (nomSommet.isEmpty() || sommetsCount >= totalSommets) {
            btnNext.setDisable(sommetsCount != totalSommets);
            return;
        }

        graph.addSommet(nomSommet, sommetsCount);
        lvSommets.getItems().add(nomSommet);
        tfNomSommet.clear();
        sommetsCount++;

        btnNext.setDisable(sommetsCount < totalSommets);
    }

    @FXML
    private void onEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onAddSommet();
        }
    }

    @FXML
    private void onNext() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mkrcoding/coloriageglouton/etape3.fxml"));
            Parent etape3Root = fxmlLoader.load();

            Etape3Controller etape3Controller = fxmlLoader.getController();
            etape3Controller.initializeGraph(graph);

            Stage stage = (Stage) tfNomSommet.getScene().getWindow();
            stage.setScene(new Scene(etape3Root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
