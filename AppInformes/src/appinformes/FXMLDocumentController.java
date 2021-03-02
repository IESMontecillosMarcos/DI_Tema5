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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextInputDialog;
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

    private Connection conexion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Cargar conexion realizada al inicio.
        conexion = AppInformes.getConnection();
    }

    @FXML
    private void onActionReport1(ActionEvent event) {
        System.out.println(">> Listado Facturas...");
        generaInforme1();    }

    @FXML
    private void onActionReport2(ActionEvent event) {
        System.out.println(">> Ventas Totales...");
        generaInforme2();
    }

    @FXML
    private void onActionReport3(ActionEvent event) {
        System.out.println(">> Facturas por Cliente...");
        generaInforme3();
    }

    @FXML
    private void onActionReport4(ActionEvent event) {
        System.out.println(">> Listado Facturas (SubInformes)...");
        generaInforme4();
    }

    public void generaInforme1() {

        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Pedidos_por_Documento.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void generaInforme2() {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Ventas_Totales.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void generaInforme3() {
        int cod;
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText("Introduzca ID del cliente.");
        tid.setContentText("ID:");
        String codString = tid.showAndWait().get();

        // Comprueba si es un numero.
        try {
            cod = Integer.parseInt(codString);
        } catch (NumberFormatException nfe) {
            System.out.println("No es un numero");
            return;
        }

        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Facturas_por_Cliente.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            parametros.put("codigo_cliente", cod);

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void generaInforme4() {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("Pedidos_por_Documento_SubInf.jasper"));
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("SubInforme_PedDoc.jasper"));
            Map parametros = new HashMap();
            parametros.put("subReportParameter", jsr);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
