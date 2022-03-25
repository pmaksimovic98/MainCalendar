package com.pm.maincalendar;

import android.content.Context;
import android.graphics.Color;
import android.media.metrics.Event;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.EventListener;
import java.util.HashMap;


public class CalendarButton<DataClass>
        extends AppCompatTextView {

    private BaseCalendarData<DataClass> data;
    private  HashMap<LocalDate, DataClass> changedData;
    private  HashMap<LocalDate, DataClass> oldChangedData;
    public CalendarButton superCalendarButton;

    public CalendarButton(Context context) {
        super(context);
        init();

    }


    public CalendarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){


    }

    public void setData(@NonNull ArrayList<HashMap<LocalDate, DataClass>> localDatesWithValues){
        System.out.println("Dates with values" + localDatesWithValues);
        data = new BaseCalendarData<>(localDatesWithValues);
        data.setBaseCalendarData(localDatesWithValues);
    }

    public void changeValueOfCalendarData(
            HashMap<LocalDate, DataClass> oldValue,
            HashMap<LocalDate, DataClass> newValue
    ) {
        data.changeValueOfCalendarData(oldValue, newValue);
        changedData = newValue;
    }

    public BaseCalendarData<DataClass> getCalendarData(){
        return this.data;
    }

    public void setOnChangedDataListener(OnChangeDataListener<DataClass> e){
        e.onChange(oldChangedData, changedData);
    }

    public void setOnInitListener(OnInitDataListener<DataClass> e){
        e.onInit(data.baseCalendarData);
    }

    public void setOnDayClickListener(OnDayClickListener e){
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                e.onClick(this, CalendarButton.this);
            }
        });
    }



    public interface OnChangeDataListener<DataClass> {
        void onChange(HashMap<LocalDate, DataClass> oldValue,
                      HashMap<LocalDate, DataClass> newValue);
    }

    public interface OnInitDataListener<DataClass> {
        void onInit(ArrayList<HashMap<LocalDate, DataClass>> localDatesWithValues);
    }

    public interface OnDayClickListener{
        void onClick(OnClickListener onClickListener, CalendarButton calendarButton);
    }



}

