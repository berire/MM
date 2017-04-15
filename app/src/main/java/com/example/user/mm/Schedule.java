package com.example.user.mm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.Mnemonica.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedule extends AppCompatActivity {

    String birthday = "";
    String rDate = "";
    private TextView eventTitle;
    private Button startAlarmBtn;
    private TimePickerDialog timePickerDialog;
    final static int REQUEST_CODE = 1;
    AppCompatButton gname;
    public String ss = "deneme";
    ArrayList<Integer> a;
    ArrayList<String> actNames;
    int i=0;
    int b=23;
    int d=1;
    int h[]= new int[3];
    Calendar calNow = Calendar.getInstance();
    Calendar calSet = (Calendar) calNow.clone();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        final Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        final int month = c.get(c.MONTH) + 1;
        int dayOfMonth = c.get(c.DAY_OF_MONTH);

        //Get the widgets reference from XML layout
        final TextView tv = (TextView) findViewById(R.id.tv);
        final Button dateButton = (Button) findViewById(R.id.dateButton);
        final DatePicker dp = (DatePicker) findViewById(R.id.dp);
        gname = (AppCompatButton) findViewById(R.id.bRegister);
        eventTitle = (TextView) findViewById(R.id.eventTitle);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rDate = df.format(c.getTime());
        //Display the DatePicker initial date
        tv.setText("Initial Date [mm/dd/yyyy]:\n" + month + "/" + dayOfMonth + "/" + year);

        actNames = new ArrayList<String>();
        gname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               //actNames.add((String) gname.getText());
               // ss= eventTitle.getText().toString();
            }
        });

        a = new ArrayList<Integer>();
        a.add(2);
        a.add(20);
        a.add(2);
        a.add(22);

        al();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the DatePicker Selected Date
                tv.setText("Selected Date of Birth: [mm/dd/yyyy]\n");
                tv.setText(tv.getText() + "" + (dp.getMonth() + 1) + "/" + dp.getDayOfMonth() + "/" + dp.getYear());
                birthday = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth();
                calSet.set(dp.getYear(), dp.getMonth(),dp.getDayOfMonth());
                dp.updateDate(c.get(c.YEAR) + 1, c.get(c.MONTH) + 2, c.get(c.DAY_OF_MONTH) + 10);

                //alrm(b,d+1);

              // openPickerDialog(false);
               // al(false);
            }
        });


    }
/*
    private void openPickerDialog(boolean is24hour) {

        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                Schedule.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24hour);
        timePickerDialog.setTitle("Alarm Ayarla");

        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){

                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar alarmCalender){


        Toast.makeText(getApplicationContext(),"Alarm Ayarlandı!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        //intent.putExtra("send_String",ss);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }
    */

   private void al(){


        //Calendar calendar[] = new Calendar[3];
       h[0]=54;
       h[1]=55;
       h[2]=56;
        Calendar cal[] = new Calendar[3];


        for (int i = 0; i < 3; i++) {
            cal[i] = Calendar.getInstance();
            cal[i].set(Calendar.HOUR_OF_DAY, b);
            cal[i].set(Calendar.MINUTE, h[i]);
            cal[i].set(Calendar.SECOND, 0);
        }

        AlarmManager[] alarmManager = new AlarmManager[3];
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        for (int f = 0; f < cal.length; f++) {
            Intent intent = new Intent(Schedule.this,
                    AlarmReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(
                    Schedule.this, f, intent, 0);

            alarmManager[f] = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager[f].set(AlarmManager.RTC_WAKEUP,
                    cal[f].getTimeInMillis(), pi);

            intentArray.add(pi);

        }
    }

}



