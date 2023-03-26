package com.example.thomasmoreboekingssysteem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import android.widget.ViewAnimator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status extends AppCompatActivity {

    Connection conncect;
    String connectionRes="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Connection connection;
        String output = "";
        String newString;
        Button button1, button2;
        TextView status,id,naam,voornaam;
        FloatingActionButton floatingActionButton;
        //final RelativeLayout relativeLayout;
        status = findViewById(R.id.textViewStatus);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button4);
        id = findViewById(R.id.textView9);
        naam= findViewById(R.id.textView7);
        voornaam= findViewById(R.id.textView8);
        Intent intent = getIntent();
        newString= intent.getStringExtra("personeelsnummer");
        Persoon_model docent = new Persoon_model();

        ConnectionDB con = new ConnectionDB();
        connection = con.conclass();
        if (con != null) {
            try {
                String sql = "select * from Boekingssysteem.Persoon where Personeelnummer like '"+newString+"'";
                Statement smt = connection.createStatement();
                ResultSet set = smt.executeQuery(sql);
                while (set.next()){
                    docent.Personeelnummer = set.getString(1);
                    docent.Naam = set.getString(2);
                    docent.Voornaam = set.getString(3);
                    docent.Aanwezig = set.getBoolean(5);
                }
                id.setText(docent.Personeelnummer);
                naam.setText(docent.Naam);
                voornaam.setText(docent.Voornaam);

                if (docent.Aanwezig == true){
                    status.setBackgroundColor(Color.parseColor("#55A4C639"));
                    status.setText("Aanwezig");
                }
                else {
                    status.setBackgroundColor(Color.parseColor("#55EC4849"));
                    status.setText("Afwezig");
                }
                //connection.close();
            }catch (Exception e){
                Log.e("error is", e.getMessage());
            }
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setBackgroundColor(Color.parseColor("#55A4C639"));
                status.setText("Aanwezig");
                Persoon_model docent = new Persoon_model();

                ConnectionDB connectionDB = new ConnectionDB();
                try {
                    if (connectionDB != null){
                        String sql = "update Boekingssysteem.Persoon set Aanwezig = 'true' where Personeelnummer like '"+newString+"'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                    }
                }catch (Exception e){
                    Log.e("error is", e.getMessage());
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setBackgroundColor(Color.parseColor("#55EC4849"));
                status.setText("Afwezig");
                Persoon_model docent = new Persoon_model();
                ConnectionDB connectionDB = new ConnectionDB();
                try {
                    if (connectionDB != null){
                        String sql = "update Boekingssysteem.Persoon set Aanwezig = 'false' where Personeelnummer like '"+newString+"'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                    }
                }catch (Exception e){
                    Log.e("error is", e.getMessage());
                }

            }
        });
    }
}