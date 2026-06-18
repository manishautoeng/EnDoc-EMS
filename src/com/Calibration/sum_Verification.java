package com.Calibration;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author manis
 */
public class sum_Verification {

    public void setDefaultSUM(JTextArea LUT, JLabel sum) {
        

        
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

    }

