package com.DatabaseManagement;

import com.Database.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author manis
 */
public class defaultDB {

    Connection con;
    Statement st;

    //Method to fetch DbConnection
    private void get_DB_Connection() {
        try {
            con = dbConnection.dbConnect();
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(insertDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to insert Licensing Details
    public void insertLicensing() {
        try {
            get_DB_Connection();
            st.executeUpdate("INSERT INTO License VALUES ('31', '3', '2024')");
        } catch (SQLException ex) {
            Logger.getLogger(insertDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void defaultDiagnostics() {
        try {
            get_DB_Connection();
            String[] S_no = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
            String[] Pcode = {"P0689", "P0690", "P0120", "P0123", "P0335", "P0197", "P0195", "P0196", "P0199", "P0406", "P0405", "P0400", "P0404", "P0401", "P0402", "P204D", "P204A", "P204B", "P20E8", "P2048", "P2047", "P203F", "P20F5", "P20F6"};
            String[] DESCRIPTION = {"Battery Voltage Low", "Battery Voltage High", "TPS Open Circuit", "TPS Sensor for circuit High", "Crank sensor Open Circuit", "ET circuit Low", "ET Open Circuit", "EOT Sensor out of range", "EOT Sensor Performance Error", "EGR Circuit High", "EGR Circuit Low", "EGR Open Circuit", "EGR Out of range", "EGR Stuck flow insufficient", "EGR Stuck flow Excessive", "Reductant Pr Sensor Circuit High", "Reductant Pr Sensor open Circuit", "Reductant Pr Sensor out of range circuit range/performance", "Reductant Pr Sensor too Low", "Reductant Injector Circuit Low", "Reductant Injector open circuit", "Reductant level Empty", "Reductant Consumption to high", "Reductant Consumption to Low"};
            String[] D_Bounce = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
            String[] Min_Adc = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
            String[] Max_Adc = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};

            st.executeUpdate("DELETE FROM Diagnostics");

            for (int i = 0; i < Pcode.length; i++) {
                st.executeUpdate("INSERT INTO Diagnostics VALUES('" + S_no[i] + "', '" + Pcode[i] + "', '" + DESCRIPTION[i] + "','" + D_Bounce[i] + "', '" + Min_Adc[i] + "', '" + Max_Adc[i] + "')");
            }

        } catch (SQLException ex) {
            Logger.getLogger(defaultDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
