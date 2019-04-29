package com.example.badquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Good Quotes";
    TextView quoteText;
    Typeface typeface;
    private static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);

        Intent intent = getIntent();
        String ourFont = intent.getStringExtra("FontChoice");
        String ourSize = intent.getStringExtra("SizeChoice");

        if (ourFont.equals("alice")) {
            typeface = ResourcesCompat.getFont(this, R.font.alice);
        } else if (ourFont.equals("sacramento")) {
            typeface = ResourcesCompat.getFont(this, R.font.sacramento);
        } else if (ourFont.equals("caveat_bold")) {
            typeface = ResourcesCompat.getFont(this, R.font.caveat_bold);
        } else if (ourFont.equals("volkhov")) {
            typeface = ResourcesCompat.getFont(this, R.font.volkhov);
        } else {
            typeface = ResourcesCompat.getFont(this, R.font.aladin);
        }

        if (ourSize.equals("Small")) {
            quoteText.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        } else if (ourSize.equals("Medium")) {
            quoteText.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
        } else {
            quoteText.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }

        quoteText.setTypeface(typeface);

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

    public String getQuote(final JSONObject response) {
        String input = response.toString();
        return ParseJson.getQuote(input);
    }

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
        Intent intent = new Intent(MainActivity.this, SetUpActivity.class);
        startActivity(intent);
    }

    void postRequest() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en",
                    null,
                    this::handleApiResponse,
                    this::handleApiError
                );
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void handleApiResponse(final JSONObject response) {
        Log.d(TAG, response.toString());
        setTextView(response);
    }

    void handleApiError(final VolleyError error) {
        Log.w(TAG, "Error: " + error.toString());
    }
}
