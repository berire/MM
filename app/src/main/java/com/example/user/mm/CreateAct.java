package com.example.user.mm;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.Mnemonica.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bengu on 28.3.2017.
 */

public class CreateAct extends AppCompatActivity {
    private static int  num = 0;
    private Button add;
    private Button showAct;
    private EditText actName;
    private TimePicker tp;
    private DatePicker dp;
    private String actN;
    private String uid;
    private static int numberOfActivity=0;
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    private String dayStr;
    private String monthStr;
    private String yearStr;
    private String hourStr;
    private String minuteStr;
    private String numOfAct;
    private String activityID;
    private double lat,longt;
    private String placename;
    public static ArrayList<Activity> activities = new ArrayList<>();
    private Location activity_location;
    EditText addresstext;
    String address;
    private static final String TAG = "GEO_ADDY_SERVICE";
    public Context mContext;
    Address addrr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_layout);
        mContext=this;
        init();

        final FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
        uid = user2.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                actN = actName.getText().toString();
                address=addresstext.getText().toString();

                Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
                String errorMessage = "";
                List<Address> addresses = null;

                try {
                    addresses = geocoder.getFromLocationName(address, 1);
                } catch (IOException e) {
                    errorMessage = "Service not available";
                    Log.e(TAG, errorMessage, e);
                }

                if(addresses != null && addresses.size() > 0)
                    addrr=addresses.get(0);

                longt=addrr.getLongitude();
                lat=addrr.getLatitude();


                hour = tp.getCurrentHour();
                minute = tp.getCurrentMinute();

                dayStr = String.valueOf(dp.getDayOfMonth());
                monthStr = String.valueOf(dp.getMonth());
                yearStr = String.valueOf(dp.getYear());
                if (TextUtils.isEmpty(actN)) {
                    Toast.makeText(getApplicationContext(), "Enter Title of Activity!", Toast.LENGTH_SHORT).show();
                    return;
                }
                hourStr = String.valueOf(hour);
                minuteStr = String.valueOf(minute);


                //////CREATE ACTIVITY
                Activity act=new Activity(actN, dayStr,monthStr, yearStr, hourStr, minuteStr,lat,longt,placename,activity_location);
                activities.add(act);
                //Adding name of activity to database
                num++;
                activityID = String.valueOf(num);

                //myRef.child("Users").child(uid).child("activities").child(activityID).child("Activity Name").setValue(eventName);
                DatabaseReference newRef =  myRef.child("Users").child(uid).child("activities").child(activityID);
                newRef.child("Activity Name").setValue(actN);
                newRef.child("Activity Hour").setValue(hourStr);
                newRef.child("Avtivity Minute").setValue(minuteStr);
                newRef.child("Avtivity Day").setValue(dayStr);
                newRef.child("Avtivity Month").setValue(monthStr);
                newRef.child("Avtivity Year").setValue(yearStr);
                numberOfActivity++;

                //numOfAct = String.valueOf(numberOfActivity);
                myRef.child("Users").child(uid).child("Number of Activities").setValue(numberOfActivity);
                //Adding location information of activity to database
            }
        });

        showAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAct.this,ActList.class);
                startActivity(intent);
            }
        });

    }

    private void init(){
        add = (Button) findViewById(R.id.add);
        showAct = (Button) findViewById(R.id.showAct);
        actName = (EditText) findViewById(R.id.actName);
        dp = (DatePicker)findViewById(R.id.dp);
        tp = (TimePicker)findViewById(R.id.timePicker2);
    }
}