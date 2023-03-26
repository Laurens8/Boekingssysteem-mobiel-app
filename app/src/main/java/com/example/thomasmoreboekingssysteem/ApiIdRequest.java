package com.example.thomasmoreboekingssysteem;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiIdRequest {
    Context context;



    public ApiIdRequest(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String persoonId);
    }
    //String url = "https://api-example-auth-ldw.azurewebsites.net/campus";
    String persoonId = "";
    String url="http://localhost:5094/api/Persoon/id?id=r123";
    public void GetId(String persoonid, VolleyResponseListener volleyResponseListener){
    JsonArrayRequest request;
        //url = "http://localhost:5094/api/Persoon/id?id=r123";
       request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                       JSONObject id = response.getJSONObject(0);
                        persoonId = id.getString("personeelnummer");
                    } catch (JSONException e) {     e.printStackTrace();
                   }
                    volleyResponseListener.onResponse(persoonId);
               },
               error -> //Toast.makeText(context, "Verkeerde personeelsnummer", Toast.LENGTH_SHORT).show());
       volleyResponseListener.onError("Verkeerde personeelsnummer"));
       RequestSingelton.getInstance(context).addToRequestQueue(request);
       Toast.makeText(context, "in class ID" +persoonId, Toast.LENGTH_SHORT).show();
        //return persoonId;
    }

    public void getPersoonDetails(String Id){
    List<Persoon_model> details = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray campus = response.getJSONArray("");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestSingelton.getInstance(context);
    }
}
