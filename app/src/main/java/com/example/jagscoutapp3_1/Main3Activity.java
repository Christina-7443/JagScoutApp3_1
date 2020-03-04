package com.example.jagscoutapp3_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jagscoutapp3_1.DatabaseHelper;
import com.example.jagscoutapp3_1.MainActivity;
import com.example.jagscoutapp3_1.R;

import static com.example.jagscoutapp3_1.Main2Activity.innerCount;
import static com.example.jagscoutapp3_1.Main2Activity.lowerCount;
import static com.example.jagscoutapp3_1.Main2Activity.outerCount;
import static com.example.jagscoutapp3_1.Main2Activity.missedCount;
import static com.example.jagscoutapp3_1.Main2Activity.penaltyCount;

public class Main3Activity extends AppCompatActivity {
    Switch drive,
            rotation,
            position,
            level,
            climb,
            park,
            none;
    EditText
            notes;

    String stDrive,
            stRotation,
            stPosition,
            strClimb,
            strLevel,
            strPark,
            strNone,
            stNotes;
    TextView tv,
            tvMatch,
            tvRobot;
    String st,
            stMatch,
            stRobot;
    DatabaseHelper myDB;
    //Names Buttons
    Button btnS;
    Button btnVA;
    Button btnER;



    private Button BtnEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myDB = new DatabaseHelper(this);
        BtnEnd = findViewById(R.id.BtnEnd);

        btnS = (Button) findViewById(R.id.BtnEnd);
        btnVA = (Button)findViewById(R.id.viewData);
        btnER = (Button) findViewById(R.id.endRoundButton);

        tv = findViewById(R.id.scout);
        tvMatch = findViewById(R.id.match);
        tvRobot = findViewById(R.id.robot);

        drive = findViewById(R.id.driveOffLine);
        rotation = findViewById(R.id.rotation);
        position = findViewById(R.id.position);
        climb = findViewById(R.id.climbed);
        level = findViewById(R.id.level);
        park = findViewById(R.id.rendezvous);
        none = findViewById(R.id.none);
        notes = findViewById(R.id.matchNotes);




        st=getIntent().getExtras().getString("nameView");
        stMatch=getIntent().getExtras().getString("nameMatch");
        stRobot=getIntent().getExtras().getString("nameRobot");
        stDrive=getIntent().getExtras().getString("didDrive");
        stRotation=getIntent().getExtras().getString("didRotation");
        stPosition=getIntent().getExtras().getString("didPosition");
        tv.setText(st);
        tvMatch.setText(stMatch);
        tvRobot.setText(stRobot);

        AddData();
        viewAll();
        BtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                moveToFirst();
            }
        });
    }
    public void resetVariables(){
        innerCount = 0;
        outerCount = 0;
        lowerCount = 0;
        missedCount = 0;
        penaltyCount = 0;
    }
    private void moveToFirst(){
        resetVariables();
        Intent intent = new Intent(Main3Activity.this, MainActivity.class);
        startActivity(intent);

    }

    public void AddData(){

        btnER.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String stClimb, stLevel, stPark, stNone;
                        if (climb.isChecked())
                            stClimb = climb.getTextOn().toString();
                        else
                            stClimb = climb.getTextOff().toString();
                        if (level.isChecked())
                            stLevel = level.getTextOn().toString();
                        else
                            stLevel = level.getTextOff().toString();
                        if (park.isChecked())
                            stPark = park.getTextOn().toString();
                        else
                            stPark = park.getTextOff().toString();
                        if (none.isChecked())
                            stNone = none.getTextOn().toString();
                        else
                            stNone = none.getTextOff().toString();
                        stNotes = notes.getText().toString();

                        boolean isInsterted = myDB.insertData(stRobot,innerCount,outerCount,lowerCount,missedCount,penaltyCount,st,stMatch,stDrive,stRotation, stPosition,stClimb,stLevel,stPark,stNone,stNotes);
                        if(isInsterted =true) {
                            Toast.makeText(Main3Activity.this,"Data Inserted", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(Main3Activity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
    public void viewAll (){
        btnVA.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result = myDB.getAllData();
                        if (result.getCount()==0){
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(result.moveToNext()){
                            buffer.append("Id :" + result.getString(0)+"\n");
                            buffer.append("Team number :" + result.getString(1)+"\n");
                            buffer.append("Inner goals :" + result.getString(2)+"\n");
                            buffer.append("Outer goals :" + result.getString(3)+"\n");
                            buffer.append("Lower goals :" + result.getString(4)+"\n");
                            buffer.append("Missed Count :" + result.getString(5)+"\n");
                            buffer.append("Penalty Count :" + result.getString(6)+"\n");
                            buffer.append("Scouter Name :" + result.getString(7)+"\n");
                            buffer.append("Match Number :" + result.getString(8)+"\n");
                            buffer.append("Drive Off Line :" + result.getString(9)+"\n");
                            buffer.append("Rotation Control :" + result.getString(10)+"\n");
                            buffer.append("Position Control :" + result.getString(11)+"\n");
                            buffer.append("Climb :" + result.getString(12)+"\n");
                            buffer.append("Level :" + result.getString(13)+"\n");
                            buffer.append("Park :" + result.getString(14)+"\n");
                            buffer.append("None :" + result.getString(15)+"\n");
                            buffer.append("Notes :" + result.getString(16)+"\n\n");
                        }

                        //show all data
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }

    public void  showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
