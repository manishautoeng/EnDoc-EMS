package com.Calibration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manish
 */
public class ExportCalibration {

    String Filepath;

    public void exportData(JTable Calibration_Table) {
        DefaultTableModel Calibration_model = (DefaultTableModel) Calibration_Table.getModel();

        //Opening JFileChooser
        JFileChooser selectFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv file", ".csv");
        selectFile.setFileFilter(filter);
        int response = selectFile.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            //File fileName = new File(selectFile.getSelectedFile().getName());
            Filepath = selectFile.getSelectedFile().getPath() + ".csv";
        }

        //Exporting Data
        try {
            FileWriter writer = new FileWriter(Filepath);
            try (BufferedWriter bw = new BufferedWriter(writer)) {

                //Calibration table                
                bw.write("\n");
                for (int i = 0; i < Calibration_model.getColumnCount(); i++) {
                    bw.write(Calibration_model.getColumnName(i) + ",");
                }
                bw.write("\n");

                for (int i = 0; i < Calibration_model.getRowCount(); i++) {
                    for (int j = 0; j < Calibration_model.getColumnCount(); j++) {
                        bw.write(Calibration_model.getValueAt(i, j) + ",");
                    }
                    bw.write("\n");
                }

            }
            JOptionPane.showMessageDialog(null, "Data Saved Successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
}
