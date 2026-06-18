package com.Calibration;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manis
 */
public class CalulateSUM {

    //DOI SUM
    public void Calculate_LUT_SUM(JTable DOI, JLabel DOI_SUM, JTable EGR, JLabel EGR_SUM, JTable Temp, JLabel Temp_SUM, JTable Pressure, JLabel Pressure_SUM, JTable SOI, JLabel SOI_SUM, int[] Engine_Param, JLabel Engine_SUM, String Cal_ID, JLabel Cal_ID_sum, JLabel Total_SUM, JLabel LUT_SUM, JLabel sum, JTextArea LUT, JTable ExhaustTemperature, JTable Delta_Pressure, JLabel EGT_SUM, JLabel delta_Pressure_SUM) {
        DOI_SUM.setText(String.valueOf(DOI_SUM(DOI)));
        EGR_SUM.setText(String.valueOf(EGR_SUM(EGR)));
        Temp_SUM.setText(String.valueOf(Temp_SUM(Temp)));
        Pressure_SUM.setText(String.valueOf(Pressure_SUM(Pressure)));
        SOI_SUM.setText("0" + String.valueOf(SOI_SUM(SOI)));
        Engine_SUM.setText(String.valueOf(Engine_Param_SUM(Engine_Param)));
        Cal_ID_sum.setText(String.valueOf(CAL_ID_SUM(Cal_ID)));
        EGT_SUM.setText(String.valueOf(EGT_Sum(ExhaustTemperature)));
        delta_Pressure_SUM.setText(String.valueOf(DeltaPressure_Sum(Delta_Pressure)));
        Total_SUM.setText(String.valueOf(DOI_SUM(DOI) + EGR_SUM(EGR) + Temp_SUM(Temp) + Pressure_SUM(Pressure) + SOI_SUM(SOI) + Engine_Param_SUM(Engine_Param) + CAL_ID_SUM(Cal_ID) + EGT_Sum(ExhaustTemperature) + DeltaPressure_Sum(Delta_Pressure)));
        LUT_SUM.setText(String.valueOf(DOI_SUM(DOI) + EGR_SUM(EGR) + Temp_SUM(Temp) + Pressure_SUM(Pressure) + SOI_SUM(SOI) + Engine_Param_SUM(Engine_Param) + CAL_ID_SUM(Cal_ID) + EGT_Sum(ExhaustTemperature) + DeltaPressure_Sum(Delta_Pressure)));
        
        String DeafultSUM = String.valueOf(DOI_SUM(DOI) + EGR_SUM(EGR) + Temp_SUM(Temp) + Pressure_SUM(Pressure) + SOI_SUM(SOI) + Engine_Param_SUM(Engine_Param) + CAL_ID_SUM(Cal_ID) + EGT_Sum(ExhaustTemperature) + DeltaPressure_Sum(Delta_Pressure));
/*
        if (LUT.getText().equals("Sample LUT")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("PVPL Passenger")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("PVPL Cargo")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("Atul Cargo/Passenger RE")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("Atul Cargo FE")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("MLR Passenger")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("MLR Cargo")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("Baxy Passenger RE")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("Baxy Cargo FE")) {
            sum.setText(DeafultSUM);
        } else if (LUT.getText().equals("Unkown LUT")) {
            sum.setText(DeafultSUM);
        }
        
        */
          if (LUT.getText().equals("Sample LUT")) {
            sum.setText("107027");
        } else if (LUT.getText().equals("PVPL-REAR PASSENGER")) {
            sum.setText("768595");
        } else if (LUT.getText().equals("PVPL-REAR CARGO")) {
            sum.setText("724257");
        } else if (LUT.getText().equals("AAL-REAR P&C")) {
            sum.setText("767758");
        } else if (LUT.getText().equals("AAL-FRONT CARGO SHAKTI")) {
            sum.setText("727321");
        } else if (LUT.getText().equals("MLR-REAR PASSENGER")) {
            sum.setText("767649");
        } else if (LUT.getText().equals("MLR-REAR CARGO")) {
            sum.setText("724331");
        } else if (LUT.getText().equals("BAXY-REAR PASS EXPRESS")) {
            sum.setText("768142");
        } else if (LUT.getText().equals("BAXY-FRONT CARGO BINDASS")) {
            sum.setText("726706");
        } else if (LUT.getText().equals("Unkown LUT")) {
            sum.setText("00");
        } else if (LUT.getText().equals("BAXY-REAR CARGO SUPER KING")) {
            sum.setText("726029");
        }
    }

