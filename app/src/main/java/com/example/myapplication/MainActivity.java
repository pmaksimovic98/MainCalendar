package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.pm.maincalendar.BaseCalendarData;
import com.pm.maincalendar.CalendarButton;
import com.pm.maincalendar.MainCalendar;

import java.time.LocalDate;
import java.util.ArrayList;
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
        HashMap<LocalDate, Cube> hashMap = new HashMap();
        hashMap.put(LocalDate.now(), root);
        hashMap.put(LocalDate.of(2022, 3, 29), root);


        ArrayList<HashMap<LocalDate, Cube>> arrayList;
        arrayList = new ArrayList();
        arrayList.add(hashMap);
        //MainCalendar<Cube> mainCalendar = new MainCalendar<Cube>(getApplicationContext());
        MainCalendar<Cube> mainCalendar = findViewById(R.id.main_calendar);
        mainCalendar.setDataClass(root);
        mainCalendar.getAllButtons().forEach(k->{

            k.setOnChangedDataListener(new CalendarButton.OnChangeDataListener<Cube>() {
                @Override
                public void onChange(Cube oldValue, Cube newValue) {
                    System.out.println("Data changed!");
                }
            });
        });

        mainCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainCalendar) v).getButton(LocalDate.of(2022, 3, 1)).changeValueOfCalendarData(LocalDate.of(2022, 3, 1), root);
                //((MainCalendar )v).calendarButton.changeValueOfCalendarData(LocalDate.now(), root);
            }
        });


/*        findViewById(R.id.main_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println((((MainCalendar) v).calendarButton).getCalendarData());

                ((MainCalendar) v).calendarButton.setOnChangedDataListener(new CalendarButton.OnChangeDataListener() {
                    @Override
                    public void onChange(Object oldValue, Object newValue) {
                        System.out.println("Old value:" + oldValue + "New value: " + newValue);

                    }

 *//*                   @Override
                    public void onChange(HashMap oldValue, HashMap newValue) {
                        System.out.println("Old value:" + oldValue + "New value: " + newValue);
                    }*//*
                });
                HashMap<LocalDate, Cube> hashMap = new HashMap<LocalDate, Cube>();
                hashMap.put(LocalDate.now(), root);


               // ((MainCalendar) v).calendarButton.changeValueOfCalendarData( (((MainCalendar) v).calendarButton.getCalendarData()), hashMap);

            }
        });*/


/*        ((MainCalendar) findViewById(R.id.main_calendar)).setOnClickListener(new MainCalendar.OnMainClickListener() {
            @Override
            public void onClick(MainCalendar mainCalendar, CalendarButton button) {
                if(button != null)
                   System.out.println("Show button after click action " + button);
            }
        });*/


    }

}