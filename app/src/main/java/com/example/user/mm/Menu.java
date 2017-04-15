package com.example.user.mm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.Mnemonica.R;

/**
 * Created by Bengu on 16.3.2017.
 */


public class Menu extends Activity {
    Button scheduleBtn;
    Button editAccount;
    Button alarm;
    Button dataTest;
    Button addAct;
    Button btnlocation, findAddressLocation, findDistTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnlocation=(Button) findViewById(R.id.location);
        btnlocation.setVisibility(View.VISIBLE);
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,AndroidGPSTrackingActivity.class);
                startActivity(intent);

            }
        });

        findDistTime = (Button) findViewById(R.id.findDistTime);
        findDistTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,MapsActivity.class);
                startActivity(intent);
            }
        });


        findAddressLocation=(Button) findViewById(R.id.findAdressLocation);
        findAddressLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,AddressLocation.class);
                startActivity(intent);
            }
        });


        Init();
        //openPickerDialog(false);
        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Schedule.class);
                startActivity(intent);
            }
        });

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,MainActivity.class);
                startActivity(intent);
            }
        });

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,Alarm.class);
                startActivity(intent);
            }
        });
        dataTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,ExpandData.class);
                startActivity(intent);
            }
        });
        addAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this,CreateAct.class);
                startActivity(intent);
            }
        });


    }

    private void Init() {

        scheduleBtn = (Button)findViewById(R.id.scheduleBtn);
        editAccount = (Button)findViewById(R.id.editAccount);
        alarm = (Button) findViewById(R.id.alarm);
        dataTest = (Button) findViewById(R.id.dataTest);
        addAct = (Button) findViewById(R.id.addAct);

    }

}
