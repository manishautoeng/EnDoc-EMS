package com.Calibration;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class HighlightCellRenderer extends DefaultTableCellRenderer {

    private int highlightRow; // Row index to be highlighted
    private int highlightColumn; // Column index to be highlighted

    public HighlightCellRenderer(int highlightRow, int highlightColumn) {
        this.highlightRow = highlightRow;
        this.highlightColumn = highlightColumn;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check if the current cell matches the specified row and column indices for highlighting
        if (row == highlightRow && column == highlightColumn) {
            //System.out.println("Upar wali condition!");
            cellComponent.setBackground(Color.YELLOW); // Change background color to yellow (you can change it to any color you want)
        } else {
            // Reset background color for other cells       
            table.repaint();
            cellComponent.setBackground(table.getBackground());
            table.repaint();

        }

        return cellComponent;
    }
}
