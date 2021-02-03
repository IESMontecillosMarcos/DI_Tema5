/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Marcos Gonzalez Leon
 */
public class AppInformes extends Application {
    
    private static Connection conexion;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
        conexion.close();
    }
    
    public static void setConexion(Connection conexion) {
        AppInformes.conexion = conexion;
    }
    
}
