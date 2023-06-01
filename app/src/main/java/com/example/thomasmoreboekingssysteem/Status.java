package com.example.thomasmoreboekingssysteem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ViewAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status extends AppCompatActivity {
    Boolean admin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);


        String newString;
        Button button1, button2;
        TextView status,id,naam,voornaam;
        FloatingActionButton floatingActionButton;
        status = findViewById(R.id.textViewStatus);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button4);
        id = findViewById(R.id.textView9);
        naam= findViewById(R.id.textView7);
        voornaam= findViewById(R.id.textView8);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String personeelnummerinlog = extras.getString("personeelsnummer");
        String wachtwoordinlog = extras.getString("wachtwoord");

        String url = "https://boekingssysteem-api.azurewebsites.net/api/Persoon/get" + personeelnummerinlog.trim();
        String url2 = "https://boekingssysteem-api.azurewebsites.net/api/Persoon/put" + personeelnummerinlog.trim();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String persoonId = response.getString("personeelnummer");
                    String persoonNaam = response.getString("naam");
                    String persoonVoornaam = response.getString("voornaam");
                    admin = response.getBoolean("admin");
                    String persoonStatus = response.getString("aanwezig");
                    if (persoonId.equals(personeelnummerinlog)) {

                        id.setText(persoonId);
                        naam.setText(persoonNaam);
                        voornaam.setText(persoonVoornaam);
                        if (persoonStatus == "true"){
                            status.setBackgroundColor(Color.parseColor("#55A4C639"));
                            status.setText("Aanwezig");
                        }
                        else if (persoonStatus == "false"){
                            status.setBackgroundColor(Color.parseColor("#55EC4849"));
                            status.setText("Afwezig");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Status.this, "Netwerk fout", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Status.this, "Netwerk fout", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(Status.this);
        queue.add(request);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setBackgroundColor(Color.parseColor("#55A4C639"));
                status.setText("Aanwezig");

                // Create a JSONObject containing the data to be updated.
                JSONObject data = new JSONObject();
                try {
                    //data.put("Content-Type", "application/json");
                    data.put("personeelnummer", personeelnummerinlog);
                    data.put("naam", naam.getText());
                    data.put("voornaam", voornaam.getText());
                    data.put("aanwezig", true);
                    data.put("admin", admin);
                    data.put("wachtwoord", wachtwoordinlog);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create the PUT request with the data in the body.
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url2, data, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response if needed.
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Status.this);
                queue.add(request);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status.setBackgroundColor(Color.parseColor("#55EC4849"));
                status.setText("Afwezig");

                // Create a JSONObject containing the data to be updated.
                JSONObject data = new JSONObject();
                try {
                    //data.put("Content-Type", "application/json");
                    data.put("personeelnummer", personeelnummerinlog);
                    data.put("naam", naam.getText());
                    data.put("voornaam", voornaam.getText());
                    data.put("aanwezig", false);
                    data.put("admin", admin);
                    data.put("wachtwoord", wachtwoordinlog);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create the PUT request with the data in the body.
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url2, data, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response if needed.
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(Status.this);
                queue.add(request);
            }
        });

    }
}