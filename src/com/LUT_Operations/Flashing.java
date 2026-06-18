package com.LUT_Operations;

import com.DatabaseManagement.fetchDB;
import com.DatabaseManagement.insertDB;
import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Flashing {

    int[] DOI_array = new int[513];
    int[] EGR_array = new int[257];
    int[] SOI_array = new int[209];
    int[] cal_array_num = new int[17];
    int[] cal_array_alpha = new int[8];
    int[] VIN_array = new int[18];
    int[] Engine_Load_array = new int[17];
    int Engine_Speed, Fuel_cut_OFF, DOI_microsec, Delay_for_injection, EOT_C, EOT_CF, pumpON, glowPlugON, VSS_CF, max_Acc, EGR_POT_ADC_Max;
    int EGR_POT_ADC_Min, Consumption_Error_Max, Consumption_Error_Min, WIReset, MinConsumption, Inducement;
    int Para3, Para4, Para5, Para6, Para7;
    int[] Temperature_array = new int[33];
    int[] Temperature_ADC_array = new int[33];
    int[] Temperature_CF_array = new int[17];
    int[] Engine_RPM_array = new int[33];
    int[] Pressure_ADC_array = new int[17];
    int[] Pressure_voltage_array = new int[9];
    int[] EGT_1_array = new int[33];
    int[] EGT_2_array = new int[33];
    int[] EGT_ADC_array = new int[33];
    int[] Delta_Pressure_array = new int[25];
    int[] Delta_Pressure_ADC_array = new int[25];
    int[] D_Bounce_array = new int[200];
    int[] D_Bounce_Min_ADC = new int[200];
    int[] D_Bounce_Max_ADC = new int[200];
    int DBounce_count = 0;

    //Fetch All LUT
    //Fetching DOI LUT with Shifted Value
    private void fetch_DOI_data(JTable DOI) {
        DefaultTableModel DOI_model = (DefaultTableModel) DOI.getModel();

        int counter = 1;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                DOI_array[counter++] = ((int) DOI_model.getValueAt(j, i)) >> 8;
                DOI_array[counter++] = ((int) DOI_model.getValueAt(j, i));
            }
        }
    }

    //Fetching SOI Data without fetching
    private void fetch_SOI_data(JTable SOI) {
        DefaultTableModel SOI_model = (DefaultTableModel) SOI.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 14; j++) {
                SOI_array[counter++] = ((int) SOI_model.getValueAt(i, j));
            }
        }
    }

    //Method to fetch CAL-ID
    //Method to fetch CAL ID
    private void fetch_CALID(String cal_ID) {
        int[] cal_array = new int[17];

        //Converting calId into integer values
        for (int i = 1; i < 17; i++) {
            cal_array[i] = (int) cal_ID.charAt(i - 1);
        }

        //Storing numerical data
        cal_array_num[1] = cal_array[1];
        cal_array_num[2] = cal_array[2];
        cal_array_num[3] = cal_array[3];
        cal_array_num[4] = cal_array[4];
        cal_array_num[5] = cal_array[5];
        cal_array_num[6] = cal_array[6];
        cal_array_num[7] = cal_array[7];

        //Storing alphabetical data
        cal_array_num[8] = cal_array[8];
        cal_array_num[9] = cal_array[9];
        cal_array_num[10] = cal_array[10];
        cal_array_num[11] = cal_array[11];
        cal_array_num[12] = cal_array[12];
        cal_array_num[13] = cal_array[13];
        cal_array_num[14] = cal_array[14];
        cal_array_num[15] = cal_array[15];
        cal_array_num[16] = cal_array[16];

    }

    public void fetch_VIN(String vin) {

        String Default_vin = "MBX0000XXXX000000";
        if (vin.equals("")) {
            System.out.println("Getting Default VIN");
            VIN_array[0] = (int) Default_vin.charAt(0); //a
            VIN_array[1] = (int) Default_vin.charAt(1); //a
            VIN_array[2] = (int) Default_vin.charAt(2); //a
            VIN_array[3] = (int) Default_vin.charAt(3);
            VIN_array[4] = (int) Default_vin.charAt(4);
            VIN_array[5] = (int) Default_vin.charAt(5);
            VIN_array[6] = (int) Default_vin.charAt(6);
            VIN_array[7] = (int) Default_vin.charAt(7);//a
            VIN_array[8] = (int) Default_vin.charAt(8);//a
            VIN_array[9] = (int) Default_vin.charAt(9);//a
            VIN_array[10] = (int) Default_vin.charAt(10);//a
            VIN_array[11] = (int) Default_vin.charAt(11);
            VIN_array[12] = (int) Default_vin.charAt(12);
            VIN_array[13] = (int) Default_vin.charAt(13);
            VIN_array[14] = (int) Default_vin.charAt(14);
            VIN_array[15] = (int) Default_vin.charAt(15);
            VIN_array[16] = (int) Default_vin.charAt(16);
        } else {
            System.out.println("Getting NON Default VIN");
            VIN_array[0] = (int) vin.charAt(0); //a
            VIN_array[1] = (int) vin.charAt(1); //a
            VIN_array[2] = (int) vin.charAt(2); //a
            VIN_array[3] = (int) vin.charAt(3);
            VIN_array[4] = (int) vin.charAt(4);
            VIN_array[5] = (int) vin.charAt(5);
            VIN_array[6] = (int) vin.charAt(6);
            VIN_array[7] = (int) vin.charAt(7);//a
            VIN_array[8] = (int) vin.charAt(8);//a
            VIN_array[9] = (int) vin.charAt(9);//a
            VIN_array[10] = (int) vin.charAt(10);//a
            VIN_array[11] = (int) vin.charAt(11);
            VIN_array[12] = (int) vin.charAt(12);
            VIN_array[13] = (int) vin.charAt(13);
            VIN_array[14] = (int) vin.charAt(14);
            VIN_array[15] = (int) vin.charAt(15);
            VIN_array[16] = (int) vin.charAt(16);
        }

    }

    //Method to fetch Engine Load data
    private void fetch_Engine_Load(JTable table_DOI) {
        DefaultTableModel DOI_model = (DefaultTableModel) table_DOI.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            Engine_Load_array[counter++] = (int) DOI_model.getValueAt(i, 0);
        }
        counter = 1;
        for (int i = 1; i < 17; i++) {
            Engine_RPM_array[counter++] = ((int) DOI_model.getValueAt(0, i)) >> 8;
            Engine_RPM_array[counter++] = (int) DOI_model.getValueAt(0, i);
        }
    }

    private void fetch_Engine_Params(int[] Params) {
        Engine_Speed = Params[1];
        Fuel_cut_OFF = Params[2];
        DOI_microsec = Params[3];
        Delay_for_injection = Params[4];
        EOT_C = Params[5];
        EOT_CF = Params[6];
        pumpON = Params[7];
        glowPlugON = Params[8];
        VSS_CF = Params[9];
        max_Acc = Params[10];
        EGR_POT_ADC_Max = Params[11];
        EGR_POT_ADC_Min = Params[12];
        Consumption_Error_Max = Params[13];
        Consumption_Error_Min = Params[14];
        WIReset = Params[15];
        MinConsumption = Params[16];
        Inducement = Params[17];
        Para3 = Params[18];
        Para4 = Params[19];
        Para5 = Params[20];
        Para6 = Params[21];
        Para7 = Params[22];
    }

    //Fetching EGR LUT without shifting
    private void fetch_EGR_data(JTable EGR) {
        DefaultTableModel EGR_model = (DefaultTableModel) EGR.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                EGR_array[counter++] = ((int) EGR_model.getValueAt(j, i));
            }
        }
    }

    //Feth Temperature
    private void fetch_Temperature(JTable Temperature) {
        DefaultTableModel Temp_model = (DefaultTableModel) Temperature.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            Temperature_array[counter++] = ((int) Temp_model.getValueAt(i, 0)) >> 8;
            Temperature_array[counter++] = ((int) Temp_model.getValueAt(i, 0));
        }
        counter = 1;
        for (int i = 1; i < 17; i++) {
            Temperature_ADC_array[counter++] = ((int) Temp_model.getValueAt(i, 2)) >> 8;
            Temperature_ADC_array[counter++] = ((int) Temp_model.getValueAt(i, 2));
        }
        counter = 1;
        for (int i = 1; i < 17; i++) {
            Temperature_CF_array[counter++] = ((int) Temp_model.getValueAt(i, 1));
        }
    }

