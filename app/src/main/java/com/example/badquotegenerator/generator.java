package com.example.badquotegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class generator extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final Button switchUI = findViewById(R.id.switchUI);
        switchUI.setOnClickListener(v -> {
            switchScreen();
        });


    }
    void switchScreen() {
        Intent intent = new Intent(generator.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
