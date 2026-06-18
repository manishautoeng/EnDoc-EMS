package com.UI;

import com.Calender.DateTime;
import com.Calibration.CalulateSUM;
import com.Calibration.DTC_Engage;
import com.Calibration.sum_Verification;
import com.DatabaseManagement.Create_Tables;
import com.DatabaseManagement.fetchDB;
import com.DatabaseManagement.insertDB;
import com.LUT.ABV_Chhakada;
import com.LUT.Atul_Passenger;
import com.LUT.Atul_shakti;
import com.LUT.Baxy_Cargo;
import com.LUT.Baxy_Cargo_FE;
import com.LUT.Baxy_Passenger;
import com.LUT.Baxy_Superking;
import com.LUT.MLR_Cargo;
import com.LUT.MLR_Passenger;
import com.LUT.PVPL_Cargo;
import com.LUT.PVPL_Passenger;
import com.LUT.Sample;
import com.LUT_Operations.CAN_Flashing;
import com.LUT_Operations.Flashing;
import com.LUT_Operations.ReadLUT;
import com.LUT_Operations.ReadLUT2;
import com.LUT_Operations.UpdateLUT;
import com.LUT_Operations.openLUT;
import com.LUT_Operations.saveLUT2;
import com.Licensing.EnDoc_License;
import com.PDFReader.PDFRead;
import com.VIN.VIN_flashing;
import com.VirtualErrors.VirtualError;
import com.fazecast.jSerialComm.SerialPort;
import com.image.setImageIcon;
import com.lutDAO.EngineParam;
import com.lutDAO.fetchLUT;
import com.online_values.Export_OnlineData2;
import com.online_values.SerialReadData;
import com.serial.serialConnection;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author IamDe
 */
public class index extends javax.swing.JFrame {

    //Global Variable Declaration
    SerialPort portVar;
    int sum = 0;
    InputStream read_Serial;

    //Class Refrence Variables
    serialConnection con = new serialConnection();
    //Serial_Reading read_online = new Serial_Reading();
    SerialReadData Read_Obj = new SerialReadData();
    setImageIcon img = new setImageIcon();

    String filepath;
    int frequencyTimer = 100;
    String dateVari, RoleUser;

    //Engine Parameter Variable Decalaration
    /**
     * Creates new form index
     */
    public index() {

        initComponents();
        setIconImage();
        DateTime dateVar = new DateTime();
        dateVar.curDateTime(lblCurDate, lblCurTime);
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        //by Default full screen
        setExtendedState(MAXIMIZED_BOTH);
        //DefaultTableModel model_DOI = (DefaultTableModel) table_DOI.getModel();

        flashProgressBar.setMinimum(0);
        flashProgressBar.setMaximum(100);
        waterLevelProgress.setMinimum(0);
        waterLevelProgress.setMaximum(100);

        flashProgressBar.setForeground(Color.green);
        btnFlashVIN.setEnabled(false);
        btnRefreshApplication.setSelected(false);
        jPanel22.setVisible(false);

        // table_DOI_calibration.setVisible(false);
        btnClearCalibrationTable.setVisible(false);
        btnExportCalibration.setVisible(false);
        btnReadLUT.setEnabled(false);
        btnUpdateReadLUT.setEnabled(false);
        txtCalID.setEditable(false);
        btnConnect.setEnabled(false);
        btnDisconnect.setEnabled(false);

        TableColumnModel columnModel = Diagnostics_Table.getColumnModel();
        TableColumn column = columnModel.getColumn(4);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);

