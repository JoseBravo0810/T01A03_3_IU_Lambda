/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t01a03_3_iu_lambda;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author jose
 */
public class T01A03_3_IU_Lambda extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label lb1 = new Label("Annual Interest Rate:");
        Label lb2 = new Label("Number of Years:");
        Label lb3 = new Label("Loan Amount:");
        Label lb4 = new Label("Monthly Payment:");
        Label lb5 = new Label("Total Payment:");
        Label error = new Label();
        
        TextField tf1 = new TextField();
        tf1.setAlignment(Pos.BASELINE_RIGHT);
        TextField tf2 = new TextField();
        tf2.setAlignment(Pos.BASELINE_RIGHT);
        TextField tf3 = new TextField();
        tf3.setAlignment(Pos.BASELINE_RIGHT);
        TextField tf4 = new TextField();
        tf4.setAlignment(Pos.BASELINE_RIGHT);
        tf4.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            e.consume();
        });
        TextField tf5 = new TextField();
        tf5.setAlignment(Pos.BASELINE_RIGHT);
        tf5.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            e.consume();
        });
        
        Button boton = new Button("Calculate");
        
        GridPane root = new GridPane();
        root.setHgap(5.0);
        root.setVgap(5.0);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15.0));
        
        root.add(lb1, 0, 0);
        root.add(lb2, 0, 1);
        root.add(lb3, 0, 2);
        root.add(lb4, 0, 3);
        root.add(lb5, 0, 4);
        
        root.add(tf1, 1, 0);
        root.add(tf2, 1, 1);
        root.add(tf3, 1, 2);
        root.add(tf4, 1, 3);
        root.add(tf5, 1, 4);
        
        boton.setOnAction(e -> {
                double tfn1, tfn2, tfn3, tfn4, tfn5, r, num, denom;
                
                DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
                simbolos.setDecimalSeparator('.');
                DecimalFormat formateador = new DecimalFormat("$ .##", simbolos);
                
                try
                {
                    tfn1 = Double.parseDouble(tf1.getText());
                    tfn2 = Double.parseDouble(tf2.getText());
                    tfn3 = Double.parseDouble(tf3.getText());
                    
                    r = tfn1 / (100 * 12);
                    
                    denom = Math.pow(1 + r, -12 * tfn2);
                    denom = 1 - denom;
                    
                    num = tfn3 * r;
                    
                    tfn4 = num / denom;
                    
                    tfn5 = tfn4 * 12 * 30;
                    
                    root.getChildren().remove(error);
                    
                    tf4.setText(String.valueOf(formateador.format(tfn4)));
                    tf5.setText(String.valueOf(formateador.format(tfn5)));
                }
                catch(NumberFormatException err)
                {
                    root.getChildren().remove(error);
                    error.setText("Los tres primeros campos deben estar completos");
                    root.setColumnSpan(error, 2);
                    error.setStyle("-fx-text-fill: orange;");
                    root.add(error, 0, 6);
                    e.consume();
                }
        });
        
        FlowPane botonera = new FlowPane();
        botonera.setAlignment(Pos.BOTTOM_RIGHT);
        botonera.maxWidth(root.getWidth() / 2);
        root.add(botonera, 1, 5);
        botonera.getChildren().add(boton);
        
        Scene scene = new Scene(root, 600, 400);
        scene.addEventFilter(KeyEvent.KEY_TYPED, (e -> {
            if(!Character.isDigit(e.getCharacter().charAt(0)) && !(e.getCharacter().charAt(0) == '.'))
            {
                root.getChildren().remove(error);
                error.setText("No puede introducir nada que no sean digitos o '.'");
                root.add(error, 0, 6);
                root.setColumnSpan(error, 2);
                error.setStyle("-fx-text-fill: red;");
                root.add(error, 0, 6);
                e.consume();
            }
            else
            {
                root.getChildren().remove(error);
            }
        }));
        
        primaryStage.setTitle("Calculadora Hipoteca Lambda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
