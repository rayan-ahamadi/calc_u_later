package com.example.calc_u_later;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class calc_u_later extends Application {

    private TextField displayField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculatrice");

        // Création du champ d'affichage
        displayField = new TextField();
        displayField.setEditable(false);
        displayField.setAlignment(Pos.CENTER_RIGHT);

        // Création des boutons
        Button btn1 = createButton("1");
        Button btn2 = createButton("2");
        Button btn3 = createButton("3");
        Button btn4 = createButton("4");
        Button btn5 = createButton("5");
        Button btn6 = createButton("6");
        Button btn7 = createButton("7");
        Button btn8 = createButton("8");
        Button btn9 = createButton("9");
        Button btn0 = createButton("0");
        Button btnPlus = createButton("+");
        Button btnMinus = createButton("-");
        Button btnMultiply = createButton("*");
        Button btnDivide = createButton("/");
        Button btnEquals = createButton("=");

        // Création de la disposition en grille pour les boutons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(displayField, 0, 0, 4, 1);
        gridPane.add(btn1, 0, 1);
        gridPane.add(btn2, 1, 1);
        gridPane.add(btn3, 2, 1);
        gridPane.add(btnPlus, 3, 1);
        gridPane.add(btn4, 0, 2);
        gridPane.add(btn5, 1, 2);
        gridPane.add(btn6, 2, 2);
        gridPane.add(btnMinus, 3, 2);
        gridPane.add(btn7, 0, 3);
        gridPane.add(btn8, 1, 3);
        gridPane.add(btn9, 2, 3);
        gridPane.add(btnMultiply, 3, 3);
        gridPane.add(btn0, 0, 4);
        gridPane.add(btnEquals, 1, 4);
        gridPane.add(btnDivide, 2, 4);

        // Création de l'action pour chaque bouton
        btn1.setOnAction(e -> appendToDisplay("1"));
        btn2.setOnAction(e -> appendToDisplay("2"));
        btn3.setOnAction(e -> appendToDisplay("3"));
        btn4.setOnAction(e -> appendToDisplay("4"));
        btn5.setOnAction(e -> appendToDisplay("5"));
        btn6.setOnAction(e -> appendToDisplay("6"));
        btn7.setOnAction(e -> appendToDisplay("7"));
        btn8.setOnAction(e -> appendToDisplay("8"));
        btn9.setOnAction(e -> appendToDisplay("9"));
        btn0.setOnAction(e -> appendToDisplay("0"));
        btnPlus.setOnAction(e -> appendToDisplay("+"));
        btnMinus.setOnAction(e -> appendToDisplay("-"));
        btnMultiply.setOnAction(e -> appendToDisplay("*"));
        btnDivide.setOnAction(e -> appendToDisplay("/"));
        btnEquals.setOnAction(e -> evaluateExpression());

        // Création de la scène principale
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.setPrefSize(50, 50);
        return button;
    }

    private void appendToDisplay(String value) {
        displayField.setText(displayField.getText() + value);
    }

    private void evaluateExpression() {
        String expression = displayField.getText();
        try {
            double result = evaluate(expression);
            displayField.setText(Double.toString(result));
        } catch (Exception e) {
            displayField.setText("Erreur");
        }
    }

    private double evaluate(String expression) {
        // Ici, vous pouvez implémenter votre propre logique d'évaluation d'expression
        // Par exemple, vous pouvez utiliser la classe ScriptEngine pour évaluer l'expression arithmétique
        // ou implémenter votre propre algorithme d'évaluation en utilisant une pile (stack).

        // Pour cet exemple basique, nous allons simplement utiliser la méthode eval() de la classe ScriptEngine

        // Exigence : Vous devez ajouter l'importation suivante au début de votre fichier :
        // import javax.script.ScriptEngine;
        // import javax.script.ScriptEngineManager;

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            return (double) engine.eval(expression);
        } catch (Exception e) {
            throw new RuntimeException("Erreur d'évaluation de l'expression : " + expression);
        }
    }
}