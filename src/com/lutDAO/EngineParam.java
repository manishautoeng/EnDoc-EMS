package com.lutDAO;

import com.Database.dbConnection;
import com.DatabaseManagement.insertDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Manish Pandey
 */
public class EngineParam {

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

    //Method to add Engine Parameters in db
    public void saveEngineParam(
            String LUT,
            String VSS_CF,
            String Fuel_cutoff,
            String DOI_microsec,
            String DOI_OP_microsec,
            String EOT_sec,
            String EOT_CF,
            String Pump_ON,
            String Glow_Plug,
            String DeltaPressure_AVG,
            String DeltaPressure_SUM,
            String EGR_max,
            String EGR_min,
            String Consumption_max,
            String Consumption_min,
            String WI_reset,
            String min_Consumption,
            String Inducement,
            String DeltaPressure_Temp,
            String EGR_CF,
            String DOI_CF,
            String Param_6,
            String Param_7
    ) {
        get_DB_Connection();
        try {
            st = con.createStatement();

            if (LUT.equals("")) {
                JOptionPane.showMessageDialog(null, "LUT name cannot be empty");
            } else {
                try {
                    st.executeUpdate("INSERT INTO EngineParam VALUES ('" + LUT + "', '" + Fuel_cutoff + "', '" + VSS_CF + "', '" + DOI_microsec + "', '" + DOI_OP_microsec + "', '" + EOT_sec + "', '" + EOT_CF + "', '" + Pump_ON + "', '" + Glow_Plug + "' ,'" + DeltaPressure_AVG + "', '" + DeltaPressure_SUM + "', '" + EGR_max + "', '" + EGR_min + "', '" + Consumption_max + "', '" + Consumption_min + "', '" + WI_reset + "', '" + min_Consumption + "', '" + Inducement + "' ,'" + DeltaPressure_Temp + "', '" + EGR_CF + "', '" + DOI_CF + "', '" + Param_6 + "', '" + Param_7 + "')");
                    JOptionPane.showMessageDialog(null, "Data Saved");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data is already saved for this LUT");
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(EngineParam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Method to update engine param in db 
    public void updateEngineParam(
            String LUT,
            String VSS_CF,
            String Fuel_cutOff,
            String DOI_microsec,
            String DOI_OP_microsec,
            String EOT_sec,
            String EOT_CF,
            String Pump_ON,
            String Glow_Plug,
            String DeltaPressure_AVG,
            String DeltaPressure_SUM,
            String EGR_max,
            String EGR_min,
            String Consumption_max,
            String Consumption_min,
            String WI_reset,
            String min_Consumption,
            String Inducement,
            String DeltaPressure_Temp,
            String EGR_CF,
            String DOI_CF,
            String Param_6,
            String Param_7
    ) {
        get_DB_Connection();
        try {
            st = con.createStatement();

            if (LUT.equals("LUT")) {
                JOptionPane.showMessageDialog(null, "LUT name cannot be empty");
            } else {
                st.executeUpdate(" UPDATE EngineParam SET VSS_CF = '" + VSS_CF + "', Fuel_cutoff = '" + Fuel_cutOff + "', DOI_microsec =  '" + DOI_microsec + "', DOI_OP_microsec = '" + DOI_OP_microsec + "', EOT_sec = '" + EOT_sec + "', EOT_CF ='" + EOT_CF + "', Pump_ON = '" + Pump_ON + "', Glow_Plug = '" + Glow_Plug + "' ,DeltaPressure_AVG = '" + DeltaPressure_AVG + "', DeltaPressure_SUM = '" + DeltaPressure_SUM + "', EGR_max = '" + EGR_max + "', EGR_min = '" + EGR_min + "', Consumption_max = '" + Consumption_max + "', Consumption_min = '" + Consumption_min + "',WI_reset = '" + WI_reset + "', min_Consumption = '" + min_Consumption + "', Inducement = '" + Inducement + "' ,DeltaPressure_Temp = '" + DeltaPressure_Temp + "', EGR_CF = '" + EGR_CF + "', DOI_CF = '" + DOI_CF + "', Param_6 = '" + Param_6 + "', Param_7 = '" + Param_7 + "' WHERE LUT = '" + LUT + "' ");
                JOptionPane.showMessageDialog(null, "Data Updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EngineParam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
