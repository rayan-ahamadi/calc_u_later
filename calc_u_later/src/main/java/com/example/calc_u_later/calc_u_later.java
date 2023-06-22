package com.example.calc_u_later;

import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.*;

import java.util.Arrays;

import static javafx.geometry.NodeOrientation.RIGHT_TO_LEFT;

public class calc_u_later extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Stage primaryStage;
    Scene sceneBasique;


    @Override
    public void start(Stage primaryStage) {
        createSceneBasic();

        primaryStage.setTitle("Calculatrice");
        primaryStage.setScene(sceneBasique);
        primaryStage.show();
    }


    private void createSceneBasic(){
        GridPane root = new GridPane();

        /*Mode button*/

        GridPane modeButton = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        modeButton.getColumnConstraints().add(columnConstraints);

        modeButton.prefWidthProperty().bind(root.widthProperty());
        modeButton.setHgap(40);
        String[] modes = new String[] {"1","2","3"};
        Button[] modesBtn = new Button[modes.length];
        int modeColumn = 0;
        for(int i=0;i<modes.length;i++){
            modesBtn[i] = new Button();
            modesBtn[i].setText(modes[i]);
            modesBtn[i].setPrefSize(60,30);

            if (i==1) {
                modesBtn[i].setOnAction(e -> primaryStage.setScene(sceneBasique));
            }

            modeButton.add(modesBtn[i],modeColumn,0);
            modeColumn++;
        }

        /*textfield*/

        GridPane textfield = new GridPane();
        TextField calc = new TextField();
        calc.prefWidthProperty().bind(root.widthProperty());
        calc.setStyle("-fx-focus-color: transparent;");
        calc.setNodeOrientation(RIGHT_TO_LEFT);
        calc.setStyle("-fx-display-caret: false;");
        calc.setPrefHeight(60);
        textfield.add(calc,0,0);

        /* Bouton */

        GridPane button = new GridPane();
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setHgrow(javafx.scene.layout.Priority.ALWAYS);
        button.getColumnConstraints().add(columnConstraints1);



        textfield.prefWidthProperty().bind(root.widthProperty());
        String[] BUTTONS = new String[] {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        Button[] btn = new Button[BUTTONS.length];
        int row= 1;
        int column = 0;
        for(int i=0;i<BUTTONS.length;i++){
            btn[i] = new Button();
            btn[i].setText(BUTTONS[i]);
            btn[i].setPrefSize(60,60);
            int finalI = i;

            if(BUTTONS[i] != "="){
                btn[i].setOnAction(e -> appendText(calc,BUTTONS[finalI]));
            }else {
                btn[i].setOnAction(e -> evaluateExpression(calc) );
            }

            button.add(btn[i],column,row);
            column++;
            if(column >= 4) {
                row++;
                column = 0;
            }
        }
        /*Clear Button*/

        GridPane clear = new GridPane();
        Button clearButton = new Button();
        clearButton.setPrefWidth(250);
        clearButton.setText("Clear");
        clearButton.setOnAction(event -> calc.clear());
        clear.add(clearButton,0,0);

        /* ajout de la scène au stage */

        root.add(modeButton,0,0);
        root.add(textfield,0,1);
        root.add(button,0,2);
        root.add(clear,0,3);
        sceneBasique = new Scene(root,240,300);



    }



    private void appendText(TextField textField, String text) {
        textField.appendText(text);
        textField.positionCaret(textField.getText().length());
    }

    private void evaluateExpression(TextField textField) {
        String expression = textField.getText();
        if (!expression.isEmpty()) {
            try {
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("JavaScript");
                System.out.println(engine);
                Object result = engine.eval(expression);
                textField.setText(result.toString());
            } catch (ScriptException e) {
                // Gérer l'erreur d'évaluation de l'expression
                textField.setText("error");
            }
        }
    }


}