//    Fetch EGT
    private void fetch_EGT_data(JTable EGT) {
        DefaultTableModel EGT_model = (DefaultTableModel) EGT.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            EGT_1_array[counter++] = ((int) EGT_model.getValueAt(i, 0)) >> 8;
            EGT_1_array[counter++] = ((int) EGT_model.getValueAt(i, 0));
        }
        counter = 1;
        for (int i = 1; i < 17; i++) {
            EGT_2_array[counter++] = ((int) EGT_model.getValueAt(i, 2)) >> 8;
            EGT_2_array[counter++] = ((int) EGT_model.getValueAt(i, 2));
        }
        counter = 1;
        for (int i = 1; i < 17; i++) {
            EGT_ADC_array[counter++] = ((int) EGT_model.getValueAt(i, 1)) >> 8;
            EGT_ADC_array[counter++] = ((int) EGT_model.getValueAt(i, 1));
        }
    }

    //fetching Delta Pressure
    private void fetch_Delta_Pressure(JTable deltaPressure) {
        DefaultTableModel Delta_Pressure_model = (DefaultTableModel) deltaPressure.getModel();
        int counter = 1;
        for (int i = 1; i < 12; i++) {
            Delta_Pressure_array[counter++] = ((int) Delta_Pressure_model.getValueAt(i, 0)) >> 8;
            Delta_Pressure_array[counter++] = ((int) Delta_Pressure_model.getValueAt(i, 0));
        }

        counter = 1;
        for (int i = 1; i < 12; i++) {
            Delta_Pressure_ADC_array[counter++] = ((int) Delta_Pressure_model.getValueAt(i, 1)) >> 8;
            Delta_Pressure_ADC_array[counter++] = ((int) Delta_Pressure_model.getValueAt(i, 1));
        }
    }

    //Fetching Pressure Voltage
    private void fetch_Pressure_Voltage(JTable Pressure) {
        DefaultTableModel pressure_model = (DefaultTableModel) Pressure.getModel();
        int counter = 1;
        for (int i = 1; i < 9; i++) {
            Pressure_ADC_array[counter++] = ((int) pressure_model.getValueAt(i, 0)) >> 8;
            Pressure_ADC_array[counter++] = ((int) pressure_model.getValueAt(i, 0));
        }
        for (int i = 1; i < 9; i++) {
            Pressure_voltage_array[i] = (int) pressure_model.getValueAt(i, 1);
        }
    }

    //Fetch D_Bounce Time
    private void fetch_DBounce_array(JTable Dbounce) {
        DefaultTableModel Dbounce_model = (DefaultTableModel) Dbounce.getModel();
        DBounce_count = Dbounce_model.getRowCount();
        int min_ADC_Counter = 0;
        int max_ADC_Counter = 0;
        for (int i = 0; i < Dbounce_model.getRowCount(); i++) {
            D_Bounce_array[i] = Integer.parseInt((String) Dbounce_model.getValueAt(i, 3));

            D_Bounce_Min_ADC[min_ADC_Counter++] = Integer.parseInt((String) Dbounce_model.getValueAt(i, 4)) >> 8;
            D_Bounce_Min_ADC[min_ADC_Counter++] = Integer.parseInt((String) Dbounce_model.getValueAt(i, 4));

            D_Bounce_Max_ADC[max_ADC_Counter++] = Integer.parseInt((String) Dbounce_model.getValueAt(i, 5)) >> 8;
            D_Bounce_Max_ADC[max_ADC_Counter++] = Integer.parseInt((String) Dbounce_model.getValueAt(i, 5));

        }

        for (int i = 0; i < 65; i++) {
            System.out.println("counter DBounce MAX: " + D_Bounce_Max_ADC[i]);
            System.out.println("counter DBounce Min: " + D_Bounce_Min_ADC[i]);
        }
    }

    //Fetch Cal ID
    //Fetch Engine Param
    public void flash_module(SerialPort portVar, JTable table_DOI, JTable table_EGR, JTable table_SOI, String Cal_ID, String VIN, int[] Engine_Params, JTable Temperature, JTable Pressure, JProgressBar progress, JLabel lblProcess, JTable table_EGT, JTable table_DeltaPressure, JTable table_Dbounce, int EGR_sum, int Engine_Param_sum, JLabel FlashCount) {
        //Cal methods to fetch data respectively
        lblProcess.setText("FETCHING DATA");
        fetch_DOI_data(table_DOI);
        fetch_EGR_data(table_EGR);
        fetch_SOI_data(table_SOI);
        fetch_CALID(Cal_ID);
        fetch_Engine_Load(table_DOI);
        fetch_VIN(VIN);
        fetch_Engine_Params(Engine_Params);
        fetch_Temperature(Temperature);
        fetch_Pressure_Voltage(Pressure);
        fetch_EGT_data(table_EGT);
        fetch_Delta_Pressure(table_DeltaPressure);
        fetch_DBounce_array(table_Dbounce);
        //Call method to Flash
        lblProcess.setText("FLASHING IN PROGRESS");
        flash_Data(portVar, progress, lblProcess, EGR_sum, Engine_Param_sum, FlashCount);
    }

    //Method to Flash data
    private void flash_Data(SerialPort port, JProgressBar progress, JLabel lblProcess, int EGR_sum, int Engine_Param_sum, JLabel FlashCount) {
        progress.setValue(50);
        try {
            if (port.isOpen()) {
                OutputStream Serial = port.getOutputStream();
                Serial.flush();

                //Sending Command to Start Flashing
                Serial.write(0xAA);     //1
                Serial.write(0x55);     //2
                Serial.write(0x01);     //3

                //Send DOI Data  : total Data: 512          //3 + 512 = 515
                for (int i = 1; i < 513; i++) {
                    Serial.write(DOI_array[i]);
                }

                //Sending Checksum for DOI
                Serial.write(0x05);    //516
                Serial.write(0x06);    //517
                Serial.write(0x07);    //518
                Serial.write(0x11);    //519

                //Sending RPM array                        //519 + 32 = 551
                for (int i = 1; i < 33; i++) {
                    Serial.write(Engine_RPM_array[i]);
                    System.out.println("Engine RPM: " + Engine_RPM_array[i]);
                }

                //Sending Engine Load Data [TPS]         //551 + 16 = 567
                for (int i = 1; i < 17; i++) {
                    Serial.write(Engine_Load_array[i]);
                }

                //Sending EGR data Values               //567 + 256 = 823
                for (int i = 1; i < 257; i++) {
                    Serial.write(EGR_array[i]);

                }

                //Sending Checksum for EGR                
                Serial.write(EGR_sum >> 8);         //824
                Serial.write(EGR_sum);           //825

                //Sending Temperature               //825 + 32 = 857
                for (int i = 1; i < 33; i++) {
                    Serial.write(Temperature_array[i]);
                }

                //Sending Temperature ADC               //857 + 32 = 889
                for (int i = 1; i < 33; i++) {
                    Serial.write(Temperature_ADC_array[i]);
                }

                //Sending Temperature CF                //889 + 16 = 905
                for (int i = 1; i < 17; i++) {
                    Serial.write(Temperature_CF_array[i]);
                }

                //Sending Pressure ADC              //905 + 16 = 921
                for (int i = 1; i < 17; i++) {
                    Serial.write(Pressure_ADC_array[i]);
                }

                //Sending Pressure Voltage              //921 + 8 = 929
                for (int i = 1; i < 9; i++) {
                    Serial.write(Pressure_voltage_array[i]);
                }

                //Sending numerical values of CAL ID : total data  : //929 + 7 = 936
                for (int i = 1; i < 17; i++) {
                    Serial.write(cal_array_num[i]);
                }

//              Exhaust temperature 
                //EGT_ADC                                      //945 + 32 = 977
                for (int i = 1; i < 33; i++) {
                    Serial.write(EGT_ADC_array[i]);
                }

                //EGT_1                                        //977 + 32 = 1009
                for (int i = 1; i < 33; i++) {
                    Serial.write(EGT_1_array[i]);
                }

                //EGT 2                                        //1009 + 32 = 1041
                for (int i = 1; i < 33; i++) {
                    Serial.write(EGT_2_array[i]);
                }

                //Delta pressure
                //Sending pressure ADC                         //1041 + 24 = 1065
                for (int i = 1; i < 25; i++) {
                    Serial.write(Delta_Pressure_ADC_array[i]);
                }

                //Sending pressure                              //1065 + 24 = 1089
                for (int i = 1; i < 25; i++) {
                    Serial.write(Delta_Pressure_array[i]);
                }

                //Engine Param
                //Sending Engine Speed @Cranking
                Serial.write(Engine_Speed >> 8);      //1090
                Serial.write(Engine_Speed);        //1091

                //Sending pump time
                Serial.write(pumpON >> 8);           //1092
                Serial.write(pumpON);              //1093

                //Sending Glow Plug Time
                Serial.write(glowPlugON >> 8);       //1094
                Serial.write(glowPlugON);          //1095

                //Sending CSS_CF
                Serial.write(VSS_CF >> 8);           //1096
                Serial.write(VSS_CF);             //1097

                //Sending EOT
                Serial.write(EOT_C >> 8);           //1098
                Serial.write(EOT_C);              //1099

                //Sending EOT_CF
                Serial.write(EOT_CF >> 8);          //1100
                Serial.write(EOT_CF);            //1101

                //Sending Injection Delay
                Serial.write(Delay_for_injection >> 8);   //1102
                Serial.write(Delay_for_injection);      //1103

                //sending DOI_microsec
                Serial.write(DOI_microsec >> 8);          //1104
                Serial.write(DOI_microsec);             //1105

                //Sending Max Acceleration
                Serial.write(max_Acc >> 8);              //1106
                Serial.write(max_Acc);                 //1107

                //Sending EGR_POT_MAX
                Serial.write(EGR_POT_ADC_Max >> 8);      //1108
                Serial.write(EGR_POT_ADC_Max);        //1109

                //Sending EGR_POT_MIN
                Serial.write(EGR_POT_ADC_Min >> 8);      //1110
                Serial.write(EGR_POT_ADC_Min);         //1111

                //Sending water_limit_MAX
                Serial.write(Consumption_Error_Max >> 8);   //1112
                Serial.write(Consumption_Error_Max);      //1113

                //Sending water_limit_MIN                 
                Serial.write(Consumption_Error_Min >> 8);    //1114
                Serial.write(Consumption_Error_Min);       //1115

                //Sending Key)reset
                Serial.write(WIReset >> 8);                  //1116
                Serial.write(WIReset);                    //1117

                //Sending Min_Consumption
                Serial.write(MinConsumption >> 8);          //1118
                Serial.write(MinConsumption);             //1119

                //Sending Inducement
                Serial.write(Inducement >> 8);              //1120
                Serial.write(Inducement);                 //1121

                //Sending Inducement
                Serial.write(Fuel_cut_OFF >> 8);              //1120
                Serial.write(Fuel_cut_OFF);

                //Sending Para3
                Serial.write(Para3 >> 8);
                Serial.write(Para3);

                //Sending Para4
                Serial.write(Para4 >> 8);
                Serial.write(Para4);

                //Sending Para5
                Serial.write(Para5 >> 8);
                Serial.write(Para5);

                //Sending Para6
                Serial.write(Para6 >> 8);
                Serial.write(Para6);

                //Sending Para7
                Serial.write(Para7 >> 8);
                Serial.write(Para7);

//                Serial.write(0x08);            //1122
//                Serial.write(0x09);            //1123
                for (int i = 0; i < DBounce_count - 1; i++) {                     //1123 + 32 = 1155
                    Serial.write(D_Bounce_array[i] / 500);
                }
                
                System.out.println("DBounce count: "+ DBounce_count);
                
                int minADC_Count = 0;
                for (int i = 0; i < DBounce_count - 1; i++) {                     //1123 + 32 = 1155
                    Serial.write(D_Bounce_Min_ADC[minADC_Count++]);
                    Serial.write(D_Bounce_Min_ADC[minADC_Count++]);
                }

                Serial.write(D_Bounce_Max_ADC[14]);
                Serial.write(D_Bounce_Max_ADC[15]);

                Serial.write(D_Bounce_Max_ADC[24]);
                Serial.write(D_Bounce_Max_ADC[25]);

                Serial.write(D_Bounce_Max_ADC[26]);
                Serial.write(D_Bounce_Max_ADC[27]);

                Serial.write(D_Bounce_Max_ADC[28]);
                Serial.write(D_Bounce_Max_ADC[29]);

                Serial.write(D_Bounce_Max_ADC[34]);
                Serial.write(D_Bounce_Max_ADC[35]);

                Serial.write(D_Bounce_Max_ADC[52]);
                Serial.write(D_Bounce_Max_ADC[53]);

                Serial.write(D_Bounce_Max_ADC[52]);
                Serial.write(D_Bounce_Max_ADC[53]);

                Serial.write(D_Bounce_Max_ADC[62]);
                Serial.write(D_Bounce_Max_ADC[63]);

//                for(int i = 0; i < (DBounce_count * 2) ; i++){                     //1123 + 32 = 1155
//                    
//                }
                //Flashing Ends Here.........
                progress.setValue(100);
                lblProcess.setText("DATA FLASHED");
                insertDB variable = new insertDB();
                variable.addFlashCount(FlashCount);
                JOptionPane.showMessageDialog(null, "Data Flashed!");
                fetchDB fetchvar = new fetchDB();
                fetchvar.fetch_FlashECU(FlashCount);
                progress.setValue(0);
                lblProcess.setText("IDLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
