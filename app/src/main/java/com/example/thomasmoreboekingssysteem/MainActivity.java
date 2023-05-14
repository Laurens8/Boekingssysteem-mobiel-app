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
        Button button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = findViewById(R.id.textInputLayout4);
                EditText wachtinput = findViewById(R.id.editTextTextPassword2);
                String wachtwoord = wachtinput.getText().toString().trim();
                String personeelsnummer = input.getText().toString().trim();
                String url = "https://boekingssysteem-api.azurewebsites.net/api/Persoon/get" + personeelsnummer;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String persoonId = response.getString("personeelnummer");
                            String wachtwoordId = response.getString("wachtwoord");
                            //Toast.makeText(MainActivity.this, wachtwoordId, Toast.LENGTH_SHORT).show();
                            if (persoonId.equals(personeelsnummer) && wachtwoordId.equals(wachtwoord)) {
                                Intent intent = new Intent(MainActivity.this, Status.class);
                                Bundle extras = new Bundle();
                                extras.putString("personeelsnummer", personeelsnummer);
                                extras.putString("wachtwoord", wachtwoord);
                                intent.putExtras(extras);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "r-nummer of wachtwoord incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "r-nummer of wachtwoord incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "r-nummer of wachtwoord incorrect", Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(request);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WachtwoordVergeten.class);
                startActivity(intent);
            }
        });
    }
}
