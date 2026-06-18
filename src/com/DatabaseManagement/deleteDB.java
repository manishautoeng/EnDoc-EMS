package com.DatabaseManagement;

import com.Database.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manis
 */
public class deleteDB {

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

    //Method to Delete Diagnostic
    public void delete_Diagnostic(String P_code) {
        get_DB_Connection();
        try {
            st.executeUpdate("DELETE FROM Diagnostics WHERE P_Code = '" + P_code + "'");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(insertDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
