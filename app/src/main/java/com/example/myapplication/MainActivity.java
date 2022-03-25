package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pm.maincalendar.CalendarButton;
import com.pm.maincalendar.MainCalendar;


public class MainActivity extends AppCompatActivity  {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MainCalendar) findViewById(R.id.main_calendar)).setOnClickListener(new MainCalendar.OnMainClickListener() {
            @Override
            public void onClick(MainCalendar mainCalendar, CalendarButton button) {
                if(button != null)
                   System.out.println("Show button after click action " + button);
            }
        });


    }

}