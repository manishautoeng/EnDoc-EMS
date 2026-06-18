package com.lutDAO;

import com.Database.dbConnection;
import com.DatabaseManagement.insertDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author manis
 */
public class fetchLUT {

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

    //Method to fetch Engine Parameters
    public void fetchEngineParam(
            String LUT,
            JTextField VSS_CF,
            JTextField Fuel_cutoff,
            JTextField DOI_microsec,
            JTextField DOI_OP_microsec,
            JTextField EOT_sec,
            JTextField EOT_Sec_CF,
            JTextField Pump_ON,
            JTextField Glow_Plug,
            JTextField DeltaPressure_AVG,
            JTextField DeltaPressure_SUM,
            JTextField EGR_ADC_Max,
            JTextField EGR_ADC_Min,
            JTextField Consumption_max,
            JTextField Consumption_min,
            JTextField WI_reset,
            JTextField min_Consumption,
            JTextField Inducement,
            JTextField DeltaPressure_Temp,
            JTextField EGR_CF,
            JTextField DOI_CF,
            JTextField Param6,
            JTextField Param7
    ) {
        try {
            get_DB_Connection();
            //Get statement
            st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM EngineParam WHERE LUT = '" + LUT + "'");
            if (rs.equals("null")) {
                JOptionPane.showMessageDialog(null, "Data is already saved for this LUT");
            } else {
                while (rs.next()) {
                    VSS_CF.setText(rs.getString(2));
                    Fuel_cutoff.setText(rs.getString(3));
                    DOI_microsec.setText(rs.getString(4));
                    DOI_OP_microsec.setText(rs.getString(5));
                    EOT_sec.setText(rs.getString(6));
                    EOT_Sec_CF.setText(rs.getString(7));
                    Pump_ON.setText(rs.getString(8));
                    Glow_Plug.setText(rs.getString(9));
                    DeltaPressure_AVG.setText(rs.getString(10));
                    DeltaPressure_SUM.setText(rs.getString(11));
                    EGR_ADC_Max.setText(rs.getString(12));
                    EGR_ADC_Min.setText(rs.getString(13));
                    Consumption_max.setText(rs.getString(14));
                    Consumption_min.setText(rs.getString(15));
                    WI_reset.setText(rs.getString(16));
                    min_Consumption.setText(rs.getString(17));
                    Inducement.setText(rs.getString(18));
                    DeltaPressure_Temp.setText(rs.getString(19));
                    EGR_CF.setText(rs.getString(20));
                    DOI_CF.setText(rs.getString(21));
                    Param6.setText(rs.getString(22));
                    Param7.setText(rs.getString(23));

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(fetchLUT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
