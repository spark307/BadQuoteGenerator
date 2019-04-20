package com.example.badquotegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Bad Quotes";

    private String[] quotes = {"Some list", "second list", "alsdkjflsadjf"};
    private String[] people = {"George Washington", "Obama", "The queen"};
    private String combineText;
    TextView quoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);

        final Button generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(v -> {
            Log.d(TAG, "Open file button clicked");
            setTextView();
        });


    }


    public String getQuote() {
        int randomIndex;
        Random random = new Random();
        randomIndex = random.nextInt(quotes.length);
        return quotes[randomIndex];
    }
    public String getPeople() {
        int randomIndex;
        Random random = new Random();
        randomIndex = random.nextInt(people.length);
        return people[randomIndex];
    }
    public String getText() {
        combineText = getQuote() + " - " + getPeople();
        return combineText;
    }
    public void setTextView() {
        quoteText.setText(getText());
    }
}
