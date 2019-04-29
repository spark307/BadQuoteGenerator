package com.example.badquotegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SetUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String fontChoice;
    private String sizeChoice;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        final Button switchUI = findViewById(R.id.switchUI);
        switchUI.setOnClickListener(v -> {
            switchScreen();
        });

        Spinner fontSpinner = findViewById(R.id.fontSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Fonts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adapter);
        fontSpinner.setOnItemSelectedListener(this);

        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<CharSequence> secondAdapter = ArrayAdapter.createFromResource(this, R.array.FontSize, android.R.layout.simple_spinner_item);
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(secondAdapter);
        sizeSpinner.setOnItemSelectedListener(this);
    }
    void switchScreen() {
        Intent intent = new Intent(SetUpActivity.this, MainActivity.class);

        intent.putExtra("FontChoice", fontChoice);
        intent.putExtra("SizeChoice", sizeChoice);

        startActivity(intent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.fontSpinner) {
            fontChoice = parent.getItemAtPosition(position).toString();
        }
        else if(parent.getId() == R.id.sizeSpinner) {
            sizeChoice = parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
