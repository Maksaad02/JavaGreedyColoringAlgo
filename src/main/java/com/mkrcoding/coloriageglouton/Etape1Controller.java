package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Etape1Controller {
    @FXML
    private TextField tfNbrSommets; // Champ de saisie pour le nombre de sommets

    public void onEnter(KeyEvent event) {
        // Vérifiez si la touche appuyée est "Entrée"
        if (event.getCode() == KeyCode.ENTER) {
            onNext();  // Appeler la méthode de validation
        }
    }

    @FXML
    private void onNext() {
        try {
            int numberOfVertices = Integer.parseInt(tfNbrSommets.getText().trim());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("etape2.fxml"));
            Parent etape2Root = fxmlLoader.load();

            Etape2Controller etape2Controller = fxmlLoader.getController();
            etape2Controller.initializeGraph(numberOfVertices); // Passage du nombre de sommets

            Stage stage = (Stage) tfNbrSommets.getScene().getWindow();
            stage.setScene(new Scene(etape2Root));
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer un nombre valide.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
