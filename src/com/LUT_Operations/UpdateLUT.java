package com.LUT_Operations;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UpdateLUT {

    int[] DOI_array = new int[513];
    int[] EGR_array = new int[257];
    int[] SOI_array = new int[209];
    int[] cal_array_num = new int[8];
    int[] cal_array_alpha = new int[8];
    int[] VIN_array = new int[18];
    int[] Engine_Load_array = new int[17];
    int Engine_Speed, Fuel_cut_OFF, DOI_microsec, Delay_for_injection, EOT_C, EOT_CF, pumpON, glowPlugON, VSS_CF, max_Acc, EGR_POT_ADC_Max;
    int EGR_POT_ADC_Min, Consumption_Error_Max, Consumption_Error_Min, WIReset, MinConsumption, Inducement;
    int[] Temperature_ADC_array = new int[31];
    int[] Temperature_CF_array = new int[31];
    int[] Engine_RPM_array = new int[33];
    int[] Pressure_voltage_array = new int[8];
    int[] EGT_array = new int[97];

    //Fetch All LUT
    //Fetching DOI LUT with Shifted Value
    private void fetch_DOI_data(JTable DOI) {
        DefaultTableModel DOI_model = (DefaultTableModel) DOI.getModel();

        int counter = 1;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                DOI_array[counter++] = ((int) DOI_model.getValueAt(i, j)) >> 8;
                DOI_array[counter++] = ((int) DOI_model.getValueAt(i, j));
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
        int[] cal_array = new int[15];

        //Converting calId into integer values
        for (int i = 1; i < 15; i++) {
            cal_array[i] = (int) cal_ID.charAt(i - 1);
        }

        //Storing numerical data
        cal_array_num[1] = cal_array[2];
        cal_array_num[2] = cal_array[3];
        cal_array_num[3] = cal_array[4];
        cal_array_num[4] = cal_array[11];
        cal_array_num[5] = cal_array[12];
        cal_array_num[6] = cal_array[13];
        cal_array_num[7] = cal_array[14];

        //Storing alphabetical data
        cal_array_alpha[1] = cal_array[1];
        cal_array_alpha[2] = cal_array[5];
        cal_array_alpha[3] = cal_array[6];
        cal_array_alpha[4] = cal_array[7];
        cal_array_alpha[5] = cal_array[8];
        cal_array_alpha[6] = cal_array[9];
        cal_array_alpha[7] = cal_array[10];

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
            Engine_RPM_array[counter++] = ((int) DOI_model.getValueAt(i, 0)) >> 8;
            Engine_RPM_array[counter++] = (int) DOI_model.getValueAt(i, 0);
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
    }

    //Fetching EGR LUT without shifting
    private void fetch_EGR_data(JTable EGR) {
        DefaultTableModel EGR_model = (DefaultTableModel) EGR.getModel();
        int counter = 1;
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                EGR_array[counter++] = ((int) EGR_model.getValueAt(i, j));
            }
        }
    }

    private void fetch_Temperature(JTable Temperature) {
        DefaultTableModel Temp_model = (DefaultTableModel) Temperature.getModel();
        int counter = 1;
        for (int i = 1; i < 16; i++) {
            Temperature_ADC_array[counter++] = ((int) Temp_model.getValueAt(i, 2)) >> 8;
            Temperature_ADC_array[counter++] = ((int) Temp_model.getValueAt(i, 2));
        }

        counter = 1;
        for (int i = 1; i < 16; i++) {
            Temperature_CF_array[counter++] = ((int) Temp_model.getValueAt(i, 1)) >> 8;
            Temperature_CF_array[counter++] = ((int) Temp_model.getValueAt(i, 1));
        }
    }

    //Fetching Pressure Voltage
    private void fetch_Pressure_Voltage(JTable Pressure) {
        DefaultTableModel pressure_model = (DefaultTableModel) Pressure.getModel();
        for (int i = 1; i < 8; i++) {
            Pressure_voltage_array[i] = (int) pressure_model.getValueAt(i, 1);
        }
    }

    //Fetch Cal ID
    //Fetch Engine Param
    public void update_module(JTable table_DOI, JTable table_EGR, JTable table_SOI, String Cal_ID, String VIN, int[] Engine_Params, JTable Temperature, JTable Pressure) {
        //Cal methods to fetch data respectively
        fetch_DOI_data(table_DOI);
        fetch_EGR_data(table_EGR);
        fetch_SOI_data(table_SOI);
        fetch_CALID(Cal_ID);
        fetch_Engine_Load(table_DOI);
        fetch_VIN(VIN);
        fetch_Engine_Params(Engine_Params);
        fetch_Temperature(Temperature);
        fetch_Pressure_Voltage(Pressure);
        //Call method to Flash
        update_Data();
    }

    //Method to Flash data
    private void update_Data() {
        try {

            {

                //Sending Command to Start Flashing
                System.out.println(0xAA);
                System.out.println(0x51);
                System.out.println(0x01);

                //Send DOI Data  : total Data: 512
                for (int i = 1; i < 513; i++) {
                    System.out.println(DOI_array[i]);
                }

                //Sending Checksum for DOI
                System.out.println(0x05);
                System.out.println(0x06);
                System.out.println(0x07);
                System.out.println(0x11);

                //Send SOI Data  : total Data: 208
                for (int i = 1; i < 209; i++) {
                    System.out.println(SOI_array[i]);
                }

                //Sending numerical values of CAL ID : total data  : 7
                for (int i = 1; i < 8; i++) {
                    System.out.println(cal_array_num[i]);
                }

                //Sending Checksum
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);

                //Sending alphabetical values of VIN
                System.out.println(VIN_array[11]);
                System.out.println(VIN_array[12]);
                System.out.println(VIN_array[13]);
                System.out.println(VIN_array[14]);
                System.out.println(VIN_array[15]);
                System.out.println(VIN_array[16]);

                //Sending alphabetical values of CAL ID : total data  : 7
                for (int i = 1; i < 8; i++) {
                    System.out.println(cal_array_alpha[i]);
                }

                //Sending Checksum
                System.out.println(0x00);
                System.out.println(0x00);

                //Sending Numerical Values of VIN
                //VIN index: 3,4,5,6
                System.out.println(VIN_array[3]);
                System.out.println(VIN_array[4]);
                System.out.println(VIN_array[5]);
                System.out.println(VIN_array[6]);
                //VIN Index: 0,1,2
                System.out.println(VIN_array[0]);
                System.out.println(VIN_array[1]);
                System.out.println(VIN_array[2]);
                //VIN Index: 7,8,9,10
                System.out.println(VIN_array[7]);
                System.out.println(VIN_array[8]);
                System.out.println(VIN_array[9]);
                System.out.println(VIN_array[10]);

                //Sending Checksum total Data : 9                
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);

                //Sending LUT Checksum
                System.out.println(0x80);
                System.out.println(0xAA);
                System.out.println(0x55);
                System.out.println(0x00);

                //Sending Engine Load Data
                for (int i = 1; i < 17; i++) {
                    System.out.println(Engine_Load_array[i]);
                }

                //Sending Engine Speed @Cranking
                System.out.println(Engine_Speed / 10);

                //Sending IACV
                System.out.println(0x01);
                System.out.println(0x02);
                System.out.println(0x03);
                System.out.println(0x04);
                System.out.println(0x05);

                //Sending pump time
                System.out.println(pumpON);

                //Sending Glow Plug Time
                System.out.println(glowPlugON);

                //Sending CSS_CF
                System.out.println(VSS_CF >> 8);
                System.out.println(VSS_CF);

                //Sending EOT
                System.out.println(EOT_C >> 8);
                System.out.println(EOT_C);

                //Sending EOT_CF
                System.out.println(EOT_CF);

                //Sending ECU_TImer
                System.out.println(0);

                //Sending Injection Delay
                System.out.println(Delay_for_injection >> 8);
                System.out.println(Delay_for_injection);

                //Sending CheckSum
                System.out.println(0x80);
                System.out.println(0xAA);
                System.out.println(0x55);
                System.out.println(0x19);

                //Sending EGR data Values
                for (int i = 1; i < 257; i++) {
                    System.out.println(EGR_array[i]);
                }

                //Sending Cheksum
                System.out.println(0x80);
                System.out.println(0xAA);
                System.out.println(0x55);
                System.out.println(0x21);

                //sending DOI_microsec
                System.out.println(DOI_microsec >> 8);
                System.out.println(DOI_microsec);

                //Sending Temperature ADC
                for (int i = 1; i < 31; i++) {
                    System.out.println(Temperature_ADC_array[i]);
                }

                //Sending Battery Compoensastion
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);

                //Sending Temperature CF
                for (int i = 1; i < 31; i++) {
                    System.out.println(Temperature_CF_array[i]);
                }

                //Sending AIR Temperature ADC
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);
                System.out.println(0x00);

                //Sending RPM array
                for (int i = 1; i < 33; i++) {
                    System.out.println(Engine_RPM_array[i]);
                }

                //Sending Battery Compensastion D well
                for (int i = 1; i < 69; i++) {
                    System.out.println(0x00);
                }

                //Sending Fuel Cut OFF
                System.out.println(Fuel_cut_OFF >> 8);
                System.out.println(Fuel_cut_OFF);

                //Sending Pressure Voltage
                for (int i = 1; i < 8; i++) {
                    System.out.println(Pressure_voltage_array[i]);
                }

                //Sending Max Acceleration
                System.out.println(max_Acc >> 8);
                System.out.println(max_Acc);

                //Sending Temp_Acceleration
                System.out.println(0);
                System.out.println(0);

                //Sending Flash
                System.out.println(0x01);

                //Sending EGR_POT_MAX
                System.out.println(EGR_POT_ADC_Max >> 8);
                System.out.println(EGR_POT_ADC_Min);

                //Sending water_limit_MAX
                System.out.println(Consumption_Error_Max >> 8);
                System.out.println(Consumption_Error_Max);

                //Sending water_limit_MIN                 
                System.out.println(Consumption_Error_Min >> 8);
                System.out.println(Consumption_Error_Min);

                //Sending Key)reset
                System.out.println(WIReset >> 8);
                System.out.println(WIReset);

                //Sending Min_Consumption
                System.out.println(MinConsumption >> 8);
                System.out.println(MinConsumption);

                //Sending Inducement
                System.out.println(Inducement >> 8);
                System.out.println(Inducement);

                System.out.println(0x22);
                System.out.println(0x4D);

                JOptionPane.showMessageDialog(null, "Data Updated!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
