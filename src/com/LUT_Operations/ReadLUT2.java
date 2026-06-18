package com.LUT_Operations;

import com.UI.index;
import com.fazecast.jSerialComm.SerialPort;
import com.online_values.SerialReadData;
import com.serial.serialConnection;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manis
 */
public class ReadLUT2 {

    int[] Serial_Read_array = new int[1351];
    int reading_Index = 0;

    //Reading LUT from ECU
    private void Read_ECU_LUT(SerialPort port) {
        serialConnection con = new serialConnection();
        try {
            port = con.getSerial();
            //making inpuststream to read ECU
            InputStream Read = port.getInputStream();

            System.out.println("Reading ECU starts Here:.........");

            System.out.println("Reading ECU starts Here:.........");

            for (int i = 0; i < 1200; i++) {
                Serial_Read_array[i] = (int) Read.read();
                System.out.println("Data: " + Serial_Read_array[i] + "   Counter: " + i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Adding Data into Tables 
    //READ DOI
    private void Read_DOI_Data(JTable tableDOI) {
        int[][] DOI_array = new int[17][17];
        DefaultTableModel DOI_model = (DefaultTableModel) tableDOI.getModel();
        //DOI Data
        int counter = 4;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                int intermediate = Serial_Read_array[counter++] << 8;
                DOI_array[i][j] = intermediate + Serial_Read_array[counter++];
                DOI_model.setValueAt((int) DOI_array[i][j], j, i);
            }
        }
    }

    //Read Engine RPM
    private void Read_Engine_RPM(JTable tableDOI, JTable tableEGR, JTable tableSOI) {
        int[] Engine_RPM_array = new int[17];
        DefaultTableModel DOI_model = (DefaultTableModel) tableDOI.getModel();
        DefaultTableModel EGR_model = (DefaultTableModel) tableEGR.getModel();
        DefaultTableModel SOI_model = (DefaultTableModel) tableSOI.getModel();
        //Engine RPM Array Data
        int counter = 516;
        for (int i = 1; i < 17; i++) {
            int intermediate = Serial_Read_array[counter++] << 8;
            Engine_RPM_array[i] = intermediate + Serial_Read_array[counter++];
            DOI_model.setValueAt((int) Engine_RPM_array[i], 0, i);
            EGR_model.setValueAt((int) Engine_RPM_array[i], 0, i);
        }
        for (int i = 1; i < 14; i++) {
            SOI_model.setValueAt((int) Engine_RPM_array[i], 0, i);
        }
    }

    //Read Engine Load - TPS
    private void Read_Engine_load(JTable DOI, JTable SOI, JTable EGR) {
        DefaultTableModel DOI_model = (DefaultTableModel) DOI.getModel();
        DefaultTableModel EGR_model = (DefaultTableModel) EGR.getModel();
        DefaultTableModel SOI_model = (DefaultTableModel) SOI.getModel();
        int counter = 548;
        int[] engine_load = new int[17];
        for (int i = 1; i < 17; i++) {
            engine_load[i] = (int) Serial_Read_array[counter++];
        }

        for (int i = 1; i < 17; i++) {
            DOI_model.setValueAt((int) engine_load[i], i, 0);
            EGR_model.setValueAt((int) engine_load[i], i, 0);
            SOI_model.setValueAt((int) engine_load[i], i, 0);
        }

        DOI_model.setValueAt("Load/RPM", 0, 0);
        EGR_model.setValueAt("Load/RPM", 0, 0);
        SOI_model.setValueAt("Load/RPM", 0, 0);
    }

    //READ EGR
    private void Read_EGR_Data(JTable tableEGR) {
        int[][] EGR_array = new int[17][17];
        DefaultTableModel EGR_model = (DefaultTableModel) tableEGR.getModel();
        //DOI Data
        int counter = 564;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                EGR_array[i][j] = Serial_Read_array[counter++];
                EGR_model.setValueAt((int) EGR_array[i][j], j, i);
            }
        }
    }

