package com.DatabaseManagement;

import com.Database.dbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manish Pandey
 */
public class updateDB {

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

    //Method to update diagnostics
    public void updateDiagnostics(JTable Diagnostics) {
        DefaultTableModel Diagnostic_model = (DefaultTableModel) Diagnostics.getModel();
        try {
            get_DB_Connection();
            st.executeUpdate("DELETE FROM Diagnostics");
            for (int i = 0; i < Diagnostic_model.getRowCount(); i++) {
                st.executeUpdate("INSERT INTO Diagnostics VALUES ('" + Diagnostic_model.getValueAt(i, 0) + "', '" + Diagnostic_model.getValueAt(i, 1) + "', '" + Diagnostic_model.getValueAt(i, 2) + "', '" + Diagnostic_model.getValueAt(i, 3) + "', '" + Diagnostic_model.getValueAt(i, 4) + "', '" + Diagnostic_model.getValueAt(i, 5) + "' )");
            }

        } catch (Exception e) {
            Logger.getLogger(insertDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Method to Reset flash count in database
    public void resetFlashCount() {
        try {
            get_DB_Connection();
            st.executeUpdate("UPDATE totalFlashCount SET count = '" + String.valueOf(0) + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
