package com.example.thomasmoreboekingssysteem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.database.sqlite.SQLiteOpenHelper;
public class ConnectionDB {
    Connection connection;
    @SuppressLint("NewApi")
    public Connection conclass()
    {
        StrictMode.ThreadPolicy a= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        Connection ConnectURL=null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //String con = "jdbc:jtds:sqlserver://sqlserverthomasmore.database.windows.net:1433;database=BoekingssysteemDB;user=adminlaurens@sqlserverthomasmore;password=Tongelsbos_;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;ssl=require";
            String con = "jdbc:jtds:sqlserver://192.168.116.1;instance=SQLEXPRESS;databasename=BoekingssysteemDB;user=sa;password=Tongelsbos";
            ConnectURL = DriverManager.getConnection(con);
        }
        catch (Exception e)
        {
            Log.e("Error is ", e.getMessage());
        }
        return ConnectURL;
    }


}
