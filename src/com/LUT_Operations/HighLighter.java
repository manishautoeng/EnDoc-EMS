package com.LUT_Operations;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author manis
 */
public class HighLighter {

    public void HighlightCell(JTable DOI, JTable EGR, JTable SOI, JTable temperature, JTable Pressure, JTable ExhaustTemp, JTable DeltaPressure) {
        //DOI     
        for (int i = 1; i < DOI.getColumnCount(); i++) {
            DOI.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }
        DOI.getColumnModel().getColumn(0).setCellRenderer(highlightRendererCOL);

        //EGR    
        for (int i = 1; i < EGR.getColumnCount(); i++) {
            EGR.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }
        EGR.getColumnModel().getColumn(0).setCellRenderer(highlightRendererCOL);

        //Temprature
        for (int i = 0; i < temperature.getColumnCount(); i++) {
            temperature.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }

        //Pressure
        for (int i = 0; i < Pressure.getColumnCount(); i++) {
            Pressure.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }

        //EGT
        for (int i = 0; i < ExhaustTemp.getColumnCount(); i++) {
            ExhaustTemp.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }

        //delta
        for (int i = 0; i < DeltaPressure.getColumnCount(); i++) {
            DeltaPressure.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }

        //SOI    
        for (int i = 1; i < SOI.getColumnCount(); i++) {
            SOI.getColumnModel().getColumn(i).setCellRenderer(highlightRendererROW);
        }
        SOI.getColumnModel().getColumn(0).setCellRenderer(highlightRendererCOL);

    }

    TableCellRenderer highlightRendererROW = new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            // Get the default renderer component
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            int rowIndexToHighlight = 0;
            if (row == rowIndexToHighlight) {
                component.setBackground(Color.ORANGE);
            } else {
                component.setBackground(table.getBackground());
            }

            return component;
        }
    };

    TableCellRenderer highlightRendererCOL = new DefaultTableCellRenderer() {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            // Get the default renderer component
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            int colIndexToHighlight = 0;
            if (column == colIndexToHighlight) {
                component.setBackground(Color.lightGray);
            } else {
                component.setBackground(table.getBackground());
            }

            return component;
        }
    };

}
