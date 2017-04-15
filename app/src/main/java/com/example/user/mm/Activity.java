package com.example.user.mm;

import android.location.Location;

/**
 * Created by user on 15.4.2017.
 */
public class Activity {
    private String title;
    private String dayStr;
    private String monthStr;
    private String yearStr;
    private String hourStr;
    private String minuteStr;
    private String numOfAct;
    private String activityID;
    private double lat;
    private double longt;
    private String placename;
    private Location actloc;

    public Activity(String title, String day, String month, String year, String hour, String minute, double lat, double longt, String placename, Location actloc )
    {
        this.title=title;
        this.dayStr=day;
        this.monthStr=month;
        this.yearStr=year;
        this.hourStr=hour;
        this.minuteStr=minute;
        this.lat=lat;
        this.longt=longt;
        this.placename=placename;
        this.actloc=actloc;
    }

    public Location getLocation()
    {
        return actloc;
    }

    public double getLat() {return  lat;}
    public double getLongt() {return  longt;}
    public String getMinute() {return  minuteStr;}
    public String getHour() {return  hourStr;}
    public String getDay() {return  dayStr;}
    public String getMonth() {return  monthStr;}
    public String getYear() {return yearStr;}




}
