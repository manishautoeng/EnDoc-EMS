package com.image;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Manish Pandey
 */
public class setImageIcon {

    public void setIcon(String lutName, JButton label) {
        try {
            Image imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("EnDocLogoDesign.png"));

            if (lutName.equals("Sample")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("EnDocLogoDesign.png"));
            } else if (lutName.equals("PVPL Passenger")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("pvplpass.jpg"));
            } else if (lutName.equals("PVPL Cargo")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("pvplcargo.png"));
            } else if (lutName.equals("Atul Passenger")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("atul_Pass.png"));
            } else if (lutName.equals("Atul Cargo")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("atulcargoauto.png"));
            } else if (lutName.equals("MLR Passenger")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("MLR_Pass.jpg"));
            } else if (lutName.equals("MLR Cargo")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("MLR_Cargo.png"));
            } else if (lutName.equals("Baxy Passenger")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("baxyPassenger.png"));
            } else if (lutName.equals("Baxy Cargo")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("baxyCargo.jpg"));
            } else if (lutName.equals("Baxy Superking")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("BAXYSUPERKING.png"));
            } else if (lutName.equals("ABV-CHHAKADA")) {
                imgSource = Toolkit.getDefaultToolkit().getImage(getClass().getResource("chhakda.jpg"));
            }

            ImageIcon icon = new ImageIcon(imgSource);
            Image img = icon.getImage();
            Image imgScale = img.getScaledInstance(label.getWidth() - 35, label.getHeight() - 20, Image.SCALE_SMOOTH);

            ImageIcon scaledIcon = new ImageIcon(imgScale);
            label.setIcon(scaledIcon);
            //label.setText(lutName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
