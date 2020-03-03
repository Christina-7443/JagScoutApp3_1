package com.example.jagscoutapp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText matchNumber;
    EditText robotNumber;
    Button startMatch;
    String scout;
    String match;
    String robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startMatch = findViewById(R.id.BtnStart);
        name = findViewById(R.id.personName);
        matchNumber = findViewById(R.id.matchNumber);
        robotNumber = findViewById(R.id.robotNumber);


        startMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchPlay();
            }
        });
    }
    public void openMatchPlay(){


        Intent openMatchPlay = new Intent(MainActivity.this, Main2Activity.class);
        scout = name.getText().toString();
        match = matchNumber.getText().toString();
        robot = robotNumber.getText().toString();
        openMatchPlay.putExtra("nameView", scout);
        openMatchPlay.putExtra("nameMatch", match);
        openMatchPlay.putExtra("nameRobot", robot);
        startActivity(openMatchPlay);
        finish();
    }
}
