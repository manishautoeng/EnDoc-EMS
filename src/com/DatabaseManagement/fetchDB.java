package com.DatabaseManagement;

import com.Database.dbConnection;
import com.Licensing.getDeviceDetails;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manis
 */
public class fetchDB {

    Connection con;
    Statement st;

    private void get_Db_Connection() {
        try {
            con = dbConnection.dbConnect();
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(fetchDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fetch License
    public void fetchLicense(JLabel licenseDate, JLabel device) {
        try {
            getDeviceDetails getDetail = new getDeviceDetails();
            String day = "DD-";
            String month = "MM-";
            String year = "YYYY";
            String systemDetail = getDetail.fetchDetails();
            String deviceDetails = "DEVICE_HW_DETAILS";
            get_Db_Connection();
            ResultSet rs = st.executeQuery("SELECT * FROM License");
            while (rs.next()) {
                deviceDetails = rs.getString(1);
                day = rs.getString(2);
                month = rs.getString(3);
                year = rs.getString(4);
            }

            device.setText(systemDetail);
            //licenseDate.setText(day + "/" + month + "/" + year);

        } catch (Exception e) {
            Logger.getLogger(fetchDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Fetch License
    public void fetchLicense_Date(JLabel licenseDate) {
        try {
            getDeviceDetails getDetail = new getDeviceDetails();
            String day = "DD-";
            String month = "MM-";
            String year = "YYYY";
            String deviceDetails = "DEVICE_HW_DETAILS";
            String systemDetail = getDetail.fetchDetails();
            get_Db_Connection();
            ResultSet rs = st.executeQuery("SELECT * FROM License WHERE deviceDetails = '" + systemDetail + "'");
            while (rs.next()) {
                deviceDetails = rs.getString(1);
                day = rs.getString(2);
                month = rs.getString(3);
                year = rs.getString(4);
            }

            licenseDate.setText(day + "/" + month + "/" + year);

        } catch (Exception e) {
            Logger.getLogger(fetchDB.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //Method to fetch ECU flash
    public void fetch_FlashECU(JLabel flashCounter) {
        try {
            get_Db_Connection();
            int flashCount = Integer.parseInt(flashCounter.getText());
            ResultSet rs = st.executeQuery("SELECT * FROM totalFlashCount");
            while (rs.next()) {
                flashCount = Integer.parseInt(rs.getString(1));
            }
            flashCounter.setText(String.valueOf(flashCount));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method to Fetch Data from db
    public void addECU_Count(JLabel totalCount) {
        try {
            int rowCount = 0;
            int ecuCount = 0;
            int count = 0;
            con = dbConnection.dbConnect();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM totalCount");
            ResultSet rt = st.executeQuery("SELECT * FROM totalCount");
            while (rs.next()) {
                rowCount = Integer.parseInt(rs.getString(1));
                ecuCount = Integer.parseInt(rt.getString("ECU_Count"));
                count = ecuCount + 1;
            }
            if (rowCount > 0) {
                st.executeQuery("DELETE FROM totalCount");

            } else {
                st.executeQuery("INSERT INTO totalCount VALUES('" + (count + 1) + "')");
            }

            ResultSet rd = st.executeQuery("Select * from totalCount");
            while (rd.next()) {
                totalCount.setText(rd.getString("ECU_Count"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(fetchDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Fetch Diagnostics Table
    public void fetchDiagnostics(JTable Diagnostics) {
        try {
            get_Db_Connection();
            DefaultTableModel table_model = (DefaultTableModel) Diagnostics.getModel();
            table_model.setRowCount(0);
            ResultSet rs = st.executeQuery("SELECT * FROM Diagnostics");
            while (rs.next()) {
                Object[] row = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),};
                table_model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(fetchDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