    //EGR LUT
    private int EGR_SUM(JTable EGR) {
        int EGR_sum = 0;
        DefaultTableModel EGR_model = (DefaultTableModel) EGR.getModel();
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                EGR_sum += (int) EGR_model.getValueAt(i, j);
            }
        }
        printstat("EGR SUM", EGR_sum);
        return EGR_sum;
    }

    //Engine Param 
    private int Engine_Param_SUM(int[] Engine_Param) {
        int Engine_Param_sum = 0;

        for (int index = 1; index < 19; index++) {
            Engine_Param_sum += Engine_Param[index];
        }
        printstat("Engine Param", Engine_Param_sum);
        return Engine_Param_sum;
    }

    //Cal ID    
    private int CAL_ID_SUM(String Cal_ID) {
        int CAL_ID_sum = 0;

        //Converting calId into integer values
        for (int i = 1; i < Cal_ID.length() + 1; i++) {
            CAL_ID_sum += (int) Cal_ID.charAt(i - 1);

        }
        
        return 0;
    }

    //DOI sum
    private int DOI_SUM(JTable DOI) {
        int DOI_sum = 0;
        DefaultTableModel DOI_model = (DefaultTableModel) DOI.getModel();
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                DOI_sum += (int) DOI_model.getValueAt(i, j);
            }
        }
        printstat("DOI", DOI_sum);
        return DOI_sum;
    }

    //Temp Sum    
    private int Temp_SUM(JTable Temp) {
        int Temp_sum = 0;
        DefaultTableModel Temp_model = (DefaultTableModel) Temp.getModel();
        for (int i = 1; i < 17; i++) {
            for (int j = 0; j < 3; j++) {
                Temp_sum += (int) Temp_model.getValueAt(i, j);
            }
        }
        printstat("Temperature ", Temp_sum);
        return Temp_sum;
    }

    //Pressure Sum    
    private int Pressure_SUM(JTable Pressure) {
        int Pressure_sum = 0;
        DefaultTableModel Pressure_model = (DefaultTableModel) Pressure.getModel();
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                Pressure_sum += (int) Pressure_model.getValueAt(i, j);
            }
        }
        printstat("Pressure ", Pressure_sum);
        return Pressure_sum;
    }

    //SOI sum
    private int SOI_SUM(JTable SOI) {
        int SOI_sum = 0;
        DefaultTableModel SOI_model = (DefaultTableModel) SOI.getModel();
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 14; j++) {
                SOI_sum += (int) SOI_model.getValueAt(i, j);
            }
        }
        printstat("SOI ", SOI_sum);
        return SOI_sum;
    }

    //EGT sum
    private int EGT_Sum(JTable EGT) {
        int EGT_sum = 0;
        DefaultTableModel EGT_model = (DefaultTableModel) EGT.getModel();
        for (int i = 1; i < EGT_model.getRowCount(); i++) {
            for (int j = 0; j < 3; j++) {
                EGT_sum += (int) EGT_model.getValueAt(i, j);
                System.out.println(EGT_sum);
            }
        }
        printstat("EGT ", EGT_sum);
        return EGT_sum;
    }

    //Delta Pressure sum
    private int DeltaPressure_Sum(JTable DeltaPressure) {
        int DP_sum = 0;
        DefaultTableModel DP_model = (DefaultTableModel) DeltaPressure.getModel();
        for (int i = 1; i < DP_model.getRowCount(); i++) {
            for (int j = 0; j < 2; j++) {
                DP_sum += (int) DP_model.getValueAt(i, j);
                System.out.println(DP_sum);
            }
        }
        printstat("Delta  Pressure", DP_sum);
        return DP_sum;
    }
    
    public static void printstat(String Title, int SUM){
        System.out.println(Title+ " : " + SUM);
    }

}
