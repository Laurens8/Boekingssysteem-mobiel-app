package com.example.thomasmoreboekingssysteem;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Queue;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Connection connection;
                    EditText input;
                    input = findViewById(R.id.textInputLayout4);
                    String output = "";
                    String personeelsnummer = String.valueOf(input.getText());
                    ConnectionDB con = new ConnectionDB();
                    connection = con.conclass();
                    if (con != null) {
                        try {
                            String sql = "select * from Boekingssysteem.Persoon where Personeelnummer like '"+personeelsnummer+"'";
                            Statement smt = connection.createStatement();
                            ResultSet set = smt.executeQuery(sql);
                            while (set.next()){
                                output = set.getString(1);
                               if (output.contains(personeelsnummer)) {
                                    Intent intent = new Intent(MainActivity.this, Status.class);
                                    intent.putExtra("personeelsnummer", personeelsnummer);
                                    startActivity(intent);
                               }
                            }
                            connection.close();
                        }catch (Exception e){
                            Log.e("error is", e.getMessage());
                        }
                    }
                }
        });
    }
}

//ApiIdRequest apiIdRequest = new ApiIdRequest(MainActivity.this);
//   apiIdRequest.GetId(inputtext, new ApiIdRequest.VolleyResponseListener() {
//                    @Override
//                    public void onError(String message) {
//                        Toast.makeText(MainActivity.this, "Verkeerde personeelsnummer", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String persoonId) {
//                        Toast.makeText(MainActivity.this, persoonId, Toast.LENGTH_SHORT).show();
//                        if (persoonId == inputtext){
//                            Intent intent=new Intent(MainActivity.this,Status.class);
//                            intent.putExtra("personeelsnummer", inputtext);
//                            startActivity(intent);
//                        }
//                    }
//                });