package com.LUT_Operations;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manis
 */
public class openLUT {

    public String[] recr = new String[1027];
    public String[] recrIM = new String[1027];

    public void openSavedLUT(JTable table_DOI, JTable table_EGR, JTable table_Temperature, JTable table_Pressure, JTable table_SOI, JTextField CAL_ID, JTextField Engine_Speed, JTextField FuelCut_OFF, JTextField Cranking_DOI, JTextField DOI_OP, JTextField Sec_EOT, JTextField EOT_CF, JTextField Pump_ON, JTextField GlowPlug, JTextField VSS_CF, JTextField Max_Accleration, JTextField EGR_ADC_Max, JTextField EGR_ADC_Min, JTextField ConsumptionError_Max, JTextField ConsumptionError_Min, JTextField WI_Reset, JTextField Min_Consumption, JTextField Inducement, JTextField DeltaPressure_Temp, JTextField EGR_CF, JTextField DOI_CF, JTextField Param_6, JTextField Param_7, JTable ExhaustTemperature, JTable DeltaPressure, JTextArea lutName) {
        //Making model for Tables
        DefaultTableModel DOI_model = (DefaultTableModel) table_DOI.getModel();
        DefaultTableModel EGR_model = (DefaultTableModel) table_EGR.getModel();
        DefaultTableModel Temp_model = (DefaultTableModel) table_Temperature.getModel();
        DefaultTableModel Pressure_model = (DefaultTableModel) table_Pressure.getModel();
        DefaultTableModel SOI_model = (DefaultTableModel) table_SOI.getModel();
        DefaultTableModel Exhaust_Temp_model = (DefaultTableModel) ExhaustTemperature.getModel();
        DefaultTableModel Delta_Pressure_model = (DefaultTableModel) DeltaPressure.getModel();

        File filesrc = null;
        JFileChooser selectFile = new JFileChooser();
        String filename = null;
        int response = selectFile.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File fileName = new File(selectFile.getSelectedFile().getName());
            filesrc = selectFile.getSelectedFile();
            filename = selectFile.getName(filesrc);

            try {
                FileReader reader = new FileReader(filesrc);
                CSVReader csvReader = new CSVReaderBuilder(reader).build();

                List<String[]> records = csvReader.readAll();
                String[][] data = new String[records.size()][];
                data = records.toArray(data);
                int i = 0;
                for (String[] record : data) {
                    for (String cell : record) {
                        System.out.print(cell + "\t");
                        if (cell.equals("")) {
                            continue;
                        } else {
                            if (cell.equals("null")) {
                                recrIM[i] = "";
                            } else {
                                recrIM[i] = cell;
                            }
                            recr[i] = recrIM[i];
                            i++;
                        }

                    }
                    // strArray.toArray(record);
                    System.out.println();

                }

                for (int j = 0; j < 1027; j++) {
                    System.out.println("recr: " + recr[j] + " Counter: \t" + j);
                }

                if (recr[1026].equals("Generated with EnDoc LPI")) {

                    //Filling DOI Table
                    int counter = 1;
                    for (int x = 0; x < DOI_model.getRowCount(); x++) {
                        for (int y = 0; y < DOI_model.getColumnCount(); y++) {
                            if (x == 0 && y == 0) {
                                DOI_model.setValueAt(recr[counter++], x, y);
                            } else {
                                DOI_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                            }
                        }
                    }

                    //Filling EGR Table
                    counter = 291;
                    for (int x = 0; x < EGR_model.getRowCount(); x++) {
                        for (int y = 0; y < EGR_model.getColumnCount(); y++) {
                            if (x == 0 && y == 0) {
                                EGR_model.setValueAt(recr[counter++], x, y);
                            } else {
                                EGR_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                            }
                        }
                    }

                    //Filling Temperature Table
                    counter = 584;
                    Temp_model.setValueAt("Temp.", 0, 0);
                    Temp_model.setValueAt("Temp CF", 0, 1);
                    Temp_model.setValueAt("Temp ADC", 0, 2);
                    for (int x = 1; x < Temp_model.getRowCount(); x++) {
                        for (int y = 0; y < Temp_model.getColumnCount(); y++) {
                            Temp_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                        }
                    }

                    //Filling Pressure Table
                    counter = 635;
                    Pressure_model.setValueAt("Pr. ADC", 0, 0);
                    Pressure_model.setValueAt("Pr. Volt.", 0, 1);
                    for (int x = 1; x < Pressure_model.getRowCount(); x++) {
                        for (int y = 0; y < Pressure_model.getColumnCount(); y++) {
                            Pressure_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                        }
                    }

                    //Filling SOI Table
                    counter = 652;
                    for (int x = 0; x < SOI_model.getRowCount(); x++) {
                        for (int y = 0; y < SOI_model.getColumnCount(); y++) {
                            if (x == 0 && y == 0) {
                                SOI_model.setValueAt(recr[counter++], x, y);
                            } else {
                                SOI_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                            }
                        }
                    }

                    //Filling Exhaust Temperature Table
                    counter = 928;
                    Exhaust_Temp_model.setValueAt("EGT 1", 0, 0);
                    Exhaust_Temp_model.setValueAt("EGT ADC", 0, 1);
                    Exhaust_Temp_model.setValueAt("EGT 2", 0, 2);
                    for (int x = 1; x < Exhaust_Temp_model.getRowCount(); x++) {
                        for (int y = 0; y < Exhaust_Temp_model.getColumnCount(); y++) {
                            if (x == 0 && y == 0) {
                                Exhaust_Temp_model.setValueAt(recr[counter++], x, y);
                            } else {
                                Exhaust_Temp_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                            }
                        }
                    }

                    //Filling Delta Pressure table
                    counter = 979;
                    Delta_Pressure_model.setValueAt("Delta Pressure", 0, 0);
                    Delta_Pressure_model.setValueAt("Delta Pr. ADC", 0, 1);
                    for (int x = 1; x < Delta_Pressure_model.getRowCount(); x++) {
                        for (int y = 0; y < Delta_Pressure_model.getColumnCount(); y++) {
                            if (x == 0 && y == 0) {
                                Delta_Pressure_model.setValueAt(recr[counter++], x, y);
                            } else {
                                Delta_Pressure_model.setValueAt(Integer.parseInt(recr[counter++]), x, y);
                            }
                        }
                    }

                    //Filling Engine Parameters
                    Engine_Speed.setText(recr[1002]);
                    FuelCut_OFF.setText(recr[1003]);
                    Cranking_DOI.setText(recr[1004]);
                    DOI_OP.setText(recr[1005]);
                    Sec_EOT.setText(recr[1006]);
                    EOT_CF.setText(recr[1007]);
                    Pump_ON.setText(recr[1008]);
                    GlowPlug.setText(recr[1009]);
                    VSS_CF.setText(recr[1010]);
                    Max_Accleration.setText(recr[1011]);
                    EGR_ADC_Max.setText(recr[1012]);
                    EGR_ADC_Min.setText(recr[1013]);
                    ConsumptionError_Max.setText(recr[1014]);
                    ConsumptionError_Min.setText(recr[1015]);
                    WI_Reset.setText(recr[1016]);
                    Min_Consumption.setText(recr[1017]);
                    Inducement.setText(recr[1018]);
                    DeltaPressure_Temp.setText(recr[1019]);
                    EGR_CF.setText(recr[1020]);
                    DOI_CF.setText(recr[1021]);
                    Param_6.setText(recr[1022]);
                    Param_7.setText(recr[1023]);

                    //Filling Cal_ID
                    CAL_ID.setText(recr[1025]);
                    lutName.setText(filename);
                    JOptionPane.showMessageDialog(null, "Selected File was opened Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Selected File is Invalid or Not Supported");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Error Encoountered!");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation was Aborted!");
        }
    }
}