    //Read Temperature 
    private void Read_Temperature(JTable Temperature) {
        DefaultTableModel temp_model = (DefaultTableModel) Temperature.getModel();
        temp_model.setValueAt("Temp[°C]", 0, 0);
        temp_model.setValueAt("Temp CF", 0, 1);
        temp_model.setValueAt("Temp ADC", 0, 2);
        int temperature_array[] = new int[17];
        int temperature_ADC_array[] = new int[17];
        int temperature_CF_array[] = new int[17];
        int counter = 820;
        for (int i = 1; i < 17; i++) {
            temperature_array[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            if (temperature_array[i] > 60000) {
                temperature_array[i] -= 65536;
            }
            temp_model.setValueAt((int) temperature_array[i], i, 0);
        }
        for (int i = 1; i < 17; i++) {
            temperature_ADC_array[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            temp_model.setValueAt((int) temperature_ADC_array[i], i, 2);
        }
        for (int i = 1; i < 17; i++) {
            temperature_CF_array[i] = Serial_Read_array[counter++];
            temp_model.setValueAt((int) temperature_CF_array[i], i, 1);
        }

    }

    //Read Pressure Array
    //Read Pressure
    private void Read_Pressure(JTable Pressure) {
        DefaultTableModel Pressure_model = (DefaultTableModel) Pressure.getModel();
        Pressure_model.setValueAt("Pr. ADC", 0, 0);
        Pressure_model.setValueAt("Pr. VOlt", 0, 1);
        int pressure_ADC[] = new int[9];
        int pressure[] = new int[9];
        int counter = 900;
        for (int i = 1; i < 9; i++) {
            pressure_ADC[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            Pressure_model.setValueAt((int) pressure_ADC[i], i, 0);
        }

        for (int i = 1; i < 9; i++) {
            pressure[i] = Serial_Read_array[counter++];
            Pressure_model.setValueAt((int) pressure[i], i, 1);
        }

        System.out.println("Counter after Pressure array reading: " + counter);
    }

    //READ CAL-ID
    private void Read_CAL_ID(JTextField Cal_ID) {
        String calIdfromECU = "";
        int[] read_call_array
                = {Serial_Read_array[924], //P
                    Serial_Read_array[925], //4
                    Serial_Read_array[926], //0
                    Serial_Read_array[927], //1
                    Serial_Read_array[928], //R
                    Serial_Read_array[929], //E
                    Serial_Read_array[930], //P
                    Serial_Read_array[931], //A
                    Serial_Read_array[932], //D
                    Serial_Read_array[933], //I
                    Serial_Read_array[934], //1
                    Serial_Read_array[935], //0
                    Serial_Read_array[936], //0
                    Serial_Read_array[937], //1
                    Serial_Read_array[938], //2
                    Serial_Read_array[939] //3
            };

        for (int i = 0; i < read_call_array.length; i++) {

            {
                String dta = "";
                dta = String.valueOf(read_call_array[i]);
                System.out.println("num: " + dta);
                int d = Integer.parseInt(dta);
                char c;

                c = (char) d;
                System.out.println("CalId C : " + c + " " + "char D: " + d);
                calIdfromECU = calIdfromECU + String.valueOf(c);

            }

        }
        Cal_ID.setText(calIdfromECU);

    }

    //Read EGT Temperature
    private void Read_EGT(JTable EGT) {
        DefaultTableModel EGT_model = (DefaultTableModel) EGT.getModel();
        EGT_model.setValueAt("EGT1[°C]", 0, 0);
        EGT_model.setValueAt("EGT ADC", 0, 1);
        EGT_model.setValueAt("EGT2[°C]", 0, 2);

        int EGT1_array[] = new int[17];
        int EGT2_array[] = new int[17];
        int EGT_ADC_array[] = new int[17];

        int counter = 940;
        for (int i = 1; i < 17; i++) {
            EGT_ADC_array[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];

            EGT_model.setValueAt((int) EGT_ADC_array[i], i, 1);
        }

        for (int i = 1; i < 17; i++) {
            EGT1_array[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            if (EGT1_array[i] > 60000) {
                EGT1_array[i] -= 65536;
            }
            EGT_model.setValueAt((int) EGT1_array[i], i, 0);
        }

        for (int i = 1; i < 17; i++) {
            EGT2_array[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            if (EGT2_array[i] > 60000) {
                EGT2_array[i] -= 65536;
            }
            EGT_model.setValueAt((int) EGT2_array[i], i, 2);
        }

        System.out.println("Counter after EGT array: " + counter);

    }

    //Read Delta Pressure Table
    private void Read_Delta_Pressure(JTable DeltaPressure) {
        DefaultTableModel DP_model = (DefaultTableModel) DeltaPressure.getModel();
        DP_model.setValueAt("Delta Pr. Volt. %", 0, 0);
        DP_model.setValueAt("Delta Pr. ADC", 0, 1);
        int counter = 1036;
        int pressure_ADC[] = new int[12];
        int pressure_volt[] = new int[12];
        for (int i = 1; i < 12; i++) {
            pressure_ADC[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            DP_model.setValueAt((int) pressure_ADC[i], i, 1);
        }
        counter = 1060;
        for (int i = 1; i < 12; i++) {
            pressure_volt[i] = (Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++];
            DP_model.setValueAt((int) pressure_volt[i], i, 0);
        }

        System.out.println("Counter after  Delta Pressure;; " + counter);

    }

    //READ SOI
    private void Read_SOI_Data(JTable tableSOI) {
        int[][] SOI_array = new int[17][15];
        DefaultTableModel SOI_model = (DefaultTableModel) tableSOI.getModel();
        //DOI Data
        int counter = 518;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 14; j++) {
                SOI_array[i][j] = Serial_Read_array[counter++];
                SOI_model.setValueAt(0, i, j);
            }
        }
    }

    //READ VIN
    private void Read_VIN(JTextField VIN) {
        String vinfromECU = "";
        int counter = 1124;
        int[] read_vin_array
                = {
                    Serial_Read_array[counter++], //R
                    Serial_Read_array[counter++], //B
                    Serial_Read_array[counter++], //X
                    Serial_Read_array[counter++], //1
                    Serial_Read_array[counter++], //2
                    Serial_Read_array[counter++], //3
                    Serial_Read_array[counter++], //4
                    Serial_Read_array[counter++], //N
                    Serial_Read_array[counter++], //C
                    Serial_Read_array[counter++], //T
                    Serial_Read_array[counter++], //H
                    Serial_Read_array[counter++], //9
                    Serial_Read_array[counter++], //8
                    Serial_Read_array[counter++], //7
                    Serial_Read_array[counter++], //0
                    Serial_Read_array[counter++], //5
                    Serial_Read_array[counter++] //6
                };

        for (int i = 0; i < read_vin_array.length; i++) {
            String dta = "";
            dta = String.valueOf(read_vin_array[i]);
            System.out.println("num: " + dta);
            int d = Integer.parseInt(dta);
            char c;
            c = (char) d;
            System.out.println("VIN C : " + c);
            vinfromECU = vinfromECU + String.valueOf(c);
        }
        VIN.setText(vinfromECU);
    }

    //READ CVN
    private void read_CVN(JTextField CVN, JTextArea lutName) {
        String[] CVN_array = new String[5];
        String[] mainarray = new String[5];
        String strCVN = "";
        CVN_array[1] = String.valueOf(Serial_Read_array[1120]);
        CVN_array[2] = String.valueOf(Serial_Read_array[1122]);
        CVN_array[3] = String.valueOf(Serial_Read_array[1122]);
        CVN_array[4] = String.valueOf(Serial_Read_array[1123]);

        for (int i = 1; i < 5; i++) {
            if ((Integer.parseInt(CVN_array[i])) <= 15) {
                mainarray[i] = "0" + Integer.toHexString(Integer.parseInt(CVN_array[i]));
            } else {
                mainarray[i] = Integer.toHexString(Integer.parseInt(CVN_array[i]));
            }
        }
//        
//        System.out.println("Reading CVN.....");
        for (int i = 1; i < 5; i++) {
            //CVN = CVN + " " + String.valueOf(mainArray[i]);
            strCVN += String.valueOf(mainarray[i]) + " ";
        }

        String CVN_LUT = strCVN;
        //CVN = CVN_array[1] + CVN_array[2] + CVN_array[3] + CVN_array[4];
        CVN.setText(strCVN.toUpperCase());

        System.out.println("CVN LUT : " + CVN_LUT);

        if (CVN_LUT.equals("41 d6 0a 00 ")) {
            lutName.setText("PVPL Passenger");
        } else if (CVN_LUT.equals("4e 22 0a 00 ")) {
            lutName.setText("PVPL Cargo");
        } else if (CVN_LUT.equals("c7 d1 0a 00 ")) {
            lutName.setText("MLR Passenger");
        } else if (CVN_LUT.equals("02 22 0a 00 ")) {
            lutName.setText("MLR Cargo");
        } else if (CVN_LUT.equals("08 2c 0a 00 ")) {
            lutName.setText("Atul Shakti");
        } else if (CVN_LUT.equals("39 d6 0a 00 ")) {
            lutName.setText("Atul Cargo");
        } else if (CVN_LUT.equals("e1 d3 0a 00 ")) {
            lutName.setText("Baxy Passenger");
        } else if (CVN_LUT.equals("d4 2a 0a 00 ")) {
            lutName.setText("Baxy Cargo");
        } else if (CVN_LUT.equals("6c 38 01 00 ")) {
            lutName.setText("Sample LUT");
        } else {
            lutName.setText("Unkown LUT");
        }

    }

    private void ScrollTOBottom(String btnText, JScrollPane jScrollPane5) {
        AdjustmentListener scroll = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        };
        if (btnText.equals("Disconnect")) {
            AdjustmentListener[] listeners = jScrollPane5.getVerticalScrollBar().getAdjustmentListeners();
            for (AdjustmentListener listener : listeners) {
                jScrollPane5.getVerticalScrollBar().removeAdjustmentListener(listener);
            }
        } else {
            jScrollPane5.getVerticalScrollBar().addAdjustmentListener(scroll);
        }
    }

    //Read Engine Parameter
    private void read_EngineParam(
            JTextField Engine_Speed,
            JTextField Fuel_Cut_OFF,
            JTextField DOI_miocrosec,
            JTextField DOI_OP,
            JTextField Sec_EOT,
            JTextField Sec_EOT_CF,
            JTextField pumpON,
            JTextField glowPlug,
            JTextField VSS_CF,
            JTextField Max_Accleration,
            JTextField EGR_ADC_Max,
            JTextField EGR_ADC_Min,
            JTextField Consumption_Error_max,
            JTextField Consumption_Error_min,
            JTextField WI_Reset,
            JTextField min_Consumption,
            JTextField inducement
    ) {
        int counter = 1084;

        Engine_Speed.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        pumpON.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        glowPlug.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        VSS_CF.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        Sec_EOT.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        Sec_EOT_CF.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        DOI_OP.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        DOI_miocrosec.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        Max_Accleration.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        EGR_ADC_Max.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        EGR_ADC_Min.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        Consumption_Error_max.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        Consumption_Error_min.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        WI_Reset.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        min_Consumption.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        inducement.setText(String.valueOf((Serial_Read_array[counter++]) + Serial_Read_array[counter++]));
        //Fuel_Cut_OFF.setText(String.valueOf((Serial_Read_array[counter++]) + Serial_Read_array[counter++]));
        Fuel_Cut_OFF.setText(String.valueOf(5500));

        //Sec EOT_CF        
    }

    public void Read_EnginePara(
            JTextField txtParam3,
            JTextField txtParam4,
            JTextField txtParam5,
            JTextField txtParam6,
            JTextField txtParam7
    ) {
        int counter = 1141;

        txtParam3.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        txtParam4.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        txtParam5.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        txtParam6.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));
        txtParam7.setText(String.valueOf((Serial_Read_array[counter++] << 8) + Serial_Read_array[counter++]));

        txtParam3.setText("25");
        txtParam4.setText("0");
        txtParam5.setText("0");
        txtParam6.setText("0");
        txtParam7.setText("0");
    }

    public void Read_ECU(
            SerialPort portVar,
            JTable tableDOI,
            JTable tableSOI,
            JTextField Cal_ID,
            JTextField VIN,
            JTable tableEGR,
            JTextField CVN,
            JTextField Engine_Speed,
            JTextField Fuel_Cut_OFF,
            JTextField DOI_miocrosec,
            JTextField DOI_OP,
            JTextField Sec_EOT,
            JTextField Sec_EOT_CF,
            JTextField pumpON,
            JTextField glowPlug,
            JTextField VSS_CF,
            JTextField Max_Accleration,
            JTextField EGR_ADC_Max,
            JTextField EGR_ADC_Min,
            JTextField Consumption_Error_max,
            JTextField Consumption_Error_min,
            JTextField WI_Reset,
            JTextField min_Consumption,
            JTextField inducement,
            JTable Temperature,
            JTable Pressure,
            JTextArea lutName,
            JCheckBox onlineData,
            SerialPort port,
            JButton ReadBtn,
            JButton UpdateBtn,
            JButton flashBtn,
            //Online Data values
            JTable table_OD,
            JTable table_ADC,
            JTable errorCodeDTC,
            JTable freezeFrame,
            JTable descriptionDTC,
            JProgressBar statusMIL,
            JProgressBar statusPump,
            JProgressBar statusEngineHalt,
            JTable consumptionData,
            JLabel lblDTCCount,
            JProgressBar waterLevel,
            JLabel waterLevelPercent,
            JLabel display_message,
            JTable tableEGT,
            JTable tableDeltaPressure,
            JTable newFreezeFrame,
            JLabel display_msg_1,
            JRadioButtonMenuItem btn100,
            JRadioButtonMenuItem btn500,
            JRadioButtonMenuItem btn1000,
            JTable table_DOI,
            JLabel comStatus,
            JButton btnConnect,
            JButton btnDisconnect,
            JScrollPane jScrollPane5,
            JTextField txtParam3,
            JTextField txtParam4,
            JTextField txtParam5,
            JTextField txtParam6,
            JTextField txtParam7,
            JLabel lblDatafrequency,
            JCheckBoxMenuItem ConvertADC,
            JProgressBar statusWIM, JProgressBar statusWaterEmpty, JProgressBar statusInducement, JLabel lbl_FWversion, JLabel comProtocol, JLabel functionalityTest
    ) {
        //Send Message to Read ECU
        Read_ECU_LUT(portVar);
        //Checking if the reading was done successfully if data misses we can check and ask for retrying
        //if (Serial_Read_array[1] == 38 && Serial_Read_array[1117] == 38 && Serial_Read_array[1118] == 38 && Serial_Read_array[1150] == 38 && Serial_Read_array[1151] == 38) 
        if (Serial_Read_array[1] == 38 && Serial_Read_array[2] == 38 && Serial_Read_array[1152] == 38 && Serial_Read_array[1153] == 38) {
            Read_DOI_Data(tableDOI);
            Read_SOI_Data(tableSOI);
            Read_Engine_RPM(tableDOI, tableEGR, tableSOI);
            Read_Engine_load(tableDOI, tableSOI, tableEGR);
            Read_EGR_Data(tableEGR);
            Read_Temperature(Temperature);
            Read_Pressure(Pressure);
            Read_CAL_ID(Cal_ID);
            Read_EGT(tableEGT);
            Read_Delta_Pressure(tableDeltaPressure);
            read_EngineParam(Engine_Speed, Fuel_Cut_OFF, DOI_miocrosec, DOI_OP, Sec_EOT, Sec_EOT_CF, pumpON, glowPlug, VSS_CF, Max_Accleration, EGR_ADC_Max, EGR_ADC_Min, Consumption_Error_max, Consumption_Error_min, WI_Reset, min_Consumption, inducement);
            read_CVN(CVN, lutName);
            Read_VIN(VIN);
            Read_EnginePara(txtParam3, txtParam4, txtParam5, txtParam6, txtParam7);

//            
            onlineData.setSelected(false);
            SerialReadData Read_Obj = new SerialReadData();
            OutputStream Serial = port.getOutputStream();
//            
//            
//            
//            add_Engine_RPM(tableDOI, tableSOI, tableEGR);
            try {
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                flashBtn.setEnabled(true);
                ReadBtn.setEnabled(false);
                UpdateBtn.setEnabled(false);
                Read_Obj.readOnlineData(onlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevel, waterLevelPercent, display_message, newFreezeFrame, display_msg_1, btn100, btn500, btn1000, table_DOI, comStatus, btnConnect, btnDisconnect, jScrollPane5, lblDatafrequency, ConvertADC, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, comProtocol, functionalityTest);

                // Read_Obj.readOnlineData(onlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevel, waterLevelPercent, display_message, newFreezeFrame, display_msg_1, btn100, btn500, btn1000, table_DOI, comStatus, btnConnect, btnDisconnect, jScrollPane5, lblDatafrequency, ConvertADC);
                if (onlineData.isSelected()) {
                    ScrollTOBottom("Disconnect", jScrollPane5);
                } else {
                    ScrollTOBottom("Scroll", jScrollPane5);
                }

            } catch (IOException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

//            
//            
//            
//            
            JOptionPane.showMessageDialog(null, "Reading Succesfull!");
        } else {
            //Call Reset Method
            onlineData.setSelected(false);
            SerialReadData Read_Obj = new SerialReadData();
            OutputStream Serial = port.getOutputStream();
            try {
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                flashBtn.setEnabled(true);
                ReadBtn.setEnabled(false);
                UpdateBtn.setEnabled(false);
                Read_Obj.readOnlineData(onlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevel, waterLevelPercent, display_message, newFreezeFrame, display_msg_1, btn100, btn500, btn1000, table_DOI, comStatus, btnConnect, btnDisconnect, jScrollPane5, lblDatafrequency, ConvertADC, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, comProtocol, functionalityTest);

                // Read_Obj.readOnlineData(onlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevel, waterLevelPercent, display_message, newFreezeFrame, display_msg_1, btn100, btn500, btn1000, table_DOI, comStatus, btnConnect, btnDisconnect, jScrollPane5, lblDatafrequency, ConvertADC);
                if (onlineData.isSelected()) {
                    ScrollTOBottom("Disconnect", jScrollPane5);
                } else {
                    ScrollTOBottom("Scroll", jScrollPane5);
                }

            } catch (IOException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null, "Error in Reading, Please retry Reading!");

        }

    }

}
