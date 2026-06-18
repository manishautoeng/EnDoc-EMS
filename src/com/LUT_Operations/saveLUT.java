package com.LUT_Operations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manish Pandey
 */
public class saveLUT {

    String filepath;

    public void save_LUT(JTable DOI, JTable EGR, JTable Temperature, JTable Pressure, JTable SOI, int[] Engine_Params, String Cal_ID, JTable ExhaustTable, JTable DeltaPressure) {
        DefaultTableModel DOI_model = (DefaultTableModel) DOI.getModel();
        DefaultTableModel EGR_model = (DefaultTableModel) EGR.getModel();
        DefaultTableModel Temperature_model = (DefaultTableModel) Temperature.getModel();
        DefaultTableModel Pressure_model = (DefaultTableModel) Pressure.getModel();
        DefaultTableModel SOI_model = (DefaultTableModel) SOI.getModel();
        DefaultTableModel ExhaustTable_model = (DefaultTableModel) ExhaustTable.getModel();
        DefaultTableModel DeltaPresure_model = (DefaultTableModel) DeltaPressure.getModel();

        //Opening JFileChooser
        JFileChooser selectFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv file", ".csv");
        selectFile.setFileFilter(filter);
        int response = selectFile.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File fileName = new File(selectFile.getSelectedFile().getName());
            filepath = selectFile.getSelectedFile().getPath() + ".csv";
        } else if (response == JFileChooser.CANCEL_OPTION) {
            filepath = "NoFile";
        }

        if (filepath.equals("NoFile")) {
            JOptionPane.showMessageDialog(null, "Operation was cancelled!");
        } else {
            //Saving Data into File
            try {
                FileWriter writer = new FileWriter(filepath);
                try (BufferedWriter bw = new BufferedWriter(writer)) {

                    //DOI table 
                    bw.write("DOI Table");
                    bw.write("\n");
                    for (int i = 0; i < DOI_model.getRowCount(); i++) {
                        for (int j = 0; j < DOI_model.getColumnCount(); j++) {
                            bw.write(DOI_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //EGR table 
                    bw.write("EGR Table");
                    bw.write("\n");
                    for (int i = 0; i < EGR_model.getRowCount(); i++) {
                        for (int j = 0; j < EGR_model.getColumnCount(); j++) {
                            bw.write(EGR_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //Temperature Table               
                    bw.write("Temperature Table");
                    bw.write("\n");
                    for (int i = 0; i < Temperature_model.getRowCount(); i++) {
                        for (int j = 0; j < Temperature_model.getColumnCount(); j++) {
                            bw.write(Temperature_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //Pressure Table               
                    bw.write("Pressure Table");
                    bw.write("\n");
                    for (int i = 0; i < Pressure_model.getRowCount(); i++) {
                        for (int j = 0; j < Pressure_model.getColumnCount(); j++) {
                            bw.write(Pressure_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //SOI table 
                    bw.write("SOI Table");
                    bw.write("\n");
                    for (int i = 0; i < SOI_model.getRowCount(); i++) {
                        for (int j = 0; j < SOI_model.getColumnCount(); j++) {
                            bw.write(SOI_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //Engine Load
                    bw.write("Engine Load");
                    bw.write("\n");
                    for (int i = 1; i < 17; i++) {
                        bw.write(DOI_model.getValueAt(i, 0) + ",");
                    }
                    bw.write("\n");

                    bw.write("\n");

                    //Engine RPM
                    bw.write("Engine RPM");
                    bw.write("\n");
                    for (int i = 1; i < 17; i++) {
                        bw.write(DOI_model.getValueAt(0, i) + ",");
                    }
                    bw.write("\n");

                    bw.write("\n");

                    //Exhaust Temprature Table
                    bw.write("Exhaust Gas Temp.");
                    bw.write("\n");
                    for (int i = 0; i < ExhaustTable_model.getRowCount(); i++) {
                        for (int j = 0; j < ExhaustTable_model.getColumnCount(); j++) {
                            bw.write(ExhaustTable_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //Delta Pressure Table
                    bw.write("Delta Pressure");
                    bw.write("\n");
                    for (int i = 0; i < DeltaPresure_model.getRowCount(); i++) {
                        for (int j = 0; j < DeltaPresure_model.getColumnCount(); j++) {
                            bw.write(DeltaPresure_model.getValueAt(i, j) + ",");
                        }
                        bw.write("\n");
                    }

                    bw.write("\n");

                    //Engine Parameter
                    bw.write("Engine Param");
                    bw.write("\n");
                    for (int i = 1; i < 23; i++) {
                        bw.write(Engine_Params[i] + ",");
                    }
                    bw.write("\n");

                    bw.write("\n");

                    //Cal ID
                    bw.write("Cal ID");
                    bw.write("\n");
                    bw.write(Cal_ID);

                    bw.write("\n");
                    bw.write("\n");
                    bw.write("\n");
                    bw.write("Generated with EnDoc LPI");

                    JOptionPane.showMessageDialog(null, "Data Saved Successfully");

                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No File was saved - Operation Cancelled");
            }
        }

    }

}
