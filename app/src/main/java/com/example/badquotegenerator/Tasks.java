package com.example.badquotegenerator;

import android.util.Log;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import java.net.HttpURLConnection;

import java.util.HashMap;

import java.util.Map;

class Tasks {

    private static final String TAG = "BadQuotes";

    private Map<String, String> getParams() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("method", "getQuote");
        parameters.put("format", "json");
        parameters.put("lang", "en");

        return parameters;
    }

    void postRequest() {
        String url = "http://api.forismatic.com/api/1.0/";
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    new JSONObject(getParams()),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
