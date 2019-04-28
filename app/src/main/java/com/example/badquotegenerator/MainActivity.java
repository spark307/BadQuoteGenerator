package com.example.badquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;

import android.util.Log;

import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.net.HttpURLConnection;

import java.util.HashMap;

import java.util.Map;

import java.lang.reflect.GenericArrayType;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button switchUI;
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Bad Quotes";
    private String[] quotes = {"Some list", "second list", "alsdkjflsadjf"};
    private String[] people = {"George Washington", "Obama", "The queen"};
    TextView quoteText;

    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);

        requestQueue = Volley.newRequestQueue(this);

        final Button generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            Log.d(TAG, "Open file button clicked");
            postRequest();
        });

        final Button switchUI = findViewById(R.id.switchToSetup);
        switchUI.setOnClickListener(v -> {
            switchScreen();
        });

    }


//    public String getQuote() {
//        int randomIndex;
//        Random random = new Random();
//        randomIndex = random.nextInt(quotes.length);
//        return quotes[randomIndex];
//    }

    public String getQuote(final JSONObject response) {
        String input = response.toString();
        return ParseJson.getQuote(input);
    }

//    public String getPeople() {
//        int randomIndex;
//        Random random = new Random();
//        randomIndex = random.nextInt(people.length);
//        return people[randomIndex];
//    }

    public String getPeople(final JSONObject response) {
        String input = response.toString();
        return ParseJson.getAuthor(input);
    }

    public String getText(final JSONObject response) {
        String combineText;
        combineText = getQuote(response) + " - " + getPeople(response);
        return combineText;
    }

    public void setTextView(final JSONObject response) {
        quoteText.setText(getText(response));
    }

    void switchScreen() {
        Intent intent = new Intent(MainActivity.this, generator.class);
        startActivity(intent);
        finish();
    }

    private JSONObject getParams() {
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("method", "getQuote");
            parameters.put("format", "json");
            parameters.put("lang", "en");
        } catch (JSONException e){
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        System.out.println(parameters);
        return parameters;
    }

    void postRequest() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d(TAG, response.toString());
                            setTextView(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, "Error: " + error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void handleApiResponse(final JSONObject response) {
        Log.d(TAG, response.toString());
        System.out.println(1);
        setTextView(response);
    }

    void handleApiError(final VolleyError error) {
        // On failure just clear the progress bar
        System.out.println(2);
        Log.w(TAG, "Error: " + error.toString());

    }
}