        timer_100_Radio.setSelected(true);
        //convertADC();
        //Database Configurations
        Database_Management();

    }

    public index(String date, String role) {

        initComponents();
        setIconImage();
        DateTime dateVar = new DateTime();
        fetchDB vari = new fetchDB();
        vari.fetchLicense_Date(lblDate);
        dateVar.curDateTime(lblCurDate, lblCurTime);
        //by Default full screen
        dateVari = date;
        RoleUser = role;
        //setExtendedState(MAXIMIZED_BOTH);
        //DefaultTableModel model_DOI = (DefaultTableModel) table_DOI.getModel();
        flashProgressBar.setMinimum(0);
        flashProgressBar.setMaximum(100);
        waterLevelProgress.setMinimum(0);
        waterLevelProgress.setMaximum(100);
        int valueLevel = 0;
        lblLoginRole.setText(role);
        flashProgressBar.setForeground(Color.green);
        btnFlashVIN.setEnabled(false);
        btnRefreshApplication.setSelected(false);
        jPanel22.setVisible(false);
        btnClearCalibrationTable.setVisible(false);
        btnExportCalibration.setVisible(false);
        btnReadLUT.setEnabled(false);
        btnUpdateReadLUT.setEnabled(false);
        txtCalID.setEditable(false);
        btnConnect.setEnabled(false);
        btnDisconnect.setEnabled(false);
        TableColumnModel columnModel = Diagnostics_Table.getColumnModel();
        TableColumn column = columnModel.getColumn(4);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
        TableColumn column2 = columnModel.getColumn(5);
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setWidth(0);
        column2.setPreferredWidth(0);
        column2.setResizable(false);
        timer_100_Radio.setSelected(true);

        //Database Configurations
        Database_Management();
        fetchDB dbFetch = new fetchDB();
        dbFetch.fetch_FlashECU(totalFlashCount);

        if (role.equals("User")) {
            userRole();
        } else if (role.equals("Customer")) {
            customerRole();
            convertADC();
        } else if (role.equals("Admin")) {
            AdminRole();
            convertADC();
        }

    }

    public index(String date, String role, String comMode) {

        initComponents();
        setIconImage();
        DateTime dateVar = new DateTime();
        fetchDB vari = new fetchDB();
        vari.fetchLicense_Date(lblDate);
        dateVar.curDateTime(lblCurDate, lblCurTime);
        lbl_ComMode.setText(comMode);
        //by Default full screen
        dateVari = date;
        RoleUser = role;
        //setExtendedState(MAXIMIZED_BOTH);
        //DefaultTableModel model_DOI = (DefaultTableModel) table_DOI.getModel();
        flashProgressBar.setMinimum(0);
        flashProgressBar.setMaximum(100);
        waterLevelProgress.setMinimum(0);
        waterLevelProgress.setMaximum(100);
        int valueLevel = 0;
        lblLoginRole.setText(role);
        flashProgressBar.setForeground(Color.green);
        btnFlashVIN.setEnabled(false);
        btnRefreshApplication.setSelected(false);
        jPanel22.setVisible(false);
        btnClearCalibrationTable.setVisible(false);
        btnExportCalibration.setVisible(false);
        btnReadLUT.setEnabled(false);
        btnUpdateReadLUT.setEnabled(false);
        txtCalID.setEditable(false);
        btnConnect.setEnabled(false);
        btnDisconnect.setEnabled(false);
        TableColumnModel columnModel = Diagnostics_Table.getColumnModel();
        TableColumn column = columnModel.getColumn(4);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
        TableColumn column2 = columnModel.getColumn(5);
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setWidth(0);
        column2.setPreferredWidth(0);
        column2.setResizable(false);
        timer_100_Radio.setSelected(true);

        //Database Configurations
        Database_Management();
        fetchDB dbFetch = new fetchDB();
        dbFetch.fetch_FlashECU(totalFlashCount);

        if (role.equals("User")) {
            userRole();
        } else if (role.equals("Customer")) {
            customerRole();
            convertADC();
        } else if (role.equals("Admin")) {
            AdminRole();
            convertADC();
        }

    }

    private void userRole() {

        //Cannot Access VIN
        txtVIN.setEnabled(false);
        btnFlashVIN.setEnabled(false);
        btnFlashVIN1.setEnabled(false);

        //Cannot open external LUT file
        btnSaveLUT.setEnabled(false);
        btnOpenLUT.setEnabled(false);
        btnUpdateLUT.setEnabled(false);

        //Cannot Edit MAP files
        table_DOI.setEnabled(false);
        table_EGR.setEnabled(false);
        table_SOI.setEnabled(false);
        table_Temperature.setEnabled(false);
        table_Prerssure.setEnabled(false);
        table_ExhaustTemperature.setEnabled(false);
        table_DeltaPrerssure.setEnabled(false);

        //Cannot update Diagnostics
        btnUpdateDiagnostics.setEnabled(false);

        //Cannot Manipulate ADC Module
        btnGenerateError.setEnabled(false);
        btnResetError.setEnabled(false);

        //Cannot use Edit module
        btnRefreshApplication.setEnabled(false);
        jMenuItem5.setEnabled(false);

        //Cannot Edit  Engine Parameters
        txtDeltaPressure_AVG.setEnabled(false);
        txtFuelCutOff.setEnabled(false);
        txtCrankingDOI.setEnabled(false);
        txtDOI_Op.setEnabled(false);
        txtSecEOT.setEnabled(false);
        txtSecEOT_CF.setEnabled(false);
        txtPump_ON.setEnabled(false);
        txtGlowPlug_ON.setEnabled(false);
        txtVSS_CF.setEnabled(false);
        txtDeltaPressure_SUM.setEnabled(false);
        txtEGR_POT_Max.setEnabled(false);
        txtEGR_POT_Min.setEnabled(false);
        txtConsumption_Error_Max.setEnabled(false);
        txtConsumption_Error_Min.setEnabled(false);
        txtWI_Reset.setEnabled(false);
        txtMinConsumption.setEnabled(false);
        txtInducement.setEnabled(false);
        txtDeltaPressure_Temp.setEnabled(false);
        txtEGR_CF.setEnabled(false);
        txtDOI_CF.setEnabled(false);
        txtParam6.setEnabled(false);
        txtParam7.setEnabled(false);

        //Cannot convert voltage to ADc
        checkADCtoVoltage.setEnabled(false);

        //Cannot update and Save Engine Param
        btnSaveEngineParam.setEnabled(false);
        btnUpdateEngineParam.setEnabled(false);
        
        lbl_Functionality_Test.setVisible(true);

    }

    private void customerRole() {
        jMenuItem4.setEnabled(false);
        jMenuItem5.setEnabled(false);
        btnUpdateDiagnostics.setEnabled(false);

        //Cannot convert voltage to ADC
        if (lbl_ComMode.getText().equals("CAN")) {
            checkADCtoVoltage.setEnabled(false);
            checkADCtoVoltage.setSelected(true);
        } else {
            checkADCtoVoltage.setEnabled(true);
            checkADCtoVoltage.setSelected(false);
        }

        btnSaveEngineParam.setEnabled(false);
        lbl_Functionality_Test.setVisible(false);

    }

    private void AdminRole() {
        checkADCtoVoltage.setEnabled(true);
        lbl_Functionality_Test.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timergroup = new javax.swing.ButtonGroup();
        panelCOM = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboCOM = new javax.swing.JComboBox<>();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblConStatus = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        lblDataFrequency = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        lbl_ComMode = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pLUT = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_OD = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        statusMIL = new javax.swing.JProgressBar();
        jLabel27 = new javax.swing.JLabel();
        statusPump = new javax.swing.JProgressBar();
        jLabel28 = new javax.swing.JLabel();
        statusEngineHalt = new javax.swing.JProgressBar();
        jLabel76 = new javax.swing.JLabel();
        statusWIM = new javax.swing.JProgressBar();
        jLabel77 = new javax.swing.JLabel();
        statusWaterEmpty = new javax.swing.JProgressBar();
        jLabel78 = new javax.swing.JLabel();
        statusInducement = new javax.swing.JProgressBar();
        lbl_Functionality_Test = new javax.swing.JLabel();
        panelLUTPanel = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        table_DOI = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        table_EGR = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        table_Temperature = new javax.swing.JTable();
        jScrollPane33 = new javax.swing.JScrollPane();
        table_Prerssure = new javax.swing.JTable();
        jScrollPane34 = new javax.swing.JScrollPane();
        table_ExhaustTemperature = new javax.swing.JTable();
        jScrollPane35 = new javax.swing.JScrollPane();
        table_DeltaPrerssure = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        table_SOI = new javax.swing.JTable();
        btnSaveLUT = new javax.swing.JButton();
        btnUpdateLUT = new javax.swing.JButton();
        btnOpenLUT = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtDeltaPressure_AVG = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFuelCutOff = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCrankingDOI = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDOI_Op = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSecEOT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtSecEOT_CF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPump_ON = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtGlowPlug_ON = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtVSS_CF = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDeltaPressure_SUM = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEGR_POT_Max = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtEGR_POT_Min = new javax.swing.JTextField();
        txtConsumption_Error_Max = new javax.swing.JTextField();
        txtConsumption_Error_Min = new javax.swing.JTextField();
        txtWI_Reset = new javax.swing.JTextField();
        txtMinConsumption = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtInducement = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtDeltaPressure_Temp = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtEGR_CF = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtParam6 = new javax.swing.JTextField();
        txtDOI_CF = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtParam7 = new javax.swing.JTextField();
        btnSaveEngineParam = new javax.swing.JButton();
        btnUpdateEngineParam = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        errorCodeDTC = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        descriptionDTC = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        freezeFrame = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        newFreezeFrame = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Diagnostics_Table = new javax.swing.JTable();
        btnUpdateDiagnostics = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        consumptionData = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        checkBattery = new javax.swing.JCheckBox();
        errorBattery = new javax.swing.JSpinner();
        checkTPS = new javax.swing.JCheckBox();
        errorTPS = new javax.swing.JSpinner();
        checkEGR = new javax.swing.JCheckBox();
        errorEGR = new javax.swing.JSpinner();
        checkPressureSensor = new javax.swing.JCheckBox();
        errorPressureSensor = new javax.swing.JSpinner();
        checkDeltaPressure = new javax.swing.JCheckBox();
        errorDeltaPressure = new javax.swing.JSpinner();
        checkEOT = new javax.swing.JCheckBox();
        errorEOT = new javax.swing.JSpinner();
        checkEGT1 = new javax.swing.JCheckBox();
        errorEGT1 = new javax.swing.JSpinner();
        checkEGT2 = new javax.swing.JCheckBox();
        errorEGT2 = new javax.swing.JSpinner();
        btnGenerateError = new javax.swing.JButton();
        EOT_Intermit = new javax.swing.JCheckBox();
        EGT1_Intermit = new javax.swing.JCheckBox();
        EGT2_Intermit = new javax.swing.JCheckBox();
        btnResetError = new javax.swing.JButton();
        jScrollPane28 = new javax.swing.JScrollPane();
        table_ADC = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        btnExportCalibration = new javax.swing.JButton();
        btnClearCalibrationTable = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        DOI_SUM = new javax.swing.JLabel();
        EGR_SUM = new javax.swing.JLabel();
        Temp_SUM = new javax.swing.JLabel();
        Pressure_SUM = new javax.swing.JLabel();
        SOI_SUM = new javax.swing.JLabel();
        EngineParam_SUM = new javax.swing.JLabel();
        CAL_ID_SUM = new javax.swing.JLabel();
        Total_SUM = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        EGT_SUM = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        Delta_Pressure_SUM = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnFlash = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        flashProgressBar = new javax.swing.JProgressBar();
        lblProcessStatus = new javax.swing.JLabel();
        panelUniqueVal = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCalID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCVN = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtVIN = new javax.swing.JTextField();
        btnFlashVIN = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        vinLength = new javax.swing.JLabel();
        btnFlashVIN1 = new javax.swing.JButton();
        panelLUTSelection = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        comboLUT = new javax.swing.JComboBox<>();
        btnLoad_LUT = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        checkboxOnlineData = new javax.swing.JCheckBox();
        btnReadLUT = new javax.swing.JButton();
        btnUpdateReadLUT = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        LUT_sum = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        Default_SUM = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lutName1 = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblCurDate = new javax.swing.JLabel();
        lblCurTime = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        sessionCount = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        totalFlashCount = new javax.swing.JLabel();
        panel_ADC = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        lblLoginRole = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        lbl_FWversion = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        lblDTCCount = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        display_msg = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        waterLevelProgress = new javax.swing.JProgressBar();
        waterLevelPercent = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        display_msg1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        btnRefreshApplication = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        timer_100_Radio = new javax.swing.JRadioButtonMenuItem();
        timer_500_Radio = new javax.swing.JRadioButtonMenuItem();
        timer_1000_Radio = new javax.swing.JRadioButtonMenuItem();
        checkADCtoVoltage = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EnDoc By Tecsage");
        setBounds(new java.awt.Rectangle(100, 100, 10000, 10000));
        setLocation(new java.awt.Point(0, 0));
        setSize(new java.awt.Dimension(1080, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        panelCOM.setBackground(new java.awt.Color(95, 130, 206));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COM PORT:");

        comboCOM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        comboCOM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCOM.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                comboCOMPopupMenuWillBecomeVisible(evt);
            }
        });
        comboCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCOMActionPerformed(evt);
            }
        });

        btnConnect.setBackground(new java.awt.Color(255, 255, 255));
        btnConnect.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Connected.png"))); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnDisconnect.setBackground(new java.awt.Color(255, 255, 255));
        btnDisconnect.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDisconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Disconnected.png"))); // NOI18N
        btnDisconnect.setText("Disconnect");
        btnDisconnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisconnectActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COM Status: ");

        lblConStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblConStatus.setForeground(new java.awt.Color(255, 255, 153));
        lblConStatus.setText("Disconnected");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("Data Freq. (ms):");

        lblDataFrequency.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDataFrequency.setForeground(new java.awt.Color(255, 255, 153));
        lblDataFrequency.setText("- ms");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("COM Mode:");

        lbl_ComMode.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ComMode.setForeground(new java.awt.Color(255, 255, 153));
        lbl_ComMode.setText("CAN/UART");

        javax.swing.GroupLayout panelCOMLayout = new javax.swing.GroupLayout(panelCOM);
        panelCOM.setLayout(panelCOMLayout);
        panelCOMLayout.setHorizontalGroup(
            panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCOMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCOMLayout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ComMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCOMLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDataFrequency))
                    .addGroup(panelCOMLayout.createSequentialGroup()
                        .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCOMLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelCOMLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblConStatus))
                            .addComponent(btnDisconnect)
                            .addComponent(btnConnect))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCOMLayout.setVerticalGroup(
            panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCOMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(comboCOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisconnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblConStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(lblDataFrequency))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(lbl_ComMode))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCOMLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comboCOM, jLabel1});

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 204));
        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        pLUT.setBackground(new java.awt.Color(95, 130, 206));

        jScrollPane5.setAutoscrolls(true);
        jScrollPane5.setNextFocusableComponent(table_OD.getNextFocusableComponent());
        jScrollPane5.setViewport(jScrollPane5.getViewport());
        jScrollPane5.setViewportView(table_OD);

        table_OD.setBackground(new java.awt.Color(153, 204, 255));
        table_OD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_OD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Battery [V]", "TPS [%]", "ERPM", "EOT [°C]", "DOI [μs]", "Inj. per Sec", "VS [Km/Hr]", "Sol. Pr [kPa]", "EGR Set [%]", "Actual EGR [%]", "ΔP mBar", "EGT 1 [°C]", "EGT 2 [°C]", "Cons. Timer [min]", "DTC Code", "DTC Code 2", "DTC Code 3", "EOT [V]", "EGR [V]", "Sol. Pr. [V]", "ΔP [V]", "ΔP SUM / 10", "ΔP Count", "TPS [V]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_OD.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table_OD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table_OD.setFillsViewportHeight(true);
        table_OD.setGridColor(new java.awt.Color(95, 130, 206));
        table_OD.setIntercellSpacing(new java.awt.Dimension(40, 1));
        table_OD.setSelectionBackground(new java.awt.Color(255, 255, 153));
        jScrollPane5.setViewportView(table_OD);
        if (table_OD.getColumnModel().getColumnCount() > 0) {
            table_OD.getColumnModel().getColumn(9).setPreferredWidth(100);
            table_OD.getColumnModel().getColumn(13).setPreferredWidth(100);
            table_OD.getColumnModel().getColumn(14).setPreferredWidth(100);
            table_OD.getColumnModel().getColumn(15).setPreferredWidth(100);
            table_OD.getColumnModel().getColumn(16).setPreferredWidth(100);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("MIL:");

        statusMIL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusMIL.setForeground(new java.awt.Color(51, 51, 51));
        statusMIL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusMIL.setString("0");
        statusMIL.setStringPainted(true);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("PUMP:");

        statusPump.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        statusPump.setForeground(new java.awt.Color(95, 130, 206));
        statusPump.setMaximum(1);
        statusPump.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusPump.setString("0");
        statusPump.setStringPainted(true);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("ENGINE HALT:");

        statusEngineHalt.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusEngineHalt.setForeground(new java.awt.Color(51, 51, 51));
        statusEngineHalt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusEngineHalt.setString("0");
        statusEngineHalt.setStringPainted(true);

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("WIM:");

        statusWIM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusWIM.setForeground(new java.awt.Color(51, 51, 51));
        statusWIM.setMaximum(1);
        statusWIM.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusWIM.setString("0");
        statusWIM.setStringPainted(true);

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Water Empty:");

        statusWaterEmpty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        statusWaterEmpty.setForeground(new java.awt.Color(95, 130, 206));
        statusWaterEmpty.setMaximum(1);
        statusWaterEmpty.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusWaterEmpty.setString("0");
        statusWaterEmpty.setStringPainted(true);

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Inducement:");

        statusInducement.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusInducement.setForeground(new java.awt.Color(51, 51, 51));
        statusInducement.setMaximum(1);
        statusInducement.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        statusInducement.setString("0");
        statusInducement.setStringPainted(true);

        lbl_Functionality_Test.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_Functionality_Test.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Functionality_Test.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Functionality_Test.setText("ECU STATUS");
        lbl_Functionality_Test.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pLUTLayout = new javax.swing.GroupLayout(pLUT);
        pLUT.setLayout(pLUTLayout);
        pLUTLayout.setHorizontalGroup(
            pLUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLUTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pLUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                    .addGroup(pLUTLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusMIL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusPump, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusEngineHalt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusWIM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusWaterEmpty, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusInducement, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_Functionality_Test, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pLUTLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {statusEngineHalt, statusInducement, statusMIL, statusPump, statusWIM, statusWaterEmpty});

        pLUTLayout.setVerticalGroup(
            pLUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLUTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pLUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusMIL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusPump, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusEngineHalt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusWIM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusWaterEmpty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusInducement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_Functionality_Test, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        pLUTLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel5, statusMIL});

        pLUTLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel27, statusPump});

        pLUTLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel28, statusEngineHalt});

        pLUTLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel76, jLabel77, jLabel78, statusInducement, statusWIM, statusWaterEmpty});

        jTabbedPane1.addTab("Online Values", pLUT);

        panelLUTPanel.setBackground(new java.awt.Color(95, 130, 206));

        jPanel13.setBackground(new java.awt.Color(95, 130, 206));

        table_DOI.setBackground(new java.awt.Color(153, 204, 255));
        table_DOI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_DOI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_DOI.setFillsViewportHeight(true);
        table_DOI.setGridColor(new java.awt.Color(95, 130, 206));
        table_DOI.setIntercellSpacing(new java.awt.Dimension(25, 1));
        table_DOI.setRowHeight(22);
        table_DOI.getTableHeader().setReorderingAllowed(false);
        jScrollPane29.setViewportView(table_DOI);
        if (table_DOI.getColumnModel().getColumnCount() > 0) {
            table_DOI.getColumnModel().getColumn(0).setResizable(false);
            table_DOI.getColumnModel().getColumn(0).setPreferredWidth(100);
            table_DOI.getColumnModel().getColumn(1).setResizable(false);
            table_DOI.getColumnModel().getColumn(2).setResizable(false);
            table_DOI.getColumnModel().getColumn(3).setResizable(false);
            table_DOI.getColumnModel().getColumn(4).setResizable(false);
            table_DOI.getColumnModel().getColumn(5).setResizable(false);
            table_DOI.getColumnModel().getColumn(6).setResizable(false);
            table_DOI.getColumnModel().getColumn(7).setResizable(false);
            table_DOI.getColumnModel().getColumn(8).setResizable(false);
            table_DOI.getColumnModel().getColumn(9).setResizable(false);
            table_DOI.getColumnModel().getColumn(10).setResizable(false);
            table_DOI.getColumnModel().getColumn(11).setResizable(false);
            table_DOI.getColumnModel().getColumn(12).setResizable(false);
            table_DOI.getColumnModel().getColumn(13).setResizable(false);
            table_DOI.getColumnModel().getColumn(14).setResizable(false);
            table_DOI.getColumnModel().getColumn(15).setResizable(false);
            table_DOI.getColumnModel().getColumn(16).setResizable(false);
        }

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("DOI [microsec]", jPanel13);

        jPanel14.setBackground(new java.awt.Color(95, 130, 206));

        table_EGR.setBackground(new java.awt.Color(153, 204, 255));
        table_EGR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_EGR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_EGR.setGridColor(new java.awt.Color(95, 130, 206));
        table_EGR.setIntercellSpacing(new java.awt.Dimension(25, 1));
        table_EGR.setRowHeight(22);
        table_EGR.getTableHeader().setReorderingAllowed(false);
        jScrollPane30.setViewportView(table_EGR);
        if (table_EGR.getColumnModel().getColumnCount() > 0) {
            table_EGR.getColumnModel().getColumn(0).setResizable(false);
            table_EGR.getColumnModel().getColumn(0).setPreferredWidth(100);
            table_EGR.getColumnModel().getColumn(1).setResizable(false);
            table_EGR.getColumnModel().getColumn(2).setResizable(false);
            table_EGR.getColumnModel().getColumn(3).setResizable(false);
            table_EGR.getColumnModel().getColumn(4).setResizable(false);
            table_EGR.getColumnModel().getColumn(5).setResizable(false);
            table_EGR.getColumnModel().getColumn(6).setResizable(false);
            table_EGR.getColumnModel().getColumn(7).setResizable(false);
            table_EGR.getColumnModel().getColumn(8).setResizable(false);
            table_EGR.getColumnModel().getColumn(9).setResizable(false);
            table_EGR.getColumnModel().getColumn(10).setResizable(false);
            table_EGR.getColumnModel().getColumn(11).setResizable(false);
            table_EGR.getColumnModel().getColumn(12).setResizable(false);
            table_EGR.getColumnModel().getColumn(13).setResizable(false);
            table_EGR.getColumnModel().getColumn(14).setResizable(false);
            table_EGR.getColumnModel().getColumn(15).setResizable(false);
            table_EGR.getColumnModel().getColumn(16).setResizable(false);
        }

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("EGR [%]", jPanel14);

        jPanel19.setBackground(new java.awt.Color(95, 130, 206));

        table_Temperature.setBackground(new java.awt.Color(153, 204, 255));
        table_Temperature.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_Temperature.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_Temperature.setGridColor(new java.awt.Color(95, 130, 206));
        table_Temperature.setIntercellSpacing(new java.awt.Dimension(30, 1));
        table_Temperature.setRowHeight(22);
        table_Temperature.getTableHeader().setReorderingAllowed(false);
        jScrollPane32.setViewportView(table_Temperature);
        if (table_Temperature.getColumnModel().getColumnCount() > 0) {
            table_Temperature.getColumnModel().getColumn(0).setResizable(false);
            table_Temperature.getColumnModel().getColumn(1).setResizable(false);
            table_Temperature.getColumnModel().getColumn(2).setResizable(false);
        }

        table_Prerssure.setBackground(new java.awt.Color(153, 204, 255));
        table_Prerssure.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_Prerssure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_Prerssure.setGridColor(new java.awt.Color(95, 130, 206));
        table_Prerssure.setIntercellSpacing(new java.awt.Dimension(50, 1));
        table_Prerssure.setRowHeight(22);
        table_Prerssure.getTableHeader().setReorderingAllowed(false);
        jScrollPane33.setViewportView(table_Prerssure);
        if (table_Prerssure.getColumnModel().getColumnCount() > 0) {
            table_Prerssure.getColumnModel().getColumn(0).setResizable(false);
            table_Prerssure.getColumnModel().getColumn(1).setResizable(false);
        }

        table_ExhaustTemperature.setBackground(new java.awt.Color(153, 204, 255));
        table_ExhaustTemperature.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_ExhaustTemperature.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_ExhaustTemperature.setGridColor(new java.awt.Color(95, 130, 206));
        table_ExhaustTemperature.setIntercellSpacing(new java.awt.Dimension(30, 1));
        table_ExhaustTemperature.setRowHeight(22);
        table_ExhaustTemperature.getTableHeader().setReorderingAllowed(false);
        jScrollPane34.setViewportView(table_ExhaustTemperature);
        if (table_ExhaustTemperature.getColumnModel().getColumnCount() > 0) {
            table_ExhaustTemperature.getColumnModel().getColumn(0).setResizable(false);
            table_ExhaustTemperature.getColumnModel().getColumn(1).setResizable(false);
            table_ExhaustTemperature.getColumnModel().getColumn(2).setResizable(false);
        }

        table_DeltaPrerssure.setBackground(new java.awt.Color(153, 204, 255));
        table_DeltaPrerssure.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_DeltaPrerssure.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_DeltaPrerssure.setGridColor(new java.awt.Color(95, 130, 206));
        table_DeltaPrerssure.setIntercellSpacing(new java.awt.Dimension(30, 1));
        table_DeltaPrerssure.setRowHeight(22);
        table_DeltaPrerssure.getTableHeader().setReorderingAllowed(false);
        jScrollPane35.setViewportView(table_DeltaPrerssure);
        if (table_DeltaPrerssure.getColumnModel().getColumnCount() > 0) {
            table_DeltaPrerssure.getColumnModel().getColumn(0).setResizable(false);
            table_DeltaPrerssure.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("EOT Sensor Calibration table & CF [%] for DOI");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Sol. Pr. Sensor Calibration table");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("EGT sensor Calibration table");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Delta Pr. Sensor Calibration table");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jScrollPane35, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Compensation Factor", jPanel19);

        jPanel2.setBackground(new java.awt.Color(95, 130, 206));

        table_SOI.setBackground(new java.awt.Color(153, 204, 255));
        table_SOI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        table_SOI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_SOI.setGridColor(new java.awt.Color(95, 130, 206));
        table_SOI.setIntercellSpacing(new java.awt.Dimension(20, 1));
        table_SOI.setRowHeight(22);
        table_SOI.getTableHeader().setReorderingAllowed(false);
        jScrollPane31.setViewportView(table_SOI);
        if (table_SOI.getColumnModel().getColumnCount() > 0) {
            table_SOI.getColumnModel().getColumn(0).setResizable(false);
            table_SOI.getColumnModel().getColumn(1).setResizable(false);
            table_SOI.getColumnModel().getColumn(2).setResizable(false);
            table_SOI.getColumnModel().getColumn(3).setResizable(false);
            table_SOI.getColumnModel().getColumn(4).setResizable(false);
            table_SOI.getColumnModel().getColumn(5).setResizable(false);
            table_SOI.getColumnModel().getColumn(6).setResizable(false);
            table_SOI.getColumnModel().getColumn(7).setResizable(false);
            table_SOI.getColumnModel().getColumn(8).setResizable(false);
            table_SOI.getColumnModel().getColumn(9).setResizable(false);
            table_SOI.getColumnModel().getColumn(10).setResizable(false);
            table_SOI.getColumnModel().getColumn(11).setResizable(false);
            table_SOI.getColumnModel().getColumn(12).setResizable(false);
            table_SOI.getColumnModel().getColumn(13).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("SOI [°CA]", jPanel2);

        btnSaveLUT.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveLUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Save.png"))); // NOI18N
        btnSaveLUT.setText("Save LUT");
        btnSaveLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveLUTActionPerformed(evt);
            }
        });

        btnUpdateLUT.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateLUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Refresh.png"))); // NOI18N
        btnUpdateLUT.setText("Update LUT");
        btnUpdateLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateLUTActionPerformed(evt);
            }
        });

        btnOpenLUT.setBackground(new java.awt.Color(255, 255, 255));
        btnOpenLUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Load From File.png"))); // NOI18N
        btnOpenLUT.setText("Open LUT");
        btnOpenLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenLUTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLUTPanelLayout = new javax.swing.GroupLayout(panelLUTPanel);
        panelLUTPanel.setLayout(panelLUTPanelLayout);
        panelLUTPanelLayout.setHorizontalGroup(
            panelLUTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLUTPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLUTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLUTPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateLUT)
                        .addGap(18, 18, 18)
                        .addComponent(btnOpenLUT)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveLUT))
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );
        panelLUTPanelLayout.setVerticalGroup(
            panelLUTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLUTPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLUTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveLUT)
                    .addComponent(btnUpdateLUT)
                    .addComponent(btnOpenLUT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LUT Module", panelLUTPanel);

        jPanel5.setBackground(new java.awt.Color(95, 130, 206));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("∆P High Threshold Value (Avg.) (mBar):");

        txtDeltaPressure_AVG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDeltaPressure_AVG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeltaPressure_AVGKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fuel Cut-Off Cond. @ Engine speed (RPM):");

        txtFuelCutOff.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtFuelCutOff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFuelCutOffKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("DOI @ Engine Cranking mode (microsec) : ");

        txtCrankingDOI.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCrankingDOI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCrankingDOIKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Delay for Injection Operation (microsec) :");

        txtDOI_Op.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDOI_Op.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDOI_OpKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sec. CF EOT(°C) :");

        txtSecEOT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSecEOT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSecEOTKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sec. EOT Compensation Factor:");

        txtSecEOT_CF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSecEOT_CF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSecEOT_CFKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("VSS Correction Factor (CF * 10) : ");

        txtPump_ON.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtPump_ON.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPump_ONKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("EGR POT ADC Max. :");

        txtGlowPlug_ON.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGlowPlug_ON.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGlowPlug_ONKeyReleased(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("EGR POT ADC Min. :");

        txtVSS_CF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtVSS_CF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVSS_CFKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Pump ON time (sec) :");

        txtDeltaPressure_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDeltaPressure_SUM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeltaPressure_SUMKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Glow Plug ON time (sec) :");

        txtEGR_POT_Max.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEGR_POT_Max.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEGR_POT_MaxKeyReleased(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("∆P Low Threshold Value (SUM)(mBar):");

        txtEGR_POT_Min.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEGR_POT_Min.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEGR_POT_MinKeyReleased(evt);
            }
        });

        txtConsumption_Error_Max.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtConsumption_Error_Max.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsumption_Error_MaxKeyReleased(evt);
            }
        });

        txtConsumption_Error_Min.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtConsumption_Error_Min.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsumption_Error_MinKeyReleased(evt);
            }
        });

        txtWI_Reset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtWI_Reset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtWI_ResetKeyReleased(evt);
            }
        });

        txtMinConsumption.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMinConsumption.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinConsumptionKeyReleased(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Consumption Error Max Limit (%) :");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Consumption Error Min Limit (%):");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("WI Reset :");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Min Consumption Qty (Q/4) :");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Inducement ON/OFF:");

        txtInducement.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtInducement.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtInducementKeyReleased(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Delta Pr. Temp. Cond (°C):");

        txtDeltaPressure_Temp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDeltaPressure_Temp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDeltaPressure_TempKeyReleased(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("EGR CF %:");

        txtEGR_CF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtEGR_CF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEGR_CFKeyReleased(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("DOI CF %:");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Parameter 6:");

        txtParam6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtParam6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParam6KeyReleased(evt);
            }
        });

        txtDOI_CF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDOI_CF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDOI_CFKeyReleased(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Parameter 7:");

        txtParam7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtParam7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParam7KeyReleased(evt);
            }
        });

        btnSaveEngineParam.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveEngineParam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveEngineParam.setText("Save in EP");
        btnSaveEngineParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEngineParamActionPerformed(evt);
            }
        });

        btnUpdateEngineParam.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateEngineParam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateEngineParam.setText("Update EP");
        btnUpdateEngineParam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateEngineParamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel17)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFuelCutOff, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCrankingDOI, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSecEOT, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDOI_Op, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConsumption_Error_Max)
                            .addComponent(txtDeltaPressure_SUM)
                            .addComponent(txtGlowPlug_ON)
                            .addComponent(txtVSS_CF)
                            .addComponent(txtPump_ON, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(txtDeltaPressure_AVG, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSecEOT_CF, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(277, 277, 277)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel38))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEGR_CF)
                                    .addComponent(txtInducement)
                                    .addComponent(txtMinConsumption)
                                    .addComponent(txtWI_Reset)
                                    .addComponent(txtDeltaPressure_Temp, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel63))
                                .addGap(150, 150, 150)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtParam7, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtParam6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txtDOI_CF))))
                        .addGap(104, 104, 104))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtConsumption_Error_Min)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateEngineParam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveEngineParam)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEGR_POT_Max)
                            .addComponent(txtEGR_POT_Min, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtConsumption_Error_Max, txtConsumption_Error_Min, txtCrankingDOI, txtDOI_Op, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtFuelCutOff, txtGlowPlug_ON, txtInducement, txtMinConsumption, txtPump_ON, txtSecEOT, txtSecEOT_CF, txtVSS_CF, txtWI_Reset});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDeltaPressure_Temp, txtEGR_CF, txtParam6, txtParam7});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVSS_CF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel46)
                    .addComponent(txtWI_Reset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFuelCutOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCrankingDOI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDOI_Op, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSecEOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSecEOT_CF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPump_ON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGlowPlug_ON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDeltaPressure_AVG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMinConsumption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInducement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDeltaPressure_Temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEGR_CF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDOI_CF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtParam6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtParam7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDeltaPressure_SUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEGR_POT_Max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEGR_POT_Min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConsumption_Error_Max, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConsumption_Error_Min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(btnSaveEngineParam)
                    .addComponent(btnUpdateEngineParam))
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Engine Parameters", jPanel1);

        jPanel3.setBackground(new java.awt.Color(95, 130, 206));

        errorCodeDTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "MIL Status", "Pending DTC Count", "Stored DTC Count", "MIL ON Time [min]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        errorCodeDTC.setIntercellSpacing(new java.awt.Dimension(50, 1));
        errorCodeDTC.setRowHeight(22);
        jScrollPane8.setViewportView(errorCodeDTC);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("DTC Error Codes");

        descriptionDTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stored DTC Code", "DTC Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        descriptionDTC.setIntercellSpacing(new java.awt.Dimension(25, 1));
        jScrollPane9.setViewportView(descriptionDTC);
        if (descriptionDTC.getColumnModel().getColumnCount() > 0) {
            descriptionDTC.getColumnModel().getColumn(0).setPreferredWidth(10);
            descriptionDTC.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("DTC Error Description");

        freezeFrame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "DTC code", "RPM", "EOT  [°C]", "TPS [%]", "Reductant Pr. [kPa]", "Actual EGR [%]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        freezeFrame.setIntercellSpacing(new java.awt.Dimension(35, 1));
        freezeFrame.setRowHeight(22);
        freezeFrame.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(freezeFrame);
        if (freezeFrame.getColumnModel().getColumnCount() > 0) {
            freezeFrame.getColumnModel().getColumn(0).setPreferredWidth(80);
            freezeFrame.getColumnModel().getColumn(1).setPreferredWidth(40);
            freezeFrame.getColumnModel().getColumn(2).setPreferredWidth(40);
            freezeFrame.getColumnModel().getColumn(3).setPreferredWidth(40);
            freezeFrame.getColumnModel().getColumn(4).setPreferredWidth(70);
            freezeFrame.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Freeze Frame Data");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/clear.png"))); // NOI18N
        jButton3.setText("Clear Table");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        newFreezeFrame.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "ΔP mBar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        newFreezeFrame.setIntercellSpacing(new java.awt.Dimension(60, 1));
        newFreezeFrame.setRowHeight(22);
        newFreezeFrame.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(newFreezeFrame);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/clear.png"))); // NOI18N
        jButton6.setText("Erase/Update DTC Codes");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                    .addComponent(jScrollPane10)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel31)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(221, 221, 221))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(descriptionDTC);

        jTabbedPane1.addTab("OBD Module", jPanel3);

        jPanel7.setBackground(new java.awt.Color(95, 130, 206));

        jPanel8.setBackground(new java.awt.Color(95, 154, 227));

        Diagnostics_Table.setBackground(new java.awt.Color(153, 204, 255));
        Diagnostics_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "P_Code", "Description", "Dbounce Time [mSec]", "Min ADC", "Max ADC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Diagnostics_Table.setIntercellSpacing(new java.awt.Dimension(80, 1));
        Diagnostics_Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Diagnostics_Table);
        if (Diagnostics_Table.getColumnModel().getColumnCount() > 0) {
            Diagnostics_Table.getColumnModel().getColumn(0).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(0).setPreferredWidth(5);
            Diagnostics_Table.getColumnModel().getColumn(1).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(2).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(2).setPreferredWidth(300);
            Diagnostics_Table.getColumnModel().getColumn(3).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(4).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(4).setPreferredWidth(-10);
            Diagnostics_Table.getColumnModel().getColumn(5).setResizable(false);
            Diagnostics_Table.getColumnModel().getColumn(5).setPreferredWidth(0);
        }

        btnUpdateDiagnostics.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateDiagnostics.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateDiagnostics.setText("Update Diagnostics");
        btnUpdateDiagnostics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDiagnosticsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateDiagnostics)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateDiagnostics, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Diagnostics", jPanel8);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("DTC Module", jPanel7);

        jPanel9.setBackground(new java.awt.Color(95, 130, 206));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Consumption Data");

        consumptionData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Actual Cons. [ml/hr]", "Demand Cons. [ml/hr]", "Injector / Pr. [min]", "Level Delta [%]", "Cons. Timer [min]", "Current water [%]", "Water % @zero min", "Inducement Runtime [min]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        consumptionData.setIntercellSpacing(new java.awt.Dimension(50, 1));
        consumptionData.setRowHeight(22);
        consumptionData.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(consumptionData);
        if (consumptionData.getColumnModel().getColumnCount() > 0) {
            consumptionData.getColumnModel().getColumn(7).setPreferredWidth(120);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sol. Cons. Module", jPanel9);

        jPanel22.setBackground(new java.awt.Color(95, 130, 206));
        jPanel22.setEnabled(false);

        jPanel23.setBackground(new java.awt.Color(95, 130, 206));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(95, 130, 206));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 1, true));

        checkBattery.setText("Sol. Level % :");
        checkBattery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBatteryActionPerformed(evt);
            }
        });

        checkTPS.setText("TPS :");

        checkEGR.setText("EGR :");

        checkPressureSensor.setText("Red. Pr Sensor :");

        checkDeltaPressure.setText("Delta Pressure :");

        checkEOT.setText("EOT :");

        checkEGT1.setText("Sensor 1:");
        checkEGT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEGT1ActionPerformed(evt);
            }
        });

        checkEGT2.setText("Sensor 2:");

        btnGenerateError.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerateError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGenerateError.setText("Generate Error");
        btnGenerateError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateErrorActionPerformed(evt);
            }
        });

        EOT_Intermit.setText("Intermittent");

        EGT1_Intermit.setText("Intermittent");

        EGT2_Intermit.setText("Intermittent");

        btnResetError.setBackground(new java.awt.Color(255, 255, 255));
        btnResetError.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnResetError.setText("Reset");
        btnResetError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetErrorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(checkEGT2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkEGT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkEOT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkDeltaPressure, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(checkTPS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkEGR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkPressureSensor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                .addComponent(checkBattery, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(errorEGT2)
                                    .addComponent(errorEOT)
                                    .addComponent(errorEGT1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(EGT1_Intermit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EGT2_Intermit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(EOT_Intermit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(errorEGR)
                            .addComponent(errorPressureSensor)
                            .addComponent(errorBattery, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(errorTPS)
                            .addComponent(errorDeltaPressure)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnGenerateError, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnResetError, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBattery)
                    .addComponent(errorBattery, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkTPS)
                    .addComponent(errorTPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkEGR)
                    .addComponent(errorEGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkPressureSensor)
                    .addComponent(errorPressureSensor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorDeltaPressure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkDeltaPressure))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(errorEOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkEOT))
                    .addComponent(EOT_Intermit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EGT1_Intermit)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(errorEGT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkEGT1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EGT2_Intermit)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(errorEGT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkEGT2)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerateError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkBattery, errorBattery});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkTPS, errorTPS});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkEGR, errorEGR});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkPressureSensor, errorPressureSensor});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkDeltaPressure, errorDeltaPressure});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkEOT, errorEOT});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkEGT1, errorEGT1});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {checkEGT2, errorEGT2});

        table_ADC.setBackground(new java.awt.Color(153, 204, 255));
        table_ADC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        table_ADC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "EGR [V]", "EOT [V]", "Pr.[kPa] [V]", "TPS [V]", "ΔP [V]", "ΔP SUM / 10", "ΔP Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ADC.setGridColor(new java.awt.Color(95, 130, 206));
        table_ADC.setIntercellSpacing(new java.awt.Dimension(50, 1));
        table_ADC.setRowHeight(22);
        table_ADC.getTableHeader().setReorderingAllowed(false);
        jScrollPane28.setViewportView(table_ADC);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(230, 230, 230))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Sensor Module", jPanel22);

        jPanel11.setEnabled(false);

        jPanel16.setBackground(new java.awt.Color(95, 130, 206));
        jPanel16.setEnabled(false);

        btnExportCalibration.setBackground(new java.awt.Color(255, 255, 255));
        btnExportCalibration.setText("Export Data");
        btnExportCalibration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportCalibrationActionPerformed(evt);
            }
        });

        btnClearCalibrationTable.setBackground(new java.awt.Color(255, 255, 255));
        btnClearCalibrationTable.setText("Clear Table");
        btnClearCalibrationTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearCalibrationTableActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("SUM of LUT:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("DOI  LUT :");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("EGR LUT :");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Temp. LUT :");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Pressure LUT :");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("SOI LUT :");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Engine Param :");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Cal ID :");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Total SUM :");

        DOI_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DOI_SUM.setForeground(new java.awt.Color(255, 255, 204));
        DOI_SUM.setText("00");

        EGR_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EGR_SUM.setForeground(new java.awt.Color(255, 255, 204));
        EGR_SUM.setText("00");

        Temp_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Temp_SUM.setForeground(new java.awt.Color(255, 255, 204));
        Temp_SUM.setText("00");

        Pressure_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Pressure_SUM.setForeground(new java.awt.Color(255, 255, 204));
        Pressure_SUM.setText("00");

        SOI_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SOI_SUM.setForeground(new java.awt.Color(255, 255, 204));
        SOI_SUM.setText("00");

        EngineParam_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EngineParam_SUM.setForeground(new java.awt.Color(255, 255, 204));
        EngineParam_SUM.setText("00");

        CAL_ID_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CAL_ID_SUM.setForeground(new java.awt.Color(255, 255, 204));
        CAL_ID_SUM.setText("00");

        Total_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Total_SUM.setForeground(new java.awt.Color(255, 255, 204));
        Total_SUM.setText("00");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("EGT LUT :");

        EGT_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        EGT_SUM.setForeground(new java.awt.Color(255, 255, 204));
        EGT_SUM.setText("00");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("ΔP LUT:");

        Delta_Pressure_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Delta_Pressure_SUM.setForeground(new java.awt.Color(255, 255, 204));
        Delta_Pressure_SUM.setText("00");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EGR_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(Temp_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(Pressure_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(SOI_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(EngineParam_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(CAL_ID_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(Total_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(DOI_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(EGT_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Delta_Pressure_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 648, Short.MAX_VALUE)
                .addComponent(btnClearCalibrationTable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportCalibration)
                .addGap(55, 55, 55))
        );

        jPanel16Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CAL_ID_SUM, DOI_SUM, EGR_SUM, EngineParam_SUM, Pressure_SUM, SOI_SUM, Temp_SUM, Total_SUM});

        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(DOI_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(EGR_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(Temp_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(Pressure_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(SOI_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExportCalibration)
                        .addComponent(btnClearCalibrationTable))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(EGT_SUM)))
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(Delta_Pressure_SUM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(EngineParam_SUM))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(CAL_ID_SUM))
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(Total_SUM))
                .addGap(102, 102, 102))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 466, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("LUT Verification", jPanel11);

        jPanel4.setBackground(new java.awt.Color(95, 130, 206));

        btnFlash.setBackground(new java.awt.Color(255, 255, 255));
        btnFlash.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFlash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Data Backup.png"))); // NOI18N
        btnFlash.setText("Flash LUT ");
        btnFlash.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFlash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlashActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Process Status: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Flash Status: ");

        lblProcessStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblProcessStatus.setForeground(new java.awt.Color(255, 255, 153));
        lblProcessStatus.setText("IDLE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFlash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProcessStatus)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flashProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFlash, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(flashProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblProcessStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {flashProgressBar, jLabel4});

        panelUniqueVal.setBackground(new java.awt.Color(95, 130, 206));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cal ID:");

        txtCalID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCalID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("CVN:");

        txtCVN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtCVN.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("VIN:");

        txtVIN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtVIN.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtVIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVINActionPerformed(evt);
            }
        });
        txtVIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVINKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVINKeyReleased(evt);
            }
        });

        btnFlashVIN.setBackground(new java.awt.Color(255, 255, 255));
        btnFlashVIN.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFlashVIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Data Recovery.png"))); // NOI18N
        btnFlashVIN.setText("Flash VIN");
        btnFlashVIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFlashVIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlashVINActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("VIN Length:");

        vinLength.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        vinLength.setForeground(new java.awt.Color(255, 255, 153));
        vinLength.setText("00");

        btnFlashVIN1.setBackground(new java.awt.Color(255, 255, 255));
        btnFlashVIN1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFlashVIN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/clear.png"))); // NOI18N
        btnFlashVIN1.setText("Clear VIN");
        btnFlashVIN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFlashVIN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlashVIN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUniqueValLayout = new javax.swing.GroupLayout(panelUniqueVal);
        panelUniqueVal.setLayout(panelUniqueValLayout);
        panelUniqueValLayout.setHorizontalGroup(
            panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUniqueValLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelUniqueValLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vinLength)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFlashVIN1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFlashVIN)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelUniqueValLayout.createSequentialGroup()
                        .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVIN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCVN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCalID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        panelUniqueValLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCVN, txtCalID, txtVIN});

        panelUniqueValLayout.setVerticalGroup(
            panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUniqueValLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCVN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelUniqueValLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFlashVIN)
                    .addComponent(jLabel32)
                    .addComponent(vinLength)
                    .addComponent(btnFlashVIN1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelUniqueValLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, txtCVN, txtCalID, txtVIN});

        panelLUTSelection.setBackground(new java.awt.Color(95, 130, 206));
        panelLUTSelection.setAutoscrolls(true);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Select LUT:");

        comboLUT.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        comboLUT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sample LUT", "PVPL-REAR PASSENGER", "PVPL-REAR CARGO", "AAL-REAR P&C", "AAL-FRONT CARGO SHAKTI", "MLR-REAR PASSENGER", "MLR-REAR CARGO", "BAXY-REAR PASS EXPRESS", "BAXY-FRONT CARGO BINDASS", "BAXY-REAR CARGO SUPER KING", "ABV-CHHAKADA CARGO" }));
        comboLUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLoad_LUT.setBackground(new java.awt.Color(255, 255, 255));
        btnLoad_LUT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLoad_LUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Load From File.png"))); // NOI18N
        btnLoad_LUT.setText("Load LUT");
        btnLoad_LUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoad_LUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoad_LUTActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Selected LUT: ");

        checkboxOnlineData.setText("Stop Online Data");
        checkboxOnlineData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkboxOnlineData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxOnlineDataActionPerformed(evt);
            }
        });

        btnReadLUT.setBackground(new java.awt.Color(255, 255, 255));
        btnReadLUT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReadLUT.setText("Read");
        btnReadLUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReadLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadLUTActionPerformed(evt);
            }
        });

        btnUpdateReadLUT.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdateReadLUT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateReadLUT.setText("Update");
        btnUpdateReadLUT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateReadLUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateReadLUTActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("LUT SUM :");

        LUT_sum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        LUT_sum.setForeground(new java.awt.Color(255, 255, 153));
        LUT_sum.setText("00");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Default SUM :");

        Default_SUM.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Default_SUM.setForeground(new java.awt.Color(255, 255, 153));
        Default_SUM.setText("00");

        lutName1.setEditable(false);
        lutName1.setColumns(25);
        lutName1.setLineWrap(true);
        lutName1.setRows(5);
        lutName1.setText("LUT");
        lutName1.setWrapStyleWord(true);
        lutName1.setAutoscrolls(false);
        jScrollPane2.setViewportView(lutName1);

        javax.swing.GroupLayout panelLUTSelectionLayout = new javax.swing.GroupLayout(panelLUTSelection);
        panelLUTSelection.setLayout(panelLUTSelectionLayout);
        panelLUTSelectionLayout.setHorizontalGroup(
            panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboLUT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkboxOnlineData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                        .addComponent(btnReadLUT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateReadLUT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LUT_sum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Default_SUM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                        .addGroup(panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(btnLoad_LUT)
                            .addComponent(jLabel33))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLUTSelectionLayout.setVerticalGroup(
            panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLUTSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboLUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoad_LUT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(Default_SUM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(LUT_sum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkboxOnlineData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLUTSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReadLUT)
                    .addComponent(btnUpdateReadLUT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(95, 130, 206));

        jButton2.setBackground(new java.awt.Color(95, 130, 206));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(95, 130, 206));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("License Valid Upto :");

        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Valid_Date");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Current Date & Time :");

        lblCurDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCurDate.setForeground(new java.awt.Color(255, 255, 255));
        lblCurDate.setText("Current_Date");

        lblCurTime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCurTime.setForeground(new java.awt.Color(255, 255, 255));
        lblCurTime.setText("Current_Time");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("EnDoc [V2.1.1] - OBD IIB LPI");

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Log out");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurDate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurTime, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblDate)
                    .addComponent(jLabel16)
                    .addComponent(lblCurDate)
                    .addComponent(lblCurTime)
                    .addComponent(jButton4))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(95, 130, 206));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("ECU Flashing Count :");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Session :");

        sessionCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sessionCount.setForeground(new java.awt.Color(255, 255, 255));
        sessionCount.setText("00");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Total :");

        totalFlashCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalFlashCount.setForeground(new java.awt.Color(255, 255, 255));
        totalFlashCount.setText("00");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sessionCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalFlashCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(sessionCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(totalFlashCount))
                .addContainerGap())
        );

        panel_ADC.setBackground(new java.awt.Color(95, 130, 206));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Role: ");

        lblLoginRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoginRole.setForeground(new java.awt.Color(255, 255, 255));
        lblLoginRole.setText("LOGIN_ROLE");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Build:");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("03122025");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("F/W Version:");

        lbl_FWversion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_FWversion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_FWversion.setText("Version");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("DB Version:");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("V3.4.7");

        javax.swing.GroupLayout panel_ADCLayout = new javax.swing.GroupLayout(panel_ADC);
        panel_ADC.setLayout(panel_ADCLayout);
        panel_ADCLayout.setHorizontalGroup(
            panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ADCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ADCLayout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoginRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_ADCLayout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_ADCLayout.createSequentialGroup()
                        .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ADCLayout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
                            .addComponent(lbl_FWversion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel_ADCLayout.setVerticalGroup(
            panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ADCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(lblLoginRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ADCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(lbl_FWversion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(95, 130, 206));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Erase DTC Codes");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("DTC Count :");

        lblDTCCount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDTCCount.setForeground(new java.awt.Color(255, 255, 255));
        lblDTCCount.setText("0");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Message 1 : ");

        display_msg.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        display_msg.setForeground(new java.awt.Color(255, 255, 255));
        display_msg.setText("Display Message Read Here");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Water Level: ");

        waterLevelProgress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        waterLevelPercent.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        waterLevelPercent.setForeground(new java.awt.Color(255, 255, 255));
        waterLevelPercent.setText("0%");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Message 2 : ");

        display_msg1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        display_msg1.setForeground(new java.awt.Color(255, 255, 255));
        display_msg1.setText("Display Message Read Here");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDTCCount, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(display_msg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(display_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(waterLevelProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(waterLevelPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(display_msg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(display_msg1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(waterLevelProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(waterLevelPercent)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(lblDTCCount))
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel36, waterLevelProgress});

        ;

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/Save.png"))); // NOI18N
        jMenuItem1.setText("Save Online Data");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/clear.png"))); // NOI18N
        jMenuItem2.setText("Clear Online Data");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        btnRefreshApplication.setText("Edit");

        jMenuItem4.setText("Tecsage Login");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        btnRefreshApplication.add(jMenuItem4);

        jMenu2.setText("Edit Data Frequency");

        timergroup.add(timer_100_Radio);
        timer_100_Radio.setText("100ms");
        timer_100_Radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timer_100_RadioActionPerformed(evt);
            }
        });
        jMenu2.add(timer_100_Radio);

        timergroup.add(timer_500_Radio);
        timer_500_Radio.setText("500ms");
        jMenu2.add(timer_500_Radio);

        timergroup.add(timer_1000_Radio);
        timer_1000_Radio.setText("1000ms");
        jMenu2.add(timer_1000_Radio);

        btnRefreshApplication.add(jMenu2);

        checkADCtoVoltage.setSelected(true);
        checkADCtoVoltage.setText("Convert ADC to Voltage");
        checkADCtoVoltage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkADCtoVoltageActionPerformed(evt);
            }
        });
        btnRefreshApplication.add(checkADCtoVoltage);

        jMenuBar1.add(btnRefreshApplication);

        jMenu3.setText("License");

        jMenuItem3.setText("Details");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem5.setText("Update License");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Help");

        jMenuItem17.setText("How to Start?");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenu6.setText("Communication");

        jMenuItem8.setText("How to Connect?");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem9.setText("How to Disconnect?");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenu5.add(jMenu6);

        jMenu7.setText("LUT Module");

        jMenuItem10.setText("How to Load LUT?");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem10);

        jMenuItem11.setText("How to Read LUT from ECU?");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuItem12.setText("How to Edit/Update LUT?");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem13.setText("How to Save LUT?");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuItem14.setText("How to open External LUT?");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenuItem15.setText("How To Flash LUT?");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem15);

        jMenu5.add(jMenu7);

        jMenu8.setText("VIN Flashing");

        jMenuItem16.setText("How to Flash VIN?");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenu5.add(jMenu8);

        jMenu4.setText("ADC Module");

        jMenuItem6.setText("How to Generate Error?");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenu5.add(jMenu4);
        jMenu5.add(jSeparator1);

        jMenuItem7.setText("About");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLUTSelection, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCOM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_ADC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelUniqueVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLUTSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelUniqueVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_ADC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboCOMPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboCOMPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        try {
            addCOMPorts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_comboCOMPopupMenuWillBecomeVisible

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        serialConnection serial_Obj = new serialConnection();

        if (comboCOM.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Please connect the cable!");
        } else if (comboCOM.getSelectedItem().toString().contains("Item")) {
            JOptionPane.showMessageDialog(null, "Please Select COM Port!");
        } else {

            try {
                serial_Obj.serialCon(comboCOM.getSelectedIndex(), comboCOM.getSelectedItem().toString());

                portVar = serial_Obj.getSerial();
                if (portVar.isOpen()) {
                    lblConStatus.setText("Connected");
                    lblConStatus.setForeground(Color.green);
                    btnDisconnect.setEnabled(true);
                    btnConnect.setEnabled(false);
                    Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);
                } else {
                    lblConStatus.setText("Disconnected");
                    lblConStatus.setForeground(Color.black);
                }
                ScrollTOBottom(btnConnect.getText());
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null, e);
                btnConnect.setEnabled(false);
            }
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnFlashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlashActionPerformed
        lblProcessStatus.setText("Verifying...");
        
        if(( Integer.parseInt(Default_SUM.getText()) == Integer.parseInt(Total_SUM.getText())) ||  Integer.parseInt(Default_SUM.getText()) == 00  )
            
        {
        
        int response = JOptionPane.showConfirmDialog(null, "<html> Do you want to flash the ECU? <br><b> LUT: </b> '" + lutName1.getText() + "' </html>", "Flash Auth", JOptionPane.OK_CANCEL_OPTION);

        if (response == 0) {

            lblProcessStatus.setText("CHECKING ENVIRONMENT");
            int flashCount = Integer.parseInt(sessionCount.getText());
            if (portVar != null) {

                if (lutName1.getText().equals("LUT")) {
                    JOptionPane.showMessageDialog(null, "Please Load the LUT file before flashing!");
                    flashProgressBar.setValue(0);
                } else {
                    if (txtCalID.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Empty Cal-ID can not be flashed! ");
                    } else {
                        try {
                            lblProcessStatus.setText("COMMUNICATING");
                            flashProgressBar.setValue(10);
                            DefaultTableModel table_OD_model = (DefaultTableModel) table_OD.getModel();
                            int beforeRowCount = table_OD_model.getRowCount();
                            Thread.sleep(1200);
                            int afterRowCount = table_OD_model.getRowCount();

                            // if (beforeRowCount == afterRowCount)
                            {
                                //     JOptionPane.showMessageDialog(null, "ECU seems to be Not Connected");
                                //} else {

                                int[] Engine_Params = new int[23];

                                Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
                                Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
                                Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
                                Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
                                Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
                                Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
                                Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
                                Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
                                Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
                                Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
                                Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
                                Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
                                Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
                                Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
                                Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
                                Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
                                Engine_Params[17] = Integer.parseInt(txtInducement.getText());
                                Engine_Params[18] = Integer.parseInt(txtDeltaPressure_Temp.getText());
                                Engine_Params[19] = Integer.parseInt(txtEGR_CF.getText());
                                Engine_Params[20] = Integer.parseInt(txtDOI_CF.getText());
                                Engine_Params[21] = Integer.parseInt(txtParam6.getText());
                                Engine_Params[22] = Integer.parseInt(txtParam7.getText());
                                flashProgressBar.setValue(30);

                                if (lbl_ComMode.getText().equals("UART")) {
                                    Flashing flash_Obj = new Flashing();
                                    flash_Obj.flash_module(portVar, table_DOI, table_EGR, table_SOI, txtCalID.getText(), txtVIN.getText(), Engine_Params, table_Temperature, table_Prerssure, flashProgressBar, lblProcessStatus, table_ExhaustTemperature, table_DeltaPrerssure, Diagnostics_Table, Integer.parseInt(String.valueOf(EGR_SUM.getText())), Integer.parseInt(String.valueOf(EngineParam_SUM.getText())), totalFlashCount);

                                } else if (lbl_ComMode.getText().equals("CAN")) {
                                    CAN_Flashing can_flash_Obj = new CAN_Flashing();
                                    can_flash_Obj.flash_module(portVar, table_DOI, table_EGR, table_SOI, txtCalID.getText(), txtVIN.getText(), Engine_Params, table_Temperature, table_Prerssure, flashProgressBar, lblProcessStatus, table_ExhaustTemperature, table_DeltaPrerssure, Diagnostics_Table, Integer.parseInt(String.valueOf(EGR_SUM.getText())), Integer.parseInt(String.valueOf(EngineParam_SUM.getText())), totalFlashCount);

                                }
                                if (flashCount < 9) {
                                    sessionCount.setText("0" + String.valueOf(flashCount + 1));
                                } else {
                                    sessionCount.setText(String.valueOf(flashCount + 1));
                                }

                            }

                        } catch (InterruptedException ex) {
                            Logger.getLogger(index.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Connect UART before Flashing!");
            }
            flashProgressBar.setValue(0);
            lblProcessStatus.setText("IDLE");
        } else {

        }
        
        } else{
                JOptionPane.showMessageDialog(null, "MAP file is not validated!");
                }
    }//GEN-LAST:event_btnFlashActionPerformed

    private void btnFlashVINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlashVINActionPerformed
        // TODO add your handling code here:
        String vin = txtVIN.getText();
        if (vin.length() == 17) {
            try {
                lblProcessStatus.setText("Flashing VIN");

                DefaultTableModel table_OD_model = (DefaultTableModel) table_OD.getModel();
                int beforeRowCount = table_OD_model.getRowCount();
                Thread.sleep(300);
                int afterRowCount = table_OD_model.getRowCount();

                if (beforeRowCount == afterRowCount) {
                    JOptionPane.showMessageDialog(null, "ECU seems to be not Connected");
                } else {
                    VIN_flashing vinFlash_Obj = new VIN_flashing();
                    vinFlash_Obj.main(txtVIN.getText(), table_OD, txtVIN, portVar, lblProcessStatus);
                }
                vinLength.setText("00");
                vinLength.setForeground(Color.YELLOW);
                lblProcessStatus.setText("IDLE");

            } catch (InterruptedException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (vin.length() == 0) {
            JOptionPane.showMessageDialog(null, "Empty VIN Cannot be flashed!");
        } else {
            JOptionPane.showMessageDialog(null, "VIN Length Mismatched!");
        }

        Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);

    }//GEN-LAST:event_btnFlashVINActionPerformed

    private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisconnectActionPerformed
        // TODO add your handling code here:
        try {
            portVar.closePort();
            lblConStatus.setText(("Disconnected"));
            lblConStatus.setForeground(Color.YELLOW);
            btnDisconnect.setEnabled(false);
            btnConnect.setEnabled(true);
            ScrollTOBottom(btnDisconnect.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Connect before Disconnecting!");
        }
    }//GEN-LAST:event_btnDisconnectActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableClear_OD = (DefaultTableModel) table_OD.getModel();
        if (tableClear_OD.getRowCount() > 0) {
            int result = JOptionPane.showConfirmDialog(null, "Do you really want to clear the Table?", "Select", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                tableClear_OD.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(null, "Operation Cancelled!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Table is already empty!");
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            // TODO add your handling code here:
//            Export_OnlineData ond = new Export_OnlineData();
//            ond.save_OnlineData(table_OD, lblCurDate, lblCurTime, lutName1, txtCalID, txtCVN, txtVIN, lblDataFrequency, LUT_sum);
            boolean isADC = true;

            if (checkADCtoVoltage.isSelected()) {
                isADC = false;
            }

            if (txtCalID.getText().equals("") | txtCVN.getText().equals("") | txtVIN.getText().equals("")) {
                int a = JOptionPane.showConfirmDialog(null, "CAL ID, CVN or VIN is Missing. Do you wish to save data without them?", "Choose", JOptionPane.YES_NO_OPTION);

                if ((a == 0)) {
                    Export_OnlineData2 ond2 = new Export_OnlineData2();
                    ond2.save_OnlineData(table_OD, lblCurDate, lblCurTime, lutName1, txtCalID, txtCVN, txtVIN, lblDataFrequency, LUT_sum, lbl_FWversion, isADC);

                } else {
                    JOptionPane.showMessageDialog(null, "Please Read the ECU!", "Choose", JOptionPane.OK_OPTION);
                }
            } else {
                Export_OnlineData2 ond2 = new Export_OnlineData2();
                ond2.save_OnlineData(table_OD, lblCurDate, lblCurTime, lutName1, txtCalID, txtCVN, txtVIN, lblDataFrequency, LUT_sum, lbl_FWversion, isADC);

            }

        } catch (IOException ex) {
            Logger.getLogger(index.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtVINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVINActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtVINActionPerformed

    private void txtVINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVINKeyPressed
        // TODO add your handling code here:
        String barcode = txtVIN.getText();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtVIN.setText("");
            txtVIN.setText(barcode);
            System.out.println(barcode);
        }

    }//GEN-LAST:event_txtVINKeyPressed

    private void btnFlashVIN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlashVIN1ActionPerformed
        // TODO add your handling code here:
        txtVIN.setText("");
        vinLength.setText("00");
        vinLength.setForeground(Color.YELLOW);
        btnFlashVIN.setEnabled(false);
    }//GEN-LAST:event_btnFlashVIN1ActionPerformed

    private void txtVINKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVINKeyReleased
        // TODO add your handling code here:
        vinLength();
    }//GEN-LAST:event_txtVINKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelDTC = (DefaultTableModel) descriptionDTC.getModel();
        modelDTC.setRowCount(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnUpdateLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateLUTActionPerformed
        // TODO add your handling code here:

        int[] Engine_Params = new int[18];

        Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
        Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
        Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
        Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
        Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
        Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
        Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
        Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
        Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
        Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
        Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
        Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
        Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
        Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
        Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
        Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
        Engine_Params[17] = Integer.parseInt(txtInducement.getText());

        UpdateLUT updateVar = new UpdateLUT();
        updateVar.update_module(table_DOI, table_EGR, table_SOI, txtCalID.getText(), txtVIN.getText(), Engine_Params, table_Temperature, table_Prerssure);

        //Method to Calculate SUM
        CalulateSUM sum_obj = new CalulateSUM();
        sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);

    }//GEN-LAST:event_btnUpdateLUTActionPerformed

    private void btnSaveLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveLUTActionPerformed
        // TODO add your handling code here:
        int[] Engine_Params = new int[23];

        Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
        Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
        Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
        Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
        Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
        Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
        Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
        Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
        Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
        Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
        Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
        Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
        Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
        Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
        Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
        Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
        Engine_Params[17] = Integer.parseInt(txtInducement.getText());
        Engine_Params[18] = Integer.parseInt(txtDeltaPressure_Temp.getText());
        Engine_Params[19] = Integer.parseInt(txtEGR_CF.getText());
        Engine_Params[20] = Integer.parseInt(txtDOI_CF.getText());
        Engine_Params[21] = Integer.parseInt(txtParam6.getText());
        Engine_Params[22] = Integer.parseInt(txtParam7.getText());

//        saveLUT save_Var = new saveLUT();
//        save_Var.save_LUT(table_DOI, table_EGR, table_Temperature, table_Prerssure, table_SOI, Engine_Params, txtCalID.getText(), table_ExhaustTemperature, table_DeltaPrerssure);
        saveLUT2 save_Var2 = new saveLUT2();
        save_Var2.save_LUT(table_DOI, table_EGR, table_Temperature, table_Prerssure, table_SOI, Engine_Params, txtCalID.getText(), table_ExhaustTemperature, table_DeltaPrerssure);

    }//GEN-LAST:event_btnSaveLUTActionPerformed

    private void btnExportCalibrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportCalibrationActionPerformed
        // TODO add your handling code here:        
