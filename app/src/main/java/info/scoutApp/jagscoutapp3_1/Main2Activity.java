package info.scoutApp.jagscoutapp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import info.scoutApp.jagscoutapp3_1.R;


public class Main2Activity extends AppCompatActivity {

    //declaring the variables for use later

    Switch  swDrive,
            swInner,
            swAuto,
            swRotation,
            swPosition;

    TextView tv,
            tvMatch,
            tvRobot;
    String st,
            stMatch,
            stRobot;
    //names buttons for findViewById

    Button btnU;
    Button btnL;
    Button btnM;
    Button btnP;
    Button btnML;
    Button btnMU;

    Button BtnNext;
    //labels variables for inside the public voids

    public static int upperCount = 0;
    public static int lowerCount = 0;
    public static int missedCount = 0;
    public static int penaltyCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Game Play");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swDrive = findViewById(R.id.driveOffLine);
        swAuto = findViewById(R.id.autoShots);
        swInner = findViewById(R.id.innerSwitch);
        swRotation = findViewById(R.id.rotation);
        swPosition = findViewById(R.id.position);

        //makes the buttons connect with the ones in the .xml

        btnU = (Button)findViewById(R.id.upperButton);
        btnL = (Button)findViewById(R.id.lowerButton);
        btnM = (Button)findViewById(R.id.missedShotButton);
        btnP = (Button)findViewById(R.id.penaltyButton);

        btnMU = (Button)findViewById(R.id.minusUpper);
        btnML = (Button)findViewById(R.id.minusLower);





        tv = findViewById(R.id.scout);
        tvMatch = findViewById(R.id.match);
        tvRobot = findViewById(R.id.robot);


        st=getIntent().getExtras().getString("nameView");
        stMatch=getIntent().getExtras().getString("nameMatch");
        stRobot=getIntent().getExtras().getString("nameRobot");
        tv.setText(st);
        tvMatch.setText(stMatch);
        tvRobot.setText(stRobot);




        Intent openMatchPlay = getIntent();

        BtnNext = findViewById(R.id.BtnNext);
        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToEnd();
            }
        });



    }
    //makes the outer button say how many times it has been pressed
    public void CountUpper(View v){
        upperCount++;
        btnU.setText("Upper : " +  String.valueOf(upperCount));
    }
    //subtracts 1 from Outer
    public void MinusUpper(View v){
        upperCount--;
        btnU.setText("upper : " +  String.valueOf(upperCount));
    }
    //makes the lower button say how many times it has been pressed
    public void CountLower(View v){
        lowerCount++;
        btnL.setText("Lower : " + String.valueOf(lowerCount));
    }
    //subtracts 1 from Lower
    public void MinusLower(View v){
        lowerCount--;
        btnL.setText("Lower : " + String.valueOf(lowerCount));
    }
    //makes the missed shot button say how many times it has been pressed
    public void CountMissed(View v){
        missedCount++;
        btnM.setText("Missed Shot : " + String.valueOf(missedCount));
    }
    //subtracts 1 from missed
    public void MinusMissed(View v){
        missedCount--;
        btnM.setText("Missed Shot : " + String.valueOf(missedCount));
    }
    //makes the penalty button say how many times it has been pressed
    public void CountPenalty(View v){
        penaltyCount++;
        btnP.setText("Penalty : " + String.valueOf(penaltyCount));
    }
    //subtracts 1 from penalty
    public void MinusPenalty(View v){
        penaltyCount--;
        btnP.setText("Penalty : " + String.valueOf(penaltyCount));
    }
    //makes it so when you click the close button it goes to the final page
    public void moveToEnd(){
        Intent lastPage = new Intent(Main2Activity.this, Main3Activity.class);

        String strDrive, strAuto, strInner, strRotation, strPosition;
        if (swDrive.isChecked())
            strDrive = swDrive.getTextOn().toString();
        else
            strDrive = swDrive.getTextOff().toString();
        if (swAuto.isChecked())
            strAuto = swAuto.getTextOn().toString();
        else
            strAuto = swAuto.getTextOff().toString();
        if (swInner.isChecked())
            strInner = swInner.getTextOn().toString();
        else
            strInner = swInner.getTextOff().toString();
        if (swRotation.isChecked())
            strRotation = swRotation.getTextOn().toString();
        else
            strRotation = swRotation.getTextOff().toString();
        if (swPosition.isChecked())
            strPosition = swPosition.getTextOn().toString();
        else
            strPosition = swPosition.getTextOff().toString();

        lastPage.putExtra("nameView", st);
        lastPage.putExtra("nameMatch" , stMatch);
        lastPage.putExtra("nameRobot" , stRobot);
        lastPage.putExtra("didDrive", strDrive);
        lastPage.putExtra("didShoot", strAuto);
        lastPage.putExtra("didInner", strInner);
        lastPage.putExtra("didRotation", strRotation);
        lastPage.putExtra("didPosition", strPosition);
        startActivity(lastPage);
        finish();
    }

}