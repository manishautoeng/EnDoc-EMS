package com.DatabaseManagement;

import com.Database.dbConnection;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author manis
 */
public class insertDB {

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

    //Method to Add DTC
    public void addDiagnostic(String sNo, String PCode, String Desc, String DBounce, String minADC, String maxADC) {
        try {
            get_DB_Connection();
            st.executeUpdate("INSERT INTO Diagnostics VALUES('" + sNo + "', '" + PCode + "', '" + Desc + "', '" + DBounce + "', '" + minADC + "', '" + maxADC + "')");

        } catch (Exception e) {
            Logger.getLogger(insertDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Method to add Licensing details
    public void addLicense(String deviceDetails, String vDay, String vMonth, String vYear) {

        try {
            get_DB_Connection();
            System.out.println("Method Called!");

            Statement tuf = con.createStatement();
            
            
             tuf.executeUpdate("UPDATE License SET validDay = '" + vDay + "', validMonth = '" + vMonth + "', validYear = '" + vYear + "' WHERE deviceDetails = '" + deviceDetails + "' ");
       
            
//            boolean isPresent = false;
//
//            while (rs.next()) {
//                if (deviceDetails.equals(rs.getString("deviceDetails"))) {
//                    isPresent = true;
//                } else {
//                    isPresent = false;
//                }
//            }
//
//            if (isPresent) {
//                    } else {
//                tuf.executeUpdate("INSERT INTO License VALUES('" + deviceDetails + "','" + vDay + "', '" + vMonth + "', '" + vYear + "')");
//
//            }

            System.out.println("Data update ho gaya hai!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Method to add ECU flash count
    public void addFlashCount(JLabel flashCounter) {
        try {
            get_DB_Connection();
            int flash = Integer.parseInt(flashCounter.getText()) + 1;
            st.executeUpdate("DELETE FROM totalFlashCount");
            st.executeUpdate("INSERT INTO totalFlashCount VALUES('" + (flash) + "')");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Method to save todays date
    public void addLastLogin(String day, String month, String year) {
        get_DB_Connection();
        try {

            st.executeUpdate("UPDATE lastLogin SET day = '" + day + "',  month = '"+month+"' , year = '"+year+"'  ");

            st.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
