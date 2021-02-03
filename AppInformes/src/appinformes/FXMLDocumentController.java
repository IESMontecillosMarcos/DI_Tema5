/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appinformes;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Marcos Gonzalez Leon
 */
public class FXMLDocumentController implements Initializable {
    
    // Vars.
    private Connection conexion;
    private String urlDB; 
    
    
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD();
        AppInformes.setConexion(conexion);
        // TODO
    }
    
    public void createListadoFacturas() {

        try {
            // POR AQUIA SJDALS d.
            // Fallo al cargar el informe. Revisar jrxml?
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("facturas.jasper"));
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void createVentasTotales() {
        try {
            // Fallo al cargar el informe. Revisar jrxml? TAMBIEN!
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("ventastotales.jasper"));
            
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   
    public void conectaBD() {
        //Establecemos conexión con la BD
        urlDB = "jdbc:hsqldb:hsql://localhost:9001/xdb";
        String usuario = "sa";
        String clave = "";
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(urlDB, usuario, clave);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        } catch (java.lang.InstantiationException sqlex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        } catch (Exception ex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
    }

    @FXML
    private void onActionListadoFacturas(ActionEvent event) {
        createListadoFacturas();
    }

    @FXML
    private void onActionVentasTotales(ActionEvent event) {
        createVentasTotales();
    }

    @FXML
    private void onActionFacturasCliente(ActionEvent event) {
        
    }

    @FXML
    private void onActionListadoFacturasSubinformes(ActionEvent event) {
        
    }
    
}