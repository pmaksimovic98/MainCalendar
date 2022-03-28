package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pm.maincalendar.CalendarButton;
import com.pm.maincalendar.MainCalendar;

import java.time.LocalDate;
import java.util.HashMap;

class Cube {
    private final int height;
    private final int width;
    private final int depth;

    public Cube(int height, int width, int depth) {

        this.height = height;

        this.width = width;

        this.depth = depth;

    }

}

public class MainActivity extends AppCompatActivity {

    //example for use in application !
    private Cube root = new Cube(1,2,3);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainCalendar mainCalendar = new MainCalendar(getApplicationContext());
        mainCalendar.setDataClass(root);
        findViewById(R.id.main_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println((((MainCalendar) v).calendarButton).getCalendarData());

                ((MainCalendar) v).calendarButton.setOnChangedDataListener(new CalendarButton.OnChangeDataListener() {
                    @Override
                    public void onChange(HashMap oldValue, HashMap newValue) {
                        System.out.println("Old value:" + oldValue + "New value: " + newValue);
                    }
                });
                HashMap<LocalDate, Cube> hashMap = new HashMap<LocalDate, Cube>();
                hashMap.put(LocalDate.now(), root);


               // ((MainCalendar) v).calendarButton.changeValueOfCalendarData( (((MainCalendar) v).calendarButton.getCalendarData()), hashMap);

            }
        });


/*        ((MainCalendar) findViewById(R.id.main_calendar)).setOnClickListener(new MainCalendar.OnMainClickListener() {
            @Override
            public void onClick(MainCalendar mainCalendar, CalendarButton button) {
                if(button != null)
                   System.out.println("Show button after click action " + button);
            }
        });*/


    }

}