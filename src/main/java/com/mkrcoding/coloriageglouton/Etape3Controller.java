package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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
    public void initializeGraph() {
        // Désactiver les champs et boutons au démarrage
        tfEdge.setDisable(true);
        btnAddEdge.setDisable(true);
        btnFinish.setDisable(true);
    }

    // Méthode appelée après la saisie du nombre total d'arêtes
    @FXML
    private void onValidateEdgeCount() {
        try {
            // Récupérer et vérifier le nombre d'arêtes
            totalEdgesAllowed = Integer.parseInt(tfEdgeCount.getText().trim());
            if (totalEdgesAllowed <= 0) {
                throw new IllegalArgumentException("Le nombre d'arêtes doit être supérieur à 0.");
            }

            // Initialiser le graphe avec un nombre arbitraire de sommets (exemple : 26 pour A-Z)
            graph = new GeneralGraph(totalEdgesAllowed);
            for (int i = 0; i < totalEdgesAllowed; i++) {
                graph.addSommet(String.valueOf((char) ('A' + i)), i);
            }

            // Activer les champs pour les arêtes
            tfEdge.setDisable(false);
            btnAddEdge.setDisable(false);
            lblError.setText("Entrez les arêtes (exemple : AB).");

        } catch (NumberFormatException e) {
            lblError.setText("Veuillez entrer un nombre entier pour le nombre d'arêtes.");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        }
    }

    // Méthode pour ajouter une arête
    @FXML
    private void onAddEdge() {
        try {
            // Vérifier si le nombre total d'arêtes est atteint
            if (edgesAdded >= totalEdgesAllowed) {
                throw new IllegalStateException("Nombre maximum d'arêtes atteint !");
            }

            String edge = tfEdge.getText().trim();
            if (edge.length() != 2) {
                throw new IllegalArgumentException("Veuillez entrer une arête valide (2 lettres, ex : AB).");
            }

            String from = String.valueOf(edge.charAt(0));
            String to = String.valueOf(edge.charAt(1));

            // Ajouter l'arête au graphe
            graph.addEdge(from, to);

            // Ajouter l'arête à la liste
            lvEdges.getItems().add(edge);
            edgesAdded++;
            tfEdge.clear();

            // Activer le bouton Terminer si toutes les arêtes sont ajoutées
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

    // Méthode pour terminer et lancer l'algorithme
    @FXML
    private void onFinish() {
        if (edgesAdded != totalEdgesAllowed) {
            lblError.setText("Le nombre d'arêtes ajoutées ne correspond pas au nombre total.");
            return;
        }

        // Afficher les résultats du coloriage
        graph.displayColors();
        lblError.setText("Coloriage terminé ! Vérifiez la console.");
    }
}
