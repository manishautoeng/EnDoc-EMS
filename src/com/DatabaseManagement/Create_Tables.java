package com.DatabaseManagement;

import com.Database.dbConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manis
 */
public class Create_Tables {

    Connection con = dbConnection.dbConnect();
    Statement st;

    //Method to create tables
    public void create_Tables() {
        try {
            st = con.createStatement();

            //Making Diagnostics table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS License(deviceDetails TEXT UNIQUE, validDay TEXT, validMonth TEXT, validYear TEXT)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS lastLogin(day TEXT, month TEXT, year TEXT)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS totalFlashCount(count Text)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Diagnostics(S_No TEXT, P_Code TEXT, Description TEXT, D_bounce TEXT, Min_ADC TEXT, Max_ADC TEXT)");
            // JOptionPane.showMessageDialog(null, "Tables Created Successfully!");
            EngineParamLUT();

        } catch (SQLException ex) {
            Logger.getLogger(Create_Tables.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //LUT Database
    private void EngineParamLUT() {
        try {
            st = con.createStatement();

            //Engine Parameters
            st.execute("CREATE TABLE IF NOT EXISTS EngineParam(LUT TEXT UNIQUE, VSS_CF TEXT, Fuel_cutOff TEXT, DOI_microsec TEXT, DOI_OP_microsec TEXT, EOT_sec TEXT, EOT_CF TEXT, Pump_ON Text, Glow_Plug TEXT, DeltaPressure_Avg TEXT, DeltaPressure_SUM TEXT, EGR_max TEXT, EGR_min TEXT, Consumption_max TEXT, Consumption_min TEXT, WI_reset TEXT, min_Consumption TEXT, Inducement TEXT, DeltaPressure_Temp TEXT, EGR_CF TEXT, DOI_CF TEXT, Param_6 TEXT, Param_7 TEXT)");

        } catch (SQLException ex) {
            Logger.getLogger(Create_Tables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
