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

public class WachtwoordVergeten extends AppCompatActivity {
    Boolean admin,status;
    String id,naam,voornaam,wachtwoord;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wachtwoord_vergeten);

        Button button;
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText wachtwoord1 = findViewById(R.id.textInputLayout4);
                EditText wachtwoord2 = findViewById(R.id.editTextTextPassword2);
                EditText input = findViewById(R.id.inputpersoneel);
                String personeelnummerinlog = input.getText().toString().trim();
                String url = "https://boekingssysteem-api.azurewebsites.net/api/Persoon/get" + personeelnummerinlog.trim();
                String url2 = "https://boekingssysteem-api.azurewebsites.net/api/Persoon/put" + personeelnummerinlog.trim();
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            id = response.getString("personeelnummer");
                            naam = response.getString("naam");
                            voornaam = response.getString("voornaam");
                            admin = response.getBoolean("admin");
                            status = response.getBoolean("aanwezig");

                            if (personeelnummerinlog == ""){
                                Toast.makeText(WachtwoordVergeten.this, "Personeelnummer moet ingevuld zijn", Toast.LENGTH_LONG).show();
                            }else{
                                if (wachtwoord1.getText().toString().equals("") || wachtwoord2.getText().toString().equals("")){
                                    Toast.makeText(WachtwoordVergeten.this, "Wachtwoorden moet ingevuld zijn", Toast.LENGTH_LONG).show();
                                }else{
                                    if (!wachtwoord1.getText().toString().equals(wachtwoord2.getText().toString())){
                                        Toast.makeText(WachtwoordVergeten.this, "Wachtwoorden moet overeen komen", Toast.LENGTH_LONG).show();
                                    }
                                    if(wachtwoord1.getText().length() < 8 && wachtwoord2.getText().length() < 8){
                                        Toast.makeText(WachtwoordVergeten.this, "Wachtwoord moet minstens 8 tekens lang zijn", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        JSONObject data = new JSONObject();
                                        try {
                                            wachtwoord = wachtwoord1.getText().toString();
                                            //data.put("Content-Type", "application/json");
                                            data.put("personeelnummer", id);
                                            data.put("naam", naam);
                                            data.put("voornaam", voornaam);
                                            data.put("aanwezig", status);
                                            data.put("admin", admin);
                                            data.put("wachtwoord", wachtwoord);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
                                        RequestQueue queue = Volley.newRequestQueue(WachtwoordVergeten.this);
                                        queue.add(request);
                                        Toast.makeText(WachtwoordVergeten.this, "Wachtwoord is aangepast", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(WachtwoordVergeten.this, "Personeelnummer niet gevonden", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WachtwoordVergeten.this, "Personeelnummer niet gevonden", Toast.LENGTH_SHORT).show();
                    }
                });

                RequestQueue queue = Volley.newRequestQueue(WachtwoordVergeten.this);
                queue.add(request);
            }
        });
    }
}