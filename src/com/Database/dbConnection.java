package com.Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manish Pandey
 */
public class dbConnection {

    //Method do Create instance of Database Connection
    public static Connection dbConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement st = con.createStatement();
            st.executeUpdate("PRAGMA writable_schema = 1");
            st.executeUpdate("PRAGMA foreign_keys = ON");

            return con;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
