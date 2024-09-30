package com.example.costtrack.Handlers;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        String db = "CostTrack_Database";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectURL = "jdbc:jtds:sqlserver://10.0.2.2:1433;databaseName=CostTrack_Database;ssl=require;user=Android;password=Android;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            con = DriverManager.getConnection(connectURL);
        } catch (Exception e) {
            Log.e("Error :", e.getMessage());

        }
        return con;
    }
}
