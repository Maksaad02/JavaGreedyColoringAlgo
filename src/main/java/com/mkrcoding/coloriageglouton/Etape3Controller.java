package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Etape3Controller {

    @FXML
    private TextField tfEdgeCount; // Champ pour le nombre d'arêtes
    @FXML
    private TextField tfEdge; // Champ pour saisir une arête
    @FXML
    private ListView<String> lvEdges; // Liste des arêtes saisies
    @FXML
    private Label lblError; // Label pour afficher les messages d'erreur
    @FXML
    private Button btnAddEdge; // Bouton pour ajouter une arête
    @FXML
    private Button btnFinish; // Bouton pour terminer

    private GeneralGraph graph;
    private int totalEdgesAllowed; // Nombre total d'arêtes autorisées
    private int edgesAdded = 0; // Nombre d'arêtes ajoutées

    @FXML
    public void initializeGraph(GeneralGraph graph) {
        this.graph = graph;
        tfEdge.setDisable(true);
        btnAddEdge.setDisable(true);
        btnFinish.setDisable(true);
    }

    @FXML
    private void onValidateEdgeCount() {
        try {
            totalEdgesAllowed = Integer.parseInt(tfEdgeCount.getText().trim());
            if (totalEdgesAllowed <= 0) {
                throw new IllegalArgumentException("Le nombre d'arêtes doit être supérieur à 0.");
            }

            tfEdge.setDisable(false);
            btnAddEdge.setDisable(false);
            lblError.setText("Entrez les arêtes (exemple : AB).");

        } catch (NumberFormatException e) {
            lblError.setText("Veuillez entrer un nombre entier pour le nombre d'arêtes.");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        }
    }

    @FXML
    private void onAddEdge() {
        try {
            if (edgesAdded >= totalEdgesAllowed) {
                throw new IllegalStateException("Nombre maximum d'arêtes atteint !");
            }

            String edge = tfEdge.getText().trim();
            if (edge.length() != 2) {
                throw new IllegalArgumentException("Veuillez entrer une arête valide (2 lettres, ex : AB).");
            }

            String from = String.valueOf(edge.charAt(0));
            String to = String.valueOf(edge.charAt(1));

            graph.addEdge(from, to);

            lvEdges.getItems().add(edge);
            edgesAdded++;
            tfEdge.clear();

            if (edgesAdded == totalEdgesAllowed) {
                btnFinish.setDisable(false);
                lblError.setText("Toutes les arêtes ont été ajoutées. Cliquez sur Terminer.");
            } else {
                lblError.setText("Arête ajoutée. Entrez une nouvelle arête.");
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            lblError.setText(e.getMessage());
        }
    }

    @FXML
    private void onFinish() {
        if (edgesAdded != totalEdgesAllowed) {
            lblError.setText("Le nombre d'arêtes ajoutées ne correspond pas au nombre total.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mkrcoding/coloriageglouton/resultDisplay.fxml"));
            Parent root = loader.load();

            ResultDisplayController controller = loader.getController();
            int[] colors = graph.greedyColoring();
            controller.displayResults(graph.getNomSommets(), graph.getAdj(), colors);

            Stage stage = new Stage();
            stage.setTitle("Résultat du coloriage");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
