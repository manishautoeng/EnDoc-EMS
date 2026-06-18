package com.Licensing;

import com.Database.dbConnection;
import com.Licensing.LicensingDetails;
import com.Licensing.Unregistered;
import com.Licensing.getDeviceDetails;
import com.UI.Main_Login;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class checkLicense {

    public void check(int vDay, int vMonth, int vYear) {
        try {
            
           boolean isValid =  checkLastLogin(vDay, vMonth, vYear);
            
           if(!isValid){
               JOptionPane.showMessageDialog(null, "System Date was Altered! ");
               return;
           }
            
            
            getDeviceDetails device = new getDeviceDetails();
            String currentDeviceId = device.fetchDetails();
            ArrayList<String> registeredDevices = new ArrayList<>();

            int validDay = 0, validMonth = 0, validYear = 0;
            boolean isRegistered = false;

            Connection con = dbConnection.dbConnect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM License");

            while (rs.next()) {
                String dbDeviceId = rs.getString(1);
                registeredDevices.add(dbDeviceId);

                if (dbDeviceId.equals(currentDeviceId)) {
                    isRegistered = true;
                    validDay = Integer.parseInt(rs.getString(2));
                    validMonth = Integer.parseInt(rs.getString(3));
                    validYear = Integer.parseInt(rs.getString(4));
                }
            }

            if (!isRegistered) {
                new Unregistered(currentDeviceId).setVisible(true);
                return;
            }

            // Logging info
            System.out.println("Current Date: " + vDay + "/" + vMonth + "/" + vYear);
            System.out.println("Valid Till: " + validDay + "/" + validMonth + "/" + validYear);
            System.out.println("Device: " + currentDeviceId);

            LocalDate currentDate = LocalDate.of(vYear, vMonth, vDay);
            LocalDate expiryDate = LocalDate.of(validYear, validMonth, validDay);

            if (!currentDate.isAfter(expiryDate)) {
                new Main_Login(validDay + "/" + validMonth + "/" + validYear).setVisible(true);
            } else {
                new LicensingDetails().setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(checkLicense.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      public boolean checkLastLogin(int vDay, int vMonth, int vYear) {
        try {
            
            
            
          

            int validDay = 0, validMonth = 0, validYear = 0;
            boolean isRegistered = false;

            Connection con = dbConnection.dbConnect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM lastLogin");

            while (rs.next()) {
                        

              
                    validDay = Integer.parseInt(rs.getString(1));
                    validMonth = Integer.parseInt(rs.getString(2));
                    validYear = Integer.parseInt(rs.getString(3));
                
            }

          

            // Logging info
            System.out.println("Current Date: " + vDay + "/" + vMonth + "/" + vYear);
            System.out.println("Last Login: " + validDay + "/" + validMonth + "/" + validYear);
      

            LocalDate currentDate = LocalDate.of(vYear, vMonth, vDay);
            LocalDate lastLoginDate = LocalDate.of(validYear, validMonth, validDay);

            if ( currentDate.equals(lastLoginDate)) {
                return true;
            } else if(!currentDate.isAfter(lastLoginDate)) {
                return false;
            }else{
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(checkLicense.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    
}
