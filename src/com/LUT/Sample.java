package com.LUT;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Sample {
    //LUT declaration

    public static int[][] DOI_array = new int[17][17];
    public static int[][] EGR_array = new int[17][17];
    public static int[][] SOI_array = new int[17][17];
    public static int[][] Temp_LUT = new int[17][4];
    public static int[] Load_array = new int[17];
    public static int[] RPM_array = new int[17];
    public static int[][] pressure_array = new int[9][3];
    public static int[][] EGT_Array = new int[17][4];
    public static int[][] Delta_pressure_array = new int[13][2];

    private void fill_LOAD_RPM_array() {
        //Filling Load Array
        Load_array[1] = 15;
        Load_array[2] = 20;
        Load_array[3] = 25;
        Load_array[4] = 30;
        Load_array[5] = 35;
        Load_array[6] = 40;
        Load_array[7] = 45;
        Load_array[8] = 50;
        Load_array[9] = 55;
        Load_array[10] = 60;
        Load_array[11] = 65;
        Load_array[12] = 70;
        Load_array[13] = 75;
        Load_array[14] = 80;
        Load_array[15] = 85;
        Load_array[16] = 90;

        //filling RPM array
        RPM_array[1] = 800;
        RPM_array[2] = 1300;
        RPM_array[3] = 1500;
        RPM_array[4] = 1800;
        RPM_array[5] = 2000;
        RPM_array[6] = 2200;
        RPM_array[7] = 2500;
        RPM_array[8] = 2600;
        RPM_array[9] = 2700;
        RPM_array[10] = 2800;
        RPM_array[11] = 2900;
        RPM_array[12] = 3000;
        RPM_array[13] = 3200;
        RPM_array[14] = 3500;
        RPM_array[15] = 4000;
        RPM_array[16] = 4500;

    }

    private void fill_DOI_LUT_array() {

        //row 1  DOI_array[1][iterator]
        DOI_array[1][1] = 1;
        DOI_array[1][2] = 1;
        DOI_array[1][3] = 1;
        DOI_array[1][4] = 1;
        DOI_array[1][5] = 1;
        DOI_array[1][6] = 1;
        DOI_array[1][7] = 1;
        DOI_array[1][8] = 1;
        DOI_array[1][9] = 1;
        DOI_array[1][10] = 1;
        DOI_array[1][11] = 1;
        DOI_array[1][12] = 1;
        DOI_array[1][13] = 1;
        DOI_array[1][14] = 1;
        DOI_array[1][15] = 1;
        DOI_array[1][16] = 1;

        //row 2  DOI_array[2][iterator]
        DOI_array[2][1] = 1;
        DOI_array[2][2] = 1;
        DOI_array[2][3] = 1;
        DOI_array[2][4] = 1;
        DOI_array[2][5] = 1;
        DOI_array[2][6] = 1;
        DOI_array[2][7] = 1;
        DOI_array[2][8] = 1;
        DOI_array[2][9] = 1;
        DOI_array[2][10] = 1;
        DOI_array[2][11] = 1;
        DOI_array[2][12] = 1;
        DOI_array[2][13] = 1;
        DOI_array[2][14] = 1;
        DOI_array[2][15] = 1;
        DOI_array[2][16] = 1;

        //row 3  DOI_array[3][iterator]
        DOI_array[3][1] = 1;
        DOI_array[3][2] = 1;
        DOI_array[3][3] = 1;
        DOI_array[3][4] = 1;
        DOI_array[3][5] = 1;
        DOI_array[3][6] = 1;
        DOI_array[3][7] = 1;
        DOI_array[3][8] = 1;
        DOI_array[3][9] = 1;
        DOI_array[3][10] = 1;
        DOI_array[3][11] = 1;
        DOI_array[3][12] = 1;
        DOI_array[3][13] = 1;
        DOI_array[3][14] = 1;
        DOI_array[3][15] = 1;
        DOI_array[3][16] = 1;

        //row 4  DOI_array[4][iterator]
        DOI_array[4][1] = 1;
        DOI_array[4][2] = 1;
        DOI_array[4][3] = 1;
        DOI_array[4][4] = 1;
        DOI_array[4][5] = 1;
        DOI_array[4][6] = 1;
        DOI_array[4][7] = 1;
        DOI_array[4][8] = 1;
        DOI_array[4][9] = 1;
        DOI_array[4][10] = 1;
        DOI_array[4][11] = 1;
        DOI_array[4][12] = 1;
        DOI_array[4][13] = 1;
        DOI_array[4][14] = 1;
        DOI_array[4][15] = 1;
        DOI_array[4][16] = 1;

        //row 5  DOI_array[5][iterator]
        DOI_array[5][1] = 1;
        DOI_array[5][2] = 1;
        DOI_array[5][3] = 1;
        DOI_array[5][4] = 1;
        DOI_array[5][5] = 1;
        DOI_array[5][6] = 1;
        DOI_array[5][7] = 1;
        DOI_array[5][8] = 1;
        DOI_array[5][9] = 1;
        DOI_array[5][10] = 1;
        DOI_array[5][11] = 1;
        DOI_array[5][12] = 1;
        DOI_array[5][13] = 1;
        DOI_array[5][14] = 1;
        DOI_array[5][15] = 1;
        DOI_array[5][16] = 1;

        //row 6  DOI_array[6][iterator]
        DOI_array[6][1] = 1;
        DOI_array[6][2] = 1;
        DOI_array[6][3] = 1;
        DOI_array[6][4] = 1;
        DOI_array[6][5] = 1;
        DOI_array[6][6] = 1;
        DOI_array[6][7] = 1;
        DOI_array[6][8] = 1;
        DOI_array[6][9] = 1;
        DOI_array[6][10] = 1;
        DOI_array[6][11] = 1;
        DOI_array[6][12] = 1;
        DOI_array[6][13] = 1;
        DOI_array[6][14] = 1;
        DOI_array[6][15] = 1;
        DOI_array[6][16] = 1;

        //row 7  DOI_array[7][iterator]
        DOI_array[7][1] = 1;
        DOI_array[7][2] = 1;
        DOI_array[7][3] = 1;
        DOI_array[7][4] = 1;
        DOI_array[7][5] = 1;
        DOI_array[7][6] = 1;
        DOI_array[7][7] = 1;
        DOI_array[7][8] = 1;
        DOI_array[7][9] = 1;
        DOI_array[7][10] = 1;
        DOI_array[7][11] = 1;
        DOI_array[7][12] = 1;
        DOI_array[7][13] = 1;
        DOI_array[7][14] = 1;
        DOI_array[7][15] = 1;
        DOI_array[7][16] = 1;

        //row 8  DOI_array[8][iterator]
        DOI_array[8][1] = 1;
        DOI_array[8][2] = 1;
        DOI_array[8][3] = 1;
        DOI_array[8][4] = 1;
        DOI_array[8][5] = 1;
        DOI_array[8][6] = 1;
        DOI_array[8][7] = 1;
        DOI_array[8][8] = 1;
        DOI_array[8][9] = 1;
        DOI_array[8][10] = 1;
        DOI_array[8][11] = 1;
        DOI_array[8][12] = 1;
        DOI_array[8][13] = 1;
        DOI_array[8][14] = 1;
        DOI_array[8][15] = 1;
        DOI_array[8][16] = 1;

        //row 9   DOI_array[9][iterator]
        DOI_array[9][1] = 1;
        DOI_array[9][2] = 1;
        DOI_array[9][3] = 1;
        DOI_array[9][4] = 1;
        DOI_array[9][5] = 1;
        DOI_array[9][6] = 1;
        DOI_array[9][7] = 1;
        DOI_array[9][8] = 1;
        DOI_array[9][9] = 1;
        DOI_array[9][10] = 1;
        DOI_array[9][11] = 1;
        DOI_array[9][12] = 1;
        DOI_array[9][13] = 1;
        DOI_array[9][14] = 1;
        DOI_array[9][15] = 1;
        DOI_array[9][16] = 1;

        //row 10   DOI_array[10][iterator]
        DOI_array[10][1] = 1;
        DOI_array[10][2] = 1;
        DOI_array[10][3] = 1;
        DOI_array[10][4] = 1;
        DOI_array[10][5] = 1;
        DOI_array[10][6] = 1;
        DOI_array[10][7] = 1;
        DOI_array[10][8] = 1;
        DOI_array[10][9] = 1;
        DOI_array[10][10] = 1;
        DOI_array[10][11] = 1;
        DOI_array[10][12] = 1;
        DOI_array[10][13] = 1;
        DOI_array[10][14] = 1;
        DOI_array[10][15] = 1;
        DOI_array[10][16] = 1;

        //row 11   DOI_array[11][iterator]
        DOI_array[11][1] = 1;
        DOI_array[11][2] = 1;
        DOI_array[11][3] = 1;
        DOI_array[11][4] = 1;
        DOI_array[11][5] = 1;
        DOI_array[11][6] = 1;
        DOI_array[11][7] = 1;
        DOI_array[11][8] = 1;
        DOI_array[11][9] = 1;
        DOI_array[11][10] = 1;
        DOI_array[11][11] = 1;
        DOI_array[11][12] = 1;
        DOI_array[11][13] = 1;
        DOI_array[11][14] = 1;
        DOI_array[11][15] = 1;
        DOI_array[11][16] = 1;

        //row 12   DOI_array[12][iterator]
        DOI_array[12][1] = 1;
        DOI_array[12][2] = 1;
        DOI_array[12][3] = 1;
        DOI_array[12][4] = 1;
        DOI_array[12][5] = 1;
        DOI_array[12][6] = 1;
        DOI_array[12][7] = 1;
        DOI_array[12][8] = 1;
        DOI_array[12][9] = 1;
        DOI_array[12][10] = 1;
        DOI_array[12][11] = 1;
        DOI_array[12][12] = 1;
        DOI_array[12][13] = 1;
        DOI_array[12][14] = 1;
        DOI_array[12][15] = 1;
        DOI_array[12][16] = 1;

        //row 13   DOI_array[13][iterator]
        DOI_array[13][1] = 1;
        DOI_array[13][2] = 1;
        DOI_array[13][3] = 1;
        DOI_array[13][4] = 1;
        DOI_array[13][5] = 1;
        DOI_array[13][6] = 1;
        DOI_array[13][7] = 1;
        DOI_array[13][8] = 1;
        DOI_array[13][9] = 1;
        DOI_array[13][10] = 1;
        DOI_array[13][11] = 1;
        DOI_array[13][12] = 1;
        DOI_array[13][13] = 1;
        DOI_array[13][14] = 1;
        DOI_array[13][15] = 1;
        DOI_array[13][16] = 1;

        //row 14   DOI_array[14][iterator]
        DOI_array[14][1] = 1;
        DOI_array[14][2] = 1;
        DOI_array[14][3] = 1;
        DOI_array[14][4] = 1;
        DOI_array[14][5] = 1;
        DOI_array[14][6] = 1;
        DOI_array[14][7] = 1;
        DOI_array[14][8] = 1;
        DOI_array[14][9] = 1;
        DOI_array[14][10] = 1;
        DOI_array[14][11] = 1;
        DOI_array[14][12] = 1;
        DOI_array[14][13] = 1;
        DOI_array[14][14] = 1;
        DOI_array[14][15] = 1;
        DOI_array[14][16] = 1;

        //row 15   DOI_array[15][iterator]
        DOI_array[15][1] = 1;
        DOI_array[15][2] = 1;
        DOI_array[15][3] = 1;
        DOI_array[15][4] = 1;
        DOI_array[15][5] = 1;
        DOI_array[15][6] = 1;
        DOI_array[15][7] = 1;
        DOI_array[15][8] = 1;
        DOI_array[15][9] = 1;
        DOI_array[15][10] = 1;
        DOI_array[15][11] = 1;
        DOI_array[15][12] = 1;
        DOI_array[15][13] = 1;
        DOI_array[15][14] = 1;
        DOI_array[15][15] = 1;
        DOI_array[15][16] = 1;

        //row 16   DOI_array[16][iterator]
        DOI_array[16][1] = 1;
        DOI_array[16][2] = 1;
        DOI_array[16][3] = 1;
        DOI_array[16][4] = 1;
        DOI_array[16][5] = 1;
        DOI_array[16][6] = 1;
        DOI_array[16][7] = 1;
        DOI_array[16][8] = 1;
        DOI_array[16][9] = 1;
        DOI_array[16][10] = 1;
        DOI_array[16][11] = 1;
        DOI_array[16][12] = 1;
        DOI_array[16][13] = 1;
        DOI_array[16][14] = 1;
        DOI_array[16][15] = 1;
        DOI_array[16][16] = 1;

    }

    //EGR LUT
    public void fill_EGR_LUT_array() {

        EGR_array[1][1] = 2;
        EGR_array[1][2] = 2;
        EGR_array[1][3] = 2;
        EGR_array[1][4] = 2;
        EGR_array[1][5] = 2;
        EGR_array[1][6] = 2;
        EGR_array[1][7] = 2;
        EGR_array[1][8] = 2;
        EGR_array[1][9] = 2;
        EGR_array[1][10] = 2;
        EGR_array[1][11] = 2;
        EGR_array[1][12] = 2;
        EGR_array[1][13] = 2;
        EGR_array[1][14] = 2;
        EGR_array[1][15] = 2;
        EGR_array[1][16] = 2;

        //row 2 : EGR_array[2][iterator]
        EGR_array[2][1] = 2;
        EGR_array[2][2] = 2;
        EGR_array[2][3] = 2;
        EGR_array[2][4] = 2;
        EGR_array[2][5] = 2;
        EGR_array[2][6] = 2;
        EGR_array[2][7] = 2;
        EGR_array[2][8] = 2;
        EGR_array[2][9] = 2;
        EGR_array[2][10] = 2;
        EGR_array[2][11] = 2;
        EGR_array[2][12] = 2;
        EGR_array[2][13] = 2;
        EGR_array[2][14] = 2;
        EGR_array[2][15] = 2;
        EGR_array[2][16] = 2;

        //row 3 : EGR_array[3][iterator]
        EGR_array[3][1] = 2;
        EGR_array[3][2] = 2;
        EGR_array[3][3] = 2;
        EGR_array[3][4] = 2;
        EGR_array[3][5] = 2;
        EGR_array[3][6] = 2;
        EGR_array[3][7] = 2;
        EGR_array[3][8] = 2;
        EGR_array[3][9] = 2;
        EGR_array[3][10] = 2;
        EGR_array[3][11] = 2;
        EGR_array[3][12] = 2;
        EGR_array[3][13] = 2;
        EGR_array[3][14] = 2;
        EGR_array[3][15] = 2;
        EGR_array[3][16] = 2;

        //row 4 : EGR_array[4][iterator]
        EGR_array[4][1] = 2;
        EGR_array[4][2] = 2;
        EGR_array[4][3] = 2;
        EGR_array[4][4] = 2;
        EGR_array[4][5] = 2;
        EGR_array[4][6] = 2;
        EGR_array[4][7] = 2;
        EGR_array[4][8] = 2;
        EGR_array[4][9] = 2;
        EGR_array[4][10] = 2;
        EGR_array[4][11] = 2;
        EGR_array[4][12] = 2;
        EGR_array[4][13] = 2;
        EGR_array[4][14] = 2;
        EGR_array[4][15] = 2;
        EGR_array[4][16] = 2;

        //row 5 : EGR_array[5][iterator]
        EGR_array[5][1] = 2;
        EGR_array[5][2] = 2;
        EGR_array[5][3] = 2;
        EGR_array[5][4] = 2;
        EGR_array[5][5] = 2;
        EGR_array[5][6] = 2;
        EGR_array[5][7] = 2;
        EGR_array[5][8] = 2;
        EGR_array[5][9] = 2;
        EGR_array[5][10] = 2;
        EGR_array[5][11] = 2;
        EGR_array[5][12] = 2;
        EGR_array[5][13] = 2;
        EGR_array[5][14] = 2;
        EGR_array[5][15] = 2;
        EGR_array[5][16] = 2;

        //row 6 : EGR_array[6][iterator]
        EGR_array[6][1] = 2;
        EGR_array[6][2] = 2;
        EGR_array[6][3] = 2;
        EGR_array[6][4] = 2;
        EGR_array[6][5] = 2;
        EGR_array[6][6] = 2;
        EGR_array[6][7] = 2;
        EGR_array[6][8] = 2;
        EGR_array[6][9] = 2;
        EGR_array[6][10] = 2;
        EGR_array[6][11] = 2;
        EGR_array[6][12] = 2;
        EGR_array[6][13] = 2;
        EGR_array[6][14] = 2;
        EGR_array[6][15] = 2;
        EGR_array[6][16] = 2;

        //row 7 : EGR_array[7][iterator]
        EGR_array[7][1] = 2;
        EGR_array[7][2] = 2;
        EGR_array[7][3] = 2;
        EGR_array[7][4] = 2;
        EGR_array[7][5] = 2;
        EGR_array[7][6] = 2;
        EGR_array[7][7] = 2;
        EGR_array[7][8] = 2;
        EGR_array[7][9] = 2;
        EGR_array[7][10] = 2;
        EGR_array[7][11] = 2;
        EGR_array[7][12] = 2;
        EGR_array[7][13] = 2;
        EGR_array[7][14] = 2;
        EGR_array[7][15] = 2;
        EGR_array[7][16] = 2;

        //row 8 : EGR_array[8][iterator]
        EGR_array[8][1] = 2;
        EGR_array[8][2] = 2;
        EGR_array[8][3] = 2;
        EGR_array[8][4] = 2;
        EGR_array[8][5] = 2;
        EGR_array[8][6] = 2;
        EGR_array[8][7] = 2;
        EGR_array[8][8] = 2;
        EGR_array[8][9] = 2;
        EGR_array[8][10] = 2;
        EGR_array[8][11] = 2;
        EGR_array[8][12] = 2;
        EGR_array[8][13] = 2;
        EGR_array[8][14] = 2;
        EGR_array[8][15] = 2;
        EGR_array[8][16] = 2;

        //row 9 : EGR_array[9][iterator]
        EGR_array[9][1] = 2;
        EGR_array[9][2] = 2;
        EGR_array[9][3] = 2;
        EGR_array[9][4] = 2;
        EGR_array[9][5] = 2;
        EGR_array[9][6] = 2;
        EGR_array[9][7] = 2;
        EGR_array[9][8] = 2;
        EGR_array[9][9] = 2;
        EGR_array[9][10] = 2;
        EGR_array[9][11] = 2;
        EGR_array[9][12] = 2;
        EGR_array[9][13] = 2;
        EGR_array[9][14] = 2;
        EGR_array[9][15] = 2;
        EGR_array[9][16] = 2;

        //row 10 : EGR_array[10][iterator]
        EGR_array[10][1] = 2;
        EGR_array[10][2] = 2;
        EGR_array[10][3] = 2;
        EGR_array[10][4] = 2;
        EGR_array[10][5] = 2;
        EGR_array[10][6] = 2;
        EGR_array[10][7] = 2;
        EGR_array[10][8] = 2;
        EGR_array[10][9] = 2;
        EGR_array[10][10] = 2;
        EGR_array[10][11] = 2;
        EGR_array[10][12] = 2;
        EGR_array[10][13] = 2;
        EGR_array[10][14] = 2;
        EGR_array[10][15] = 2;
        EGR_array[10][16] = 2;

        //row 11 : EGR_array[11][iterator]
        EGR_array[11][1] = 2;
        EGR_array[11][2] = 2;
        EGR_array[11][3] = 2;
        EGR_array[11][4] = 2;
        EGR_array[11][5] = 2;
        EGR_array[11][6] = 2;
        EGR_array[11][7] = 2;
        EGR_array[11][8] = 2;
        EGR_array[11][9] = 2;
        EGR_array[11][10] = 2;
        EGR_array[11][11] = 2;
        EGR_array[11][12] = 2;
        EGR_array[11][13] = 2;
        EGR_array[11][14] = 2;
        EGR_array[11][15] = 2;
        EGR_array[11][16] = 2;

        //row 12 : EGR_array[12][iterator]
        EGR_array[12][1] = 2;
        EGR_array[12][2] = 2;
        EGR_array[12][3] = 2;
        EGR_array[12][4] = 2;
        EGR_array[12][5] = 2;
        EGR_array[12][6] = 2;
        EGR_array[12][7] = 2;
        EGR_array[12][8] = 2;
        EGR_array[12][9] = 2;
        EGR_array[12][10] = 2;
        EGR_array[12][11] = 2;
        EGR_array[12][12] = 2;
        EGR_array[12][13] = 2;
        EGR_array[12][14] = 2;
        EGR_array[12][15] = 2;
        EGR_array[12][16] = 2;

        //row 13 : EGR_array[13][iterator]
        EGR_array[13][1] = 2;
        EGR_array[13][2] = 2;
        EGR_array[13][3] = 2;
        EGR_array[13][4] = 2;
        EGR_array[13][5] = 2;
        EGR_array[13][6] = 2;
        EGR_array[13][7] = 2;
        EGR_array[13][8] = 2;
        EGR_array[13][9] = 2;
        EGR_array[13][10] = 2;
        EGR_array[13][11] = 2;
        EGR_array[13][12] = 2;
        EGR_array[13][13] = 2;
        EGR_array[13][14] = 2;
        EGR_array[13][15] = 2;
        EGR_array[13][16] = 2;

        //row 14 : EGR_array[14][iterator]
        EGR_array[14][1] = 2;
        EGR_array[14][2] = 2;
        EGR_array[14][3] = 2;
        EGR_array[14][4] = 2;
        EGR_array[14][5] = 2;
        EGR_array[14][6] = 2;
        EGR_array[14][7] = 2;
        EGR_array[14][8] = 2;
        EGR_array[14][9] = 2;
        EGR_array[14][10] = 2;
        EGR_array[14][11] = 2;
        EGR_array[14][12] = 2;
        EGR_array[14][13] = 2;
        EGR_array[14][14] = 2;
        EGR_array[14][15] = 2;
        EGR_array[14][16] = 2;

        //row 15 : EGR_array[15][iterator]
        EGR_array[15][1] = 2;
        EGR_array[15][2] = 2;
        EGR_array[15][3] = 2;
        EGR_array[15][4] = 2;
        EGR_array[15][5] = 2;
        EGR_array[15][6] = 2;
        EGR_array[15][7] = 2;
        EGR_array[15][8] = 2;
        EGR_array[15][9] = 2;
        EGR_array[15][10] = 2;
        EGR_array[15][11] = 2;
        EGR_array[15][12] = 2;
        EGR_array[15][13] = 2;
        EGR_array[15][14] = 2;
        EGR_array[15][15] = 2;
        EGR_array[15][16] = 2;

        //row 16 : EGR_array[16][iterator]
        EGR_array[16][1] = 2;
        EGR_array[16][2] = 2;
        EGR_array[16][3] = 2;
        EGR_array[16][4] = 2;
        EGR_array[16][5] = 2;
        EGR_array[16][6] = 2;
        EGR_array[16][7] = 2;
        EGR_array[16][8] = 2;
        EGR_array[16][9] = 2;
        EGR_array[16][10] = 2;
        EGR_array[16][11] = 2;
        EGR_array[16][12] = 2;
        EGR_array[16][13] = 2;
        EGR_array[16][14] = 2;
        EGR_array[16][15] = 2;
        EGR_array[16][16] = 2;
    }

    //SOI LUT
    public void fill_SOI_LUT_array() {

        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 14; j++) {
                SOI_array[i][j] = 0;
            }
        }
    }

    //Temp LUT
    public void fill_Temp_LUT_array() {
        //row 1: Temp_LUT[1][iterator]
        Temp_LUT[1][0] = -30;
        Temp_LUT[2][0] = -10;
        Temp_LUT[3][0] = 0;
        Temp_LUT[4][0] = 10;
        Temp_LUT[5][0] = 20;
        Temp_LUT[6][0] = 30;
        Temp_LUT[7][0] = 40;
        Temp_LUT[8][0] = 50;
        Temp_LUT[9][0] = 60;
        Temp_LUT[10][0] = 70;
        Temp_LUT[11][0] = 80;
        Temp_LUT[12][0] = 90;
        Temp_LUT[13][0] = 100;
        Temp_LUT[14][0] = 110;
        Temp_LUT[15][0] = 140;
        Temp_LUT[16][0] = 150;

        //row 2: Temp_LUT[2][iterator]
        Temp_LUT[1][1] = 0;
        Temp_LUT[2][1] = 0;
        Temp_LUT[3][1] = 0;
        Temp_LUT[4][1] = 0;
        Temp_LUT[5][1] = 2;
        Temp_LUT[6][1] = 16;
        Temp_LUT[7][1] = 22;
        Temp_LUT[8][1] = 26;
        Temp_LUT[9][1] = 27;
        Temp_LUT[10][1] = 12;
        Temp_LUT[11][1] = 15;
        Temp_LUT[12][1] = 30;
        Temp_LUT[13][1] = 30;
        Temp_LUT[14][1] = 30;
        Temp_LUT[15][1] = 30;
        Temp_LUT[16][1] = 30;

        //row 3: Temp_LUT[3][iterator]
        Temp_LUT[1][2] = 3936;
        Temp_LUT[2][2] = 3600;
        Temp_LUT[3][2] = 3484;
        Temp_LUT[4][2] = 3220;
        Temp_LUT[5][2] = 2900;
        Temp_LUT[6][2] = 2552;
        Temp_LUT[7][2] = 2188;
        Temp_LUT[8][2] = 1832;
        Temp_LUT[9][2] = 1508;
        Temp_LUT[10][2] = 1208;
        Temp_LUT[11][2] = 964;
        Temp_LUT[12][2] = 792;
        Temp_LUT[13][2] = 620;
        Temp_LUT[14][2] = 500;
        Temp_LUT[15][2] = 300;
        Temp_LUT[16][2] = 250;

    }

    //Pressure LUT    
    public void fill_pressure_LUT_array() {

        //row 1: pressure_array[1][iterator];
        pressure_array[1][0] = 5;
        pressure_array[2][0] = 5;
        pressure_array[3][0] = 5;
        pressure_array[4][0] = 5;
        pressure_array[5][0] = 5;
        pressure_array[6][0] = 5;
        pressure_array[7][0] = 5;
        pressure_array[8][0] = 5;

        //row 2: pressure_array[2][iterator];
        pressure_array[1][1] = 5;
        pressure_array[2][1] = 5;
        pressure_array[3][1] = 5;
        pressure_array[4][1] = 5;
        pressure_array[5][1] = 5;
        pressure_array[6][1] = 5;
        pressure_array[7][1] = 5;
        pressure_array[8][1] = 5;

    }

    //EGT LUT
    public void fill_EGT_array() {

        //EGT 1 Values
        EGT_Array[1][0] = -40;
        EGT_Array[2][0] = 0;
        EGT_Array[3][0] = 25;
        EGT_Array[4][0] = 50;
        EGT_Array[5][0] = 100;
        EGT_Array[6][0] = 200;
        EGT_Array[7][0] = 300;
        EGT_Array[8][0] = 400;
        EGT_Array[9][0] = 500;
        EGT_Array[10][0] = 600;
        EGT_Array[11][0] = 700;
        EGT_Array[12][0] = 800;
        EGT_Array[13][0] = 850;
        EGT_Array[14][0] = 900;
        EGT_Array[15][0] = 900;
        EGT_Array[16][0] = 900;

        //EGT ADC
        EGT_Array[1][1] = 595;
        EGT_Array[2][1] = 685;
        EGT_Array[3][1] = 738;
        EGT_Array[4][1] = 789;
        EGT_Array[5][1] = 887;
        EGT_Array[6][1] = 1060;
        EGT_Array[7][1] = 1209;
        EGT_Array[8][1] = 1344;
        EGT_Array[9][1] = 1461;
        EGT_Array[10][1] = 1564;
        EGT_Array[11][1] = 1657;
        EGT_Array[12][1] = 1739;
        EGT_Array[13][1] = 1778;
        EGT_Array[14][1] = 1814;
        EGT_Array[15][1] = 1814;
        EGT_Array[16][1] = 1814;

        //EGT 1 Values
        EGT_Array[1][2] = -40;
        EGT_Array[2][2] = 0;
        EGT_Array[3][2] = 25;
        EGT_Array[4][2] = 50;
        EGT_Array[5][2] = 100;
        EGT_Array[6][2] = 200;
        EGT_Array[7][2] = 300;
        EGT_Array[8][2] = 400;
        EGT_Array[9][2] = 500;
        EGT_Array[10][2] = 600;
        EGT_Array[11][2] = 700;
        EGT_Array[12][2] = 800;
        EGT_Array[13][2] = 850;
        EGT_Array[14][2] = 900;
        EGT_Array[15][2] = 900;
        EGT_Array[16][2] = 900;

    }

    //Delta Pressure LUT
    public void fill_Delta_Pressure_array() {

        //Pressure
        Delta_pressure_array[1][0] = 0;
        Delta_pressure_array[2][0] = 10;
        Delta_pressure_array[3][0] = 20;
        Delta_pressure_array[4][0] = 30;
        Delta_pressure_array[5][0] = 40;
        Delta_pressure_array[6][0] = 50;
        Delta_pressure_array[7][0] = 60;
        Delta_pressure_array[8][0] = 70;
        Delta_pressure_array[9][0] = 80;
        Delta_pressure_array[10][0] = 90;
        Delta_pressure_array[11][0] = 100;

        //Delta Pressure ADC
        Delta_pressure_array[1][1] = 0;
        Delta_pressure_array[2][1] = 410;
        Delta_pressure_array[3][1] = 819;
        Delta_pressure_array[4][1] = 1229;
        Delta_pressure_array[5][1] = 1638;
        Delta_pressure_array[6][1] = 2048;
        Delta_pressure_array[7][1] = 2457;
        Delta_pressure_array[8][1] = 2867;
        Delta_pressure_array[9][1] = 3276;
        Delta_pressure_array[10][1] = 3686;
        Delta_pressure_array[11][1] = 4095;

    }

    public void Engine_Params(
            JTextField txtEngineSpeed,
            JTextField txtFuelCutOFF,
            JTextField txtDOI,
            JTextField txtDOI_Operation,
            JTextField txtEOT_C,
            JTextField txtEOT_CF,
            JTextField txtPump_ON,
            JTextField txtGlowPlug_ON,
            JTextField txtVSS_CF,
            JTextField txtMax_acc,
            JTextField txtEGR_ADC_Max,
            JTextField txtEGR_ADC_Min,
            JTextField txtConsumption_error_Max,
            JTextField txtConsumption_error_Min,
            JTextField txtWI_reset,
            JTextField txtMin_Consumption,
            JTextField txtInducement,
            JTextField txtParam3,
            JTextField txtParam4,
            JTextField txtParam5,
            JTextField txtParam6,
            JTextField txtParam7
    ) {

        txtEngineSpeed.setText("30");
        txtFuelCutOFF.setText("5500");
        txtDOI.setText("5000");
        txtDOI_Operation.setText("20");
        txtEOT_C.setText("35");
        txtEOT_CF.setText("30");
        txtPump_ON.setText(("1"));
        txtGlowPlug_ON.setText("15");
        txtVSS_CF.setText("7");
        txtMax_acc.setText("12");
        txtEGR_ADC_Max.setText("2700");
        txtEGR_ADC_Min.setText("950");
        txtConsumption_error_Max.setText("18");
        txtConsumption_error_Min.setText("2");
        txtWI_reset.setText("5");
        txtMin_Consumption.setText("40");
        txtInducement.setText("1");
        txtParam3.setText("25");
        txtParam4.setText("0");
        txtParam5.setText("0");
        txtParam6.setText("0");
        txtParam7.setText("0");

    }

    private void call_filling_array() {
        fill_LOAD_RPM_array();
        fill_DOI_LUT_array();
        fill_EGR_LUT_array();
        fill_SOI_LUT_array();
        fill_Temp_LUT_array();
        fill_pressure_LUT_array();
        fill_EGT_array();
        fill_Delta_Pressure_array();
    }

    public void fillTables(JTable table_DOI, JTable table_EGR, JTable table_SOI, JTable table_Temperature, JTable table_Pressure, JTextField calID, JTable table_Exhaust, JTable table_Delta_Pressure) {
        call_filling_array();
        DefaultTableModel DOI_model = (DefaultTableModel) table_DOI.getModel();
        DefaultTableModel SOI_model = (DefaultTableModel) table_SOI.getModel();
        DefaultTableModel EGR_model = (DefaultTableModel) table_EGR.getModel();
        DefaultTableModel Temperature_model = (DefaultTableModel) table_Temperature.getModel();
        DefaultTableModel Pressure_model = (DefaultTableModel) table_Pressure.getModel();
        DefaultTableModel Exhaust_model = (DefaultTableModel) table_Exhaust.getModel();
        DefaultTableModel Delta_Pressure_model = (DefaultTableModel) table_Delta_Pressure.getModel();

        DOI_model.setValueAt("Load/RPM", 0, 0);
        SOI_model.setValueAt("Load/RPM", 0, 0);
        EGR_model.setValueAt("Load/RPM", 0, 0);
        Temperature_model.setValueAt("Temp[°C]", 0, 0);
        Temperature_model.setValueAt("Temp CF", 0, 1);
        Temperature_model.setValueAt("Temp ADC", 0, 2);
        Pressure_model.setValueAt("Pr. ADC", 0, 0);
        Pressure_model.setValueAt("Pr. Volt.", 0, 1);
        Exhaust_model.setValueAt("EGT 1[°C]", 0, 0);
        Exhaust_model.setValueAt("EGT ADC", 0, 1);
        Exhaust_model.setValueAt("EGT 2[°C]", 0, 2);
        Delta_Pressure_model.setValueAt("Delta Pr Volt. %", 0, 0);
        Delta_Pressure_model.setValueAt("Delta Pr. ADC", 0, 1);

        calID.setText("MBX0000XXXX000000");

        for (int i = 1; i < 17; i++) {
            DOI_model.setValueAt(Load_array[i], i, 0);
            DOI_model.setValueAt(RPM_array[i], 0, i);
            EGR_model.setValueAt(Load_array[i], i, 0);
            EGR_model.setValueAt(RPM_array[i], 0, i);
            SOI_model.setValueAt(Load_array[i], i, 0);
        }

        for (int i = 1; i < 14; i++) {
            SOI_model.setValueAt(RPM_array[i], 0, i);
        }

        //DOI
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                DOI_model.setValueAt(DOI_array[i][j], i, j);
            }
        }

        //EGR
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 17; j++) {
                EGR_model.setValueAt(EGR_array[i][j], i, j);
            }
        }

        //EGR
        for (int i = 1; i < 17; i++) {
            for (int j = 1; j < 14; j++) {
                SOI_model.setValueAt(SOI_array[i][j], i, j);
            }
        }

        //Pressure
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 2; j++) {
                Pressure_model.setValueAt(pressure_array[i][j], i, j);
            }
        }

        //Temperature
        for (int i = 1; i < 17; i++) {
            for (int j = 0; j < 3; j++) {
                Temperature_model.setValueAt(Temp_LUT[i][j], i, j);
            }
        }

        //Exhaust Temperature
        for (int i = 1; i < 17; i++) {
            for (int j = 0; j < 3; j++) {
                Exhaust_model.setValueAt(EGT_Array[i][j], i, j);
            }
        }

        //Delta Pressure
        for (int i = 1; i < 12; i++) {
            for (int j = 0; j < 2; j++) {
                Delta_Pressure_model.setValueAt(Delta_pressure_array[i][j], i, j);
            }
        }

    }

}
