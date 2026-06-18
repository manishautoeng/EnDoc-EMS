package com.LUT;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PVPL_Passenger {
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

        //Final
        //row 1  DOI_array[1][iterator]
        DOI_array[1][1] = 3488;
        DOI_array[1][2] = 3488;
        DOI_array[1][3] = 3488;
        DOI_array[1][4] = 2754;
        DOI_array[1][5] = 2754;
        DOI_array[1][6] = 2754;
        DOI_array[1][7] = 2754;
        DOI_array[1][8] = 2754;
        DOI_array[1][9] = 2754;
        DOI_array[1][10] = 2754;
        DOI_array[1][11] = 2754;
        DOI_array[1][12] = 2754;
        DOI_array[1][13] = 2754;
        DOI_array[1][14] = 2754;
        DOI_array[1][15] = 2754;
        DOI_array[1][16] = 2754;

        //row 2  DOI_array[2][iterator]
        DOI_array[2][1] = 3488;
        DOI_array[2][2] = 3488;
        DOI_array[2][3] = 3488;
        DOI_array[2][4] = 2792;
        DOI_array[2][5] = 2792;
        DOI_array[2][6] = 2678;
        DOI_array[2][7] = 2601;
        DOI_array[2][8] = 2601;
        DOI_array[2][9] = 2792;
        DOI_array[2][10] = 2792;
        DOI_array[2][11] = 2792;
        DOI_array[2][12] = 2792;
        DOI_array[2][13] = 2792;
        DOI_array[2][14] = 2792;
        DOI_array[2][15] = 2792;
        DOI_array[2][16] = 2792;

        //row 3  DOI_array[3][iterator]
        DOI_array[3][1] = 3488;
        DOI_array[3][2] = 3488;
        DOI_array[3][3] = 3488;
        DOI_array[3][4] = 2570;
        DOI_array[3][5] = 2570;
        DOI_array[3][6] = 2545;
        DOI_array[3][7] = 2520;
        DOI_array[3][8] = 2470;
        DOI_array[3][9] = 2436;
        DOI_array[3][10] = 2447;
        DOI_array[3][11] = 2457;
        DOI_array[3][12] = 2468;
        DOI_array[3][13] = 2479;
        DOI_array[3][14] = 2205;
        DOI_array[3][15] = 2095;
        DOI_array[3][16] = 2104;

        //row 4  DOI_array[4][iterator]
        DOI_array[4][1] = 2000;
        DOI_array[4][2] = 2570;
        DOI_array[4][3] = 2520;
        DOI_array[4][4] = 2570;
        DOI_array[4][5] = 2570;
        DOI_array[4][6] = 2545;
        DOI_array[4][7] = 2520;
        DOI_array[4][8] = 2470;
        DOI_array[4][9] = 2490;
        DOI_array[4][10] = 2500;
        DOI_array[4][11] = 2511;
        DOI_array[4][12] = 2522;
        DOI_array[4][13] = 2533;
        DOI_array[4][14] = 2253;
        DOI_array[4][15] = 2142;
        DOI_array[4][16] = 2152;

        //row 5  DOI_array[5][iterator]
        DOI_array[5][1] = 2000;
        DOI_array[5][2] = 2570;
        DOI_array[5][3] = 2570;
        DOI_array[5][4] = 2675;
        DOI_array[5][5] = 2645;
        DOI_array[5][6] = 2750;
        DOI_array[5][7] = 2820;
        DOI_array[5][8] = 2870;
        DOI_array[5][9] = 2850;
        DOI_array[5][10] = 2810;
        DOI_array[5][11] = 2822;
        DOI_array[5][12] = 2834;
        DOI_array[5][13] = 2845;
        DOI_array[5][14] = 2640;
        DOI_array[5][15] = 2166;
        DOI_array[5][16] = 2175;

        //row 6  DOI_array[6][iterator]
        DOI_array[6][1] = 2000;
        DOI_array[6][2] = 2475;
        DOI_array[6][3] = 2530;
        DOI_array[6][4] = 3020;
        DOI_array[6][5] = 3160;
        DOI_array[6][6] = 3294;
        DOI_array[6][7] = 3315;
        DOI_array[6][8] = 3346;
        DOI_array[6][9] = 3366;
        DOI_array[6][10] = 3333;
        DOI_array[6][11] = 3346;
        DOI_array[6][12] = 3358;
        DOI_array[6][13] = 3121;
        DOI_array[6][14] = 2900;
        DOI_array[6][15] = 2199;
        DOI_array[6][16] = 2209;

        //row 7  DOI_array[7][iterator]
        DOI_array[7][1] = 2000;
        DOI_array[7][2] = 2001;
        DOI_array[7][3] = 2480;
        DOI_array[7][4] = 3000;
        DOI_array[7][5] = 3181;
        DOI_array[7][6] = 3315;
        DOI_array[7][7] = 3387;
        DOI_array[7][8] = 3438;
        DOI_array[7][9] = 3418;
        DOI_array[7][10] = 3376;
        DOI_array[7][11] = 3389;
        DOI_array[7][12] = 3401;
        DOI_array[7][13] = 3162;
        DOI_array[7][14] = 2950;
        DOI_array[7][15] = 2223;
        DOI_array[7][16] = 2232;

        //row 8  DOI_array[8][iterator]
        DOI_array[8][1] = 2001;
        DOI_array[8][2] = 2013;
        DOI_array[8][3] = 2500;
        DOI_array[8][4] = 3032;
        DOI_array[8][5] = 3258;
        DOI_array[8][6] = 3392;
        DOI_array[8][7] = 3418;
        DOI_array[8][8] = 3449;
        DOI_array[8][9] = 3438;
        DOI_array[8][10] = 3449;
        DOI_array[8][11] = 3460;
        DOI_array[8][12] = 3473;
        DOI_array[8][13] = 3245;
        DOI_array[8][14] = 3000;
        DOI_array[8][15] = 2280;
        DOI_array[8][16] = 2294;

        //row 9   DOI_array[9][iterator]
        DOI_array[9][1] = 2020;
        DOI_array[9][2] = 2032;
        DOI_array[9][3] = 2450;
        DOI_array[9][4] = 3000;
        DOI_array[9][5] = 3284;
        DOI_array[9][6] = 3418;
        DOI_array[9][7] = 3459;
        DOI_array[9][8] = 3454;
        DOI_array[9][9] = 3473;
        DOI_array[9][10] = 3450;
        DOI_array[9][11] = 3468;
        DOI_array[9][12] = 3486;
        DOI_array[9][13] = 3254;
        DOI_array[9][14] = 3050;
        DOI_array[9][15] = 2365;
        DOI_array[9][16] = 2393;

        //row 10   DOI_array[10][iterator]
        DOI_array[10][1] = 2038;
        DOI_array[10][2] = 2050;
        DOI_array[10][3] = 2400;
        DOI_array[10][4] = 2866;
        DOI_array[10][5] = 3212;
        DOI_array[10][6] = 3346;
        DOI_array[10][7] = 3387;
        DOI_array[10][8] = 3425;
        DOI_array[10][9] = 3450;
        DOI_array[10][10] = 3474;
        DOI_array[10][11] = 3504;
        DOI_array[10][12] = 3540;
        DOI_array[10][13] = 3072;
        DOI_array[10][14] = 3100;
        DOI_array[10][15] = 2412;
        DOI_array[10][16] = 2441;

        //row 11   DOI_array[11][iterator]
        DOI_array[11][1] = 2056;
        DOI_array[11][2] = 2069;
        DOI_array[11][3] = 2350;
        DOI_array[11][4] = 2838;
        DOI_array[11][5] = 3152;
        DOI_array[11][6] = 3286;
        DOI_array[11][7] = 3368;
        DOI_array[11][8] = 3391;
        DOI_array[11][9] = 3391;
        DOI_array[11][10] = 3368;
        DOI_array[11][11] = 3419;
        DOI_array[11][12] = 3368;
        DOI_array[11][13] = 3116;
        DOI_array[11][14] = 2900;
        DOI_array[11][15] = 2460;
        DOI_array[11][16] = 2488;

        //row 12   DOI_array[12][iterator]
        DOI_array[12][1] = 2081;
        DOI_array[12][2] = 2093;
        DOI_array[12][3] = 2343;
        DOI_array[12][4] = 2511;
        DOI_array[12][5] = 2722;
        DOI_array[12][6] = 2800;
        DOI_array[12][7] = 2950;
        DOI_array[12][8] = 2970;
        DOI_array[12][9] = 2970;
        DOI_array[12][10] = 2900;
        DOI_array[12][11] = 3000;
        DOI_array[12][12] = 2950;
        DOI_array[12][13] = 2900;
        DOI_array[12][14] = 2850;
        DOI_array[12][15] = 2536;
        DOI_array[12][16] = 2559;

        //row 13   DOI_array[13][iterator]
        DOI_array[13][1] = 2099;
        DOI_array[13][2] = 2130;
        DOI_array[13][3] = 2365;
        DOI_array[13][4] = 2626;
        DOI_array[13][5] = 2700;
        DOI_array[13][6] = 2750;
        DOI_array[13][7] = 2900;
        DOI_array[13][8] = 2950;
        DOI_array[13][9] = 2930;
        DOI_array[13][10] = 2850;
        DOI_array[13][11] = 2950;
        DOI_array[13][12] = 2900;
        DOI_array[13][13] = 2850;
        DOI_array[13][14] = 2850;
        DOI_array[13][15] = 2536;
        DOI_array[13][16] = 2666;

        //row 14   DOI_array[14][iterator]
        DOI_array[14][1] = 2099;
        DOI_array[14][2] = 2130;
        DOI_array[14][3] = 2365;
        DOI_array[14][4] = 2626;
        DOI_array[14][5] = 2700;
        DOI_array[14][6] = 2750;
        DOI_array[14][7] = 2900;
        DOI_array[14][8] = 2950;
        DOI_array[14][9] = 2930;
        DOI_array[14][10] = 2850;
        DOI_array[14][11] = 2950;
        DOI_array[14][12] = 2900;
        DOI_array[14][13] = 2850;
        DOI_array[14][14] = 200;
        DOI_array[14][15] = 200;
        DOI_array[14][16] = 2666;

        //row 15   DOI_array[15][iterator]
        DOI_array[15][1] = 200;
        DOI_array[15][2] = 200;
        DOI_array[15][3] = 200;
        DOI_array[15][4] = 200;
        DOI_array[15][5] = 200;
        DOI_array[15][6] = 200;
        DOI_array[15][7] = 200;
        DOI_array[15][8] = 200;
        DOI_array[15][9] = 200;
        DOI_array[15][10] = 200;
        DOI_array[15][11] = 200;
        DOI_array[15][12] = 200;
        DOI_array[15][13] = 200;
        DOI_array[15][14] = 200;
        DOI_array[15][15] = 200;
        DOI_array[15][16] = 200;

        //row 16   DOI_array[16][iterator]
        DOI_array[16][1] = 200;
        DOI_array[16][2] = 200;
        DOI_array[16][3] = 200;
        DOI_array[16][4] = 200;
        DOI_array[16][5] = 200;
        DOI_array[16][6] = 200;
        DOI_array[16][7] = 200;
        DOI_array[16][8] = 200;
        DOI_array[16][9] = 200;
        DOI_array[16][10] = 200;
        DOI_array[16][11] = 200;
        DOI_array[16][12] = 200;
        DOI_array[16][13] = 200;
        DOI_array[16][14] = 200;
        DOI_array[16][15] = 200;
        DOI_array[16][16] = 200;

    }

    //EGR LUT
    public void fill_EGR_LUT_array() {
        //row 1 : EGR_array[1][iterator]
        EGR_array[1][1] = 75;
        EGR_array[1][2] = 75;
        EGR_array[1][3] = 50;
        EGR_array[1][4] = 50;
        EGR_array[1][5] = 50;
        EGR_array[1][6] = 50;
        EGR_array[1][7] = 50;
        EGR_array[1][8] = 50;
        EGR_array[1][9] = 50;
        EGR_array[1][10] = 50;
        EGR_array[1][11] = 50;
        EGR_array[1][12] = 50;
        EGR_array[1][13] = 50;
        EGR_array[1][14] = 50;
        EGR_array[1][15] = 50;
        EGR_array[1][16] = 50;

        //row 2 : EGR_array[2][iterator]
        EGR_array[2][1] = 75;
        EGR_array[2][2] = 75;
        EGR_array[2][3] = 50;
        EGR_array[2][4] = 50;
        EGR_array[2][5] = 50;
        EGR_array[2][6] = 50;
        EGR_array[2][7] = 50;
        EGR_array[2][8] = 50;
        EGR_array[2][9] = 50;
        EGR_array[2][10] = 50;
        EGR_array[2][11] = 50;
        EGR_array[2][12] = 50;
        EGR_array[2][13] = 50;
        EGR_array[2][14] = 50;
        EGR_array[2][15] = 50;
        EGR_array[2][16] = 50;

        //row 3 : EGR_array[3][iterator]
        EGR_array[3][1] = 75;
        EGR_array[3][2] = 50;
        EGR_array[3][3] = 50;
        EGR_array[3][4] = 50;
        EGR_array[3][5] = 50;
        EGR_array[3][6] = 50;
        EGR_array[3][7] = 50;
        EGR_array[3][8] = 50;
        EGR_array[3][9] = 50;
        EGR_array[3][10] = 50;
        EGR_array[3][11] = 50;
        EGR_array[3][12] = 50;
        EGR_array[3][13] = 50;
        EGR_array[3][14] = 50;
        EGR_array[3][15] = 50;
        EGR_array[3][16] = 50;

        //row 4 : EGR_array[4][iterator]
        EGR_array[4][1] = 75;
        EGR_array[4][2] = 50;
        EGR_array[4][3] = 50;
        EGR_array[4][4] = 50;
        EGR_array[4][5] = 50;
        EGR_array[4][6] = 50;
        EGR_array[4][7] = 50;
        EGR_array[4][8] = 50;
        EGR_array[4][9] = 50;
        EGR_array[4][10] = 50;
        EGR_array[4][11] = 50;
        EGR_array[4][12] = 50;
        EGR_array[4][13] = 50;
        EGR_array[4][14] = 50;
        EGR_array[4][15] = 50;
        EGR_array[4][16] = 50;

        //row 5 : EGR_array[5][iterator]
        EGR_array[5][1] = 75;
        EGR_array[5][2] = 50;
        EGR_array[5][3] = 50;
        EGR_array[5][4] = 50;
        EGR_array[5][5] = 50;
        EGR_array[5][6] = 50;
        EGR_array[5][7] = 50;
        EGR_array[5][8] = 50;
        EGR_array[5][9] = 50;
        EGR_array[5][10] = 50;
        EGR_array[5][11] = 50;
        EGR_array[5][12] = 50;
        EGR_array[5][13] = 50;
        EGR_array[5][14] = 50;
        EGR_array[5][15] = 50;
        EGR_array[5][16] = 50;

        //row 6 : EGR_array[6][iterator]
        EGR_array[6][1] = 75;
        EGR_array[6][2] = 50;
        EGR_array[6][3] = 50;
        EGR_array[6][4] = 50;
        EGR_array[6][5] = 50;
        EGR_array[6][6] = 50;
        EGR_array[6][7] = 50;
        EGR_array[6][8] = 50;
        EGR_array[6][9] = 50;
        EGR_array[6][10] = 50;
        EGR_array[6][11] = 50;
        EGR_array[6][12] = 50;
        EGR_array[6][13] = 50;
        EGR_array[6][14] = 50;
        EGR_array[6][15] = 50;
        EGR_array[6][16] = 50;

        //row 7 : EGR_array[7][iterator]
        EGR_array[7][1] = 75;
        EGR_array[7][2] = 50;
        EGR_array[7][3] = 50;
        EGR_array[7][4] = 50;
        EGR_array[7][5] = 50;
        EGR_array[7][6] = 50;
        EGR_array[7][7] = 50;
        EGR_array[7][8] = 50;
        EGR_array[7][9] = 50;
        EGR_array[7][10] = 50;
        EGR_array[7][11] = 50;
        EGR_array[7][12] = 50;
        EGR_array[7][13] = 50;
        EGR_array[7][14] = 50;
        EGR_array[7][15] = 50;
        EGR_array[7][16] = 50;

        //row 8 : EGR_array[8][iterator]
        EGR_array[8][1] = 75;
        EGR_array[8][2] = 50;
        EGR_array[8][3] = 50;
        EGR_array[8][4] = 50;
        EGR_array[8][5] = 50;
        EGR_array[8][6] = 50;
        EGR_array[8][7] = 50;
        EGR_array[8][8] = 50;
        EGR_array[8][9] = 50;
        EGR_array[8][10] = 50;
        EGR_array[8][11] = 50;
        EGR_array[8][12] = 50;
        EGR_array[8][13] = 50;
        EGR_array[8][14] = 50;
        EGR_array[8][15] = 50;
        EGR_array[8][16] = 50;

        //row 9 : EGR_array[9][iterator]
        EGR_array[9][1] = 75;
        EGR_array[9][2] = 50;
        EGR_array[9][3] = 50;
        EGR_array[9][4] = 50;
        EGR_array[9][5] = 50;
        EGR_array[9][6] = 50;
        EGR_array[9][7] = 50;
        EGR_array[9][8] = 50;
        EGR_array[9][9] = 50;
        EGR_array[9][10] = 50;
        EGR_array[9][11] = 50;
        EGR_array[9][12] = 50;
        EGR_array[9][13] = 50;
        EGR_array[9][14] = 50;
        EGR_array[9][15] = 50;
        EGR_array[9][16] = 50;

        //row 10 : EGR_array[10][iterator]
        EGR_array[10][1] = 75;
        EGR_array[10][2] = 50;
        EGR_array[10][3] = 50;
        EGR_array[10][4] = 50;
        EGR_array[10][5] = 50;
        EGR_array[10][6] = 50;
        EGR_array[10][7] = 50;
        EGR_array[10][8] = 50;
        EGR_array[10][9] = 50;
        EGR_array[10][10] = 50;
        EGR_array[10][11] = 50;
        EGR_array[10][12] = 50;
        EGR_array[10][13] = 50;
        EGR_array[10][14] = 50;
        EGR_array[10][15] = 50;
        EGR_array[10][16] = 50;

        //row 11 : EGR_array[11][iterator]
        EGR_array[11][1] = 75;
        EGR_array[11][2] = 50;
        EGR_array[11][3] = 50;
        EGR_array[11][4] = 50;
        EGR_array[11][5] = 50;
        EGR_array[11][6] = 50;
        EGR_array[11][7] = 50;
        EGR_array[11][8] = 50;
        EGR_array[11][9] = 50;
        EGR_array[11][10] = 50;
        EGR_array[11][11] = 50;
        EGR_array[11][12] = 50;
        EGR_array[11][13] = 50;
        EGR_array[11][14] = 50;
        EGR_array[11][15] = 50;
        EGR_array[11][16] = 0;

        //row 12 : EGR_array[12][iterator]
        EGR_array[12][1] = 75;
        EGR_array[12][2] = 50;
        EGR_array[12][3] = 50;
        EGR_array[12][4] = 50;
        EGR_array[12][5] = 50;
        EGR_array[12][6] = 50;
        EGR_array[12][7] = 50;
        EGR_array[12][8] = 50;
        EGR_array[12][9] = 50;
        EGR_array[12][10] = 50;
        EGR_array[12][11] = 50;
        EGR_array[12][12] = 50;
        EGR_array[12][13] = 50;
        EGR_array[12][14] = 50;
        EGR_array[12][15] = 50;
        EGR_array[12][16] = 0;

        //row 13 : EGR_array[13][iterator]
        EGR_array[13][1] = 75;
        EGR_array[13][2] = 50;
        EGR_array[13][3] = 50;
        EGR_array[13][4] = 50;
        EGR_array[13][5] = 50;
        EGR_array[13][6] = 50;
        EGR_array[13][7] = 50;
        EGR_array[13][8] = 50;
        EGR_array[13][9] = 50;
        EGR_array[13][10] = 50;
        EGR_array[13][11] = 50;
        EGR_array[13][12] = 50;
        EGR_array[13][13] = 50;
        EGR_array[13][14] = 0;
        EGR_array[13][15] = 0;
        EGR_array[13][16] = 0;

        //row 14 : EGR_array[14][iterator]
        EGR_array[14][1] = 75;
        EGR_array[14][2] = 50;
        EGR_array[14][3] = 50;
        EGR_array[14][4] = 50;
        EGR_array[14][5] = 50;
        EGR_array[14][6] = 50;
        EGR_array[14][7] = 50;
        EGR_array[14][8] = 50;
        EGR_array[14][9] = 50;
        EGR_array[14][10] = 50;
        EGR_array[14][11] = 50;
        EGR_array[14][12] = 50;
        EGR_array[14][13] = 50;
        EGR_array[14][14] = 0;
        EGR_array[14][15] = 0;
        EGR_array[14][16] = 0;

        //row 15 : EGR_array[15][iterator]
        EGR_array[15][1] = 0;
        EGR_array[15][2] = 0;
        EGR_array[15][3] = 0;
        EGR_array[15][4] = 0;
        EGR_array[15][5] = 0;
        EGR_array[15][6] = 0;
        EGR_array[15][7] = 0;
        EGR_array[15][8] = 0;
        EGR_array[15][9] = 0;
        EGR_array[15][10] = 0;
        EGR_array[15][11] = 0;
        EGR_array[15][12] = 0;
        EGR_array[15][13] = 0;
        EGR_array[15][14] = 0;
        EGR_array[15][15] = 0;
        EGR_array[15][16] = 0;

        //row 16 : EGR_array[16][iterator]
        EGR_array[16][1] = 0;
        EGR_array[16][2] = 0;
        EGR_array[16][3] = 0;
        EGR_array[16][4] = 0;
        EGR_array[16][5] = 0;
        EGR_array[16][6] = 0;
        EGR_array[16][7] = 0;
        EGR_array[16][8] = 0;
        EGR_array[16][9] = 0;
        EGR_array[16][10] = 0;
        EGR_array[16][11] = 0;
        EGR_array[16][12] = 0;
        EGR_array[16][13] = 0;
        EGR_array[16][14] = 0;
        EGR_array[16][15] = 0;
        EGR_array[16][16] = 0;

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
        Temp_LUT[6][1] = 18;
        Temp_LUT[7][1] = 24;
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
        pressure_array[1][0] = 350;
        pressure_array[2][0] = 1000;
        pressure_array[3][0] = 1500;
        pressure_array[4][0] = 2000;
        pressure_array[5][0] = 2500;
        pressure_array[6][0] = 3000;
        pressure_array[7][0] = 3500;
        pressure_array[8][0] = 4095;

        //row 2: pressure_array[2][iterator];
        pressure_array[1][1] = 5;
        pressure_array[2][1] = 13;
        pressure_array[3][1] = 19;
        pressure_array[4][1] = 25;
        pressure_array[5][1] = 32;
        pressure_array[6][1] = 38;
        pressure_array[7][1] = 45;
        pressure_array[8][1] = 50;

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

        calID.setText("P401REPADI100123");

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