//        ExportCalibration export = new ExportCalibration();
//        export.exportData(table_DOI_calibration);
    }//GEN-LAST:event_btnExportCalibrationActionPerformed

    private void btnClearCalibrationTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCalibrationTableActionPerformed
        // TODO add your handling code here:
//        DefaultTableModel Calibration_model = (DefaultTableModel) table_DOI_calibration.getModel();
//        Calibration_model.setRowCount(0);
    }//GEN-LAST:event_btnClearCalibrationTableActionPerformed

    private void btnLoad_LUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoad_LUTActionPerformed
        //Filling Default LUT
        lutName1.setText(comboLUT.getSelectedItem().toString());
        sum_Verification verify = new sum_Verification();

        int index = comboLUT.getSelectedIndex();
        txtCVN.setText("");
        txtVIN.setText("");
        fetchLUT ft = new fetchLUT();

        if (index == 0) {
            Sample Sample_P = new Sample();
            Sample_P.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Sample_P.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Sample LUT", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Sample", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 1) {
            PVPL_Passenger PVPL_P = new PVPL_Passenger();
            PVPL_P.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            // PVPL_P.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("PVPL Passenger", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("PVPL Passenger", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 2) {
            PVPL_Cargo PVPL_C = new PVPL_Cargo();
            PVPL_C.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //PVPL_C.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("PVPL Cargo", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("PVPL Cargo", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 3) {
            Atul_Passenger Atul_P = new Atul_Passenger();
            Atul_P.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Atul_P.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Atul Passenger", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Atul Passenger", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 4) {
            Atul_shakti Atul_Shakti = new Atul_shakti();
            Atul_Shakti.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Atul_Shakti.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Atul Shakti", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Atul Cargo", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 5) {
            MLR_Passenger MLR_P = new MLR_Passenger();
            MLR_P.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //MLR_P.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("MLR Passenger", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("MLR Passenger", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 6) {
            MLR_Cargo MLR_C = new MLR_Cargo();
            MLR_C.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //MLR_C.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("MLR Cargo", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("MLR Cargo", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 7) {
            Baxy_Passenger Baxy_P = new Baxy_Passenger();
            Baxy_P.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Baxy_P.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Baxy Passenger", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Baxy Passenger", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 8) {
            Baxy_Cargo_FE Baxy_C = new Baxy_Cargo_FE();
            Baxy_C.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Baxy_C.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Baxy Cargo", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Baxy Cargo", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 9) {
            Baxy_Superking Baxy_C = new Baxy_Superking();
            Baxy_C.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Baxy_C.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("Baxy Superking", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("Baxy Superking", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } else if (index == 10) {
            
            ABV_Chhakada ABV = new ABV_Chhakada();
            ABV.fillTables(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, txtCalID, table_ExhaustTemperature, table_DeltaPrerssure);
            //Baxy_C.Engine_Params(txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            ft.fetchEngineParam("ABV-CHHAKADA CARGO", txtVSS_CF, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtDeltaPressure_AVG, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7);
            img.setIcon("ABV-CHHAKADA", jButton2);
            verify.setDefaultSUM(lutName1, Default_SUM);

        } 

        //Highlighter cells
//        HighLighter table_Highlight = new HighLighter();
//        table_Highlight.HighlightCell(table_DOI, table_EGR, table_SOI, table_Temperature, table_Prerssure, table_ExhaustTemperature, table_DeltaPrerssure);
        //Method to Calculate SUM
        int[] Engine_Params = new int[19];

        Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
        Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
        Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
        Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
        Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
        Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
        Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
        Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
        Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
        Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
        Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
        Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
        Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
        Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
        Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
        Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
        Engine_Params[17] = Integer.parseInt(txtInducement.getText());
        Engine_Params[18] = Integer.parseInt(txtDeltaPressure_Temp.getText());

        CalulateSUM sum_obj = new CalulateSUM();
        sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);
        
        JOptionPane.showMessageDialog(null, " '"+lutName1.getText()+"' was loaded sucessfully!   ");
        
    }//GEN-LAST:event_btnLoad_LUTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DTC_Engage erase = new DTC_Engage();
        erase.eraseDTC();
        DefaultTableModel detc = (DefaultTableModel) descriptionDTC.getModel();
        detc.setRowCount(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOpenLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenLUTActionPerformed
        // TODO add your handling code here:
        openLUT open = new openLUT();
        open.openSavedLUT(table_DOI, table_EGR, table_Temperature, table_Prerssure, table_SOI, txtCalID, txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7, table_ExhaustTemperature, table_DeltaPrerssure, lutName1);
        
          int[] Engine_Params = new int[19];

        Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
        Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
        Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
        Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
        Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
        Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
        Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
        Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
        Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
        Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
        Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
        Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
        Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
        Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
        Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
        Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
        Engine_Params[17] = Integer.parseInt(txtInducement.getText());
        Engine_Params[18] = Integer.parseInt(txtDeltaPressure_Temp.getText());
        
        CalulateSUM sum_obj = new CalulateSUM();
        sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);

    }//GEN-LAST:event_btnOpenLUTActionPerformed

    private void checkboxOnlineDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxOnlineDataActionPerformed
        StopOnlineData();
        if (checkboxOnlineData.isSelected()) {
            ScrollTOBottom("Disconnect");
        } else {
            ScrollTOBottom("Scroll");
        }
        txtCVN.setText("");
        txtVIN.setText("");
        vinLength();
    }//GEN-LAST:event_checkboxOnlineDataActionPerformed

    private void btnReadLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadLUTActionPerformed

        if (lbl_ComMode.getText().equals("CAN")) {
            try {
                // TODO add your handling code here:
                Thread.sleep(300);

            } catch (InterruptedException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            // TODO add your handling code here:
            OutputStream Serial = portVar.getOutputStream();
            portVar.flushIOBuffers();
            lblProcessStatus.setText("Reading Data...");
            if (checkboxOnlineData.isSelected()) {
                try {

                    Serial.write(0xAA);
                    Serial.write(0x56);
                    Serial.write(0x23);
                    Thread.sleep(6900);

                    btnFlash.setEnabled(false);
                    btnUpdateReadLUT.setEnabled(false);
                    btnReadLUT.setEnabled(true);

                    //btnFlash.setBackground(Color.red);
                    //btnFlash.setForeground(Color.white);
                } catch (IOException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (InterruptedException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Serial.flush();
                    Serial.write(0xAB);
                    Serial.write(0xAA);
                    Serial.write(0x56);

                    btnFlash.setEnabled(true);
                    btnReadLUT.setEnabled(false);

//                    btnFlash.setBackground(Color.WHITE);
//                    btnFlash.setForeground(Color.black);
                } catch (IOException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt);

            btnReadLUT.setEnabled(false);
            try {
                Thread.sleep(500);
                btnUpdateReadLUT.setEnabled(true);

            } catch (InterruptedException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            lblProcessStatus.setText("Click Update...");
        } else {
            try {
                // TODO add your handling code here:
                Thread.sleep(300);

            } catch (InterruptedException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            // TODO add your handling code here:
            OutputStream Serial = portVar.getOutputStream();

            if (checkboxOnlineData.isSelected()) {
                try {
                    Serial.flush();
                    Serial.flush();
                    Thread.sleep(100);
                    Serial.flush();
                    Serial.flush();

                    btnFlash.setEnabled(false);
                    btnUpdateReadLUT.setEnabled(false);
                    btnReadLUT.setEnabled(true);

                    //btnFlash.setBackground(Color.red);
                    //btnFlash.setForeground(Color.white);
                } catch (IOException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);

                } catch (InterruptedException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    Serial.flush();
                    Serial.write(0xAB);
                    Serial.write(0xAA);
                    Serial.write(0x56);

                    btnFlash.setEnabled(true);
                    btnReadLUT.setEnabled(false);

//                    btnFlash.setBackground(Color.WHITE);
//                    btnFlash.setForeground(Color.black);
                } catch (IOException ex) {
                    Logger.getLogger(index.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt);

            btnReadLUT.setEnabled(false);
            try {
                Thread.sleep(500);
                btnUpdateReadLUT.setEnabled(true);

            } catch (InterruptedException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnReadLUTActionPerformed

    private void btnUpdateReadLUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateReadLUTActionPerformed

        if (lbl_ComMode.getText().equals("CAN")) {
            // TODO add your handling code here:
            //Read Data
            lblProcessStatus.setText("Updating....");
            Default_SUM.setText("00");
            ReadLUT2 read = new ReadLUT2();
            //read.Read_ECU(portVar, table_DOI, table_SOI, txtCalID, txtVIN, table_EGR, txtCVN, txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, table_Temperature, table_Prerssure, lutName1, checkboxOnlineData, portVar, btnReadLUT, btnUpdateReadLUT, btnFlash, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, table_ExhaustTemperature, table_DeltaPrerssure, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7, lblDataFrequency, checkADCtoVoltage);
            read.Read_ECU(portVar, table_DOI, table_SOI, txtCalID, txtVIN, table_EGR, txtCVN, txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, table_Temperature, table_Prerssure, lutName1, checkboxOnlineData, portVar, btnReadLUT, btnUpdateReadLUT, btnFlash, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, table_ExhaustTemperature, table_DeltaPrerssure, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);

            lblProcessStatus.setText("Updated!");

            //Method to Calculate SUM
            int[] Engine_Params = new int[18];

            Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
            Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
            Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
            Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
            Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
            Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
            Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
            Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
            Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
            Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
            Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
            Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
            Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
            Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
            Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
            Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
            Engine_Params[17] = Integer.parseInt(txtInducement.getText());

            CalulateSUM sum_obj = new CalulateSUM();
            sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);

            if (lutName1.getText().equals("Sample LUT")) {
                Default_SUM.setText("106428");
            } else if (lutName1.getText().equals("PVPL Passenger")) {
                Default_SUM.setText("768595");
            } else if (lutName1.getText().equals("PVPL Cargo")) {
                Default_SUM.setText("724257");
            } else if (lutName1.getText().equals("Atul Cargo/Passenger RE")) {
                Default_SUM.setText("767758");
            } else if (lutName1.getText().equals("Atul Cargo FE")) {
                Default_SUM.setText("727321");
            } else if (lutName1.getText().equals("MLR Passenger")) {
                Default_SUM.setText("767649");
            } else if (lutName1.getText().equals("MLR Cargo")) {
                Default_SUM.setText("724331");
            } else if (lutName1.getText().equals("Baxy Passenger RE")) {
                Default_SUM.setText("768142");
            } else if (lutName1.getText().equals("Baxy Cargo FE")) {
                Default_SUM.setText("704825");
            } else if (lutName1.getText().equals("Unknown LUT")) {
                Default_SUM.setText("00");
            }

            vinLength();
            portVar.flushIOBuffers();
            OutputStream Serial = portVar.getOutputStream();
            checkboxOnlineData.setSelected(false);
            if (checkboxOnlineData.isSelected()) {
                ScrollTOBottom("Disconnect");
            } else {
                ScrollTOBottom("Scroll");
            }
            try {
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                btnFlash.setEnabled(true);

            } catch (IOException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);
            btnUpdateReadLUT.setEnabled(false);

            if (checkboxOnlineData.isSelected()) {
                ScrollTOBottom("Disconnect");
            } else {
                ScrollTOBottom("Scroll");
            }

            if (lblLoginRole.getText().equals("User")) {
                btnFlashVIN.setEnabled(false);
            }

            Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);
            lblProcessStatus.setText("IDLE");

        } else {

// TODO add your handling code here:
            //Read Data
            Default_SUM.setText("00");
            ReadLUT read = new ReadLUT();
            read.Read_ECU(portVar, table_DOI, table_SOI, txtCalID, txtVIN, table_EGR, txtCVN, txtDeltaPressure_AVG, txtFuelCutOff, txtCrankingDOI, txtDOI_Op, txtSecEOT, txtSecEOT_CF, txtPump_ON, txtGlowPlug_ON, txtVSS_CF, txtDeltaPressure_SUM, txtEGR_POT_Max, txtEGR_POT_Min, txtConsumption_Error_Max, txtConsumption_Error_Min, txtWI_Reset, txtMinConsumption, txtInducement, table_Temperature, table_Prerssure, lutName1, checkboxOnlineData, portVar, btnReadLUT, btnUpdateReadLUT, btnFlash, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, table_ExhaustTemperature, table_DeltaPrerssure, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, txtDeltaPressure_Temp, txtEGR_CF, txtDOI_CF, txtParam6, txtParam7, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);

            //Method to Calculate SUM
            int[] Engine_Params = new int[18];

            Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
            Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
            Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
            Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
            Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
            Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
            Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
            Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
            Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
            Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
            Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
            Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
            Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
            Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
            Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
            Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
            Engine_Params[17] = Integer.parseInt(txtInducement.getText());

            CalulateSUM sum_obj = new CalulateSUM();
            sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);

            if (lutName1.getText().equals("Sample LUT")) {
                Default_SUM.setText("106428");
            } else if (lutName1.getText().equals("PVPL Passenger")) {
                Default_SUM.setText("768595");
            } else if (lutName1.getText().equals("PVPL Cargo")) {
                Default_SUM.setText("724257");
            } else if (lutName1.getText().equals("Atul Cargo/Passenger RE")) {
                Default_SUM.setText("767758");
            } else if (lutName1.getText().equals("Atul Cargo FE")) {
                Default_SUM.setText("727321");
            } else if (lutName1.getText().equals("MLR Passenger")) {
                Default_SUM.setText("767649");
            } else if (lutName1.getText().equals("MLR Cargo")) {
                Default_SUM.setText("724331");
            } else if (lutName1.getText().equals("Baxy Passenger RE")) {
                Default_SUM.setText("768142");
            } else if (lutName1.getText().equals("Baxy Cargo FE")) {
                Default_SUM.setText("704825");
            } else if (lutName1.getText().equals("Unknown LUT")) {
                Default_SUM.setText("00");
            }

            vinLength();

            OutputStream Serial = portVar.getOutputStream();
            checkboxOnlineData.setSelected(false);
            if (checkboxOnlineData.isSelected()) {
                ScrollTOBottom("Disconnect");
            } else {
                ScrollTOBottom("Scroll");
            }
            try {
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                Serial.flush();
                btnFlash.setEnabled(true);

            } catch (IOException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);
            btnUpdateReadLUT.setEnabled(false);

            if (checkboxOnlineData.isSelected()) {
                ScrollTOBottom("Disconnect");
            } else {
                ScrollTOBottom("Scroll");
            }

            if (lblLoginRole.getText().equals("User")) {
                btnFlashVIN.setEnabled(false);
            }
        }

    }//GEN-LAST:event_btnUpdateReadLUTActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        //new License().setVisible(true);
        new EnDoc_License(jLabel18.getText()).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void txtDeltaPressure_AVGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeltaPressure_AVGKeyReleased
        // TODO add your handling code here:
        if ((txtDeltaPressure_AVG.getText()).equals("")) {
            txtDeltaPressure_AVG.setText("0");
        } else {
            if (txtDeltaPressure_AVG.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtDeltaPressure_AVG.setText(txtDeltaPressure_AVG.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtDeltaPressure_AVGKeyReleased

    private void txtFuelCutOffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuelCutOffKeyReleased
        // TODO add your handling code here:
        if ((txtFuelCutOff.getText()).equals("")) {
            txtFuelCutOff.setText("0");
        } else {
            if (txtFuelCutOff.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtFuelCutOff.setText(txtFuelCutOff.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtFuelCutOffKeyReleased

    private void txtCrankingDOIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCrankingDOIKeyReleased
        // TODO add your handling code here:
        if ((txtCrankingDOI.getText()).equals("")) {
            txtCrankingDOI.setText("0");
        } else {
            if (txtCrankingDOI.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtCrankingDOI.setText(txtCrankingDOI.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtCrankingDOIKeyReleased

    private void txtDOI_OpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOI_OpKeyReleased
        // TODO add your handling code here:
        if ((txtDOI_Op.getText()).equals("")) {
            txtDOI_Op.setText("0");
        } else {
            if (txtDOI_Op.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtDOI_Op.setText(txtDOI_Op.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtDOI_OpKeyReleased

    private void txtSecEOTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSecEOTKeyReleased
        // TODO add your handling code here:
        if ((txtSecEOT.getText()).equals("")) {
            txtSecEOT.setText("0");
        } else {
            if (txtSecEOT.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtSecEOT.setText(txtSecEOT.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtSecEOTKeyReleased

    private void txtSecEOT_CFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSecEOT_CFKeyReleased
        // TODO add your handling code here:
        if ((txtSecEOT_CF.getText()).equals("")) {
            txtSecEOT_CF.setText("0");
        } else {
            if (txtSecEOT_CF.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtSecEOT_CF.setText(txtSecEOT_CF.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtSecEOT_CFKeyReleased

    private void txtPump_ONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPump_ONKeyReleased
        // TODO add your handling code here:
        if ((txtPump_ON.getText()).equals("")) {
            txtPump_ON.setText("0");
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtPump_ONKeyReleased

    private void txtGlowPlug_ONKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGlowPlug_ONKeyReleased
        // TODO add your handling code here:
        if ((txtGlowPlug_ON.getText()).equals("")) {
            txtGlowPlug_ON.setText("0");
        } else {
            if (txtGlowPlug_ON.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtGlowPlug_ON.setText(txtGlowPlug_ON.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtGlowPlug_ONKeyReleased

    private void txtVSS_CFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVSS_CFKeyReleased
        // TODO add your handling code here:
        if ((txtVSS_CF.getText()).equals("")) {
            txtVSS_CF.setText("0");
        } else {
            if (txtVSS_CF.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtVSS_CF.setText(txtVSS_CF.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtVSS_CFKeyReleased

    private void txtDeltaPressure_SUMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeltaPressure_SUMKeyReleased
        // TODO add your handling code here:
        if ((txtDeltaPressure_SUM.getText()).equals("")) {
            txtDeltaPressure_SUM.setText("0");
        } else {
            if (txtDeltaPressure_SUM.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtDeltaPressure_SUM.setText(txtDeltaPressure_SUM.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtDeltaPressure_SUMKeyReleased

    private void txtEGR_POT_MaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEGR_POT_MaxKeyReleased
        // TODO add your handling code here:
        if ((txtEGR_POT_Max.getText()).equals("")) {
            txtEGR_POT_Max.setText("0");
        } else {
            if (txtEGR_POT_Max.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtEGR_POT_Max.setText(txtEGR_POT_Max.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtEGR_POT_MaxKeyReleased

    private void txtEGR_POT_MinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEGR_POT_MinKeyReleased
        // TODO add your handling code here:
        if ((txtEGR_POT_Min.getText()).equals("")) {
            txtEGR_POT_Min.setText("0");
        } else {
            if (txtEGR_POT_Min.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtEGR_POT_Min.setText(txtEGR_POT_Min.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtEGR_POT_MinKeyReleased

    private void txtConsumption_Error_MaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsumption_Error_MaxKeyReleased
        // TODO add your handling code here:
        if ((txtConsumption_Error_Max.getText()).equals("")) {
            txtConsumption_Error_Max.setText("0");
        } else {
            if (txtConsumption_Error_Max.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtConsumption_Error_Max.setText(txtConsumption_Error_Max.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtConsumption_Error_MaxKeyReleased

    private void txtConsumption_Error_MinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsumption_Error_MinKeyReleased
        // TODO add your handling code here:
        if ((txtConsumption_Error_Min.getText()).equals("")) {
            txtConsumption_Error_Min.setText("0");
        } else {
            if (txtConsumption_Error_Min.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtConsumption_Error_Min.setText(txtConsumption_Error_Min.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtConsumption_Error_MinKeyReleased

    private void txtWI_ResetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWI_ResetKeyReleased
        // TODO add your handling code here:
        if ((txtWI_Reset.getText()).equals("")) {
            txtWI_Reset.setText("0");
        } else {
            if (txtWI_Reset.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtWI_Reset.setText(txtWI_Reset.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtWI_ResetKeyReleased

    private void txtMinConsumptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinConsumptionKeyReleased
        // TODO add your handling code here:
        if ((txtMinConsumption.getText()).equals("")) {
            txtMinConsumption.setText("0");
        } else {
            if (txtMinConsumption.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtMinConsumption.setText(txtMinConsumption.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtMinConsumptionKeyReleased

    private void txtInducementKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInducementKeyReleased
        // TODO add your handling code here:
        if ((txtInducement.getText()).equals("")) {
            txtInducement.setText("0");
        } else {
            if (txtInducement.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtInducement.setText(txtInducement.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtInducementKeyReleased

    private void btnUpdateDiagnosticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDiagnosticsActionPerformed
        // TODO add your handling code here:
        DefaultTableModel Diag_Model = (DefaultTableModel) Diagnostics_Table.getModel();
        Diag_Model.setRowCount(0);
        fetchDB db_DAO = new fetchDB();
        db_DAO.fetchDiagnostics(Diagnostics_Table);
        System.out.println("Database updated!");
    }//GEN-LAST:event_btnUpdateDiagnosticsActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:  

        fetchDB db_DAO = new fetchDB();
        db_DAO.fetchDiagnostics(Diagnostics_Table);
        img.setIcon("Default", jButton2);

    }//GEN-LAST:event_formComponentShown

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
        new loginmodule().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnGenerateErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateErrorActionPerformed
        // TODO add your handling code here:
        VirtualError genError = new VirtualError();
        genError.generateError(checkBattery, checkTPS, checkEGR, checkPressureSensor, checkDeltaPressure, checkEOT, checkEGT1, checkEGT2, errorBattery, errorTPS, errorEGR, errorPressureSensor, errorDeltaPressure, errorEOT, errorEGT1, errorEGT2, portVar, EOT_Intermit, EGT1_Intermit, EGT2_Intermit);
    }//GEN-LAST:event_btnGenerateErrorActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Tecsage_Login().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void checkBatteryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBatteryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBatteryActionPerformed

    private void btnResetErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetErrorActionPerformed
        // TODO add your handling code here:

        VirtualError genError = new VirtualError();
        genError.resetError(checkBattery, checkTPS, checkEGR, checkPressureSensor, checkDeltaPressure, checkEOT, checkEGT1, checkEGT2, errorBattery, errorTPS, errorEGR, errorPressureSensor, errorDeltaPressure, errorEOT, errorEGT1, errorEGT2, portVar, EOT_Intermit, EGT1_Intermit, EGT2_Intermit);

    }//GEN-LAST:event_btnResetErrorActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DTC_Engage erase = new DTC_Engage();
        erase.eraseDTC();
        DefaultTableModel detc = (DefaultTableModel) descriptionDTC.getModel();
        detc.setRowCount(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtDeltaPressure_TempKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDeltaPressure_TempKeyReleased
        // TODO add your handling code here:
        if ((txtDeltaPressure_Temp.getText()).equals("")) {
            txtDeltaPressure_Temp.setText("0");
        } else {
            if (txtDeltaPressure_Temp.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtDeltaPressure_Temp.setText(txtDeltaPressure_Temp.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtDeltaPressure_TempKeyReleased

    private void txtEGR_CFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEGR_CFKeyReleased
        // TODO add your handling code here:
        if ((txtEGR_CF.getText()).equals("")) {
            txtEGR_CF.setText("0");
        } else {
            if (txtEGR_CF.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtEGR_CF.setText(txtEGR_CF.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtEGR_CFKeyReleased

    private void txtParam6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParam6KeyReleased
        // TODO add your handling code here:
        if ((txtParam6.getText()).equals("")) {
            txtParam6.setText("0");
        } else {
            if (txtParam6.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtParam6.setText(txtParam6.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtParam6KeyReleased

    private void txtDOI_CFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDOI_CFKeyReleased
        // TODO add your handling code here:
        if ((txtDOI_CF.getText()).equals("")) {
            txtDOI_CF.setText("0");
        } else {
            if (txtDOI_CF.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtDOI_CF.setText(txtDOI_CF.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtDOI_CFKeyReleased

    private void txtParam7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParam7KeyReleased
        // TODO add your handling code here:
        if ((txtParam7.getText()).equals("")) {
            txtParam7.setText("0");
        } else {
            if (txtParam7.getText().charAt(0) == '0') {
                // Remove the character at the 0th index
                txtParam7.setText(txtParam7.getText().substring(1));
            }
        }
        calculateSUM_LUT();
    }//GEN-LAST:event_txtParam7KeyReleased

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToGenerateErrorPDF();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToAboutPDF();

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        //Save Todays Date in Database
        
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        Calendar cal = new GregorianCalendar();
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String year = String.valueOf(cal.get(Calendar.YEAR));
        
        insertDB db = new insertDB();
        db.addLastLogin(day, month, year);        
        
        int response = JOptionPane.showConfirmDialog(null, "Do you really want to Log out?", "Select", JOptionPane.OK_CANCEL_OPTION);
        if (response == 0) {
            try {
                portVar.closePort();
                setVisible(false);
                new Main_Login(dateVari, RoleUser).setVisible(true);
            } catch (Exception e) {
                setVisible(false);
                new Main_Login(dateVari, RoleUser).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation was aborted!");
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    
    
    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToStartPDF();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToConnectPDF();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToLoadLUTPDF();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToReadLUTPDF();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToUpdateLUTPDF();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToSaveLUTPDF();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToOpenLUTPDF();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToFlashLUTPDF();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToDisconnectPDF();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        PDFRead read = new PDFRead();
        read.HowToFlashVINPDF();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void checkEGT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEGT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkEGT1ActionPerformed

    private void btnSaveEngineParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEngineParamActionPerformed
        // TODO add your handling code here:

        String LUT = lutName1.getText();
        String VSS_CF = txtVSS_CF.getText();
        String Fuel_cutOff = txtFuelCutOff.getText();
        String DOI_microsec = txtCrankingDOI.getText();
        String DOI_OP_microsec = txtDOI_Op.getText();
        String EOT_sec = txtSecEOT.getText();
        String EOT_CF = txtSecEOT_CF.getText();
        String Pump_ON = txtPump_ON.getText();
        String Glow_Plug = txtGlowPlug_ON.getText();
        String DeltaPressure_AVG = txtDeltaPressure_AVG.getText();
        String DeltaPressure_SUM = txtDeltaPressure_SUM.getText();
        String EGR_max = txtEGR_POT_Max.getText();
        String EGR_min = txtEGR_POT_Min.getText();
        String Consumption_max = txtConsumption_Error_Max.getText();
        String Consumption_min = txtConsumption_Error_Min.getText();
        String WI_Reset = txtWI_Reset.getText();
        String min_Consumption = txtMinConsumption.getText();
        String Inducement = txtInducement.getText();
        String DeltaPressure_Temp = txtDeltaPressure_Temp.getText();
        String EGR_CF = txtEGR_CF.getText();
        String DOI_CF = txtDOI_CF.getText();
        String Param6 = txtParam6.getText();
        String Param7 = txtParam7.getText();

        //Code to save data in database
        EngineParam save_EP = new EngineParam();
        save_EP.saveEngineParam(LUT, VSS_CF, Fuel_cutOff, DOI_microsec, DOI_OP_microsec, EOT_sec, EOT_CF, Pump_ON, Glow_Plug, DeltaPressure_AVG, DeltaPressure_SUM, EGR_max, EGR_min, Consumption_max, Consumption_min, WI_Reset, min_Consumption, Inducement, DeltaPressure_Temp, EGR_CF, DOI_CF, Param6, Param7);

    }//GEN-LAST:event_btnSaveEngineParamActionPerformed

    private void btnUpdateEngineParamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateEngineParamActionPerformed
        // TODO add your handling code here:
        String LUT = lutName1.getText();
        String VSS_CF = txtVSS_CF.getText();
        String Fuel_cutOff = txtFuelCutOff.getText();
        String DOI_microsec = txtCrankingDOI.getText();
        String DOI_OP_microsec = txtDOI_Op.getText();
        String EOT_sec = txtSecEOT.getText();
        String EOT_CF = txtSecEOT_CF.getText();
        String Pump_ON = txtPump_ON.getText();
        String Glow_Plug = txtGlowPlug_ON.getText();
        String DeltaPressure_AVG = txtDeltaPressure_AVG.getText();
        String DeltaPressure_SUM = txtDeltaPressure_SUM.getText();
        String EGR_max = txtEGR_POT_Max.getText();
        String EGR_min = txtEGR_POT_Min.getText();
        String Consumption_max = txtConsumption_Error_Max.getText();
        String Consumption_min = txtConsumption_Error_Min.getText();
        String WI_Reset = txtWI_Reset.getText();
        String min_Consumption = txtMinConsumption.getText();
        String Inducement = txtInducement.getText();
        String DeltaPressure_Temp = txtDeltaPressure_Temp.getText();
        String EGR_CF = txtEGR_CF.getText();
        String DOI_CF = txtDOI_CF.getText();
        String Param6 = txtParam6.getText();
        String Param7 = txtParam7.getText();

        //Code to save data in database
        EngineParam save_EP = new EngineParam();
        save_EP.updateEngineParam(LUT, VSS_CF, Fuel_cutOff, DOI_microsec, DOI_OP_microsec, EOT_sec, EOT_CF, Pump_ON, Glow_Plug, DeltaPressure_AVG, DeltaPressure_SUM, EGR_max, EGR_min, Consumption_max, Consumption_min, WI_Reset, min_Consumption, Inducement, DeltaPressure_Temp, EGR_CF, DOI_CF, Param6, Param7);

    }//GEN-LAST:event_btnUpdateEngineParamActionPerformed

    private void checkADCtoVoltageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkADCtoVoltageActionPerformed
        // TODO add your handling code here:
        convertADC();
    }//GEN-LAST:event_checkADCtoVoltageActionPerformed

    private void timer_100_RadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer_100_RadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timer_100_RadioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCOMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCOMActionPerformed

    private void convertADC() {
        if (!checkADCtoVoltage.isSelected()) {
            JTableHeader th = table_OD.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(17);
            TableColumn tc1 = tcm.getColumn(18);
            TableColumn tc2 = tcm.getColumn(19);
            TableColumn tc3 = tcm.getColumn(20);
            TableColumn tc6 = tcm.getColumn(23);
            tc.setHeaderValue("EOT ADC");
            tc1.setHeaderValue("EGR ADC");
            tc2.setHeaderValue("Sol. Pr. ADC");
            tc3.setHeaderValue("∆P ADC");
            tc6.setHeaderValue("TPS ADC");

            th.repaint();
        } else {
            JTableHeader th = table_OD.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(17);
            TableColumn tc1 = tcm.getColumn(18);
            TableColumn tc2 = tcm.getColumn(19);
            TableColumn tc3 = tcm.getColumn(20);
            TableColumn tc6 = tcm.getColumn(23);
            tc.setHeaderValue("EOT [V]");
            tc1.setHeaderValue("EGR [V]");
            tc2.setHeaderValue("Sol. Pr. [V]");
            tc3.setHeaderValue("∆P [V]");
            tc6.setHeaderValue("TPS [V]");

            th.repaint();
        }
        convertModuleADC();
    }

    /*
    
     private void convertADC() {
        if (!checkADCtoVoltage.isSelected()) {
            JTableHeader th = table_OD.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumnModel tcm1 = table_OD.getColumnModel();
            
           // TableColumn tc = tcm1.getColumnIndexAtX(17);
            TableColumn tc1 = tcm.getColumn(18);
            TableColumn tc2 = tcm.getColumn(19);
            TableColumn tc3 = tcm.getColumn(20);            
            TableColumn tc6 = tcm.getColumn(23);
            tc.setHeaderValue("EOT ADC");
            tc1.setHeaderValue("EGR ADC");
            tc2.setHeaderValue("Sol. Pr. ADC");
            tc3.setHeaderValue("∆P ADC");
            tc6.setHeaderValue("TPS ADC");

            th.repaint();
        } else {
            JTableHeader th = table_OD.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(17);
            TableColumn tc1 = tcm.getColumn(18);
            TableColumn tc2 = tcm.getColumn(19);
            TableColumn tc3 = tcm.getColumn(20);           
            TableColumn tc6 = tcm.getColumn(23);
            tc.setHeaderValue("EOT [V]");
            tc1.setHeaderValue("EGR [V]");
            tc2.setHeaderValue("Sol. Pr. [V]");
            tc3.setHeaderValue("∆P [V]");
            tc6.setHeaderValue("TPS [V]");

            th.repaint();
        }
        //convertModuleADC();
    }
     */
    private void convertModuleADC() {
        if (!checkADCtoVoltage.isSelected()) {
            JTableHeader th = table_ADC.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(0);
            TableColumn tc1 = tcm.getColumn(1);
            TableColumn tc2 = tcm.getColumn(2);
            TableColumn tc3 = tcm.getColumn(3);
            TableColumn tc4 = tcm.getColumn(4);

            tc.setHeaderValue("EGR ADC");
            tc1.setHeaderValue("EOT ADC");
            tc2.setHeaderValue("Pr. ADC");
            tc3.setHeaderValue("TPS ADC");
            tc4.setHeaderValue("∆P ADC");

            th.repaint();
        } else {
            JTableHeader th = table_ADC.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(0);
            TableColumn tc1 = tcm.getColumn(1);
            TableColumn tc2 = tcm.getColumn(2);
            TableColumn tc3 = tcm.getColumn(3);
            TableColumn tc4 = tcm.getColumn(4);

            tc.setHeaderValue("EGR [V]");
            tc1.setHeaderValue("EOT [V]");
            tc2.setHeaderValue("Pr. [V]");
            tc3.setHeaderValue("TPS [V]");
            tc4.setHeaderValue("∆P [V]");

            th.repaint();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(index.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CAL_ID_SUM;
    private javax.swing.JLabel DOI_SUM;
    private javax.swing.JLabel Default_SUM;
    private javax.swing.JLabel Delta_Pressure_SUM;
    private javax.swing.JTable Diagnostics_Table;
    private javax.swing.JLabel EGR_SUM;
    private javax.swing.JCheckBox EGT1_Intermit;
    private javax.swing.JCheckBox EGT2_Intermit;
    private javax.swing.JLabel EGT_SUM;
    private javax.swing.JCheckBox EOT_Intermit;
    private javax.swing.JLabel EngineParam_SUM;
    private javax.swing.JLabel LUT_sum;
    private javax.swing.JLabel Pressure_SUM;
    private javax.swing.JLabel SOI_SUM;
    private javax.swing.JLabel Temp_SUM;
    private javax.swing.JLabel Total_SUM;
    private javax.swing.JButton btnClearCalibrationTable;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDisconnect;
    private javax.swing.JButton btnExportCalibration;
    private javax.swing.JButton btnFlash;
    private javax.swing.JButton btnFlashVIN;
    private javax.swing.JButton btnFlashVIN1;
    private javax.swing.JButton btnGenerateError;
    private javax.swing.JButton btnLoad_LUT;
    private javax.swing.JButton btnOpenLUT;
    private javax.swing.JButton btnReadLUT;
    private javax.swing.JMenu btnRefreshApplication;
    private javax.swing.JButton btnResetError;
    private javax.swing.JButton btnSaveEngineParam;
    private javax.swing.JButton btnSaveLUT;
    private javax.swing.JButton btnUpdateDiagnostics;
    private javax.swing.JButton btnUpdateEngineParam;
    private javax.swing.JButton btnUpdateLUT;
    private javax.swing.JButton btnUpdateReadLUT;
    private javax.swing.JCheckBoxMenuItem checkADCtoVoltage;
    private javax.swing.JCheckBox checkBattery;
    private javax.swing.JCheckBox checkDeltaPressure;
    private javax.swing.JCheckBox checkEGR;
    private javax.swing.JCheckBox checkEGT1;
    private javax.swing.JCheckBox checkEGT2;
    private javax.swing.JCheckBox checkEOT;
    private javax.swing.JCheckBox checkPressureSensor;
    private javax.swing.JCheckBox checkTPS;
    private javax.swing.JCheckBox checkboxOnlineData;
    private javax.swing.JComboBox<String> comboCOM;
    private javax.swing.JComboBox<String> comboLUT;
    private javax.swing.JTable consumptionData;
    private javax.swing.JTable descriptionDTC;
    private javax.swing.JLabel display_msg;
    private javax.swing.JLabel display_msg1;
    private javax.swing.JSpinner errorBattery;
    private javax.swing.JTable errorCodeDTC;
    private javax.swing.JSpinner errorDeltaPressure;
    private javax.swing.JSpinner errorEGR;
    private javax.swing.JSpinner errorEGT1;
    private javax.swing.JSpinner errorEGT2;
    private javax.swing.JSpinner errorEOT;
    private javax.swing.JSpinner errorPressureSensor;
    private javax.swing.JSpinner errorTPS;
    private javax.swing.JProgressBar flashProgressBar;
    private javax.swing.JTable freezeFrame;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane35;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblConStatus;
    private javax.swing.JLabel lblCurDate;
    private javax.swing.JLabel lblCurTime;
    private javax.swing.JLabel lblDTCCount;
    private javax.swing.JLabel lblDataFrequency;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblLoginRole;
    private javax.swing.JLabel lblProcessStatus;
    private javax.swing.JLabel lbl_ComMode;
    private javax.swing.JLabel lbl_FWversion;
    private javax.swing.JLabel lbl_Functionality_Test;
    private javax.swing.JTextArea lutName1;
    private javax.swing.JTable newFreezeFrame;
    private javax.swing.JPanel pLUT;
    private javax.swing.JPanel panelCOM;
    private javax.swing.JPanel panelLUTPanel;
    private javax.swing.JPanel panelLUTSelection;
    private javax.swing.JPanel panelUniqueVal;
    private javax.swing.JPanel panel_ADC;
    private javax.swing.JLabel sessionCount;
    private javax.swing.JProgressBar statusEngineHalt;
    private javax.swing.JProgressBar statusInducement;
    private javax.swing.JProgressBar statusMIL;
    private javax.swing.JProgressBar statusPump;
    private javax.swing.JProgressBar statusWIM;
    private javax.swing.JProgressBar statusWaterEmpty;
    private javax.swing.JTable table_ADC;
    private javax.swing.JTable table_DOI;
    private javax.swing.JTable table_DeltaPrerssure;
    private javax.swing.JTable table_EGR;
    private javax.swing.JTable table_ExhaustTemperature;
    private javax.swing.JTable table_OD;
    private javax.swing.JTable table_Prerssure;
    private javax.swing.JTable table_SOI;
    private javax.swing.JTable table_Temperature;
    private javax.swing.JRadioButtonMenuItem timer_1000_Radio;
    private javax.swing.JRadioButtonMenuItem timer_100_Radio;
    private javax.swing.JRadioButtonMenuItem timer_500_Radio;
    private javax.swing.ButtonGroup timergroup;
    private javax.swing.JLabel totalFlashCount;
    private javax.swing.JTextField txtCVN;
    private javax.swing.JTextField txtCalID;
    private javax.swing.JTextField txtConsumption_Error_Max;
    private javax.swing.JTextField txtConsumption_Error_Min;
    private javax.swing.JTextField txtCrankingDOI;
    private javax.swing.JTextField txtDOI_CF;
    private javax.swing.JTextField txtDOI_Op;
    private javax.swing.JTextField txtDeltaPressure_AVG;
    private javax.swing.JTextField txtDeltaPressure_SUM;
    private javax.swing.JTextField txtDeltaPressure_Temp;
    private javax.swing.JTextField txtEGR_CF;
    private javax.swing.JTextField txtEGR_POT_Max;
    private javax.swing.JTextField txtEGR_POT_Min;
    private javax.swing.JTextField txtFuelCutOff;
    private javax.swing.JTextField txtGlowPlug_ON;
    private javax.swing.JTextField txtInducement;
    private javax.swing.JTextField txtMinConsumption;
    private javax.swing.JTextField txtParam6;
    private javax.swing.JTextField txtParam7;
    private javax.swing.JTextField txtPump_ON;
    private javax.swing.JTextField txtSecEOT;
    private javax.swing.JTextField txtSecEOT_CF;
    private javax.swing.JTextField txtVIN;
    private javax.swing.JTextField txtVSS_CF;
    private javax.swing.JTextField txtWI_Reset;
    private javax.swing.JLabel vinLength;
    private javax.swing.JLabel waterLevelPercent;
    private javax.swing.JProgressBar waterLevelProgress;
    // End of variables declaration//GEN-END:variables

    //User Defined Methods
    //ScrollMethods
    private void ScrollTOBottom(String btnText) {
        AdjustmentListener scroll = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        };
        if (btnText.equals("Disconnect")) {
            AdjustmentListener[] listeners = jScrollPane5.getVerticalScrollBar().getAdjustmentListeners();
            for (AdjustmentListener listener : listeners) {
                jScrollPane5.getVerticalScrollBar().removeAdjustmentListener(listener);
            }
        } else {
            jScrollPane5.getVerticalScrollBar().addAdjustmentListener(scroll);
        }
    }

    //Method to Add COM Ports:
    private void addCOMPorts() {
        comboCOM.removeAllItems();
        btnConnect.setEnabled(true);
        SerialPort[] portlist = SerialPort.getCommPorts();

        //add each element into combo box
        for (SerialPort port : portlist) {
            System.out.println(port);
            comboCOM.addItem(port.getSystemPortName());         
        }
    }

    //Exporting Online Data into CSV format
    //Method to calculate realtime length of VIN
    private void vinLength() {
        String vin = txtVIN.getText();
        //TODO: if the length of vin is not appropriate show error
        if (vin.length() < 17) {
            if (vin.length() < 10) {
                vinLength.setText("0" + String.valueOf(vin.length()));
                vinLength.setForeground(Color.WHITE);
                btnFlashVIN.setEnabled(false);
            } else {
                vinLength.setText(String.valueOf(vin.length()));
                vinLength.setForeground(Color.WHITE);
                btnFlashVIN.setEnabled(false);
            }

        } else if (vin.length() > 17) {
            vinLength.setText(String.valueOf(vin.length()));
            vinLength.setForeground(Color.WHITE);
            btnFlashVIN.setEnabled(false);
        } else if (vin.length() == 0) {
            vinLength.setText("00");
            vinLength.setForeground(Color.YELLOW);
            btnFlashVIN.setEnabled(false);
        } else {
            vinLength.setText(String.valueOf(vin.length()));
            vinLength.setForeground(new java.awt.Color(0, 204, 153));
            btnFlashVIN.setEnabled(true);
        }

    }

    //Method to erase dtc code
    public void eraseDTC() {
        try {
            OutputStream Serial = portVar.getOutputStream();
            Serial.write(0xAA);
            Serial.write(0xAB);
            Serial.write(0x69);

        } catch (IOException ex) {
            Logger.getLogger(index.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    //Method to call Calculate sum
    private void calculateSUM_LUT() {
        //Method to Calculate SUM
        int[] Engine_Params = new int[18];

        Engine_Params[1] = Integer.parseInt(txtDeltaPressure_AVG.getText());
        Engine_Params[2] = Integer.parseInt(txtFuelCutOff.getText());
        Engine_Params[3] = Integer.parseInt(txtCrankingDOI.getText());
        Engine_Params[4] = Integer.parseInt(txtDOI_Op.getText());
        Engine_Params[5] = Integer.parseInt(txtSecEOT.getText());
        Engine_Params[6] = Integer.parseInt(txtSecEOT_CF.getText());
        Engine_Params[7] = Integer.parseInt(txtPump_ON.getText());
        Engine_Params[8] = Integer.parseInt(txtGlowPlug_ON.getText());
        Engine_Params[9] = Integer.parseInt(txtVSS_CF.getText());
        Engine_Params[10] = Integer.parseInt(txtDeltaPressure_SUM.getText());
        Engine_Params[11] = Integer.parseInt(txtEGR_POT_Max.getText());
        Engine_Params[12] = Integer.parseInt(txtEGR_POT_Min.getText());
        Engine_Params[13] = Integer.parseInt(txtConsumption_Error_Max.getText());
        Engine_Params[14] = Integer.parseInt(txtConsumption_Error_Min.getText());
        Engine_Params[15] = Integer.parseInt(txtWI_Reset.getText());
        Engine_Params[16] = Integer.parseInt(txtMinConsumption.getText());
        Engine_Params[17] = Integer.parseInt(txtInducement.getText());

        CalulateSUM sum_obj = new CalulateSUM();
        sum_obj.Calculate_LUT_SUM(table_DOI, DOI_SUM, table_EGR, EGR_SUM, table_Temperature, Temp_SUM, table_Prerssure, Pressure_SUM, table_SOI, SOI_SUM, Engine_Params, EngineParam_SUM, txtCalID.getText(), CAL_ID_SUM, Total_SUM, LUT_sum, Default_SUM, lutName1, table_ExhaustTemperature, table_DeltaPrerssure, EGT_SUM, Delta_Pressure_SUM);

    }

    public void ResetOnlineData() {
        // TODO add your handling code here:
        portVar = con.getSerial();
        OutputStream Serial = portVar.getOutputStream();
        checkboxOnlineData.setSelected(false);

    }

    private void StopOnlineData() {
        // TODO add your handling code here:
        OutputStream Serial = portVar.getOutputStream();

        if (checkboxOnlineData.isSelected()) {
            try {
                Serial.flush();
                Serial.flush();
                Serial.write(0xAA);
                Serial.write(0xAB);
                Serial.write(0x55);
                btnFlash.setEnabled(false);
                btnUpdateReadLUT.setEnabled(false);
                btnReadLUT.setEnabled(true);
                Serial.flush();
//                Serial.write(0xAA);
//                Serial.write(0xAB);
//                Serial.write(0x55);
                //btnFlash.setBackground(Color.red);
                //btnFlash.setForeground(Color.white);

            } catch (IOException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Serial.flush();
                Serial.write(0xAB);
                Serial.write(0xAA);
                Serial.write(0x56);
                Serial.flush();
//                Serial.write(0xAB);
//                Serial.write(0xAA);
//                Serial.write(0x56);
                btnFlash.setEnabled(true);
                btnReadLUT.setEnabled(false);

//                    btnFlash.setBackground(Color.WHITE);
//                    btnFlash.setForeground(Color.black);
            } catch (IOException ex) {
                Logger.getLogger(index.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        Read_Obj.readOnlineData(checkboxOnlineData, table_OD, table_ADC, errorCodeDTC, freezeFrame, descriptionDTC, statusMIL, statusPump, statusEngineHalt, consumptionData, lblDTCCount, waterLevelProgress, waterLevelPercent, display_msg, newFreezeFrame, display_msg1, timer_100_Radio, timer_500_Radio, timer_1000_Radio, table_DOI, lblConStatus, btnConnect, btnDisconnect, jScrollPane5, lblDataFrequency, checkADCtoVoltage, statusWIM, statusWaterEmpty, statusInducement, lbl_FWversion, lbl_ComMode, lbl_Functionality_Test);

    }

    //Method to Create Table at Initialisation and Fetch Data from Database
    private void Database_Management() {
        //Create tables
        Create_Tables db_Tables = new Create_Tables();
        db_Tables.create_Tables();
    }

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("EnDocLogoDesign.png")));
    }
}
