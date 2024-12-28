package com.mkrcoding.coloriageglouton;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

public class ResultDisplayController {
    public Label lblTitle;
    @FXML
    private Canvas canvasGraph; // Canvas pour dessiner le graphe
    @FXML
    private Label lblInfo; // Label pour afficher les informations textuelles

    public void displayResults(String[] nomSommets, List<List<Integer>> adj, int[] colors) {
        // Affiche les informations dans le label
        StringBuilder info = new StringBuilder("Nombre de couleurs nécessaires : ")
                .append((java.util.Arrays.stream(colors).max().orElse(-1) + 1))
                .append("\n\n");
        for (int i = 0; i < colors.length; i++) {
            info.append(nomSommets[i]).append(" : Couleur ").append(colors[i] + 1).append("\n");
        }
        lblInfo.setText(info.toString());

        // Dessiner le graphe
        drawGraph(nomSommets, adj, colors);
    }

    private void drawGraph(String[] nomSommets, List<List<Integer>> adj, int[] colors) {
        GraphicsContext gc = canvasGraph.getGraphicsContext2D();
        double centerX = canvasGraph.getWidth() / 2;
        double centerY = canvasGraph.getHeight() / 2;
        double radius = 150; // Rayon du cercle
        double angleStep = 2 * Math.PI / nomSommets.length; // Angle entre chaque sommet

        // Positions des sommets
        double[] xPos = new double[nomSommets.length];
        double[] yPos = new double[nomSommets.length];

        // Couleurs
        Color[] palette = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.PURPLE};

        // Placer les sommets
        for (int i = 0; i < nomSommets.length; i++) {
            xPos[i] = centerX + radius * Math.cos(i * angleStep);
            yPos[i] = centerY + radius * Math.sin(i * angleStep);

            // Dessiner le sommet
            gc.setFill(palette[colors[i] % palette.length]);
            gc.fillOval(xPos[i] - 15, yPos[i] - 15, 30, 30);

            // Nom du sommet
            gc.setFill(Color.BLACK);
            gc.fillText(nomSommets[i], xPos[i] - 5, yPos[i] + 5);
        }

        // Dessiner les arêtes
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < adj.size(); i++) {
            for (int neighbor : adj.get(i)) {
                gc.strokeLine(xPos[i], yPos[i], xPos[neighbor], yPos[neighbor]);
            }
        }
    }
}
