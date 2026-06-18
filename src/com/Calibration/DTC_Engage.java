/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Calibration;

import com.UI.index;
import com.fazecast.jSerialComm.SerialPort;
import com.serial.serialConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author manis
 */
public class DTC_Engage {
    //Method to erase dtc code

    public void eraseDTC() {
        serialConnection con = new serialConnection();
        SerialPort portVar = con.getSerial();
        try {
            OutputStream Serial = portVar.getOutputStream();
            Serial.write(0xAA);
            Serial.write(0xAB);
            Serial.write(0x69);

        } catch (IOException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
